package com.tt.gwentapp.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tt.gwentapp.App;
import com.tt.gwentapp.R;
import com.tt.gwentapp.listeners.ItemClickListener;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.models.Rarity;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.BaseFragment;
import com.tt.gwentapp.ui.adapters.CardHorizontalAdapter;
import com.tt.gwentapp.ui.custom_views.EmptyRecyclerView;
import com.tt.gwentapp.ui.details.CardDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public class CardFragment extends BaseFragment implements ItemClickListener<Card>, CardView {

    @BindView(R.id.recyclerView) EmptyRecyclerView recyclerView;
    @BindView(R.id.txtEmptyList) TextView txtEmptyList;

    @Inject Realm realm;

    private CardPresenter presenter;
    private CardHorizontalAdapter adapter;

    public static CardFragment newInstance(Faction faction) {
        Bundle args = new Bundle();
        args.putInt("faction", faction.ordinal());
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_cards;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.getApp(getActivity()).getAppComponent().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setEmptyView(txtEmptyList);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter = new CardHorizontalAdapter(this));
        presenter = new CardPresenter(this, realm);
        showCardsForFaction(Faction.values()[getArguments().getInt("faction")]);
    }

    @Override
    public void onItemClicked(Card item, int position, View v) {
        Log.d("TTT", item.getName());
        getActivity().startActivity(CardDetailsActivity.createCardDetailsIntent(getActivity(), item.getName()));
    }

    @Override
    public void showCards(List<Card> cards) {
        adapter.updateCards(cards);
    }

    public void showCardsForFaction(Faction faction){
        presenter.getCards(faction);
    }

    public void onRarityCommonCheckedChanged(boolean checked){
        Log.d("TTT", "onRarityCommonCheckedChanged - " + checked);
    }

    public void onRarityRareCheckedChanged(boolean checked){

    }

    public void onRarityEpicCheckedChanged(boolean checked){

    }

    public void onRarityLegendaryCheckedChanged(boolean checked){

    }

}
