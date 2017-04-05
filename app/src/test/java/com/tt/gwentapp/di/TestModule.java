package com.tt.gwentapp.di;

import com.tt.gwentapp.App;
import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.interactors.DownloadInteractor;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

/**
 * @author tturcic
 *         \date 5.4.2017.
 */
@Module
public class TestModule  {

    @Provides
    static PrefsManager provideTestPrefsManager(){
        return Mockito.mock(PrefsManager.class);
    }

    @Provides
    static CardDatabase provideTestCardDatabase(){
        return Mockito.mock(CardDatabase.class);
    }

    @Provides
    static DownloadInteractor provideTestDownloadInteractor(){
        return Mockito.mock(DownloadInteractor.class);
    }
}
