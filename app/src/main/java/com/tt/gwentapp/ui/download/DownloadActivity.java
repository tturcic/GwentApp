package com.tt.gwentapp.ui.download;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.di.activity.DaggerDownloadActivityComponent;
import com.tt.gwentapp.di.activity.DownloadActivityModule;
import com.tt.gwentapp.presentation.DownloadPresenter;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.ui.main.MainTabActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class DownloadActivity extends BaseActivity implements DownloadView {

    @BindView(R.id.progressLayout) View progressLayout;
    @BindView(R.id.txtDownloadProgress) TextView txtDownloadProgress;
    @BindView(R.id.txtDownloadInfo) TextView txtDownloadInfo;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @BindView(R.id.txtError) TextView txtError;
    @BindView(R.id.errorLayout) View errorLayout;

    @Inject DownloadPresenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_download;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        DaggerDownloadActivityComponent.builder()
                .appComponent(App.getApp(this).getAppComponent())
                .downloadActivityModule(new DownloadActivityModule(this))
                .build().inject(this);

        presenter.getContent();
    }

    @OnClick(R.id.btnRetry) void onClickRetry(){
        presenter.getContent();
    }

    @Override
    public DownloadPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showHideProgressView(boolean show) {
        progressLayout.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showHideProgressIndicator(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setProgressInfoText(int resId) {
        txtDownloadInfo.setText(resId);
    }

    @Override
    public void showHideErrorView(boolean show) {
        errorLayout.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setErrorText(int resId) {
        progressLayout.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.VISIBLE);
        txtError.setText(resId);
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(this, MainTabActivity.class));
        finish();
    }

}
