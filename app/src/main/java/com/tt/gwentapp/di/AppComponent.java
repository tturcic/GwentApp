package com.tt.gwentapp.di;

import com.tt.gwentapp.App;
import com.tt.gwentapp.data.local.DatabaseInteractor;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.local.RealmModule;
import com.tt.gwentapp.data.remote.ApiModule;
import com.tt.gwentapp.data.remote.ApiService;
import com.tt.gwentapp.ui.cards.CardFragment;
import com.tt.gwentapp.ui.details.CardDetailsActivity;
import com.tt.gwentapp.ui.download.DownloadActivity;
import com.tt.gwentapp.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import rx.Scheduler;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
@Component(modules = {AppModule.class, ApiModule.class, RealmModule.class})
@Singleton
public interface AppComponent {

    PrefsManager getPrefsManager();
    ApiService getApiService();
    Scheduler getAndroidScheduler();
    DatabaseInteractor getDatabase();
    Realm getRealm();

    void inject(App app);

    void inject(CardFragment cardFragment);

    void inject(CardDetailsActivity cardDetailsActivity);
}
