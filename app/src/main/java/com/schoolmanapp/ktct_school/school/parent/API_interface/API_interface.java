package com.schoolmanapp.ktct_school.school.parent.API_interface;

import com.schoolmanapp.ktct_school.school.parent.model_class.ExamListModel;
import com.schoolmanapp.ktct_school.school.parent.model_class.ExamResultModel;
import com.schoolmanapp.ktct_school.school.parent.model_class.Model_logout;
import com.schoolmanapp.ktct_school.school.parent.model_class.add_kid_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.calender_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.kid_list_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.login_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.message_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_events_Calender;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_fees;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_forgot_pass;

import com.schoolmanapp.ktct_school.school.parent.model_class.model_message_tosend;
import com.schoolmanapp.ktct_school.school.parent.model_class.model_travellingstatus;
import com.schoolmanapp.ktct_school.school.parent.model_class.new_attendence_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.otp_request;
import com.schoolmanapp.ktct_school.school.parent.model_class.register_model;
import com.schoolmanapp.ktct_school.school.parent.model_class.remove_kid_model;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Message;
import com.schoolmanapp.ktct_school.school.school.Model.event_model;
import com.schoolmanapp.ktct_school.school.school.Model.school_list_mod;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public interface API_interface {
    @FormUrlEncoded
    @POST("ParentLogin")
    Call<login_model> loginModelCall(@Field("email") String email,
                                     @Field("password") String password,
                                     @Field("deviceToken") String deviceToken);
    @FormUrlEncoded
    @POST("ParentLogout")
    Call<Model_logout> logoutModelcall(@Field("parentId") String parentId,
                                      @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @POST("ForgetPasswordSendMail")
    Call<model_forgot_pass> forgotpassModelCall(@Field("fromType") String fromType,
                                                @Field("emailId") String emailId);
    @FormUrlEncoded
    @POST("AddParent")
    Call<register_model> registerModelCall(@Field("ParentName") String parent_name,
                                           @Field("Address") String Address,
                                           @Field("City") String City,
                                           @Field("Email") String Email,
                                           @Field("ContactNumber") String ContactNumber,
                                           @Field("Password") String Password,
                                           @Field("State") String State,
                                           @Field("image") String image,
                                           @Field("FilePath") String FilePath);
    @FormUrlEncoded
    @POST("AddKid")
    Call<add_kid_model> addkidModelCall(@Field("parentId") String parentId,
                                        @Field("kidSpecialId") String kidSpecialId,
                                        @Field("OTP") String OTP);

    @FormUrlEncoded
    @POST("ListStudent")
    Call<kid_list_model> kidlistModelCall(@Field("parentId") int parentId
    );

    @FormUrlEncoded
    @POST("KidTravellingStatus")
    Call<model_travellingstatus> kidtravel_statusModelCall(@Field("kidId") int kidId
    );

    @FormUrlEncoded
    @POST("Messages")
    Call<message_model> messageModelCall(@Field("kidId") int kidId,
                                         @Field("index") int index,
                                         @Field("length") int length,
                                         @Field("SchoolId") int SchoolId,
                                         @Field("ClassId") int ClassId);

    @FormUrlEncoded
    @POST("KidAttendanceData")
    Call<calender_model> calenderModelCall(@Field("kidId") int kidId,
                                           @Field("month") int month,
                                           @Field("year") int year
    );
    @FormUrlEncoded
    @POST("StudentBillHistory")
    Call<model_fees> paid_history(@Field("studentId") int studentId,
                                  @Field("classId") int classId
    );


    @Multipart
    @POST("SendMessage")
    Call<model_message_tosend> messagetoteacher(@PartMap Map<String, RequestBody> params);
    ///events///
    @FormUrlEncoded
    @POST("School/CircularEventWithDate")
    Call<event_model> getevent(@Field("ScholId") String ScholId,
                               @Field("EventDate") String EventDate);
    @FormUrlEncoded
    @POST("School/CircularNotificationList")
    Call<model_events_Calender> getcalenderevent(@Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("RemoveKid")
    Call<remove_kid_model> removekid(@Field("ParentId") int parentid,
                                     @Field("KidId") int kidid );
    @FormUrlEncoded
    @POST("Conversation")
    Call<Mod_Message> MessageModelCall(@Field("StudentId") int kidId,
                                       @Field("start") int index,
                                       @Field("length") int length);
    @FormUrlEncoded
    @POST("OTPRequestAddKid")
    Call<otp_request>Otp_request(@Field("parentId") String parentId,
                                 @Field("kidSpecialId") String kidSpecialId,
                                 @Field("SchoolId") String schoolid);

    @FormUrlEncoded
    @POST("NewKidAttendanceData")
    Call<new_attendence_model> new_attendence(@Field("kidId") int kidId,
                                                 @Field("month") int month,
                                                 @Field("year") int year
    );

    @GET("AllSchoolList")
    Call<school_list_mod> new_attendence();

    @FormUrlEncoded
    @POST("OTPSubmitAddKid")
    Call<add_kid_model>Otp_submit(@Field("parentId") String parentId,
                                  @Field("kidSpecialId") String kidSpecialId,
                                  @Field("OTP") String otp,
                                  @Field("SchoolId") String schoolid);



    @POST("School/ProgressCardExamNameList")
    Call<ExamListModel> examlistcall(@Body RequestBody requestBody);

    @POST("School/ProgressCardExamResults")
    Call<ExamResultModel> examresultcall(@Body RequestBody requestBody);

}
