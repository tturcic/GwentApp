package com.tt.gwentapp.listeners;

import android.view.View;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public interface ItemClickListener<T> {

    void onItemClicked(T item, int position, View v);
}
