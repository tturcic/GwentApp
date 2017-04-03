package com.tt.gwentapp.data.local;

import com.tt.gwentapp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
@Module
public class RealmModule {

    @Provides
    @Singleton
    static RealmConfiguration provideRealmConfiguration(App app){
        Realm.init(app);
        RealmConfiguration.Builder b = new RealmConfiguration.Builder();
        b.deleteRealmIfMigrationNeeded()
                .name("GwentApp");
        return b.build();
    }

    @Provides
    static Realm provideRealm(RealmConfiguration realmConfiguration){
        return Realm.getInstance(realmConfiguration);
    }

    @Provides
    static DatabaseInteractor provideRealmInteractor(Realm realm){
        return new RealmInteractor(realm);
    }
}
