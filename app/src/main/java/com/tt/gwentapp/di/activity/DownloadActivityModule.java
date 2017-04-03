package com.tt.gwentapp.di.activity;

import com.tt.gwentapp.data.local.DatabaseInteractor;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.remote.ApiService;
import com.tt.gwentapp.data.remote.RxTransformer;
import com.tt.gwentapp.interactors.DownloadInteractor;
import com.tt.gwentapp.interactors.DownloadInteractorImpl;
import com.tt.gwentapp.presentation.DownloadPresenter;
import com.tt.gwentapp.ui.download.DownloadView;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * @author tturcic
 *         \date 31.3.2017.
 */
@Module
public class DownloadActivityModule {

    private final DownloadView view;

    public DownloadActivityModule(DownloadView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    DownloadView provideDownloadView(){
        return view;
    }

    @Provides
    @ActivityScope
    static DownloadInteractor provideDownloadInteractor(ApiService apiService){
        return new DownloadInteractorImpl(apiService);
    }

    @Provides
    @ActivityScope
    static DownloadPresenter provideDownloadPresenter(DownloadView view, DownloadInteractor downloadInteractor,
                                                      Scheduler scheduler, DatabaseInteractor databaseInteractor, PrefsManager prefsManager){
        return new DownloadPresenter(view, downloadInteractor, new RxTransformer.DefaultRxTransformer(), databaseInteractor, prefsManager);
    }
}
