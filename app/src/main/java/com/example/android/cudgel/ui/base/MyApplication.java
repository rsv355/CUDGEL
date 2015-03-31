package com.example.android.cudgel.ui.base;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by krishn on 2/1/2015.
 */
public class MyApplication extends Application{
    static final String TAG = "MyApp";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ha9xuGetKeAc9qNh4ST2BP0QBNRkFtF8jcT305xX", "ivbh23bJ1OKzBxcnbmbEH91dFBj5tSBCBG74MAaG");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);



        // intializing the Shared prefernces
        Prefs.initPrefs(this);

    }
}
