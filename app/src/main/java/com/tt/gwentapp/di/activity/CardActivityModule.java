package com.tt.gwentapp.di.activity;

import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.cards.CardView;

import dagger.Module;
import dagger.Provides;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
@Module(includes = ActivityModule.class)
public class CardActivityModule {

    private CardView view;

    public CardActivityModule(CardView view) {
        this.view = view;
    }

    @Provides
    CardView provideCardView() {
        return view;
    }


    @Provides
    @ActivityScope
    static CardPresenter provideCardPresenter(CardView view, CardDatabase cardDatabase){
        return new CardPresenter(view, cardDatabase);
    }
}
