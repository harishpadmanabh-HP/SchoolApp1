package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

public class mod_sms {

    /**
     * TypeId : 1
     * Message : Message testing from App
     * SchoolId : 20140
     * MultipleClass : [{"ClassId":"10439","DivisionId":"10619"}]
     */

    private String TypeId;
    private String Message;
    private String SchoolId;
    /**
     * ClassId : 10439
     * DivisionId : 10619
     */

    private List<MultipleClassBean> MultipleClass;

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String TypeId) {
        this.TypeId = TypeId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(String SchoolId) {
        this.SchoolId = SchoolId;
    }

    public List<MultipleClassBean> getMultipleClass() {
        return MultipleClass;
    }

    public void setMultipleClass(List<MultipleClassBean> MultipleClass) {
        this.MultipleClass = MultipleClass;
    }

    public static class MultipleClassBean {
        private String ClassId;
        private String DivisionId;

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
    }
}
