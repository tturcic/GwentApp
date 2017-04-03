package com.tt.gwentapp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tt.gwentapp.R;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.ui.cards.CardFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    //@BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CardFragment(), "Cards")
                .commit();
    }
}
