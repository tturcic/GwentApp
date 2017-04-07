package com.tt.gwentapp.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.di.application.FontManager;

import javax.inject.Inject;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
public class FontTextView extends android.support.v7.widget.AppCompatTextView {

    @Inject FontManager fontManager;

    public FontTextView(Context context) {
        super(context);
        init(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet){
        App.getApp(context).getAppComponent().inject(this);
        if(attributeSet != null){
            TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FontTextView, 0, 0);
            int typeface = a.getInt(R.styleable.FontTextView_font, 0);
            this.setTypeface(fontManager.getTypeface(typeface));
            a.recycle();
        }
    }
}
