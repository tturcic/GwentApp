package com.tt.gwentapp.ui.main;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import com.tt.gwentapp.R;
import com.tt.gwentapp.listeners.ColorChangePageScrollListener;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.ui.adapters.MainTabAdapter;
import com.tt.gwentapp.ui.cards.CardFragment;

import butterknife.BindView;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class MainTabActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    private MainTabAdapter mainTabAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_tabs;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        viewPager.setAdapter(mainTabAdapter = new MainTabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        int[] tabColors = new int[Faction.values().length];
        int[] statusBarColors = new int[Faction.values().length];
        for(int i = 0; i < Faction.values().length; i++){
            tabColors[i] = ContextCompat.getColor(this, Faction.values()[i].getColorTab());
            statusBarColors[i] = ContextCompat.getColor(this, Faction.values()[i].getColorStatusBar());
        }
        viewPager.addOnPageChangeListener(new ColorChangePageScrollListener(tabColors, statusBarColors, (tabColor, statusBarColor) -> {
            if (getSupportActionBar() != null)
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(tabColor));
            tabLayout.setBackgroundColor(tabColor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                getWindow().setStatusBarColor(statusBarColor);
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());
        SparseArray<CardFragment> cardsFragments = mainTabAdapter.getCardFragments();
        switch (item.getItemId()){
            case R.id.menuCheckCommon:
                for(int i = 0; i <cardsFragments.size(); i++) {
                    CardFragment fragment  = cardsFragments.valueAt(i);
                    if(fragment != null)
                        fragment.onRarityCommonCheckedChanged(item.isChecked());
                }
                break;
            case R.id.menuCheckRare:
                for(int i = 0; i <cardsFragments.size(); i++) {
                    CardFragment fragment = cardsFragments.valueAt(i);
                    if (fragment != null)
                        fragment.onRarityRareCheckedChanged(item.isChecked());
                }
                break;
            case R.id.menuCheckEpic:
                for(int i = 0; i <cardsFragments.size(); i++) {
                    CardFragment fragment = cardsFragments.valueAt(i);
                    if (fragment != null)
                        fragment.onRarityEpicCheckedChanged(item.isChecked());
                }
                break;
            case R.id.menuCheckLegendary:
                for(int i = 0; i <cardsFragments.size(); i++) {
                    CardFragment fragment = cardsFragments.valueAt(i);
                    if (fragment != null)
                        fragment.onRarityLegendaryCheckedChanged(item.isChecked());
                }
                break;
        }
        return false;
    }
}