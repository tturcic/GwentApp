package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;

import java.util.List;

import io.realm.Realm;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public class RealmDatabase implements CardDatabase {

    private final Realm realm;

    public RealmDatabase(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void saveCardsToDatabase(List<Card> cards) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cards);
        realm.commitTransaction();
    }

    @Override
    public List<Card> getCardsForFaction(String factionName) {
        return realm.where(Card.class).equalTo("faction", factionName).findAll();
    }

    @Override
    public void close() {
        realm.close();
    }
}
