package com.herokuapp.ezhao.tips.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herokuapp.ezhao.tips.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

public class TipResultFragment extends Fragment {
    @InjectView(R.id.tvBillAmount) TextView tvBillAmount;
    @InjectView(R.id.tvTipAmount) TextView tvTipAmount;
    @InjectView(R.id.tvTipAndBill) TextView tvTipAndBill;
    private double billAmount;
    private double tipPercentage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipPercentage = 0.15;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_result, container, false);
        ButterKnife.inject(this, view);
        setBillTextViews();
        return view;
    }

    public void setBill(double newBillAmount) {
        billAmount = newBillAmount;
        setBillTextViews();
    }

    public void setBillTextViews() {
        if (tvTipAmount != null && tvTipAndBill != null) {
            double tipAmount = billAmount * tipPercentage;
            tvBillAmount.setText(String.format("%.2f", billAmount));
            tvTipAmount.setText(String.format("%.2f", tipAmount));
            tvTipAndBill.setText(String.format("%.2f", tipAmount + billAmount));
        }
    }
}
