package com.tt.gwentapp.di.activity;

import com.tt.gwentapp.di.application.AppComponent;
import com.tt.gwentapp.ui.details.CardDetailsActivity;

import dagger.Component;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CardDetailsModule.class)
public interface CardDetailsComponent {

    void inject(CardDetailsActivity cardDetailsActivity);
}
