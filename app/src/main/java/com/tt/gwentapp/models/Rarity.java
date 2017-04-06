package com.tt.gwentapp.models;

import android.support.annotation.DrawableRes;

import com.tt.gwentapp.R;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public enum Rarity {

    COMMON(R.drawable.ic_rarity_common),

    RARE(R.drawable.ic_rarity_rare),

    EPIC(R.drawable.ic_rarity_epic),

    LEGENDARY(R.drawable.ic_rarity_legendary);

    private int icon;

    Rarity(int iconId){
       this.icon = iconId;
    }

    @DrawableRes
    public int getIcon(){
        return icon;
    }

}

