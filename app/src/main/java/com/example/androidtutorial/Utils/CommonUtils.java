package com.example.androidtutorial.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Objects;

public class CommonUtils {
    /**
     * 显示 通用Toast
     *
     * @param context context
     * @param content content
     */
    public static void ShowCommonToast(Context context, String content) {
        try {
            Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            LogHelper.LogError(String.format("ShowCommonToast    failed ===> content = %s, error = %s", content, e));
        }
    }

    /**
     * 获取 包名
     *
     * @param activity Activity
     * @return packageName
     */
    public static String GetPackageName(Activity activity) {
        try {
            return Objects.requireNonNull(activity.getClass().getPackage()).getName();
        } catch (Exception e) {
            LogHelper.LogError(String.format("GetPackageName    failed ===> error = %s", e));
            return "";
        }
    }


    /**
     * 应用商店枚举
     */
    public enum E_AppStorePkg {
        /**
         * Google Play商店
         */
        GooglePlay,
        /**
         * Google Play Game游戏
         */
        GooglePlayGame,
    }

    /**
     * 获取 目标应用商店的Pkg标识
     *
     * @param appStorePkg 目标应用商店
     * @return 应用商店Pkg标识
     */
    public static String GetAppStorePkg(E_AppStorePkg appStorePkg) {
        switch (appStorePkg) {
            case GooglePlay:
                return "com.android.vending";
            case GooglePlayGame:
                return "com.google.android.play.games";
        }

        return "";
    }

    /**
     * 跳转到 目标应用商店中 此App页面
     *
     * @param activity activity
     * @param storePkg 应用商店Pkg标识
     */
    public static void JumpToAppStore(Activity activity, String storePkg) {
        String applicationID = GetPackageName(activity);
        JumpToAppStore(activity, applicationID, storePkg);
    }

    /**
     * 跳转到 目标应用商店中 目标App页面
     *
     * @param activity      activity
     * @param applicationID 目标App的ApplicationID标识
     * @param storePkg      应用商店Pkg标识
     */
    public static void JumpToAppStore(Activity activity, String applicationID, String storePkg) {
        try {
            if (TextUtils.isEmpty(applicationID))
                throw new Exception("applicationID is empty.");
            if (TextUtils.isEmpty(storePkg))
                throw new Exception("storePkg is empty.");

            Uri uri = Uri.parse("market://details?id=" + applicationID);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(storePkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            activity.startActivity(intent);

        } catch (Exception e) {
            LogHelper.LogError(String.format("JumpToAppStore    failed ===> applicationID = %s, storePkg = %s, error = %s", applicationID, storePkg, e));
        }
    }
}