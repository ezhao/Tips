package com.herokuapp.ezhao.tips.fragments;

import android.app.Activity;
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
import butterknife.OnClick;

public class BillEntryFragment extends Fragment {
    private BillEntryListener listener;
    @InjectView(R.id.tvBillAmount) TextView tvBillAmount;

    public interface BillEntryListener {
        public void onBillEntry();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (BillEntryListener) activity; // TODO(emily) class cast exception
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_entry, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.rlBillEntryPage)
    public void onBillEntryPageClick(View view) {
        listener.onBillEntry();
    }

    public void setBillAmount(double billAmount) {
        tvBillAmount.setText(String.format("%.2f", billAmount));
    }
}
