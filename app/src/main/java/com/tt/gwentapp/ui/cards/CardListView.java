package com.tt.gwentapp.ui.cards;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;

import java.util.List;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
public interface CardListView {

    Faction getFaction();

    void setCards(List<Card> cards);
}
