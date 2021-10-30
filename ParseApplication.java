package com.Codepath.myapplication;

import android.app.Application;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        ParseObject.registerSubclass(post.class);
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Nt9K9kIZgLjCcmSeryuxfdS1nE1rfPtSMUXUe7F7")
                .clientKey("3CTsc7pzhLpL9S2xgGQlgpRf8LF2tNan2hAxy9x1")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
