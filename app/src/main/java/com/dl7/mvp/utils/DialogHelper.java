package com.dl7.mvp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.flyco.dialog.widget.popup.BubblePopup;

/**
 * Created by moligy on 2017/7/18.
 */

public final class DialogHelper {

    private DialogHelper() {
        throw new AssertionError();
    }

    /**
     * 删除
     * @param context
     * @param listener
     */
    public static void deleteDialog(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否删除?").setPositiveButton("确定", listener).setNegativeButton("取消", null);
        builder.create().show();
    }

    /**
     * 生成 Popup
     * @param context
     * @param layoutResId
     * @return
     */
    public static BubblePopup createPopup(Context context, int layoutResId) {
        View inflate = View.inflate(context, layoutResId, null);
        return new BubblePopup(context, inflate);
    }
}
