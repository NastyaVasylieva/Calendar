package com.nastya.calendar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources;

        MainActivity.forceLocale(this);

        resources = MainActivity.getLocalizedResources(this);

    }

    public static void forceLocale(Context context) {
        String localeCode = getLocale(context);
        String localeCodeLowerCase = localeCode.toLowerCase();

        Resources resources = context.getApplicationContext().getResources();
        Configuration overrideConfiguration = resources.getConfiguration();
        Locale overrideLocale = new Locale(localeCodeLowerCase);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            overrideConfiguration.setLocale(overrideLocale);
        } else {
            overrideConfiguration.locale = overrideLocale;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.getApplicationContext().createConfigurationContext(overrideConfiguration);
        } else {
            resources.updateConfiguration(overrideConfiguration, null);
        }
    }

    public static String getLocale(Context c) {
        return "";
        //return PreferenceManager.getDefaultSharedPreferences(c).getString(MainActivity.LOCALE_KEY, LOCALE_ENGLISH);
    }

    @NonNull
    public static Resources getLocalizedResources(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration conf = context.getResources().getConfiguration();
            conf = new Configuration(conf);
            //conf.setLocale(new Locale(getLocale(context)));
            Context localizedContext = context.createConfigurationContext(conf);
            return localizedContext.getResources();
        } else return context.getResources();
    }



}
