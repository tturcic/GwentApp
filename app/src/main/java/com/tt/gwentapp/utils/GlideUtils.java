package com.tt.gwentapp.utils;

import com.tt.gwentapp.R;
import com.tt.gwentapp.models.Card;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class GlideUtils {

    public static int getPlaceHolderForFaction(String faction) {
        switch (faction) {
            case Card.FactionName.MONSTERS:
                return R.drawable.faction_monsters;
            case Card.FactionName.NEUTRAL:
                return R.drawable.faction_monsters;
            case Card.FactionName.NILFGAARD:
                return R.drawable.faction_nilfgaard;
            case Card.FactionName.NORTHERN_REALMS:
                return R.drawable.faction_northern_realms;
            case Card.FactionName.SCOIA_TAEL:
                return R.drawable.faction_scoiatael;
            case Card.FactionName.SKELLIGE:
            default:
                return R.drawable.faction_skellige;
        }
    }
}
