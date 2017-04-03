package com.tt.gwentapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.gwentapp.presentation.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected abstract int getLayoutResId();
    protected BasePresenter getPresenter(){
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutResId() != 0){
            View root = inflater.inflate(getLayoutResId(), container, false);
            unbinder = ButterKnife.bind(this, root);
            return root;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
