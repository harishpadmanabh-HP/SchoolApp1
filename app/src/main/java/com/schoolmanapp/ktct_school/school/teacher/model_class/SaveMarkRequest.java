package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

public class SaveMarkRequest {

    /**
     * ExamId : 40080
     * SubjectId : 30131
     * StudentList : [{"InternalMark":"40","StudentId":"11746","ExternalMark":"10"},{"InternalMark":"50","StudentId":"11747","ExternalMark":"25"},{"InternalMark":"60","StudentId":"11745","ExternalMark":"10"}]
     */

    private String ExamId;
    private String SubjectId;
    private List<StudentListBean> StudentList;

    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String ExamId) {
        this.ExamId = ExamId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public List<StudentListBean> getStudentList() {
        return StudentList;
    }

    public void setStudentList(List<StudentListBean> StudentList) {
        this.StudentList = StudentList;
    }

    public static class StudentListBean {
        /**
         * InternalMark : 40
         * StudentId : 11746
         * ExternalMark : 10
         */

        private String InternalMark;
        private String StudentId;
        private String ExternalMark;

        public String getInternalMark() {
            return InternalMark;
        }

        public void setInternalMark(String InternalMark) {
            this.InternalMark = InternalMark;
        }

        public String getStudentId() {
            return StudentId;
        }

        public void setStudentId(String StudentId) {
            this.StudentId = StudentId;
        }

        public String getExternalMark() {
            return ExternalMark;
        }

        public void setExternalMark(String ExternalMark) {
            this.ExternalMark = ExternalMark;
        }
    }
}
