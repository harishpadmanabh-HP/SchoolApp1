package com.schoolmanapp.ktct_school.school.school.Model;

import java.util.List;

/**
 * Created by srishtiinnovative on 14/06/17.
 */

public class Mod_Del_division {

    /**
     * status : true
     * msg : Success
     * UserData : [{"ClassId":1,"SchoolId":4,"ClassName":"1","Division":[{"DivisionId":1,"ClassId":1,"DivisionName":"A","TimeStamp":"2017-05-31T06:35:29.743","DivisionGuid":"d9c31e9d-901e-493f-acc5-13948c7db4cb","IsActive":true,"ClassName":"1"}]},{"ClassId":3,"SchoolId":4,"ClassName":"2","Division":[]},{"ClassId":6,"SchoolId":4,"ClassName":"3","Division":[{"DivisionId":8,"ClassId":6,"DivisionName":"A","TimeStamp":"2017-06-13T08:00:39.26","DivisionGuid":"f980fb11-ad03-460f-8d7c-1d70007bdc2d","IsActive":true,"ClassName":"3"},{"DivisionId":9,"ClassId":6,"DivisionName":"B","TimeStamp":"2017-06-13T08:05:23.29","DivisionGuid":"906d8c0c-1782-4f33-ad39-52dc7db4c78f","IsActive":true,"ClassName":"3"},{"DivisionId":11,"ClassId":6,"DivisionName":"d","TimeStamp":"2017-06-13T08:31:39.157","DivisionGuid":"e4d84243-6d6f-430b-99b1-06a2e0833da3","IsActive":true,"ClassName":"3"},{"DivisionId":13,"ClassId":6,"DivisionName":"e","TimeStamp":"2017-06-13T09:32:20.133","DivisionGuid":"c4efc3e8-f174-41dd-9b57-5f5263ba9b97","IsActive":true,"ClassName":"3"}]},{"ClassId":8,"SchoolId":4,"ClassName":"4","Division":[{"DivisionId":15,"ClassId":8,"DivisionName":"b","TimeStamp":"2017-06-13T09:35:42.123","DivisionGuid":"726cabc2-9928-493d-8841-33c8d30b3917","IsActive":true,"ClassName":"4"},{"DivisionId":16,"ClassId":8,"DivisionName":"c","TimeStamp":"2017-06-13T09:36:15.273","DivisionGuid":"ab5470c9-2ce5-45ec-ab09-d6fde431acdd","IsActive":true,"ClassName":"4"}]},{"ClassId":9,"SchoolId":4,"ClassName":"5","Division":[{"DivisionId":17,"ClassId":9,"DivisionName":"e","TimeStamp":"2017-06-13T10:43:19.73","DivisionGuid":"d34d0b1f-e96d-4e3c-b7ea-60a1e1c70fc0","IsActive":true,"ClassName":"5"}]},{"ClassId":10,"SchoolId":4,"ClassName":"8","Division":[{"DivisionId":18,"ClassId":10,"DivisionName":"d","TimeStamp":"2017-06-13T12:06:41.09","DivisionGuid":"0d91fae5-4e44-4586-b096-7881c9da6c3d","IsActive":true,"ClassName":"8"}]},{"ClassId":11,"SchoolId":4,"ClassName":"9","Division":[{"DivisionId":19,"ClassId":11,"DivisionName":"c","TimeStamp":"2017-06-13T12:07:12.523","DivisionGuid":"4fb8163a-208f-4f0a-97b6-f53cd211fe18","IsActive":true,"ClassName":"9"},{"DivisionId":25,"ClassId":11,"DivisionName":"p","TimeStamp":"2017-06-14T05:41:27.013","DivisionGuid":"589f189e-aac1-430d-a2fd-066b8e6861df","IsActive":true,"ClassName":"9"}]}]
     */

    private boolean status;
    private String msg;
    private List<UserDataBean> UserData;

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

    public List<UserDataBean> getUserData() {
        return UserData;
    }

    public void setUserData(List<UserDataBean> UserData) {
        this.UserData = UserData;
    }

    public static class UserDataBean {
        /**
         * ClassId : 1
         * SchoolId : 4
         * ClassName : 1
         * Division : [{"DivisionId":1,"ClassId":1,"DivisionName":"A","TimeStamp":"2017-05-31T06:35:29.743","DivisionGuid":"d9c31e9d-901e-493f-acc5-13948c7db4cb","IsActive":true,"ClassName":"1"}]
         */

        private int ClassId;
        private int SchoolId;
        private String ClassName;
        private List<DivisionBean> Division;

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public List<DivisionBean> getDivision() {
            return Division;
        }

        public void setDivision(List<DivisionBean> Division) {
            this.Division = Division;
        }

        public static class DivisionBean {
            /**
             * DivisionId : 1
             * ClassId : 1
             * DivisionName : A
             * TimeStamp : 2017-05-31T06:35:29.743
             * DivisionGuid : d9c31e9d-901e-493f-acc5-13948c7db4cb
             * IsActive : true
             * ClassName : 1
             */

            private int DivisionId;
            private int ClassId;
            private String DivisionName;
            private String TimeStamp;
            private String DivisionGuid;
            private boolean IsActive;
            private String ClassName;

            public int getDivisionId() {
                return DivisionId;
            }

            public void setDivisionId(int DivisionId) {
                this.DivisionId = DivisionId;
            }

            public int getClassId() {
                return ClassId;
            }

            public void setClassId(int ClassId) {
                this.ClassId = ClassId;
            }

            public String getDivisionName() {
                return DivisionName;
            }

            public void setDivisionName(String DivisionName) {
                this.DivisionName = DivisionName;
            }

            public String getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(String TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getDivisionGuid() {
                return DivisionGuid;
            }

            public void setDivisionGuid(String DivisionGuid) {
                this.DivisionGuid = DivisionGuid;
            }

            public boolean isIsActive() {
                return IsActive;
            }

            public void setIsActive(boolean IsActive) {
                this.IsActive = IsActive;
            }

            public String getClassName() {
                return ClassName;
            }

            public void setClassName(String ClassName) {
                this.ClassName = ClassName;
            }
        }
    }
}
