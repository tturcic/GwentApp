package com.tt.gwentapp.di.activity;

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

    @ActivityScope
    @Provides
    static CardDatabase provideCardDatabase(Realm realm){
        LogUtils.log("new realm db");
        return new RealmDatabase(realm);
    }
}
