package com.tt.gwentapp.di.activity;

import android.app.Activity;

import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.presentation.CardDetailsPresenter;
import com.tt.gwentapp.ui.details.CardDetailsActivity;
import com.tt.gwentapp.ui.details.CardDetailsView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
@Module(includes = ActivityModule.class)
public class CardDetailsModule {

    private CardDetailsView view;

    public CardDetailsModule(CardDetailsView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CardDetailsView provideCardDetailsView(){
        return view;
    }

    @ActivityScope
    @Provides
    static Card provideCard(Activity activity, CardDatabase cardDatabase){
        String cardName = activity.getIntent().getStringExtra(CardDetailsActivity.EXTRA_CARD_NAME);
        return cardDatabase.getCard(cardName);
    }

    @ActivityScope
    @Provides
    static CardDetailsPresenter provideCardDetailsPresenter(CardDetailsView view, Card card){
        return new CardDetailsPresenter(view, card);
    }
}
