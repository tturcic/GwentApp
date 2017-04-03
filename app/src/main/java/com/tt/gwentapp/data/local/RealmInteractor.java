package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;

import java.util.List;

import io.realm.Realm;

/**
 * @author tturcic
 *         \date 3.4.2017.
 */
public class RealmInteractor implements DatabaseInteractor {

    private final Realm realm;

    public RealmInteractor(Realm realm) {
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
