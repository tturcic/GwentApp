package com.tt.gwentapp.di.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.data.local.RealmDatabase;
import com.tt.gwentapp.utils.LogUtils;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author tturcic
 *         \date 5.4.2017.
 *
 *         Activity modules that wish to use realm database should include this module.
 */
@Module
public class ActivityModule {

    /*private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    AppCompatActivity provideActivity(){
        return activity;
    }*/

    @ActivityScope
    @Provides
    static CardDatabase provideCardDatabase(Realm realm){
        LogUtils.log("new realm db");
        return new RealmDatabase(realm);
    }
}
