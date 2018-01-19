package com.example.ptwitchapon.familyday;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.RegisModel;

import java.util.ArrayList;
import java.util.List;


public class ConfirmActivity extends AppCompatActivity {
    String TAG = "Poon";
    TextView txtqr,txtname,txtlastname,txtunit,txtact,txtfol,txtqr2;
    ImageView followpick;

    String[] followList = new String[Utils.regisModel.getPARENT().size()];
    String[] parentQR = new String[Utils.regisModel.getPARENT().size()];

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
                createList();
            }
        });



    }

    public void followSelect(String[] items){
        AlertDialog dialog;
        final ArrayList seletedItems=new ArrayList();
        final ArrayList<RegisModel.PARENTBean> sel = new ArrayList<>();
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

    public void createList(){
        for (int i=0;i<Utils.regisModel.getPARENT().size();i++){
            followList[i] = Utils.regisModel.getPARENT().get(i).getRG_FNAME()+ " " +Utils.regisModel.getPARENT().get(i).getRG_LNAME();
        }
        followSelect(followList);
    }
    public void pick(){
        for (int i=0;i<Utils.regisModel.getPARENT().size();i++){
            parentQR[i] = Utils.regisModel.getPARENT().get(i).getRG_FNAME()+ " " +Utils.regisModel.getPARENT().get(i).getRG_LNAME();
        }
    }
}
