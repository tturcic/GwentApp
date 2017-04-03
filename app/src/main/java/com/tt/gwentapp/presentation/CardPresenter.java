package com.tt.gwentapp.presentation;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.ui.cards.CardView;

import io.realm.Realm;
import io.realm.Sort;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardPresenter extends BasePresenter<CardView> {

    private final Realm realm;

    public CardPresenter(CardView view, Realm realm) {
        super(view);
        this.realm = realm;
    }

    public void getCards(Faction faction) {
        switch (faction) {
            case NEUTRAL:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.NEUTRAL).findAll().sort("name", Sort.ASCENDING));
                break;
            case NIFLGAARD:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.NILFGAARD).findAll().sort("name", Sort.ASCENDING));
                break;
            case SCOIA_TAEL:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.SCOIA_TAEL).findAll().sort("name", Sort.ASCENDING));
                break;
            case SKELLIGE:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.SKELLIGE).findAll().sort("name", Sort.ASCENDING));
                break;
            case NORTHERN_REALMS:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.NORTHERN_REALMS).findAll().sort("name", Sort.ASCENDING));
                break;
            case MONSTERS:
                view.showCards(realm.where(Card.class).equalTo("faction", Card.FactionName.MONSTERS).findAll().sort("name", Sort.ASCENDING));
                break;
            case ALL:
            default:
                view.showCards(realm.where(Card.class).findAll().sort("name", Sort.ASCENDING));
                break;
        }
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        realm.close();
    }

    public void onRarityCheckedChanged(Rarity rarity){

    }
}
