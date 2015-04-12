package com.herokuapp.ezhao.tips.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.herokuapp.ezhao.tips.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BillEntryFragment extends Fragment {
    private BillEntryListener listener;
    private double billAmount;
    @InjectView(R.id.tvBillAmount) TextView tvBillAmount;
    @InjectView(R.id.btnNext) Button btnNext;
    @InjectView(R.id.btnCircle) ImageButton btnCircle;

    public interface BillEntryListener {
        public void onBillEntry();
        public void onBillNext(double billAmount);
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
        setBillAmountViews();
        return view;
    }

    @OnClick({R.id.rlBillEntryPage, R.id.btnCircle})
    public void onBillEntryPageClick(View view) {
        listener.onBillEntry();
    }

    @OnClick(R.id.btnNext)
    public void onBillNextClick(View view) {
        listener.onBillNext(billAmount);
    }

    public void setBillAmount(double newBillAmount) {
        billAmount = newBillAmount;
        setBillAmountViews();
    }

    private void setBillAmountViews() {
        if (billAmount > 0) {
            tvBillAmount.setText(String.format("%.2f", billAmount));
            btnNext.setEnabled(true);
            btnCircle.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setEnabled(false);
            if (btnCircle != null) {
                ObjectAnimator pulseX = ObjectAnimator.ofFloat(btnCircle, "scaleX", 1.0f, 1.2f);
                ObjectAnimator pulseY = ObjectAnimator.ofFloat(btnCircle, "scaleY", 1.0f, 1.2f);
                pulseX.setRepeatMode(ValueAnimator.REVERSE);
                pulseY.setRepeatMode(ValueAnimator.REVERSE);
                pulseX.setRepeatCount(ValueAnimator.INFINITE);
                pulseY.setRepeatCount(ValueAnimator.INFINITE);
                AnimatorSet pulse = new AnimatorSet();
                pulse.setDuration(1000);
                pulse.playTogether(pulseX, pulseY);
                pulse.start();
            }
        }
    }
}
