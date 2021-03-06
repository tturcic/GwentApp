package com.tt.gwentapp.interactors;

import com.tt.gwentapp.data.remote.ApiService;
import com.tt.gwentapp.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public class DownloadInteractorImpl implements DownloadInteractor {

    public static final String LATEST_DB_VERSION = "1.03";

    private final ApiService apiService;

    public DownloadInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Card>> getCards() {
        return Observable.zip(apiService.getCards(Card.FactionName.MONSTERS),
                apiService.getCards(Card.FactionName.NILFGAARD),
                apiService.getCards(Card.FactionName.NORTHERN_REALMS),
                apiService.getCards(Card.FactionName.SCOIA_TAEL),
                apiService.getCards(Card.FactionName.SKELLIGE),
                apiService.getCards(Card.FactionName.NEUTRAL), (monsters, nilfgaard, realms, scoia, skellige, neutral) -> {
                    ArrayList<Card> allCards = new ArrayList<>();
                    allCards.addAll(monsters);
                    allCards.addAll(nilfgaard);
                    allCards.addAll(realms);
                    allCards.addAll(scoia);
                    allCards.addAll(skellige);
                    allCards.addAll(neutral);
                    return allCards;
                });
    }

    /**
     * We're faking a response from server by having a two seconds delay.
     */
    @Override
    public Observable<String> getLatestDbVersion() {
        return Observable.just(LATEST_DB_VERSION).delay(1000, TimeUnit.MILLISECONDS);
    }
}
