package com.tt.gwentapp.di.activity;

import com.tt.gwentapp.di.AppComponent;
import com.tt.gwentapp.ui.download.DownloadActivity;

import dagger.Component;

/**
 * @author tturcic
 *         \date 31.3.2017.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {DownloadActivityModule.class})
public interface DownloadActivityComponent {

    void inject(DownloadActivity activity);
}
