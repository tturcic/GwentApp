package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

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
    public List<Card> getCardsForFaction(Faction faction) {
        return realm.where(Card.class).equalTo("faction", getFactionName(faction)).findAll();
    }

    @Override
    public List<Card> getCards(CardFilter cardFilter) {
        RealmResults<Card> allCards = realm.where(Card.class).findAll();
        RealmQuery<Card> query = allCards.where();

        if(cardFilter.getFactions() != null){
            String[] factions = new String[cardFilter.getFactions().length];
            for (int i = 0; i < cardFilter.getFactions().length; i++) {
                factions[i] = getFactionName(cardFilter.getFactions()[i]);
            }
            query = query.in("faction", factions);
        }

        if(cardFilter.getRarities() != null){
            String[] rarities = new String[cardFilter.getRarities().length];
            for (int i = 0; i < cardFilter.getRarities().length; i++) {
                rarities[i] = getRarityName(cardFilter.getRarities()[i]);
            }
            query = query.in("rarity", rarities);
        }

        switch (cardFilter.getSortOrder()){
            case NAME_DESC:
                return query.findAllSorted("name", Sort.DESCENDING);
            case FACTION_ASC:
                return query.findAllSorted("faction", Sort.ASCENDING);
            case FACTION_DESC:
                return query.findAllSorted("faction", Sort.DESCENDING);
            case RARITY_ASC:
                return query.findAllSorted("rarity", Sort.ASCENDING);
            case RARITY_DESC:
                return query.findAllSorted("rarity", Sort.DESCENDING);
            case NAME_ASC:
            default:
                return query.findAllSorted("name", Sort.ASCENDING);
        }
    }

    private String getRarityName(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return "common";
            case RARE:
                return "rare";
            case EPIC:
                return "epic";
            case LEGENDARY:
            default:
                return "legendary";
        }
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
