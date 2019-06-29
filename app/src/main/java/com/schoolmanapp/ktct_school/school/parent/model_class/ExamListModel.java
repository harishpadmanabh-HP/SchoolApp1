package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

public class ExamListModel {

    /**
     * msg : Success
     * status : true
     * results : [{"MarkId":0,"StudentId":0,"ExamId":6,"SubjectId":0,"Mark":0,"IsActive":false,"ContactNumber":null,"StundentName":null,"ParentName":null,"TimeStamp":"0001-01-01T00:00:00","InternalMark":null,"ExternalMark":null,"ExamName":"Final Exam","SubjectName":null},{"MarkId":0,"StudentId":0,"ExamId":20025,"SubjectId":0,"Mark":0,"IsActive":false,"ContactNumber":null,"StundentName":null,"ParentName":null,"TimeStamp":"0001-01-01T00:00:00","InternalMark":null,"ExternalMark":null,"ExamName":"Holy cross test","SubjectName":null},{"MarkId":0,"StudentId":0,"ExamId":30058,"SubjectId":0,"Mark":0,"IsActive":false,"ContactNumber":null,"StundentName":null,"ParentName":null,"TimeStamp":"0001-01-01T00:00:00","InternalMark":null,"ExternalMark":null,"ExamName":"half yearly","SubjectName":null},{"MarkId":0,"StudentId":0,"ExamId":30059,"SubjectId":0,"Mark":0,"IsActive":false,"ContactNumber":null,"StundentName":null,"ParentName":null,"TimeStamp":"0001-01-01T00:00:00","InternalMark":null,"ExternalMark":null,"ExamName":"abc","SubjectName":null},{"MarkId":0,"StudentId":0,"ExamId":30060,"SubjectId":0,"Mark":0,"IsActive":false,"ContactNumber":null,"StundentName":null,"ParentName":null,"TimeStamp":"0001-01-01T00:00:00","InternalMark":null,"ExternalMark":null,"ExamName":"final","SubjectName":null}]
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
         * StudentId : 0
         * ExamId : 6
         * SubjectId : 0
         * Mark : 0
         * IsActive : false
         * ContactNumber : null
         * StundentName : null
         * ParentName : null
         * TimeStamp : 0001-01-01T00:00:00
         * InternalMark : null
         * ExternalMark : null
         * ExamName : Final Exam
         * SubjectName : null
         */

        private int MarkId;
        private int StudentId;
        private int ExamId;
        private int SubjectId;
        private int Mark;
        private boolean IsActive;
        private Object ContactNumber;
        private Object StundentName;
        private Object ParentName;
        private String TimeStamp;
        private Object InternalMark;
        private Object ExternalMark;
        private String ExamName;
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

        public Object getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(Object ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public Object getStundentName() {
            return StundentName;
        }

        public void setStundentName(Object StundentName) {
            this.StundentName = StundentName;
        }

        public Object getParentName() {
            return ParentName;
        }

        public void setParentName(Object ParentName) {
            this.ParentName = ParentName;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public Object getInternalMark() {
            return InternalMark;
        }

        public void setInternalMark(Object InternalMark) {
            this.InternalMark = InternalMark;
        }

        public Object getExternalMark() {
            return ExternalMark;
        }

        public void setExternalMark(Object ExternalMark) {
            this.ExternalMark = ExternalMark;
        }

        public String getExamName() {
            return ExamName;
        }

        public void setExamName(String ExamName) {
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
