package com.tt.gwentapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.presentation.CardDetailsPresenter;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.utils.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class CardDetailsActivity extends BaseActivity implements CardDetailsView {

    private static final String EXTRA_CARD_NAME = "cardName";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imgCardDetails) ImageView imgCardDetails;
    @BindView(R.id.imgFaction1) ImageView imgFaction1;
    @BindView(R.id.imgFaction2) ImageView imgFaction2;
    @BindView(R.id.txtCardName) TextView txtCardName;
    @BindView(R.id.txtCardFlavour) TextView txtCardFlavour;

    @Inject Realm realm;
    private CardDetailsPresenter presenter;

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
        App.getApp(this).getAppComponent().inject(this);
        presenter = new CardDetailsPresenter(this, realm, getIntent().getStringExtra(EXTRA_CARD_NAME));
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
         Glide.with(this)
                .load(url)
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

    }

    @Override
    public void setCardFlavour(String flavour) {
        txtCardFlavour.setText(flavour);
    }
}
