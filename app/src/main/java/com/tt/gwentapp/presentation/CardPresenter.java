package com.tt.gwentapp.presentation;

import com.tt.gwentapp.data.local.CardDatabase;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.models.SortOrder;
import com.tt.gwentapp.ui.cards.CardListView;
import com.tt.gwentapp.ui.cards.CardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardPresenter extends BasePresenter<CardView> {

    private final CardDatabase database;
    private final List<Rarity> rarities = new ArrayList<>();
    private final List<CardListView> cardListViews = new ArrayList<>();
    private SortOrder sortOrder = SortOrder.DEFAULT;

    public CardPresenter(CardView view, CardDatabase database) {
        super(view);
        this.database = database;
        this.rarities.addAll(Arrays.asList(Rarity.values()));
    }

    public void registerCardListView(CardListView cardListView){
        cardListViews.add(cardListView);
        cardListView.setCards(database.getCards(cardListView.getFaction(), sortOrder, rarities));
    }

    public void unregisterCardListView(CardListView cardListView){
        cardListViews.remove(cardListView);
    }

    public void onRaritiesSelected(boolean commonSelected, boolean rareSelected, boolean epicSelected, boolean legendarySelected){
        rarities.clear();
        if(commonSelected)
            rarities.add(Rarity.COMMON);
        if(rareSelected)
            rarities.add(Rarity.RARE);
        if(epicSelected)
            rarities.add(Rarity.EPIC);
        if(legendarySelected)
            rarities.add(Rarity.LEGENDARY);

        for(CardListView cardListView : cardListViews){
            cardListView.setCards(database.getCards(cardListView.getFaction(), sortOrder, rarities));
        }
    }

    public boolean isRarityCommonChecked(){
        return rarities.contains(Rarity.COMMON);
    }

    public boolean isRarityRareChecked(){
        return rarities.contains(Rarity.RARE);
    }

    public boolean isRarityEpicChecked(){
        return rarities.contains(Rarity.EPIC);
    }

    public boolean isRarityLegendaryChecked(){
        return rarities.contains(Rarity.LEGENDARY);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        database.close();
        cardListViews.clear();
    }

}
