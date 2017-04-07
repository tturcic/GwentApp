package com.tt.gwentapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tt.gwentapp.R;
import com.tt.gwentapp.ui.BaseActivity;

import butterknife.BindView;

/**
 * @author tturcic
 *         \date 7.4.2017.
 */
public class CardPreviewActivity extends BaseActivity {

    private static final String EXTRA_IMAGE_URL = "imgUrl";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imgCardPreview) ImageView imgCardPreview;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_card_preview;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {


        String imgUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(this)
                    .load(imgUrl)
                    .asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imgCardPreview);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.card_preview_toolbar_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent createCardPreviewActivity(Context context, String imgUrl){
        Intent intent = new Intent(context, CardPreviewActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL, imgUrl);
        return intent;
    }
}
