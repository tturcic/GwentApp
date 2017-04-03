package com.tt.gwentapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tt.gwentapp.presentation.BasePresenter;

import butterknife.ButterKnife;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BasePresenter getPresenter(){
        return null;
    }

    protected abstract int getLayoutResId();
    protected abstract void setupUI(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutResId() != 0)
            setContentView(getLayoutResId());
        ButterKnife.bind(this);
        setupUI(savedInstanceState);
    }

    @Override
    protected void onStop() {
        if(getPresenter() != null)
            getPresenter().unsubscribe();
        super.onStop();
    }
}
