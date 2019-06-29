package com.schoolmanapp.ktct_school.school.school;

import android.app.AlertDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.schoolmanapp.ktct_school.school.R;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_Del_division;
import com.schoolmanapp.ktct_school.school.school.Model.Mod_List_Classdiv;
import com.schoolmanapp.ktct_school.school.school.common.BaseFragment;
import com.schoolmanapp.ktct_school.school.school.utils.AppPreferences;
import com.schoolmanapp.ktct_school.school.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by srishtiinnovative on 29/05/17.
 */

//public class Classdiv extends Activity {
//
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.classdivtab);
//    }
//
//}
public class Classdiv extends BaseFragment implements DeleteclassInterface,onClickInterface{
    ListView lv;
    ImageView plus;
    LinearLayout llhide, llshow;
    ArrayList<String> classname, division;
    ArrayList<Integer> did,cid;
    String divid,clid;
    int div,classid;


    int sid;
    AlertDialog pd;
    String scid;

    public static DeleteclassInterface deleteInterface;
    public static onClickInterface clickInterface;

    Classdiv cd;
    Bundle b;

    @Inject
    Utils utils;
    @Inject
    AppPreferences appPreferences;
    public classadapter adapter;

    private List<Mod_List_Classdiv> classdivlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View V = inflater.inflate(R.layout.classdivtab, container, false);
        Home h = (Home) getActivity();
        h.getApp().getActivityComponent().inject(this);

        ButterKnife.bind(getActivity());
b=new Bundle();
        classname = new ArrayList<String>();
        division = new ArrayList<String>();
        did = new ArrayList<Integer>();
        cid=new ArrayList<Integer>();
        llhide = (LinearLayout) V.findViewById(R.id.tabhidelay);
        llshow = (LinearLayout) V.findViewById(R.id.tabaddlay);

        deleteInterface = this;
        clickInterface=this;
        plus = (ImageView) V.findViewById(R.id.img_addclass);
        plus.setVisibility(View.INVISIBLE);


        if (V != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(V.getWindowToken(), 0);
        }
        lv = (ListView) V.findViewById(R.id.class_list);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.isInHomePage = false;
                Home.isAddButtonPressed = true;
                llhide.setVisibility(GONE);
                llshow.setVisibility(VISIBLE);


                loadFragment2(new AddclassFragment());

            }
        });


        sid = appPreferences.getInt("scid");
        scid = Integer.toString(sid);
        display();


        return V;
    }


    public void loadFragment2(AddclassFragment fragment) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();

    }
    public void loadFragment3(Student fragment) {



        FragmentManager fm = getFragmentManager();
        fragment.setArguments(b);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();


        fragmentTransaction.replace(R.id.frameadd_lay, fragment);
        fragmentTransaction.commit();

    }


    public void display() {

        pd = new SpotsDialog(getActivity());

try {

    pd.show();

    Call<Mod_List_Classdiv> call = utils.getApi().classlist(scid);

    call.enqueue(new Callback<Mod_List_Classdiv>() {
        @Override
        public void onResponse(Call<Mod_List_Classdiv> call, Response<Mod_List_Classdiv> response) {
            if (response.isSuccess()) {
                if (response.body().getMsg().equals("Success")) {
                    List<Mod_List_Classdiv.UserDataBean> details = response.body().getUserData();
                    // Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();


                    if (details.size() > 0) {

                        int l = details.size();
                        for (int i = 0; i < l; i++) {
                            String c = details.get(i).getClassName().toString();

                            List<Mod_List_Classdiv.UserDataBean.DivisionBean> d = details.get(i).getDivision();
                            int len = d.size();
                            for (int j = 0; j < len; j++) {
                                try {


                                String div = d.get(j).getDivisionName();
                                int divid = d.get(j).getDivisionId();
                                int clid = d.get(j).getClassId();
                                classname.add(c);
                                division.add(div);
                                did.add(divid);
                                cid.add(clid);
                                }catch (NullPointerException e){

                                }



                            }
                            adapter = new classadapter(getActivity(), classname, division);
                            //  adapter =new TestAdapter(getActivity(),details);
                            lv.setAdapter(adapter);

                        }
                    } else {

                        if (pd.isShowing()) {
                            plus.setVisibility(VISIBLE);
                            pd.dismiss();
                        }

                        utils.toToast(response.body().getMsg());
                        utils.toToast("No classes added for the school");
                    }
                } else {

                    if (pd.isShowing()) {
                        plus.setVisibility(VISIBLE);
                        pd.dismiss();
                    }

                    utils.toToast(response.body().getMsg());
                }

            } else

                utils.toToast(response.body().getMsg());

            if (pd.isShowing()) {


                plus.setVisibility(VISIBLE);
                pd.dismiss();
            }

        }


        @Override
        public void onFailure(Call<Mod_List_Classdiv> call, Throwable t) {
            //  utils.toToast("Network failure");
            Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
        }

    });
}catch(Exception e)
{
    e.printStackTrace();
    if(pd.isShowing())
    {
        pd.dismiss();
    }

    Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
}

    }

    @Override
    public void onDelete(final int position) {

        int div = did.get(position);
        String did = Integer.toString(div);
        pd = new SpotsDialog(getActivity());


        pd.show();

        Call<Mod_Del_division> call = utils.getApi().deldivision(scid, did);

        call.enqueue(new Callback<Mod_Del_division>() {
            @Override
            public void onResponse(Call<Mod_Del_division> call, Response<Mod_Del_division> response) {
                if (response.isSuccess()) {

                    if (response.body().getMsg().equals("Success")) {
                        //utils.toToast(response.body().getMsg());
                        pd.dismiss();
                        utils.toToast("Class deleted successfully");

                        adapter.classname.remove(position);
                        adapter.division.remove(position);
                        adapter.notifyDataSetChanged();

                    } else {
                        if (pd.isShowing()) {

                            pd.dismiss();
                        }
                        utils.toToast(response.body().getMsg());
                    }


                } else {
                    if (pd.isShowing()) {

                        pd.dismiss();
                    }
                    utils.toToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<Mod_Del_division> call, Throwable t) {
                if (pd.isShowing()) {

                    pd.dismiss();
                }
                //  utils.toToast("Network failure");
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }


        });


    }

    @Override
    public void onBackPressed() {
        Home.isInHomePage = true;

        llhide.setVisibility(VISIBLE);
        llshow.setVisibility(GONE);
    }


    @Override
    public void onllclick(int pos) {

         div = did.get(pos);
         divid = Integer.toString(div);
         classid = cid.get(pos);
         clid = Integer.toString(classid);
        b.putInt("classid",classid);
        b.putInt("divid",div);
        String cname=classname.get(pos);
        String dname=division.get(pos);
        b.putString("classname",cname);
        b.putString("divname",dname);
        Home.isclassdiv=true;
        appPreferences.saveData("classid",Integer.toString(classid));
        appPreferences.saveData("divid",Integer.toString(div));
        appPreferences.saveInt("cidtopass",classid);
        appPreferences.saveInt("didtopass",div);
        appPreferences.saveData("classtopass",cname);
        appPreferences.saveData("divisiontopass",dname);
        llhide.setVisibility(GONE);
        llshow.setVisibility(VISIBLE);

        loadFragment3(new  Student());
    }
}