package com.tt.gwentapp.data.remote;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public interface RxTransformer {

    <T> Observable.Transformer<T, T> subscribeOn();

    <T> Observable.Transformer<T, T> observeOn();

    /**
     * Combination of 2 schedulers to subscribe and observe on.
     * Substitution to Observable.subscribeOn(...).observeOn(...) combination;
     *
     * @param <T> Observable type
     * @return Observable with applied schedulers that is subscribed on one scheduler
     * and observes on the other
     */
    <T> Observable.Transformer<T, T> chainSchedulers();

    /**
     * Default transformer implementation that uses Schedulers.io() and
     * AndroidSchedulers.mainThread().
     */
    class DefaultRxTransformer implements RxTransformer {
        public <T> Observable.Transformer<T, T> subscribeOn() {
            return tObservable -> tObservable.subscribeOn(Schedulers.io());
        }

        public <T> Observable.Transformer<T, T> observeOn() {
            return tObservable -> tObservable.observeOn(AndroidSchedulers.mainThread());
        }

        public <T> Observable.Transformer<T, T> chainSchedulers() {
            return tObservable -> tObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    /**
     * Override only methods that you want to override.
     */
    class SimpleRxTransformer implements RxTransformer {

        @Override
        public <T> Observable.Transformer<T, T> subscribeOn() {
            return tObservable -> tObservable;
        }

        @Override
        public <T> Observable.Transformer<T, T> observeOn() {
            return tObservable -> tObservable;
        }

        @Override
        public <T> Observable.Transformer<T, T> chainSchedulers() {
            return tObservable -> tObservable;
        }
    }

    class TestTransformer extends RxTransformer.SimpleRxTransformer {
        @Override
        public <T> Observable.Transformer<T, T> subscribeOn() {
            return tObservable -> tObservable.subscribeOn(Schedulers.immediate());
        }

        @Override
        public <T> Observable.Transformer<T, T> observeOn() {
            return tObservable -> tObservable.subscribeOn(Schedulers.immediate());
        }

        @Override
        public <T> Observable.Transformer<T, T> chainSchedulers() {
            return tObservable -> tObservable.subscribeOn(Schedulers.immediate())
                    .observeOn(Schedulers.immediate());
        }
    }
}
