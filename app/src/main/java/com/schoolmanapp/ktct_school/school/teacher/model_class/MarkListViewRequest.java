package com.schoolmanapp.ktct_school.school.teacher.model_class;

public class MarkListViewRequest {
    /**
     * SchoolId : 10093
     * ClassId : 30503
     * DivisionId : 30740
     * ExamId : 40087
     * SubjectId : 30134
     */

    private String SchoolId;
    private String ClassId;
    private String DivisionId;
    private String ExamId;
    private String SubjectId;

    public String getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(String SchoolId) {
        this.SchoolId = SchoolId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String ClassId) {
        this.ClassId = ClassId;
    }

    public String getDivisionId() {
        return DivisionId;
    }

    public void setDivisionId(String DivisionId) {
        this.DivisionId = DivisionId;
    }

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
}
