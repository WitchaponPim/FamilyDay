package com.example.ptwitchapon.familyday;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ChangeCallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.LoginCallbackListener;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit.Retrofit;


public class ConfirmActivity extends AppCompatActivity {
    String TAG = "Poon";
    TextView txtqr,txtname,txtlastname,txtunit,txtact,txtfol,txtqr2;
    Button btn ;
    ImageView followpick,actpick;

    ArrayList<String> pickList = new ArrayList<>();
    ArrayList<Integer> position = new ArrayList<>();

    String select;
    ArrayList seletedItems=new ArrayList();
    ArrayList<RegisModel.PARENTBean> sel = new ArrayList<>();

    String[] actList = new String[Utils.regisModel.getACTIVITIES().size()];
    String[] followList = new String[Utils.regisModel.getPARENT().size()];
    String[] parentQR = new String[Utils.regisModel.getPARENT().size()];

    ConnectionManager connect = new ConnectionManager();
    ScanQrCallbackListener callbackListener;
    ChangeCallbackListener changeCallbackListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        txtqr = (TextView) findViewById(R.id.txt_qr);
        txtqr2 = (TextView) findViewById(R.id.txt_qr_h);
        txtact = (TextView) findViewById(R.id.txt_act_total);
        txtfol = (TextView)  findViewById(R.id.txt_follow_total);
        txtname = (TextView) findViewById(R.id.txt_name);
        txtlastname = (TextView) findViewById(R.id.txt_lastname);
        txtunit = (TextView) findViewById(R.id.txt_unit);
        followpick = (ImageView) findViewById(R.id.followpick);
        actpick = (ImageView) findViewById(R.id.actpick);

        btn = (Button) findViewById(R.id.savegroup);

        changeCallbackListener = new ChangeCallbackListener() {
            @Override
            public void onResponse(String result, Retrofit retrofit) {
                Log.d(TAG, "onResponse: "+result);
                Utils.toast(getApplicationContext(),result);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ");
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

        callbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
                Log.d(TAG, "onResponse: "+regisModel.getSTATUS());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ");
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
                save();
            }
        });

        txtqr.setText(Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        txtqr2.setText("QR CODE : "+Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        txtname.setText(Utils.regisModel.getPROFILE().get(0).getRG_FNAME());
        txtlastname.setText(Utils.regisModel.getPROFILE().get(0).getRG_LNAME());
        txtact.setText(String.valueOf(Utils.regisModel.getACTIVITIES().size()));
        txtunit.setText(Utils.regisModel.getPROFILE().get(0).getRG_UNIT());
        txtfol.setText(String.valueOf(Utils.regisModel.getPARENT().size()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ยืนยันการลงทะเบียน");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


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
                createAct();
            }
        });



    }

    public void followSelect(String[] items){
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
                        for (int i = 0;i<seletedItems.size();i++) {
                            Log.d(TAG, "onClick: " + seletedItems.get(i));
//                            pickList.add(String.valueOf(Utils.regisModel.getPARENT().get((Integer) seletedItems.get(i)).getRG_SMS()));
                              position.add((Integer) seletedItems.get(i));
                        }

                        for (int l = 0;l<position.size();l++){
                            pickList.add(String.valueOf(Utils.regisModel.getPARENT().get(position.get(l)).getRG_SMS()));
                        }

                        for(int k = 0;k<pickList.size();k++){
                            Log.d(TAG, "onClick: "+pickList.get(k));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel

                    }
                });

        dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();
    }

    public void activitiesSelect(final String[] items){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ConfirmActivity.this);
        builder.setTitle("กิจกรรมที่สามารถลงได้ทั้งหมด");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected = items[which];
                Toast.makeText(getApplicationContext(), "คุณชอบ " +selected, Toast.LENGTH_SHORT).show();
                if (selected.equals(getResources().getStringArray(R.array.runList)[0])||selected.equals(getResources().getStringArray(R.array.runList)[1])){
                    changeRun();
                }
            }
        });
        builder.setNegativeButton("OK", null);
        builder.create();

        builder.show();
    }
    public void createList(){
        for (int i=0;i<Utils.regisModel.getPARENT().size();i++){
            followList[i] = Utils.regisModel.getPARENT().get(i).getRG_FNAME()+ " " +Utils.regisModel.getPARENT().get(i).getRG_LNAME();
        }
        followSelect(followList);
    }

    public void createAct(){
        for(int i=0;i<Utils.regisModel.getACTIVITIES().size();i++){
            actList[i] = Utils.regisModel.getACTIVITIES().get(i).getET_TNAME();
        }
        activitiesSelect(actList);
    }


    public void save(){
        StringBuffer sb = new StringBuffer(Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        for(int i = 0;i<pickList.size();i++){
            sb.append(","+pickList.get(i));
        }

        Log.d(TAG, "save: "+sb+" "+Utils.userModel.getProfile().getUsername());

    }

    public void changeRun(){

        AlertDialog.Builder builder =
                new AlertDialog.Builder(ConfirmActivity.this);
        builder.setTitle("เปลี่ยนประเภท");
        builder.setSingleChoiceItems(getResources().getStringArray(R.array.runList), 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               select = getResources().getStringArray(R.array.runList)[which];
            }
        });
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ส่วนนี้สำหรับเซฟค่าลง database หรือ SharedPreferences.
                connect.changeRun(changeCallbackListener,Utils.regisModel.getPROFILE().get(0).getRG_SMS());

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("ยกเลิก", null);
        builder.create();

// สุดท้ายอย่าลืม show() ด้วย
        builder.show();
    }
}
