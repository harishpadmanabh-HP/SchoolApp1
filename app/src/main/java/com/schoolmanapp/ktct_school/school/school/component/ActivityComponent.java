package com.schoolmanapp.ktct_school.school.school.component;


import com.schoolmanapp.ktct_school.school.driver.MainActivity;
import com.schoolmanapp.ktct_school.school.driver.Select_trip;
import com.schoolmanapp.ktct_school.school.driver.Startstop;
import com.schoolmanapp.ktct_school.school.school.AddBusFragment;
import com.schoolmanapp.ktct_school.school.school.AddDriverFragment;
import com.schoolmanapp.ktct_school.school.school.AddStudentFragment;
import com.schoolmanapp.ktct_school.school.school.AddTeacheFragment;
import com.schoolmanapp.ktct_school.school.school.AddclassFragment;
import com.schoolmanapp.ktct_school.school.school.Bus;
import com.schoolmanapp.ktct_school.school.school.Change_password;
import com.schoolmanapp.ktct_school.school.school.Change_username;
import com.schoolmanapp.ktct_school.school.school.Classdiv;
import com.schoolmanapp.ktct_school.school.school.Displayclass;
import com.schoolmanapp.ktct_school.school.school.Driver;
import com.schoolmanapp.ktct_school.school.school.EditDriverFragment;
import com.schoolmanapp.ktct_school.school.school.Forgotpassword;
import com.schoolmanapp.ktct_school.school.school.Fragment_cfm_teacher;
import com.schoolmanapp.ktct_school.school.school.Fragment_customtravel;
import com.schoolmanapp.ktct_school.school.school.HistoryFragment;
import com.schoolmanapp.ktct_school.school.school.Home;
import com.schoolmanapp.ktct_school.school.school.Login;
import com.schoolmanapp.ktct_school.school.school.ProfileFragment;
import com.schoolmanapp.ktct_school.school.school.Registration;
import com.schoolmanapp.ktct_school.school.school.SetttingsFragment;
import com.schoolmanapp.ktct_school.school.school.Sms_school;
import com.schoolmanapp.ktct_school.school.school.Splashscreen;
import com.schoolmanapp.ktct_school.school.school.Student;
import com.schoolmanapp.ktct_school.school.school.Teacher;
import com.schoolmanapp.ktct_school.school.school.scope.ActivityScope;
import com.schoolmanapp.ktct_school.school.school.tracking.MapsActivity;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;
import com.schoolmanapp.ktct_school.school.parent.actvity_events;

import dagger.Component;

//import com.example.srishtiinnovative.school.student;


@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {
    void inject(AppPreferences sharepref);

    void inject(Utils utilsPref);

    void inject(Registration registration);

    void inject(Login login);

    void inject(Home home);

    void inject(Classdiv classdiv);

    void inject(AddclassFragment addclfrag);

    void inject(AddTeacheFragment addtrfrag);

    void inject(Teacher listtrfgmt);

    void inject(Fragment_cfm_teacher fgmt_cfm_tr);

    void inject(Bus bus);

    void inject(AddBusFragment addbus);

    void inject(Driver driver);

    void inject(Displayclass displayclass);

    void inject(AddStudentFragment addstudent);

    void inject(AddDriverFragment adddriver);

    void inject(Student s);

    void inject(SetttingsFragment settings);

    void inject(Change_username un);

    void inject(Change_password up);

    void inject(ProfileFragment profile);

    void inject(HistoryFragment history);

    void inject(Fragment_customtravel travelhist);

    void inject(Splashscreen splash);

    void inject(com.schoolmanapp.ktct_school.school.driver.Login login);

    void inject(com.schoolmanapp.ktct_school.school.driver.Home home);

    void inject(Startstop start);

    void inject(Select_trip trip);

    void inject(MainActivity main);

    void inject(Forgotpassword fgtpswd);

    void inject(MapsActivity mapact);

    void inject(EditDriverFragment mapact);
    void inject(Sms_school sms);

    //void inject(actvity_events actvity_events);
}
