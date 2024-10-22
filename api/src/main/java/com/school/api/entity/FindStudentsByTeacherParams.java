package com.school.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class FindStudentsByTeacherParams {

    @NotNull // 教師IDは必須
    private final int facilitatorId; 
    private Integer page; // ページネーションのページ数 (オプショナル)
    private Integer limit; // ページネーションの1ページあたりの表示数 (オプショナル)
    private String sort; // ソートのキー (オプショナル)
    private String order; // ソートの昇順または降順 (オプショナル)
    private String nameLike; // 生徒名による部分一致検索 (オプショナル)
    private String loginIdLike; // ログインIDによる部分一致検索 (オプショナル)
    private int offset; // SQLのパラメータとして使用するOFFSET

    private static final Map<String, String> sortMapping = Map.of(
        "id", "student_id",
        "name", "name",
        "loginId", "login_id"
    );

    private int calcOffset() {
        return (this.page - 1) * this.limit;
    }

    public FindStudentsByTeacherParams(@NotNull int facilitatorId, Integer page, Integer limit, String sort,
            String order, String nameLike, String loginIdLike) {
        this.facilitatorId = facilitatorId;
        this.page = page;
        this.limit = limit;
        this.sort = sortMapping.getOrDefault(sort, sort);
        this.order = order;
        this.nameLike = nameLike;
        this.loginIdLike = loginIdLike;
        this.offset = calcOffset();
    }
    
}
