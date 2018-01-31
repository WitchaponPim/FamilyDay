package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ChangeCallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.LoginCallbackListener;
import com.example.ptwitchapon.familyday.API.SaveQrCallbackListener;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit.Retrofit;


public class ConfirmActivity extends AppCompatActivity {
    String TAG = "Poon";
    String runTYPE;
    TextView txtqr, txtname, txtlastname, txtunit, txtact, txtfol, txtqr2,alert,runtype;
    Button btn;
    ImageView followpick, actpick,swap;
    LinearLayout btnarea,swaparea;
    boolean foundrun;
    ArrayList<String> pickList = new ArrayList<>();
    ArrayList<Integer> position = new ArrayList<>();
    ArrayList<String> actList = new ArrayList<>();

    String select;
    ArrayList seletedItems = new ArrayList();
    ArrayList<RegisModel.PARENTBean> sel = new ArrayList<>();

   
    String[] followList = new String[Utils.regisModel.getPARENT().size()];
    String[] parentQR = new String[Utils.regisModel.getPARENT().size()];

    ConnectionManager connect = new ConnectionManager();
    SaveQrCallbackListener saveQrCallbackListener;
    ScanQrCallbackListener callbackListener;
    ChangeCallbackListener changeCallbackListener;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        btnarea = (LinearLayout) findViewById(R.id.submit_area2);
        swaparea = (LinearLayout) findViewById(R.id.swZone);
        txtqr = (TextView) findViewById(R.id.txt_qr);
        txtqr2 = (TextView) findViewById(R.id.txt_qr_h);
        txtact = (TextView) findViewById(R.id.txt_act_total);
        txtfol = (TextView) findViewById(R.id.txt_follow_total);
        txtname = (TextView) findViewById(R.id.txt_name);
        txtlastname = (TextView) findViewById(R.id.txt_lastname);
        txtunit = (TextView) findViewById(R.id.txt_unit);
        alert = (TextView) findViewById(R.id.txt_alert);
        runtype = (TextView) findViewById(R.id.txt_run_type);
        followpick = (ImageView) findViewById(R.id.followpick);
        actpick = (ImageView) findViewById(R.id.actpick);
        swap = (ImageView) findViewById(R.id.swap);
        btn = (Button) findViewById(R.id.savegroup);
        check_act();
        createAct();
        saveQrCallbackListener = new SaveQrCallbackListener() {
            @Override
            public void onResponse(List<SaveModel> saveModels, Retrofit retrofit) {
                progressDialog.dismiss();
                Utils.saveModel = saveModels;
                Utils.toast(getApplicationContext(),Utils.saveModel.get(0).getSTATUS_ID()+" "+Utils.saveModel.get(0).getSTATUS());
                Log.d(TAG, "onResponse: ");
                the_end();

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: "+responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {

            }
        };

        callbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
                Utils.regisModel = regisModel;
                refresh();
                Log.d(TAG, "onResponse: " + regisModel.getSTATUS());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+ t);
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: ");
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "onBodyErrorIsNull: ");
            }
        };

        changeCallbackListener = new ChangeCallbackListener() {
            @Override
            public void onResponse(String result, Retrofit retrofit) {
                Log.d(TAG, "onResponse: " + result);
                Utils.toast(getApplicationContext(), result);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                connect.scanqr(callbackListener,Utils.regisModel.getPROFILE().get(0).getRG_SMS(),null,null);
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: ");
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "onBodyErrorIsNull: ");
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(ConfirmActivity.this,"Please wait", "Loading...",true,false);
                save();
            }
        });
        txtact.setText(String.valueOf(actList.size()));
        txtqr.setText(Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        txtqr2.setText("กิจกรรม : "+Utils.loca+"\n"
                +"QR CODE : " + Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        txtname.setText(Utils.regisModel.getPROFILE().get(0).getRG_FNAME());
        txtlastname.setText(Utils.regisModel.getPROFILE().get(0).getRG_LNAME());
        txtunit.setText(Utils.regisModel.getPROFILE().get(0).getRG_UNIT());
        txtfol.setText(String.valueOf(Utils.regisModel.getPARENT().size()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ยืนยันการลงทะเบียน");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        if(foundrun){

            swaparea.setVisibility(View.VISIBLE);
            String newRun = runTYPE.replace("เดิน-วิ่ง","");
            runtype.setText(newRun);
        }


        followpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickList.clear();
                position.clear();
                seletedItems.clear();
                createList();
            }
        });
        actpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activitiesSelect(actList);
            }
        });
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapSelect(runTYPE);
            }
        });


    }

    public void followSelect(String[] items) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("เลือกผู้ติดตามที่มาแสดงตน");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            // write your code when user checked the checkbox
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            // write your code when user Uchecked the checkbox
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here
                        for (int i = 0; i < seletedItems.size(); i++) {
                            Log.d(TAG, "onClick: " + seletedItems.get(i));
//                            pickList.add(String.valueOf(Utils.regisModel.getPARENT().get((Integer) seletedItems.get(i)).getRG_SMS()));
                            position.add((Integer) seletedItems.get(i));
                        }

                        for (int l = 0; l < position.size(); l++) {
                            pickList.add(String.valueOf(Utils.regisModel.getPARENT().get(position.get(l)).getRG_SMS()));
                        }

                        for (int k = 0; k < pickList.size(); k++) {
                            Log.d(TAG, "onClick: " + pickList.get(k));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel

                    }
                }).setNeutralButton("Select All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int l = 0; l < Utils.regisModel.getPARENT().size(); l++) {
                    pickList.add(String.valueOf(Utils.regisModel.getPARENT().get(l).getRG_SMS()));
                }
                for (int k = 0; k < pickList.size(); k++) {
                    Log.d(TAG, "ALL: " + pickList.get(k));
                }
                Utils.toast(getApplicationContext(),"เลือกผู้ติดตามทั้งหมดแล้ว");
            }
        });

        dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();
    }

    public void activitiesSelect(final ArrayList<String> items) {
        String[] item = new String[items.size()];
        for(int i = 0 ;i<items.size();i++){
            item[i] = items.get(i);
        }

        AlertDialog.Builder builder =
                new AlertDialog.Builder(ConfirmActivity.this);
        builder.setTitle("กิจกรรมที่สามารถลงได้ทั้งหมด");
        builder.setItems(item,null);
        builder.setNegativeButton("OK", null);
        builder.create();
        builder.show();
    }

    public void swapSelect(String item){
                switch (item) {
                    case "เดิน-วิ่ง 2.5 กม.": changeRun(1);
                        break;
                    case "เดิน-วิ่ง 5.0 กม.": changeRun(0);
                        break;
                    default:
                        break;
                }

    }

    public void createList() {
        for (int i = 0; i < Utils.regisModel.getPARENT().size(); i++) {
            followList[i] = Utils.regisModel.getPARENT().get(i).getRG_FNAME() + " " + Utils.regisModel.getPARENT().get(i).getRG_LNAME();

        }
        followSelect(followList);
    }

    public void createAct() {
        boolean test;
        for (int i = 0; i < Utils.regisModel.getACTIVITIES().size(); i++) {
            test = Utils.regisModel.getACTIVITIES().get(i).getET_TNAME().contains("เดิน-วิ่ง");
            Log.d(TAG, "createAct: "+test);
            if(test){
                runTYPE = Utils.regisModel.getACTIVITIES().get(i).getET_TNAME();
                foundrun = test;
            }else {
                actList.add(Utils.regisModel.getACTIVITIES().get(i).getET_TNAME());
            }

        }

    }


    public void save() {
        StringBuffer sb = new StringBuffer(Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        for (int i = 0; i < pickList.size(); i++) {
            sb.append("," + pickList.get(i));
        }

        Log.d(TAG, "save: " + sb + " " + Utils.userModel.getProfile().getUsername()+" "+Utils.act_id );

        connect.saveqr(saveQrCallbackListener,sb.toString(),Utils.userModel.getProfile().getUsername(),Utils.act_id);

    }

    public void changeRun(int i) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ConfirmActivity.this);
        builder.setTitle("เปลี่ยนประเภท");
        builder.setMessage("ต้องการเปลี่ยนประเภทการวิ่งเป็น \n"+getResources().getStringArray(R.array.runList)[i]+" หรือไม่");
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ส่วนนี้สำหรับเซฟค่าลง database หรือ SharedPreferences.
                connect.changeRun(changeCallbackListener, Utils.regisModel.getPROFILE().get(0).getRG_SMS());

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("ยกเลิก", null);
        builder.create();
        builder.show();
    }
    public void refresh(){
        Intent intent = new Intent(ConfirmActivity.this,ConfirmActivity.class);
        startActivity(intent);
        finish();
    }
    public void the_end(){
        Intent intent = new Intent(ConfirmActivity.this,SuccessActivity.class);
        startActivity(intent);
        finish();
    }
    public void check_act(){

        List<String> list = new ArrayList<>();
        for (int i = 0 ;i<Utils.regisModel.getACTIVITIES().size();i++){
            list.add(Utils.regisModel.getACTIVITIES().get(i).getET_TNAME());
            Log.d(TAG, "check_act: "+list.get(i)+ " : " + Utils.loca);
        }

        if (list.contains(Utils.loca)) {
            txtqr2.setVisibility(View.VISIBLE);
            btnarea.setVisibility(View.VISIBLE);
            alert.setVisibility(View.GONE);
        }else {
            alert.setText("ไม่ได้ลงทะเบียนกิจกรรม "+ Utils.loca + " ไว้");
            alert.setVisibility(View.VISIBLE);
            txtqr2.setVisibility(View.INVISIBLE);
            btnarea.setVisibility(View.INVISIBLE);
        }
    }
}
