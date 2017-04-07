package com.tt.gwentapp.ui.details;

import com.tt.gwentapp.ui.BaseView;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public interface CardDetailsView extends BaseView {

    void loadImage(String url);

    void setToolbarColor(int colorId);

    void setStatusBarColor(int colorId);

    void setFactionImage(int imgResId);

    void setCardName(String name);

    void setCardFlavour(String flavour);

    void setCardStrength(String strength);

    void setCardTags(String tag);

    void setCardDescription(String desc);
}
