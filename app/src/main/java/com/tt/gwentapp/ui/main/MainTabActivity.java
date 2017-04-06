package com.tt.gwentapp.ui.main;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.di.activity.CardActivityComponent;
import com.tt.gwentapp.di.activity.CardActivityModule;
import com.tt.gwentapp.di.activity.DaggerCardActivityComponent;
import com.tt.gwentapp.listeners.ColorChangePageScrollListener;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.ui.adapters.MainTabAdapter;
import com.tt.gwentapp.ui.cards.CardFilterDialog;
import com.tt.gwentapp.ui.cards.CardView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class MainTabActivity extends BaseActivity implements CardView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    private CardActivityComponent component;
    private MainTabAdapter mainTabAdapter;

    @Inject CardPresenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_tabs;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

        component = DaggerCardActivityComponent.builder()
                .appComponent(App.getApp(this).getAppComponent())
                .cardActivityModule(new CardActivityModule(this))
                .build();

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
        if(item.getItemId() == R.id.menu_filter_rarity){
            new CardFilterDialog().show(getSupportFragmentManager(), CardFilterDialog.TAG);
        }
        return false;
    }

    public CardActivityComponent getComponent() {
        return component;
    }

}
