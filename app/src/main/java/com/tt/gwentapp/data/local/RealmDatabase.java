package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.models.SortOrder;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
    public List<Card> getCards(Faction faction, SortOrder sortOrder, List<Rarity> rarities) {
        RealmResults<Card> allCards = realm.where(Card.class).findAll();
        RealmQuery<Card> query = allCards.where();
        RealmResults<Card> factionQuery = query.equalTo("faction", getFactionName(faction)).findAllSorted("name");

        // Sort rarities in a specific order
        List<Card> cardsList = new ArrayList<>();
        if(rarities.contains(Rarity.COMMON)) {
            RealmResults<Card> res = factionQuery.where().equalTo("rarity", Card.RarityName.COMMON).findAll();
            cardsList.addAll(res);
        }

        if(rarities.contains(Rarity.RARE)) {
            RealmResults<Card> res = factionQuery.where().equalTo("rarity", Card.RarityName.RARE).findAll();
            cardsList.addAll(res);
        }

        if(rarities.contains(Rarity.EPIC)) {
            RealmResults<Card> res = factionQuery.where().equalTo("rarity", Card.RarityName.EPIC).findAll();
            cardsList.addAll(res);
        }

        if(rarities.contains(Rarity.LEGENDARY)) {
            RealmResults<Card> res = factionQuery.where().equalTo("rarity", Card.RarityName.LEGENDARY).findAll();
            cardsList.addAll(res);
        }

        return cardsList;
    }

    @Override
    public Card getCard(String name) {
        return realm.where(Card.class).equalTo("name", name).findFirst();
    }

    private String getFactionName(Faction faction){
        String factionName;
        switch (faction) {
            case NEUTRAL:
                factionName = Card.FactionName.NEUTRAL;
                break;
            case NIFLGAARD:
                factionName = Card.FactionName.NILFGAARD;
                break;
            case SCOIA_TAEL:
                factionName = Card.FactionName.SCOIA_TAEL;
                break;
            case SKELLIGE:
                factionName = Card.FactionName.SKELLIGE;
                break;
            case NORTHERN_REALMS:
                factionName = Card.FactionName.NORTHERN_REALMS;
                break;
            case MONSTERS:
                factionName = Card.FactionName.MONSTERS;
                break;
            case ALL:
            default:
                factionName = Card.FactionName.NEUTRAL;
                break;
        }
        return factionName;
    }

    @Override
    public void close() {
        realm.close();
    }
}
