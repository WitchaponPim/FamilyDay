package com.example.ptwitchapon.familyday;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.Model.UserModel;

/**
 * Created by ptwitchapon on 8/1/2561.
 */

public class Utils {

    public static UserModel userModel;

    public static void toast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
