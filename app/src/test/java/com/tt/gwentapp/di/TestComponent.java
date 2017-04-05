package com.tt.gwentapp.di;

import com.tt.gwentapp.presentation.DownloadPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author tturcic
 *         \date 5.4.2017.
 */
@Component(modules = TestModule.class)
@Singleton
public interface TestComponent {

    void inject(DownloadPresenterTest test);
}
