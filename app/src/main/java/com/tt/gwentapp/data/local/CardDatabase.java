package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.models.SortOrder;

import java.util.List;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public interface CardDatabase {

    void saveCardsToDatabase(List<Card> cards);

    List<Card> getCards(Faction faction, SortOrder sortOrder, List<Rarity> rarities);

    Card getCard(String name);

    void close();

}
