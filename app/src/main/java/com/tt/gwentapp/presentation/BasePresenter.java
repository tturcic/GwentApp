package com.tt.gwentapp.presentation;

import com.tt.gwentapp.ui.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public class BasePresenter<T extends BaseView> {

    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    private CompositeSubscription subscriptions = new CompositeSubscription();

    protected void addSubscription(Subscription s){
        subscriptions.add(s);
    }

    public void unsubscribe(){
        subscriptions.clear();
    }
}
