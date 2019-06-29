package com.schoolmanapp.ktct_school.school.teacher.Api_interface;

import com.schoolmanapp.ktct_school.school.teacher.model_class.AllClassModel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.AllDivisionsmodel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.Exam;
import com.schoolmanapp.ktct_school.school.teacher.model_class.ExamsModelDTO;
import com.schoolmanapp.ktct_school.school.teacher.model_class.MarkListViewModel;
import com.schoolmanapp.ktct_school.school.teacher.model_class.SaveMark;
import com.schoolmanapp.ktct_school.school.teacher.model_class.SelectExam;
import com.schoolmanapp.ktct_school.school.teacher.model_class.StudentsFull;
import com.schoolmanapp.ktct_school.school.teacher.model_class.login_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.msgtoall_response_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.previous_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.response_attendence_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.single_message_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.student_list_model;
import com.schoolmanapp.ktct_school.school.teacher.model_class.teachermessage_model;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by srishtiinnovative on 08/06/17.
 */

public interface API_interface {

    @FormUrlEncoded
    @POST("Teacher/TeacherLogin")
    Call<login_model> loginModelCall(@Field("teacherId") String teacher_id);


    @POST("Teacher/Attendance")
    Call<response_attendence_model> update_attendence(@Body RequestBody object);

    @FormUrlEncoded
    @POST("Teacher/StudentList")
    Call<student_list_model> studentlistModelCall(@Field("teacherId") int teacherId,
                                                  @Field("classId") int classId,
                                                  @Field("divisionId") int divisionId);

    @FormUrlEncoded
    @POST("Teacher/AttendanceData")
    Call<previous_attendence_model> previous_attendence_listModelCall(@Field("teacherId") int teacherId,
                                                                      @Field("classId") int classId,
                                                                      @Field("divisionId") int divisionId,
                                                                      @Field("date") String date,
                                                                      @Field("shiftStatus") int shiftStatus);

    @FormUrlEncoded
    @POST("Teacher/ViewMessages")
    Call<teachermessage_model> message_list(@Field("teacherId") String teacherId,
                                            @Field("divisionId") String divisionId,
                                            @Field("index") String index,
                                            @Field("length") String length,
                                            @Field("studentId") String studentId);

    @Multipart
    @POST("Teacher/SentMessage")
    Call<single_message_model> singlemessageModelCall(@PartMap Map<String, RequestBody> params);
//

//    @Multipart
//    @POST("SentMessage")
//    Call<single_message_model> singlemessageModelCall(@Part("PostFile\"; filename=\"pp.png\" ") RequestBody PostFile, @Part("TeacherId") RequestBody TeacherId,
//                                                      @Part("ToSentId") RequestBody ToSentId, @Part("subject") RequestBody subject, @Part("description") RequestBody description, @Part("MessageType") RequestBody MessageType);




//    @POST("SentMessage")
//    Call<single_message_model> singlemessageModelCall(@PartMap Map<String, RequestBody> map,@Body RequestBody files);


    @POST("Teacher/MultipleMessaging")
    Call<msgtoall_response_model> message_to_allCall(@Body RequestBody object);


    @POST("School/SelectExams")
    Call<SelectExam> selectExamModelCall(@Field("SchoolId") int SchoolId,
                                         @Field("ClassId") int ClassId,
                                         @Field("DivisionId") int DivisionId);


    @FormUrlEncoded
    @POST("School/FullClassList")
    Call<AllClassModel> ALL_CLASS_MODEL_CALL (@Field("schoolId") int schoolId );

    @FormUrlEncoded
    @POST("School/FullDivisionList")
    Call<AllDivisionsmodel> allDivisionscall(@Field("schoolId") int schoolId,
                                             @Field("classId") int classId);

    @FormUrlEncoded
    @POST("School/SelectExams")
    Call<Exam> examsmodelcall (@Field("SchoolId") int SchoolId,
                               @Field("ClassId") int ClassId,
                               @Field("DivisionId") int DivisionId);

    @FormUrlEncoded
    @POST("School/FullStudentList")
    Call<StudentsFull> studentsFullCall(@Field("schoolId") int schoolId,
                                        @Field("classId") int classId,
                                        @Field("divisionId") int divisionId
                                        );

    @POST("School/SaveMark")
    Call<SaveMark> saveMarkCall (@Body RequestBody object);



    @POST("School/MarkListView")
    Call<MarkListViewModel> marklistviewcall(@Body RequestBody requestBody);

}
