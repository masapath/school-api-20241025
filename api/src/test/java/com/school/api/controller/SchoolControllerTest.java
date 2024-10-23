package com.school.api.controller;

import com.school.api.entity.FindStudentsByTeacherParams;
import com.school.api.entity.Student;
import com.school.api.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private SchoolController schoolController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(schoolController).build();
    }

    // 指定した教師IDのデータを取得
    @Test
    void testStudents_Success() throws Exception {
        Integer facilitatorId = 1;
        List<Student> studentList = Arrays.asList(new Student(), new Student());
        when(schoolService.findStudentsByTeacher(any(FindStudentsByTeacherParams.class))).thenReturn(studentList);

        mockMvc.perform(get("/students")
                .param("facilitator_id", String.valueOf(facilitatorId))
                .param("page", "1")
                .param("limit", "5")
                .param("sort", "id")
                .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.students").isArray())
                .andExpect(jsonPath("$.students.length()").value(2));

        ArgumentCaptor<FindStudentsByTeacherParams> captor = ArgumentCaptor.forClass(FindStudentsByTeacherParams.class);
        verify(schoolService).findStudentsByTeacher(captor.capture());
        FindStudentsByTeacherParams params = captor.getValue();
        assertEquals(facilitatorId, params.getFacilitatorId());
    }

    // リクエストパラメータに教師ID（必須）が無い場合400エラーとする
    @Test
    void testStudents_NoFacilitatorId() throws Exception {
        mockMvc.perform(get("/students")
                .param("page", "1")
                .param("limit", "5"))
                .andExpect(status().isBadRequest());
    }

    // 該当のデータが存在しない場合404エラーとする
    @Test
    void testStudents_NotFound() throws Exception {
        Integer facilitatorId = 1;
        when(schoolService.findStudentsByTeacher(any(FindStudentsByTeacherParams.class))).thenReturn(Arrays.asList());

        mockMvc.perform(get("/students")
                .param("facilitator_id", String.valueOf(facilitatorId))
                .param("page", "1")
                .param("limit", "5"))
                .andExpect(status().isNotFound());
    }
}
