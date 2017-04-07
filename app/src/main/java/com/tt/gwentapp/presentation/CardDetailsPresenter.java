package com.tt.gwentapp.presentation;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.ui.details.CardDetailsView;
import com.tt.gwentapp.utils.StringUtils;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardDetailsPresenter extends BasePresenter<CardDetailsView> {

    private Card card;

    public CardDetailsPresenter(CardDetailsView view, Card card) {
        super(view);
        this.card = card;
    }

    public void subscribe() {
        view.loadImage(card.getImage());
        view.setCardDescription(card.getText());
        view.setCardFlavour(card.getFlavor());
        view.setCardName(card.getName());
        view.setCardStrength(card.getStrength());

        view.setCardTags(StringUtils.createTagsForCard(card));

        Faction faction = card.getFactionEnum();
        view.setFactionImage(faction.getDrawableIconLarge());
        view.setToolbarColor(faction.getColorTab());
        view.setStatusBarColor(faction.getColorStatusBar());

    }

}
