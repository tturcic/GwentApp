package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;

import java.util.List;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public interface CardDatabase {

    void saveCardsToDatabase(List<Card> cards);

    List<Card> getCardsForFaction(String factionName);

    void close();

}
