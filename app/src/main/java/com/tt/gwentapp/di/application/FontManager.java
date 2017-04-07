package com.tt.gwentapp.di.application;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
public class FontManager {

    private SparseArray<Typeface> fonts = new SparseArray<>();
    private AssetManager assetManager;

    public FontManager(Context context){
        assetManager = context.getAssets();
    }

    public Typeface getTypeface(int typeface){
        if(fonts.valueAt(typeface) == null){
            fonts.put(typeface, createTypeface(typeface));
        }
        return fonts.get(typeface);
    }

    public void clearCache(){
        fonts.clear();
    }

    private Typeface createTypeface(int typeface){
        switch (typeface){
            case 1:
                default:
                    return Typeface.createFromAsset(assetManager, "fonts/MORPHEUS.TTF");
        }
    }
}
