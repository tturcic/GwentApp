package com.tt.gwentapp.di;

import android.content.Context;

import com.tt.gwentapp.App;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.local.PrefsManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp(){
        return app;
    }

    @Provides
    @Singleton
    static PrefsManager providePrefsManager(App app){
        return new PrefsManagerImpl(app);
    }
}
