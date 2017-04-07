package com.tt.gwentapp.ui.cards;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;

import com.tt.gwentapp.R;
import com.tt.gwentapp.presentation.CardPresenter;
import com.tt.gwentapp.ui.main.MainTabActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author tturcic
 *         \date 5.4.2017.
 *
 *         TODO - create a separate presenter for creating card filters
 */
public class CardFilterDialog extends DialogFragment {

    public static final String TAG = "CardFilterDialog";

    private Unbinder unbinder;

    @BindView(R.id.chkBoxRarityCommon) CheckBox chkBoxRarityCommon;
    @BindView(R.id.chkBoxRarityRare) CheckBox chkBoxRarityRare;
    @BindView(R.id.chkBoxRarityEpic) CheckBox chkBoxRarityEpic;
    @BindView(R.id.chkBoxRarityLegendary) CheckBox chkBoxRarityLegendary;

    @Inject CardPresenter cardPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainTabActivity)getActivity()).getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        View view = View.inflate(getActivity(), R.layout.dialog_card_filter, null);
        unbinder = ButterKnife.bind(this, view);

        chkBoxRarityCommon.setChecked(cardPresenter.isRarityCommonChecked());
        chkBoxRarityRare.setChecked(cardPresenter.isRarityRareChecked());
        chkBoxRarityEpic.setChecked(cardPresenter.isRarityEpicChecked());
        chkBoxRarityLegendary.setChecked(cardPresenter.isRarityLegendaryChecked());

        b.setView(view)
                .setPositiveButton(android.R.string.ok, (dialog, which) ->
                        cardPresenter.onRaritiesSelected(chkBoxRarityCommon.isChecked(), chkBoxRarityRare.isChecked(),
                                chkBoxRarityEpic.isChecked(), chkBoxRarityLegendary.isChecked()))
                .setNegativeButton(android.R.string.cancel, null);

        return b.create();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
