package com.example.project_calculator;

import android.app.Activity;
import android.content.Intent;

public class Themes {
    public static int mTheme;
    public final static int THEME_MATERIAL_DARK = 0;
    public final static int THEME_MATERIAL_LIGHT = 1;

    public static void changeToTheme(Activity activity, int theme) {
        mTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (mTheme) {
            default:
            case THEME_MATERIAL_LIGHT:
                activity.setTheme(R.style.AppLightTheme);
                break;
            case THEME_MATERIAL_DARK:
                activity.setTheme(R.style.AppDarkTheme);
                break;

        }

    }
}
