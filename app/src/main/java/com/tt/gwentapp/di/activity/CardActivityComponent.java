package com.tt.gwentapp.di.activity;

import com.tt.gwentapp.di.AppComponent;
import com.tt.gwentapp.ui.cards.CardFilterDialog;
import com.tt.gwentapp.ui.cards.CardFragment;
import com.tt.gwentapp.ui.main.MainActivity;
import com.tt.gwentapp.ui.main.MainTabActivity;

import dagger.Component;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = CardActivityModule.class)
public interface CardActivityComponent {

    void inject(MainTabActivity mainTabActivity);
    void inject(MainActivity mainActivity);
    void inject(CardFragment fragment);
    void inject(CardFilterDialog dialog);

}
