package com.tt.gwentapp.models;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import com.tt.gwentapp.R;

/**
 * @author tturcic
 *         \date 27.3.2017.
 *
 *         Ordering here also affects ordering of tabs.
 *         @see com.tt.gwentapp.ui.adapters.MainTabAdapter
 */
public enum Faction {

    ALL("All", R.color.color_all, R.color.color_all_status_bar,
            R.drawable.faction_neutral, R.drawable.faction_neutral),

    MONSTERS("Monsters", R.color.color_monsters, R.color.color_monsters_status_bar,
            R.drawable.faction_monsters, R.drawable.faction_monsters_large),

    NEUTRAL("Neutral", R.color.color_neutral, R.color.color_neutral_status_bar,
            R.drawable.faction_neutral, R.drawable.faction_neutral),

    NIFLGAARD("Nilfgaard", R.color.color_nilfgaard, R.color.color_nilfgaard_status_bar,
            R.drawable.faction_nilfgaard, R.drawable.faction_nilfgaard_large),

    NORTHERN_REALMS("Northern Realms", R.color.color_northern_realms, R.color.color_northern_realms_status_bar,
            R.drawable.faction_northern_realms, R.drawable.faction_northern_realms_large),

    SCOIA_TAEL("Scoia'Tael", R.color.color_scoia_tael, R.color.color_scoia_tael_status_bar,
            R.drawable.faction_scoiatael, R.drawable.faction_scoiatael_large),

    SKELLIGE("Skellige", R.color.color_skellige, R.color.color_skellige_status_bar,
            R.drawable.faction_skellige, R.drawable.faction_skellige_large);

    Faction(String uiName, int colorTab, int colorStatusBar, int drawableIcon, int drawableIconLarge) {
        this.uiName = uiName;
        this.colorTab = colorTab;
        this.colorStatusBar = colorStatusBar;
        this.drawableIcon = drawableIcon;
        this.drawableIconLarge = drawableIconLarge;
    }

    private String uiName;
    private int colorTab;
    private int colorStatusBar;
    private int drawableIcon;
    private int drawableIconLarge;

    public String getUiName() {
        return uiName;
    }

    @ColorInt
    public int getColorTab() {
        return colorTab;
    }

    @ColorInt
    public int getColorStatusBar() {
        return colorStatusBar;
    }

    @DrawableRes
    public int getDrawableIcon() {
        return drawableIcon;
    }

    @DrawableRes
    public int getDrawableIconLarge() {
        return drawableIconLarge;
    }
}
