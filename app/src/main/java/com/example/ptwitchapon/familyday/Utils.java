package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Loader;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.example.ptwitchapon.familyday.Model.UserModel;

import java.util.List;

/**
 * Created by ptwitchapon on 8/1/2561.
 */

public class Utils {

    public static String act_id;

    public static String loca;

    public static List<SaveModel> saveModel;

    public static UserModel userModel;

    public static RegisModel regisModel;

    public static List<ReportAllModel> reportModel;

    public static List<RepNitiModel> repNitiModels;

    public static void toast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        toast.show();

    }
}
