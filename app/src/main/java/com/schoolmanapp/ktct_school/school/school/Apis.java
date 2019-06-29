package com.schoolmanapp.ktct_school.school.school;


import com.schoolmanapp.ktct_school.school.driver.Model.Mod_BusId;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_login;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_start_response;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_stop;
import com.schoolmanapp.ktct_school.school.driver.Model.Mod_travel;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Bus;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Classdiv;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Stud;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Add_Teacher;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_Bus;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_division;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_stud;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Edit_Username;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Edit_password;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Bus;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Classdiv;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Students;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Login;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Teacher_List;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_cur_running_bus;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_edit_driver;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_list_division;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_school_reg;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_spin_class;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_travel_history;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_unass_div;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_unassigned;
import com.schoolmanapp.ktct_school.school.school.Model.Model_driverdetails;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;
import com.schoolmanapp.ktct_school.school.school.Model.model_forgot_pass;
import com.schoolmanapp.ktct_school.school.school.Model.sms_responce_mod;
import com.schoolmanapp.ktct_school.school.teacher.model_class.msgtoall_response_model;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by sics on 1/29/2017.
 */

public interface Apis {

//@Multipart
//@POST("SchoolRegistration/SchoolRegistration")
//Call<Mod_school_reg>school_registration(@PartMap Map<String, RequestBody> map);


    @FormUrlEncoded
    @POST("SchoolLogin/SchoolLogin")
    Call<Mod_Login> loginmodel(@Field("Email") String Email,
                               @Field("Password") String Password);

    @FormUrlEncoded
    @POST("SchoolRegistration/SchoolRegistration")
    Call<Mod_school_reg> school_registration(
            @Field("SchoolName") String SchoolName,
            @Field("Address") String Address,
            @Field("City") String City,

            @Field("Email") String Email,

            @Field("Website") String Website,

            @Field("Password") String Password,

            @Field("Contact") String Contact,

            @Field("State") String State,
            @Field("image") String image,
            @Field("FilePath") String FilePath);


    @FormUrlEncoded
    @POST("School/ListClassWithDivision")
    Call<Mod_List_Classdiv> classlist(
            @Field("SchoolId") String SchoolId);

    @FormUrlEncoded
    @POST("school/FullTeacherList")
    Call<Mod_Teacher_List>teacherlist(
            @Field("SchoolId") String SchoolId);


