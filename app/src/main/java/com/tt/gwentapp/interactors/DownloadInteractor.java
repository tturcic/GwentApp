package com.tt.gwentapp.interactors;

import com.tt.gwentapp.models.Card;

import java.util.List;

import rx.Observable;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public interface DownloadInteractor {

    Observable<List<Card>> getCards();

    Observable<String> getLatestDbVersion();
}
