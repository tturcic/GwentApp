package com.tt.gwentapp.presentation;

import com.tt.gwentapp.RxHelper;
import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.remote.RxTransformer;
import com.tt.gwentapp.interactors.DownloadInteractor;
import com.tt.gwentapp.interactors.DownloadInteractorImpl;
import com.tt.gwentapp.ui.download.DownloadView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;

import static org.mockito.Mockito.*;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloadPresenterTest {

    @Mock DownloadView view;
    @Mock DownloadInteractor downloadInteractor;
    @Mock Scheduler scheduler;
    @Mock CardDatabase cardDatabase;
    @Mock PrefsManager prefsManager;

    private DownloadPresenter presenter;

    @Mock
    private Observable<String> mockDbVersionObservable;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        presenter = new DownloadPresenter(view, downloadInteractor,
                cardDatabase, new RxTransformer.TestTransformer(), prefsManager);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetContentVerifyViewCalls(){
        when(downloadInteractor.getLatestDbVersion()).thenReturn(Observable.just("qeq"));
        presenter.getContent();
        verify(view).showHideProgressView(true);
        verify(view).showHideProgressIndicator(true);
        verify(view).showHideErrorView(false);
        verify(view, atLeastOnce()).setProgressInfoText(anyInt());
    }

    @Test
    public void testDbVersionCheckDbVersionOnError(){
        when(downloadInteractor.getLatestDbVersion()).thenReturn(Observable.error(new IOException()));
        presenter.getContent();

        verify(view).showHideProgressView(false);
        verify(view).showHideErrorView(true);
        verify(view).setErrorText(anyInt());
    }

    @Test
    public void testDbVersionMatchesNavigateToMain(){
        when(downloadInteractor.getLatestDbVersion()).thenReturn(RxHelper.createTestObservable(DownloadInteractorImpl.LATEST_DB_VERSION));
        //when(downloadInteractor.getLatestDbVersion()).thenReturn(Observable.just(DownloadInteractorImpl.LATEST_DB_VERSION));
        when(prefsManager.getLatestCardVersion()).thenReturn(DownloadInteractorImpl.LATEST_DB_VERSION);
        presenter.getContent();

        verify(view).navigateToMain();
    }

    @Test
    public void testDownloadCardsWhenVersionNotMatched(){
        when(downloadInteractor.getLatestDbVersion()).thenReturn(RxHelper.createTestObservable("random string"));
        //when(downloadInteractor.getLatestDbVersion()).thenReturn(Observable.just(DownloadInteractorImpl.LATEST_DB_VERSION));
        when(prefsManager.getLatestCardVersion()).thenReturn(DownloadInteractorImpl.LATEST_DB_VERSION);
        presenter.getContent();

        verify(downloadInteractor).getCards();
    }

    @Test
    public void testDownloadCardsSuccess(){
        when(downloadInteractor.getLatestDbVersion()).thenReturn(RxHelper.createTestObservable("Random string"));
        //when(downloadInteractor.getLatestDbVersion()).thenReturn(Observable.just(DownloadInteractorImpl.LATEST_DB_VERSION));
        when(prefsManager.getLatestCardVersion()).thenReturn(DownloadInteractorImpl.LATEST_DB_VERSION);

        when(downloadInteractor.getCards()).thenReturn(RxHelper.createTestObservable(new ArrayList<>()));
        presenter.getContent();

        verify(downloadInteractor, atLeastOnce()).getCards();
        verify(cardDatabase, atLeastOnce()).saveCardsToDatabase(anyList());
        verify(view, atLeastOnce()).setProgressInfoText(anyInt());
        verify(view, atLeastOnce()).showHideProgressIndicator(false);
        verify(prefsManager, atLeastOnce()).setLatestCardVersion(anyString());
    }

    @Test
    public void testCloseDatabaseOnUnsubscribe() throws Exception {
        presenter.unsubscribe();
        verify(cardDatabase).close();
    }

}