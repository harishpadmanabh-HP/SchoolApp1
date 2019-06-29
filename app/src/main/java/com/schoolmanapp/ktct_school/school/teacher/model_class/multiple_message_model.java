package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 27/06/17.
 */

public class multiple_message_model {

    /**
     * teacherId : 68
     * subject : AAA
     * description : Aaaaaa
     * studentList : [{"studentId":"32"},{"studentId":"31"},{"studentId":"30"},{"studentId":"29"}]
     */

    private String teacherId;
    private String subject;
    private String description;
    /**
     * studentId : 32
     */

    private List<StudentListBean> studentList;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StudentListBean> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentListBean> studentList) {
        this.studentList = studentList;
    }

    public static class StudentListBean {
        private String studentId;

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }
    }
}
