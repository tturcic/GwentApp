package com.tt.gwentapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tt.gwentapp.R;
import com.tt.gwentapp.listeners.ItemClickListener;
import com.tt.gwentapp.models.Card;
import com.tt.gwentapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author tturcic
 *         \date 26.3.2017.
 */
public class CardHorizontalAdapter extends RecyclerView.Adapter<CardHorizontalAdapter.ViewHolder> {

    private List<Card> cards = new ArrayList<>();
    private ItemClickListener<Card> listener;

    public CardHorizontalAdapter(ItemClickListener<Card> listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder vh = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_horizontal, parent, false));
        vh.itemView.setOnClickListener(v -> listener.onItemClicked(cards.get(vh.getAdapterPosition()), vh.getAdapterPosition(), v));
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void updateCards(List<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCard) ImageView imgCard;
        @BindView(R.id.txtCardDescription) TextView txtCardDescription;
        @BindView(R.id.txtCardName) TextView txtCardName;
        @BindView(R.id.imgRarity) ImageView imgRarity;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Card card) {
            Glide.with(itemView.getContext())
                    .load(card.getImage())
                    .placeholder(card.getFactionEnum().getDrawableIcon())
                    .error(card.getFactionEnum().getDrawableIcon())
                    .into(imgCard);

            imgRarity.setImageResource(card.getRarityEnum().getIcon());

            txtCardDescription.setText(card.getText());
            txtCardName.setText(card.getName());
        }
    }
}
