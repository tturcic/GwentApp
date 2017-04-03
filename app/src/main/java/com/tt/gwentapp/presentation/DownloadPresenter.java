package com.tt.gwentapp.presentation;

import com.tt.gwentapp.R;
import com.tt.gwentapp.data.local.DatabaseInteractor;
import com.tt.gwentapp.data.local.PrefsManager;
import com.tt.gwentapp.data.remote.RxTransformer;
import com.tt.gwentapp.interactors.DownloadInteractor;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.ui.download.DownloadView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class DownloadPresenter extends BasePresenter<DownloadView> {

    private final DownloadInteractor downloadInteractor;
    //private final Scheduler androidScheduler;
    private final RxTransformer rxTransformer;
    private final DatabaseInteractor database;
    private final PrefsManager prefsManager;

    public DownloadPresenter(DownloadView view, DownloadInteractor downloadInteractor, RxTransformer rxTransformer, DatabaseInteractor databaseInteractor, PrefsManager prefsManager) {
        super(view);
        this.downloadInteractor = downloadInteractor;
        this.rxTransformer = rxTransformer;
        this.database = databaseInteractor;
        this.prefsManager = prefsManager;
    }

    public void getContent() {
        view.showHideErrorView(false);
        view.showHideProgressView(true);
        view.showHideProgressIndicator(true);
        view.setProgressInfoText(R.string.download_checking_version);

        // Placeholder for real API call which would check for DB changes
        addSubscription(downloadInteractor.getLatestDbVersion()
                .compose(rxTransformer.chainSchedulers())
                .subscribe(s -> {
                    //LogUtils.log(s);
                    String prevVersion = prefsManager.getLatestCardVersion();
                    if(prevVersion != null && prevVersion.equals(s)){
                        // Same DB version - proceed to next screen
                        view.navigateToMain();
                    } else {
                        // Download cards
                        downloadCards(s);
                    }
                }, this::onError));

    }

    private void downloadCards(String latestCardVersion) {
        view.setProgressInfoText(R.string.download_downloading_cards);
        addSubscription(downloadInteractor.getCards()
                .compose(rxTransformer.chainSchedulers())
                //.doOnNext(new Subscriber<>())
                .doOnNext(new Action1<List<Card>>() {
                    @Override
                    public void call(List<Card> cards) {
                        view.showHideProgressIndicator(false);
                        view.setProgressInfoText(R.string.download_completed);
                        database.saveCardsToDatabase(cards);
                        prefsManager.setLatestCardVersion(latestCardVersion);
                    }
                })
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<List<Card>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        DownloadPresenter.this.onError(e);
                    }

                    @Override
                    public void onNext(List<Card> cards) {
                        view.navigateToMain();
                    }
                }));
    }

    private void onError(Throwable throwable) {
        view.showHideProgressView(false);
        view.showHideErrorView(true);

        if (throwable instanceof IOException)
            view.setErrorText(R.string.error_connection);
        else
            view.setErrorText(R.string.error_general);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        database.close();
    }
}
