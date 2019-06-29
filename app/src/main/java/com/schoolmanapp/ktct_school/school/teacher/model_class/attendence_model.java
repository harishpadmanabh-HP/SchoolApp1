package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 19/06/17.
 */

public class attendence_model {
    /**
     * teacherId : 5
     * attendanceDateTime : 2017-06-14 23:54:45.000
     * classId : 1
     * divisionId : 2
     * shiftstatus : 1
     * studentList : [{"studentId":"1","attendaneStatus":"0"},{"studentId":"4","attendaneStatus":"1"}]
     */

    public String teacherId;
    public String attendanceDateTime;
    public String classId;
    public String divisionId;
    public String shiftstatus;
    public List<StudentListBean> studentList;


    public static class StudentListBean {
        /**
         * studentId : 1
         * attendaneStatus : 0
         */

        public String studentId;
        public String attendaneStatus;


    }

//    /**
//     * teacherId : 5
//     * attendanceDateTime : 2017-06-14 23:54:45.000
//     * classId : 1
//     * divisionId : 2
//     * attendaneStatus : 1
//     * studentList : [{"studentId":"1","shiftstatus":"0"},{"studentId":"4","shiftstatus":"1"}]
//     */
//
//    public String teacherId;
//    public String attendanceDateTime;
//    public String classId;
//    public String divisionId;
//    public String attendaneStatus;
//    public List<StudentListBean> studentList;
//
//
//    public static class StudentListBean {
//        /**
//         * studentId : 1
//         * shiftstatus : 0
//         */
//
//        public String studentId;
//        public String shiftstatus;
//
//    }




}
