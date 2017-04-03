package com.tt.gwentapp;

import com.tt.gwentapp.models.Card;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public class RxHelper {

    public static Observable<String> createTestObservable(String value){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(value);
                subscriber.onCompleted();
            }

        });
    }

    public static Observable<List<Card>> createTestObservable(List<Card> list){
        return Observable.create(new Observable.OnSubscribe<List<Card>>() {
            @Override
            public void call(Subscriber<? super List<Card>> subscriber) {
                subscriber.onNext(list);
                subscriber.onCompleted();
            }

        });
    }
}
