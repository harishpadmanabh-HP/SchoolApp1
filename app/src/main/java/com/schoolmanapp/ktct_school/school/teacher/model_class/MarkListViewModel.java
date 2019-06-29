package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

public class MarkListViewModel {

    /**
     * msg : Success
     * status : true
     * results : [{"MarkId":0,"StudentId":147584,"ExamId":0,"SubjectId":0,"Mark":165,"IsActive":false,"ContactNumber":"7412563891","StundentName":"Jap","ParentName":"Japjap","TimeStamp":"2019-06-01T16:59:20.21","InternalMark":77,"ExternalMark":88,"ExamName":null,"SubjectName":null},{"MarkId":0,"StudentId":160904,"ExamId":0,"SubjectId":0,"Mark":198,"IsActive":false,"ContactNumber":"1234567890","StundentName":"qwerty","ParentName":"qwerty","TimeStamp":"2019-06-01T16:59:20.21","InternalMark":99,"ExternalMark":99,"ExamName":null,"SubjectName":null},{"MarkId":0,"StudentId":160905,"ExamId":0,"SubjectId":0,"Mark":132,"IsActive":false,"ContactNumber":"1234567890","StundentName":"Harish","ParentName":"Harish","TimeStamp":"2019-06-01T16:59:20.21","InternalMark":66,"ExternalMark":66,"ExamName":null,"SubjectName":null}]
     */

    private String msg;
    private boolean status;
    private List<ResultsBean> results;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * MarkId : 0
         * StudentId : 147584
         * ExamId : 0
         * SubjectId : 0
         * Mark : 165
         * IsActive : false
         * ContactNumber : 7412563891
         * StundentName : Jap
         * ParentName : Japjap
         * TimeStamp : 2019-06-01T16:59:20.21
         * InternalMark : 77
         * ExternalMark : 88
         * ExamName : null
         * SubjectName : null
         */

        private int MarkId;
        private int StudentId;
        private int ExamId;
        private int SubjectId;
        private int Mark;
        private boolean IsActive;
        private String ContactNumber;
        private String StundentName;
        private String ParentName;
        private String TimeStamp;
        private int InternalMark;
        private int ExternalMark;
        private Object ExamName;
        private Object SubjectName;

        public int getMarkId() {
            return MarkId;
        }

        public void setMarkId(int MarkId) {
            this.MarkId = MarkId;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public int getExamId() {
            return ExamId;
        }

        public void setExamId(int ExamId) {
            this.ExamId = ExamId;
        }

        public int getSubjectId() {
            return SubjectId;
        }

        public void setSubjectId(int SubjectId) {
            this.SubjectId = SubjectId;
        }

        public int getMark() {
            return Mark;
        }

        public void setMark(int Mark) {
            this.Mark = Mark;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public String getStundentName() {
            return StundentName;
        }

        public void setStundentName(String StundentName) {
            this.StundentName = StundentName;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public int getInternalMark() {
            return InternalMark;
        }

        public void setInternalMark(int InternalMark) {
            this.InternalMark = InternalMark;
        }

        public int getExternalMark() {
            return ExternalMark;
        }

        public void setExternalMark(int ExternalMark) {
            this.ExternalMark = ExternalMark;
        }

        public Object getExamName() {
            return ExamName;
        }

        public void setExamName(Object ExamName) {
            this.ExamName = ExamName;
        }

        public Object getSubjectName() {
            return SubjectName;
        }

        public void setSubjectName(Object SubjectName) {
            this.SubjectName = SubjectName;
        }
    }
}
