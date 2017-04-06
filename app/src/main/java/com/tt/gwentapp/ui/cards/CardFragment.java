package com.tt.gwentapp.ui.cards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.tt.gwentapp.R;
import com.tt.gwentapp.listeners.ItemClickListener;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.models.Faction;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.BaseFragment;
import com.tt.gwentapp.ui.adapters.CardHorizontalAdapter;
import com.tt.gwentapp.ui.custom_views.EmptyRecyclerView;
import com.tt.gwentapp.ui.details.CardDetailsActivity;
import com.tt.gwentapp.ui.main.MainTabActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public class CardFragment extends BaseFragment implements ItemClickListener<Card>, CardListView {

    private static final String KEY_FACTION = "faction";

    @BindView(R.id.recyclerView) EmptyRecyclerView recyclerView;
    @BindView(R.id.txtEmptyList) TextView txtEmptyList;

    @Inject CardPresenter presenter;
    private Faction faction;
    private CardHorizontalAdapter adapter;

    public static CardFragment newInstance(Faction faction) {
        Bundle args = new Bundle();
        args.putInt(KEY_FACTION, faction.ordinal());
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
        ((MainTabActivity)getActivity()).getComponent().inject(this);

        faction = Faction.values()[getArguments().getInt(KEY_FACTION)];

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setEmptyView(txtEmptyList);
        recyclerView.setAdapter(adapter = new CardHorizontalAdapter(this));

        presenter.registerCardListView(this);
    }

    @Override
    public void onDestroyView() {
        presenter.unregisterCardListView(this);
        super.onDestroyView();
    }

    @Override
    public void onItemClicked(Card item, int position, View v) {
        getActivity().startActivity(CardDetailsActivity.createCardDetailsIntent(getActivity(), item.getName()));
    }


    @Override
    public Faction getFaction() {
        return faction;
    }

    @Override
    public void setCards(List<Card> cards) {
        adapter.updateCards(cards);
    }
}
