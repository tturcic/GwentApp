package com.tt.gwentapp.data.remote;

import com.tt.gwentapp.models.Card;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public interface ApiService {

    @GET("card/name/{cardName}")
    Observable<Card> getCard(@Path("cardName") String cardName);

    @GET("card/faction/{cardFaction}")
    Observable<ArrayList<Card>> getCards(@Path("cardFaction") String cardFaction);
}
