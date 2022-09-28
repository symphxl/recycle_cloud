package com.zkhc.recycle_cloud.util;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * 自定义工具类
 */
public class CommonUtils {
    /**
     * 长消息提示
     *
     * @param context
     * @param msg
     */
    public static void longtMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 长消息提示
     *
     * @param context
     * @param msg
     */
    public static void shortMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * Dlg消息对话框
     *
     * @param context
     * @param msg
     */
    public static void dlgMsg(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
//                .setTitle("消息提示")
//                .setPositiveButton("确定", null)
//                .setNegativeButton("取消", null)
                .create().show();
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
