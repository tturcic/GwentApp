package com.tt.gwentapp.presentation;

import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.ui.cards.CardView;

import java.util.List;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardPresenter extends BasePresenter<CardView> {

    private final CardDatabase database;

    public CardPresenter(CardView view, CardDatabase database) {
        super(view);
        this.database = database;
    }

    public List<Card> getCards(Faction faction) {
        return database.getCardsForFaction(faction);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        database.close();
    }

    public void onRarityCheckedChanged(Rarity rarity){

    }
}
