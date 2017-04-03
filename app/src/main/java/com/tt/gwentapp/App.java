package com.tt.gwentapp;

import android.app.Application;
import android.content.Context;

import com.tt.gwentapp.di.AppComponent;
import com.tt.gwentapp.di.AppModule;
import com.tt.gwentapp.di.DaggerAppComponent;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public static App getApp(Context context){
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
