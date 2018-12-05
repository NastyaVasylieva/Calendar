package com.nastya.calendar.Styles;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import androidx.core.content.ContextCompat;

import com.nastya.calendar.View.MainActivity;
import com.nastya.calendar.R;

import java.util.ArrayList;


public class Theme {
    public static ArrayList<Theme> getAllThemes(Context c) {
        ArrayList<Theme> arrayList = new ArrayList<>();
        arrayList.add(new Theme(c, DARK_THEME));
        arrayList.add(new Theme(c, LIGHT_THEME));
        arrayList.add(new Theme(c, BLACK_THEME));
        arrayList.add(new Theme(c, WHITE_THEME));

        return arrayList;
    }

    public static String THEME_KEY = "currentTheme";
    public static String DARK_THEME = "dark_theme";
    public static String LIGHT_THEME = "light_theme";
    public static String BLACK_THEME = "black_theme";
    public static String WHITE_THEME = "white_theme";

    private String name;
    private String key;

    private int actionBarColor;
    private int backgroundColor;
    private int fabColor;
    private int contentTextColor;
    private String headerTextColor;
    private int statusBarColor;
    private int cardColor;
    private int navigationBarBackgroundColor;
    private int navigationBarTextColor;
    private int navHeaderBackground;
    private int progressWheelRimColor;
    private int progressWheelBarColor;

    private int navHeaderTextColor;

    private int activeColor;
    private int inactiveColor;

    public Theme(Context c) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        String theme = sharedPreferences.getString(THEME_KEY, LIGHT_THEME);
        setTheme(c, theme);
    }

    public Theme(Context c, String theme) {
        setTheme(c, theme);
    }

    private void setTheme(Context c, String theme) {

        Resources resources = MainActivity.getLocalizedResources(c);

        if (theme.equals(BLACK_THEME)) {
            name = resources.getString(R.string.black);
            key = BLACK_THEME;

            actionBarColor = ContextCompat.getColor(c, R.color.blackHeader);
            backgroundColor = ContextCompat.getColor(c, R.color.blackBackground);
            fabColor = ContextCompat.getColor(c, R.color.blackFabColor);
            contentTextColor = Color.WHITE;
            headerTextColor = "ffffff";
            navigationBarTextColor = Color.WHITE;
            statusBarColor = ContextCompat.getColor(c, R.color.blackStatusBar);
            cardColor = ContextCompat.getColor(c, R.color.blackCard);
            navigationBarBackgroundColor = ContextCompat.getColor(c, R.color.blackNavDrawerBackground);
            navHeaderBackground = ContextCompat.getColor(c, R.color.blackNavHeaderBackground);
            progressWheelRimColor = ContextCompat.getColor(c, R.color.blackProgressWheelRimColor);
            progressWheelBarColor = ContextCompat.getColor(c, R.color.blackProgressWheelBarColor);

            navHeaderTextColor = Color.WHITE;

        } else if (theme.equals(DARK_THEME)) {
            name = resources.getString(R.string.dark);
            key = DARK_THEME;

            actionBarColor = ContextCompat.getColor(c, R.color.newHeader);
            backgroundColor = ContextCompat.getColor(c, R.color.newBackground);
            fabColor = ContextCompat.getColor(c, R.color.newFabColor);
            contentTextColor = Color.WHITE;
            headerTextColor = "ffffff";
            navigationBarTextColor = Color.WHITE;
            statusBarColor = ContextCompat.getColor(c, R.color.newStatusBar);
            cardColor = ContextCompat.getColor(c, R.color.newCard);
            navigationBarBackgroundColor = ContextCompat.getColor(c, R.color.newNavDrawerBackground);
            navHeaderBackground = ContextCompat.getColor(c, R.color.newNavHeaderBackground);
            progressWheelRimColor = ContextCompat.getColor(c, R.color.newProgressWheelRimColor);
            progressWheelBarColor = ContextCompat.getColor(c, R.color.newProgressWheelBarColor);

            navHeaderTextColor = Color.WHITE;

        } else if (theme.equals(WHITE_THEME)) {
            name = resources.getString(R.string.white);
            key = WHITE_THEME;

            actionBarColor = ContextCompat.getColor(c, R.color.whiteHeader);
            backgroundColor = ContextCompat.getColor(c, R.color.whiteBackground);
            fabColor = ContextCompat.getColor(c, R.color.whiteFabColor);
            contentTextColor = Color.BLACK;
            headerTextColor = "ffffff";
            navigationBarTextColor = Color.BLACK;
            statusBarColor = ContextCompat.getColor(c, R.color.whiteStatusBar);
            cardColor = ContextCompat.getColor(c, R.color.whiteCard);
            navigationBarBackgroundColor = ContextCompat.getColor(c, R.color.whiteNavDrawerBackground);
            navHeaderBackground = ContextCompat.getColor(c, R.color.whiteNavHeaderBackground);
            progressWheelRimColor = ContextCompat.getColor(c, R.color.whiteProgressWheelRimColor);
            progressWheelBarColor = ContextCompat.getColor(c, R.color.whiteProgressWheelBarColor);

            navHeaderTextColor = Color.BLACK;

        } else {
            name = resources.getString(R.string.light);
            key = LIGHT_THEME;

            actionBarColor = ContextCompat.getColor(c, R.color.newLightHeader);
            backgroundColor = ContextCompat.getColor(c, R.color.newLightBackground);
            fabColor = ContextCompat.getColor(c, R.color.newLightFabColor);
            contentTextColor = Color.BLACK;
            headerTextColor = "ffffff";
            navigationBarTextColor = Color.BLACK;
            statusBarColor = ContextCompat.getColor(c, R.color.newLightStatusBar);
            cardColor = ContextCompat.getColor(c, R.color.newLightCard);
            navigationBarBackgroundColor = ContextCompat.getColor(c, R.color.newLightNavDrawerBackground);
            navHeaderBackground = ContextCompat.getColor(c, R.color.newLightNavHeaderBackground);
            progressWheelRimColor = ContextCompat.getColor(c, R.color.newLightProgressWheelRimColor);
            progressWheelBarColor = ContextCompat.getColor(c, R.color.newLightProgressWheelBarColor);

            navHeaderTextColor = Color.BLACK;
        }

        activeColor = ContextCompat.getColor(c, R.color.active);
        inactiveColor = ContextCompat.getColor(c, R.color.inactive);
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public int getActionBarColor() {
        return actionBarColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getFabColor() {
        return fabColor;
    }

    public int getContentTextColor() {
        return contentTextColor;
    }

    public int getNavigationBarBackgroundColor() {
        return navigationBarBackgroundColor;
    }

    public int getNavigationBarTextColor() {
        return navigationBarTextColor;
    }

    public String getHeaderTextColor() {
        return headerTextColor;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public int getCardColor() {
        return cardColor;
    }

    public int getNavHeaderBackground() {
        return navHeaderBackground;
    }

    public int getProgressWheelRimColor() {
        return progressWheelRimColor;
    }

    public int getProgressWheelBarColor() {
        return progressWheelBarColor;
    }

    public int getNavHeaderTextColor() {
        return navHeaderTextColor;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public int getInactiveColor() {
        return inactiveColor;
    }

}
