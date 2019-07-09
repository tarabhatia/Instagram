package com.example.instagram;

import android.app.Application;

import com.example.instagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("tara-bhatia")
                .server("http://tarabhatia-fbu-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);

    }}
