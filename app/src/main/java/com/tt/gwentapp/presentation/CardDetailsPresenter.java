package com.tt.gwentapp.presentation;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.ui.details.CardDetailsView;

import io.realm.Realm;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardDetailsPresenter extends BasePresenter<CardDetailsView> {

    private final Realm realm;
    private Card card;

    public CardDetailsPresenter(CardDetailsView view, Realm realm, String cardName) {
        super(view);
        this.realm = realm;
        this.card = realm.where(Card.class).equalTo("name", cardName).findFirst();
    }

    public void subscribe() {
        view.loadImage(card.getImage());
        view.setCardDescription(card.getText());
        view.setCardFlavour(card.getFlavor());
        view.setCardName(card.getName());

        Faction faction = card.getFactionEnum();
        view.setFactionImage(faction.getDrawableIconLarge());
        view.setToolbarColor(faction.getColorTab());
        view.setStatusBarColor(faction.getColorStatusBar());
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        realm.close();
    }
}
