package com.tt.gwentapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.di.activity.ActivityModule;
import com.tt.gwentapp.di.activity.CardDetailsModule;
import com.tt.gwentapp.di.activity.DaggerCardDetailsComponent;
import com.tt.gwentapp.presentation.CardDetailsPresenter;
import com.tt.gwentapp.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardDetailsActivity extends BaseActivity implements CardDetailsView {

    public static final String EXTRA_CARD_NAME = "cardName";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imgCardDetails) ImageView imgCardDetails;
    @BindView(R.id.imgFaction1) ImageView imgFaction1;
    @BindView(R.id.imgFaction2) ImageView imgFaction2;
    @BindView(R.id.txtCardName) TextView txtCardName;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.txtCardFlavour) TextView txtCardFlavour;
    @BindView(R.id.txtStrength) TextView txtStrength;
    @BindView(R.id.txtTags) TextView txtTags;
    @BindView(R.id.txtDescription) TextView txtDescription;

    @Inject CardDetailsPresenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_card_details;
    }

    @Override
    public CardDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Card details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DaggerCardDetailsComponent.builder()
                .appComponent(App.getApp(this).getAppComponent())
                .activityModule(new ActivityModule(this))
                .cardDetailsModule(new CardDetailsModule(this))
                .build().inject(this);

        presenter.subscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public static Intent createCardDetailsIntent(Context context, String selectedCardName){
        Intent intent = new Intent(context, CardDetailsActivity.class);
        intent.putExtra(EXTRA_CARD_NAME, selectedCardName);
        return intent;
    }

    @Override
    public void loadImage(String url) {
        progressBar.setVisibility(View.VISIBLE);
         Glide.with(this)
                .load(url)
                 .crossFade()
                 .listener(new RequestListener<String, GlideDrawable>() {
                     @Override
                     public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                         progressBar.setVisibility(View.INVISIBLE);
                         return false;
                     }

                     @Override
                     public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                         progressBar.setVisibility(View.INVISIBLE);
                         return false;
                     }
                 })
                .into(imgCardDetails);
    }

    @Override
    public void setToolbarColor(int colorId) {
        if(getSupportActionBar() != null)
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, colorId)));
    }

    @Override
    public void setStatusBarColor(int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, colorId));
    }

    @Override
    public void setFactionImage(int imgResId) {
        imgFaction1.setImageResource(imgResId);
        imgFaction2.setImageResource(imgResId);
    }

    @Override
    public void setCardName(String name) {
        txtCardName.setText(name);
    }

    @Override
    public void setCardDescription(String desc) {
        txtDescription.setText(desc);
    }

    @Override
    public void setCardFlavour(String flavour) {
        if(TextUtils.isEmpty(flavour))
            txtCardFlavour.setVisibility(View.GONE);
        else
            txtCardFlavour.setText(flavour);
    }

    @Override
    public void setCardStrength(String strength) {
        if(TextUtils.isEmpty(strength))
            txtStrength.setVisibility(View.GONE);
        else
            txtStrength.setText(String.format(getString(R.string.card_details_strength_formatted), strength));
    }

    @Override
    public void setCardTags(String tag) {
        if(TextUtils.isEmpty(tag))
            txtTags.setVisibility(View.GONE);
        else
            txtTags.setText(tag);
    }
}
