package com.tt.gwentapp.data.local;

import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;

/**
 * @author tturcic
 *         \date 5.4.2017.
 */
public class CardFilter {

    private Faction[] factions;
    private Rarity[] rarities;
    private SortOrder sortOrder;

    private CardFilter() {}

    public Faction[] getFactions() {
        return factions;
    }

    public Rarity[] getRarities() {
        return rarities;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public enum SortOrder {
        DEFAULT,
        NAME_ASC,
        NAME_DESC,
        RARITY_ASC,
        RARITY_DESC,
        FACTION_ASC,
        FACTION_DESC
    }

    public static class Builder {

        private Faction[] factions;
        private Rarity[] rarities;
        private SortOrder sortOrder = SortOrder.DEFAULT;

        public Builder factions(Faction... factions) {
            this.factions = factions;
            return this;
        }

        public Builder rarities(Rarity... rarities) {
            this.rarities = rarities;
            return this;
        }

        public Builder sortOrder(SortOrder sortOrder){
            this.sortOrder = sortOrder;
            return this;
        }

        public CardFilter build() {
            CardFilter cardFilter = new CardFilter();
            cardFilter.factions = factions;
            cardFilter.rarities = rarities;
            cardFilter.sortOrder = sortOrder;
            return cardFilter;
        }


    }

}