    @FormUrlEncoded
    @POST("School/DeleteDivision")
    Call<Mod_Del_division>deldivision(
            @Field("schoolId") String schoolId,
            @Field("divisionId") String divisionId
            );
    @FormUrlEncoded
    @POST("School/AddTeacher")
    Call<Mod_Add_Teacher> addteacher(
            @Field("schoolId") String schoolId,
            @Field("teacherName") String teacherName,
            @Field("classId") String classId,

            @Field("divisionId") String divisionId,

            @Field("contactNumber") String contactNumber,

            @Field("emailId") String emailId,




            @Field("image") String image,
            @Field("filePath") String filePath);
    @FormUrlEncoded
    @POST("School/AddClassAndDivision")
    Call<Mod_Add_Classdiv> addclassdivision(
            @Field("SchoolId") String SchoolId,
            @Field("ClassNameOrdr") String ClassName,
            @Field("Division") String Division);
    @FormUrlEncoded
    @POST("School/DeleteTeacher")
    Call<Mod_Del_treacher>delteacher(
           @Field("teacherId") String teacherId,
           @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("School/FullClassList")
    Call<Mod_spin_class>classspinlist(
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("School/FullDivisionList")
    Call<Mod_list_division>divspinlist(
            @Field("schoolId") String schoolId,
             @Field("classId") String classId);

    @FormUrlEncoded
    @POST("school/FullBusList")
    Call<Mod_List_Bus>buslist(
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("School/FullRunningBusList")
    Call<Mod_cur_running_bus>buslist_running(
            @Field("schoolId") String schoolId);



    @FormUrlEncoded
    @POST("school/DeleteBus")
    Call<Mod_Del_Bus>delbus(
            @Field("schoolId") String schoolId,
             @Field("busId") String busId);


    @FormUrlEncoded
    @POST("School/AddNewBus")
    Call<Mod_Add_Bus> addbus(
            @Field("schoolId") String schoolId,
            @Field("busName") String busName,
            @Field("tripNumber") String tripNumber,
            @Field("endLocation") String endLocation,
            @Field("busType") String busType);






    @FormUrlEncoded
    @POST("School/FullDriverList")
    Call<Mod_List_Driver> driverlist(
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("School/AddDriver")
    Call<Mod_Add_Driver> adddriver(
            @Field("SchoolId") String SchoolId,

    @Field("DriverName") String DriverName,

    @Field("LicenseNumber") String LicenseNumber,

    @Field("ContactNumber") String ContactNumber,

    @Field("Address") String Address,

    @Field("FilePath") String FilePath,

    @Field("image") String image,
    @Field("City") String City,

    @Field("State") String State);



    @FormUrlEncoded
    @POST("school/DeleteDriver")
    Call<Mod_Del_driver>deldriver(
            @Field("schoolId") String schoolId,
            @Field("driverId") String driverId);

    @FormUrlEncoded
    @POST("School/FullStudentList")
    Call<Mod_List_Students>studentlist(
            @Field("schoolId") String schoolId,
            @Field("classId") String classId,
            @Field("divisionId") String divisionId
            );



    @FormUrlEncoded
    @POST("School/AddStudent")
    Call<Mod_Add_Stud> addstud(
            @Field("schoolId") String schoolId,
            @Field("filePath") String filePath,
            @Field("studentName") String studentName,
            @Field("parentName") String parentName,
            @Field("address") String address,
            @Field("state") String state,
            @Field("city") String city,
            @Field("contact") String contact,
            @Field("classId") String classId,
            @Field("divisionId") String divisionId,
            @Field("busId") String busId,
            @Field("classNo") String classNo,
            @Field("tripNo") String tripNo,
            @Field("image") String image,
            @Field("admissionId") String admissionId,
            @Field("gender") String gender,
            @Field("bloodGroup") String bloodGroup,
            @Field("DOB") String DOB);




    @FormUrlEncoded
    @POST("School/DeleteStudent")
    Call<Mod_Del_stud> delstud(
            @Field("schoolId") String schoolId,
            @Field("studentId") String studentId);

    @FormUrlEncoded
    @POST("school/ChangeUsername")
    Call<Mod_Edit_Username>edit_user(
            @Field("schoolId") String schoolId,
            @Field("oldEmailId") String oldEmailId,
            @Field("newEmailId") String newEmailId
            );

    @FormUrlEncoded
    @POST("school/ChangePassword")
    Call<Mod_Edit_password>edit_pass(
            @Field("schoolId") String schoolId,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword
    );




    @FormUrlEncoded
    @POST("School/TravellHistory")
    Call<Mod_travel_history>history(
            @Field("busId") String busId,
            @Field("tripNumber") String tripNumber,
            @Field("dateTime") String dateTime,
            @Field("timeStatus") String timeStatus
    );

    @FormUrlEncoded
    @POST("Location/TrackLocation")
    Call<Mod_travel_history>track(
            @Field("tripNo") String tripNo,
            @Field("busSpecialId") String busSpecialId

    );





    @FormUrlEncoded
    @POST("School/UnassignedClass")
    Call<Mod_unassigned>unassgdclass(
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("School/UnassignedDivision")
    Call<Mod_unass_div>unassdiv(
            @Field("schoolId") String schoolId,
            @Field("classId") String classId);
    @FormUrlEncoded
    @POST("School/CurrentTravellingBusDriver")
    Call<Model_driverdetails>driverdetails(

            @Field("busId") String busId);






    //Driver


    @FormUrlEncoded
    @POST("Driver/DriverLogin")
    Call<Mod_login> loginmodel(@Field("driverSpecialId") String driverSpecialId);

    @FormUrlEncoded
    @POST("Driver/BusIdentification")
    Call<Mod_BusId> busidmodel(@Field("busSpecialId") String busSpecialId,
                               @Field("tripNo") String tripNo,
                               @Field("schoolId") String schoolId);



    @POST("Driver/TripStart")
    Call<Mod_start_response> start_trip(@Body RequestBody object);

    @FormUrlEncoded
    @POST("Driver/Travelling")
    Call<Mod_travel> UpdateLocation(@Field("tripId") String tripId,
                                    @Field("longitude") String longitude,
                                    @Field("latitude") String latitude);


    @FormUrlEncoded
    @POST("Driver/TripComplete")
    Call<Mod_stop> endtrip(@Field("tripId") String tripId,
                           @Field("completeTime") String completeTime,
                           @Field("latitude") String latitude,
                           @Field("longitude") String longitude);
    @FormUrlEncoded
    @POST("ForgotPassword/ForgetPasswordSendMail")
    Call<model_forgot_pass> forgotpswd(@Field("fromType") String fromType,
                                       @Field("emailId") String emailId);

    @FormUrlEncoded
    @POST("Driver/EditDriver")
    Call<Mod_edit_driver> editdriver(
            @Field("DriverId") Integer DriverId,
            @Field("DriverName") String DriverName,
            @Field("LicenseNumber") String LicenseNumber,
            @Field("ContactNumber") String ContactNumber,
            @Field("Address") String Address,
            @Field("FilePath") String FilePath,
            @Field("image") String image,
            @Field("City") String City,
            @Field("State") String State);

    @POST("School/MessageFromSchool")
    Call<sms_responce_mod> school_sms(@Body RequestBody object);
}

