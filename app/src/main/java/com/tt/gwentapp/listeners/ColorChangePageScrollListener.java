package com.tt.gwentapp.listeners;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public class ColorChangePageScrollListener implements ViewPager.OnPageChangeListener {

    private final OnColorChangeListener listener;
    private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private int[] tabColors, statusBarColors;

    public ColorChangePageScrollListener(int[] tabColors, int[] statusBarColor, OnColorChangeListener listener) {
        this.tabColors = tabColors;
        this.statusBarColors = statusBarColor;
        this.listener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position < tabColors.length - 1) {
            int tabColor = (int) argbEvaluator.evaluate(positionOffset, tabColors[position], tabColors[position + 1]);
            int statusBar = (int) argbEvaluator.evaluate(positionOffset, statusBarColors[position], statusBarColors[position + 1]);
            listener.onColorChanged(tabColor, statusBar);
        }
    }

    @Override
    public void onPageSelected(int position) {
        // Override if needed
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Override if needed
    }

    public interface OnColorChangeListener {

        void onColorChanged(int tabColor, int statusBarColor);
    }
}
