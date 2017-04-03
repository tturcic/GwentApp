package com.tt.gwentapp.ui.cards;

import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.ui.BaseView;

import java.util.List;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public interface CardView extends BaseView {

    void showCards(List<Card> cards);
}
