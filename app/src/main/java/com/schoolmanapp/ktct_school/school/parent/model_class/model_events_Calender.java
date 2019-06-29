package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 20/02/18.
 */

public class model_events_Calender {

    /**
     * status : true
     * msg : Success
     * UserData : [{"Head":"Circular ","CircularId":14,"SchoolId":10093,"CircularDate":"2018-03-03T00:00:00","Description":"554554","FilePath":"/Media/10093/CircularData/044c09c3-f413-46a2-8611-ce9fa84372ee^0B36F060C8DAF3EF86B9895600407C39B3752A7073AEF717B9^pimgpsh_fullsize_distr.jpg","TimeStamp":"2018-02-08T14:47:14.893","FromStatus":0},{"Head":"Circular ","CircularId":16,"SchoolId":10093,"CircularDate":"2018-03-23T00:00:00","Description":"qwqwqwq","FilePath":"/Media/10093/CircularData/bbe2362f-44d4-4d77-a13e-9b9abe2c91b8^0B36F060C8DAF3EF86B9895600407C39B3752A7073AEF717B9^pimgpsh_fullsize_distr.jpg","TimeStamp":"2018-02-09T11:11:44.047","FromStatus":0},{"Head":"Circular ","CircularId":17,"SchoolId":10093,"CircularDate":"2018-08-31T00:00:00","Description":"Test Circular Notifications  1212121","FilePath":"/Media/10093/CircularData/dea084e3-4e6c-487a-bdcd-80089a85e41a^0B36F060C8DAF3EF86B9895600407C39B3752A7073AEF717B9^pimgpsh_fullsize_distr.jpg","TimeStamp":"2018-02-09T14:10:15.843","FromStatus":0},{"Head":"Circular ","CircularId":21,"SchoolId":10093,"CircularDate":"2018-03-02T00:00:00","Description":"Hi. how u","FilePath":"/Media/10093/CircularData/ab616727-3c5d-47b8-afc2-2aae2388d6098f9356c09407cacc224bb179aa9c3c29.docx","TimeStamp":"2018-02-12T15:23:08.387","FromStatus":0},{"Head":"Circular ","CircularId":22,"SchoolId":10093,"CircularDate":"2018-03-24T00:00:00","Description":"Apk upload.","FilePath":"","TimeStamp":"2018-02-12T15:34:16.613","FromStatus":0},{"Head":"Circular ","CircularId":25,"SchoolId":10093,"CircularDate":"2018-02-23T00:00:00","Description":"Shinu Paul","FilePath":"/Media/10093/CircularData/291728d8-c1d8-4a1d-bac6-0e244989fb80IMG-20170219-WA0041.jpg","TimeStamp":"2018-02-20T13:31:15.097","FromStatus":0},{"Head":"Annual Day   ","CircularId":2,"SchoolId":10093,"CircularDate":"2018-02-17T00:00:00","Description":"Hi dears  ","FilePath":"","TimeStamp":"2018-02-16T14:57:21.733","FromStatus":1},{"Head":"Mission Day ","CircularId":3,"SchoolId":10093,"CircularDate":"2018-02-17T00:00:00","Description":"Hello ","FilePath":"","TimeStamp":"2018-02-16T14:58:04.94","FromStatus":1},{"Head":"Shinu Day","CircularId":4,"SchoolId":10093,"CircularDate":"2018-02-16T00:00:00","Description":"Sokam Parama Sokam","FilePath":"","TimeStamp":"2018-02-16T14:59:07.32","FromStatus":1}]
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
         * Head : Circular
         * CircularId : 14
         * SchoolId : 10093
         * CircularDate : 2018-03-03T00:00:00
         * Description : 554554
         * FilePath : /Media/10093/CircularData/044c09c3-f413-46a2-8611-ce9fa84372ee^0B36F060C8DAF3EF86B9895600407C39B3752A7073AEF717B9^pimgpsh_fullsize_distr.jpg
         * TimeStamp : 2018-02-08T14:47:14.893
         * FromStatus : 0
         */

        private String Head;
        private int CircularId;
        private int SchoolId;
        private String CircularDate;
        private String Description;
        private String FilePath;
        private String TimeStamp;
        private int FromStatus;

        public String getHead() {
            return Head;
        }

        public void setHead(String Head) {
            this.Head = Head;
        }

        public int getCircularId() {
            return CircularId;
        }

        public void setCircularId(int CircularId) {
            this.CircularId = CircularId;
        }

        public int getSchoolId() {
            return SchoolId;
        }

        public void setSchoolId(int SchoolId) {
            this.SchoolId = SchoolId;
        }

        public String getCircularDate() {
            return CircularDate;
        }

        public void setCircularDate(String CircularDate) {
            this.CircularDate = CircularDate;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String TimeStamp) {
            this.TimeStamp = TimeStamp;
        }

        public int getFromStatus() {
            return FromStatus;
        }

        public void setFromStatus(int FromStatus) {
            this.FromStatus = FromStatus;
        }
    }
}
