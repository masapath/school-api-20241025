package com.school.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.entity.FindStudentsByTeacherParams;
import com.school.api.entity.Student;
import com.school.api.entity.Students;
import com.school.api.service.SchoolService;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(value = "/students", produces = "application/json; charset=utf-8")
    public ResponseEntity<Students> students(
        @RequestParam("facilitator_id") Integer facilitatorId,
        @RequestParam(value = "page", defaultValue = "1") Integer page, 
        @RequestParam(value = "limit", defaultValue = "5") Integer limit,
        @RequestParam(value = "sort", defaultValue = "id") String sort,
        @RequestParam(value = "order", defaultValue = "asc") String order,
        @RequestParam(value = "name_like", required = false) String nameLike,
        @RequestParam(value = "loginId_like", required = false) String loginIdLike) {
        
        // TODO:バリデーション用のハンドラーに移植する
        if(Objects.isNull(facilitatorId)) {
            return ResponseEntity.badRequest().build();
        }

        // log_print(facilitatorId, page, limit, sort, order, nameLike, loginIdLike);
        
        // パラメータの情報から生徒情報を取得
        FindStudentsByTeacherParams params = new FindStudentsByTeacherParams(facilitatorId, page, limit, sort, order, nameLike, loginIdLike);
        List<Student> studentList = schoolService.findStudentsByTeacher(params);

        // 該当のデータが存在しない場合、404エラーを返す
        if (Objects.isNull(studentList) || studentList.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        Students students = new Students(studentList, studentList.size());
        return ResponseEntity.ok(students);
    }

    private void log_print(Integer facilitatorId, Integer page
                            , Integer limit, String sort, String order
                            ,String nameLike, String loginIdLike){
        // パラメータの値をコンソールに出力
        System.out.println("Facilitator ID: " + facilitatorId);
        System.out.println("Page: " + page);
        System.out.println("Limit: " + limit);
        System.out.println("Sort: " + sort);
        System.out.println("Order: " + order);
        System.out.println("Name like: " + nameLike);
        System.out.println("Login ID like: " + loginIdLike);
    }
}
