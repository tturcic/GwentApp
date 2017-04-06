package com.tt.gwentapp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.di.activity.CardActivityComponent;
import com.tt.gwentapp.di.activity.CardActivityModule;
import com.tt.gwentapp.di.activity.DaggerCardActivityComponent;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.ui.cards.CardFragment;
import com.tt.gwentapp.ui.cards.CardView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements CardView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    //@BindView(R.id.progressBar) ProgressBar progressBar;

    //@Inject CardPresenter cardPresenter;
    private CardActivityComponent component;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

        component = DaggerCardActivityComponent.builder()
                .appComponent(App.getApp(this).getAppComponent())
                .cardActivityModule(new CardActivityModule(this))
                .build();
        component.inject(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CardFragment(), "Cards")
                .commit();
    }

    public CardActivityComponent getComponent() {
        return component;
    }

}
