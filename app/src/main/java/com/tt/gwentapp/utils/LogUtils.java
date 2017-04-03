package com.tt.gwentapp.utils;

import android.util.Log;

/**
 * @author tturcic
 *         \date 31.3.2017.
 */
public class LogUtils {

    private static final String TAG = "GwentApp";

    public static void log(String msg){
        Log.d(TAG, msg);
    }

    public static void log(int msg){
        log(String.valueOf(msg));
    }

    public static void log(float msg){
        log(String.valueOf(msg));
    }

    public static void log(boolean msg){
        log(String.valueOf(msg));
    }
}
