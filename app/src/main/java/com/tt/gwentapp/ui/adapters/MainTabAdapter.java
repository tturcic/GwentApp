package com.tt.gwentapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.ui.cards.CardFragment;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class MainTabAdapter extends FragmentPagerAdapter {

    private final SparseArray<CardFragment> cardFragments = new SparseArray<>();

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(Faction.values()[position]);
    }

    @Override
    public int getCount() {
        return Faction.values().length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CardFragment fragment = (CardFragment) super.instantiateItem(container, position);
        cardFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        cardFragments.remove(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Faction.values()[position].getUiName();
    }

    public SparseArray<CardFragment> getCardFragments(){
        return cardFragments;
    }

    public CardFragment getCardFragment(int pos){
        return cardFragments.get(pos);
    }

}
