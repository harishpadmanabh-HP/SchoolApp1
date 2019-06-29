package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

public class Exam {
    /**
     * status : true
     * msg : Success
     * Exams : [{"ExamId":40077,"SchoolId":10093,"UserId":0,"ClassId":20493,"DivisionId":71079,"ExamName":"1st Term Examination","ExamDate":"2019-04-12T00:00:00","IsActive":false,"TimeStamp":"2019-04-12T11:40:18.247","ExamsSubjectsLists":[{"SubId":30121,"ExamId":40077,"Subject":"english","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:41:32.833","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-12T09:00:01","SubjectId":0},{"SubId":30122,"ExamId":40077,"Subject":"Maths","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:42:23.343","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-15T09:00:00","SubjectId":0},{"SubId":30123,"ExamId":40077,"Subject":"Physics","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:45:33.463","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-17T09:00:00","SubjectId":0},{"SubId":30124,"ExamId":40077,"Subject":"hindi","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:46:19.153","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-18T18:00:00","SubjectId":0},{"SubId":30125,"ExamId":40077,"Subject":"Biology","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:54:39.353","InternalMarks":70,"ExternalMark":30,"ExamDate":"2019-04-20T09:00:00","SubjectId":0},{"SubId":30126,"ExamId":40077,"Subject":"Chemistry","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:57:26.99","InternalMarks":70,"ExternalMark":30,"ExamDate":"2019-04-22T09:00:00","SubjectId":0}]}]
     */

    private boolean status;
    private String msg;
    private List<ExamsBean> Exams;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ExamsBean> getExams() {
        return Exams;
    }

    public void setExams(List<ExamsBean> Exams) {
        this.Exams = Exams;
    }

    public static class ExamsBean {
        /**
         * ExamId : 40077
         * SchoolId : 10093
         * UserId : 0
         * ClassId : 20493
         * DivisionId : 71079
         * ExamName : 1st Term Examination
         * ExamDate : 2019-04-12T00:00:00
         * IsActive : false
         * TimeStamp : 2019-04-12T11:40:18.247
         * ExamsSubjectsLists : [{"SubId":30121,"ExamId":40077,"Subject":"english","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:41:32.833","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-12T09:00:01","SubjectId":0},{"SubId":30122,"ExamId":40077,"Subject":"Maths","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:42:23.343","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-15T09:00:00","SubjectId":0},{"SubId":30123,"ExamId":40077,"Subject":"Physics","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:45:33.463","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-17T09:00:00","SubjectId":0},{"SubId":30124,"ExamId":40077,"Subject":"hindi","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:46:19.153","InternalMarks":50,"ExternalMark":50,"ExamDate":"2019-04-18T18:00:00","SubjectId":0},{"SubId":30125,"ExamId":40077,"Subject":"Biology","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:54:39.353","InternalMarks":70,"ExternalMark":30,"ExamDate":"2019-04-20T09:00:00","SubjectId":0},{"SubId":30126,"ExamId":40077,"Subject":"Chemistry","Mark":100,"IsActive":false,"TimeStamp":"2019-04-12T11:57:26.99","InternalMarks":70,"ExternalMark":30,"ExamDate":"2019-04-22T09:00:00","SubjectId":0}]
         */

        private int ExamId;
        private int SchoolId;
        private int UserId;
        private int ClassId;
        private int DivisionId;
        private String ExamName;
        private String ExamDate;
        private boolean IsActive;
        private String TimeStamp;
        private List<ExamsSubjectsListsBean> ExamsSubjectsLists;

        public int getExamId() {
            return ExamId;
        }

        public void setExamId(int ExamId) {
            this.ExamId = ExamId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public int getDivisionId() {
            return DivisionId;
        }

        public void setDivisionId(int DivisionId) {
            this.DivisionId = DivisionId;
        }

        public String getExamName() {
            return ExamName;
        }

        public void setExamName(String ExamName) {
            this.ExamName = ExamName;
        }

        public String getExamDate() {
            return ExamDate;
        }

        public void setExamDate(String ExamDate) {
            this.ExamDate = ExamDate;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public List<ExamsSubjectsListsBean> getExamsSubjectsLists() {
            return ExamsSubjectsLists;
        }

        public void setExamsSubjectsLists(List<ExamsSubjectsListsBean> ExamsSubjectsLists) {
            this.ExamsSubjectsLists = ExamsSubjectsLists;
        }

        public static class ExamsSubjectsListsBean {
            /**
             * SubId : 30121
             * ExamId : 40077
             * Subject : english
             * Mark : 100
             * IsActive : false
             * TimeStamp : 2019-04-12T11:41:32.833
             * InternalMarks : 50
             * ExternalMark : 50
             * ExamDate : 2019-04-12T09:00:01
             * SubjectId : 0
             */

            private int SubId;
            private int ExamId;
            private String Subject;
            private int Mark;
            private boolean IsActive;
            private String TimeStamp;
            private int InternalMarks;
            private int ExternalMark;
            private String ExamDate;
            private int SubjectId;

            public int getSubId() {
                return SubId;
            }

            public void setSubId(int SubId) {
                this.SubId = SubId;
            }

            public int getExamId() {
                return ExamId;
            }

            public void setExamId(int ExamId) {
                this.ExamId = ExamId;
            }

            public String getSubject() {
                return Subject;
            }

            public void setSubject(String Subject) {
                this.Subject = Subject;
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

            public String getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(String TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public int getInternalMarks() {
                return InternalMarks;
            }

            public void setInternalMarks(int InternalMarks) {
                this.InternalMarks = InternalMarks;
            }

            public int getExternalMark() {
                return ExternalMark;
            }

            public void setExternalMark(int ExternalMark) {
                this.ExternalMark = ExternalMark;
            }

            public String getExamDate() {
                return ExamDate;
            }

            public void setExamDate(String ExamDate) {
                this.ExamDate = ExamDate;
            }

            public int getSubjectId() {
                return SubjectId;
            }

            public void setSubjectId(int SubjectId) {
                this.SubjectId = SubjectId;
            }
        }
    }
}
