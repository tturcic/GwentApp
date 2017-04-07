package com.tt.gwentapp.di.application;

import com.tt.gwentapp.App;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.local.RealmModule;
import com.tt.gwentapp.data.remote.ApiModule;
import com.tt.gwentapp.data.remote.ApiService;
import com.tt.gwentapp.data.remote.RxTransformer;
import com.tt.gwentapp.ui.custom_views.FontTextView;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
@Component(modules = {AppModule.class, ApiModule.class, RealmModule.class})
@Singleton
public interface AppComponent {

    PrefsManager getPrefsManager();
    ApiService getApiService();
    RxTransformer getRxTransformer();
    Realm getRealm();

    void inject(App app);

    void inject(FontTextView fontTextView);

}
