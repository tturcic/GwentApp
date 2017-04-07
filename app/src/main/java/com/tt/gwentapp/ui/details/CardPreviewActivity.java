package com.tt.gwentapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.data.remote.RxTransformer;
import com.tt.gwentapp.ui.BaseActivity;
import com.tt.gwentapp.utils.ImageProcessor;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;

/**
 * @author tturcic
 *         \date 7.4.2017.
 */
public class CardPreviewActivity extends BaseActivity {

    private static final String EXTRA_CARD_NAME = "imgName";
    private static final String EXTRA_IMAGE_URL = "imgUrl";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imgCardPreview) ImageView imgCardPreview;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Inject RxTransformer rxTransformer;
    @Inject ImageProcessor imageProcessor;

    private Subscription bmTrimSubscription;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_card_preview;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        App.getApp(this).getAppComponent().inject(this);

        setupToolbar(getIntent().getStringExtra(EXTRA_CARD_NAME));
        String imgUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        if(!TextUtils.isEmpty(imgUrl)){
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(imgUrl)
                    .asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            processImage(resource);
                            return true;
                        }
                    })
                    .into(imgCardPreview);
        }
    }

    @Override
    protected void onDestroy() {
        if(bmTrimSubscription != null && !bmTrimSubscription.isUnsubscribed())
            bmTrimSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(String title){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void processImage(Bitmap original) {
        // Images returned from API have large transparent areas around the image
        // Here we trim some of those transparent pixels
        bmTrimSubscription = imageProcessor.trimBitmap(original)
                .compose(rxTransformer.chainSchedulers())
                .subscribe(bitmap -> imgCardPreview.setImageBitmap(bitmap),
                        throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show(),
                        () -> progressBar.setVisibility(View.GONE));
    }

    public static Intent createCardPreviewActivity(Context context, String cardTitle, String imgUrl){
        Intent intent = new Intent(context, CardPreviewActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL, imgUrl);
        intent.putExtra(EXTRA_CARD_NAME, cardTitle);
        return intent;
    }
}
