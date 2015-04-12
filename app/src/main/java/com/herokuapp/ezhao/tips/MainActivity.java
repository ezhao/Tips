package com.herokuapp.ezhao.tips;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.herokuapp.ezhao.tips.fragments.BillEntryFragment;
import com.herokuapp.ezhao.tips.fragments.TipResultFragment;


public class MainActivity extends ActionBarActivity implements BillEntryFragment.BillEntryListener, NumberPickerDialogFragment.NumberPickerDialogHandler {
    private static final int BILL_ENTRY_REFERENCE = 0;
    private FragmentManager fm;
    private NumberPickerBuilder npb;
    private BillEntryFragment billEntryFragment;
    private TipResultFragment tipResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        billEntryFragment = new BillEntryFragment();
        ft.replace(R.id.flFragment, billEntryFragment);
        ft.commit();

        npb = new NumberPickerBuilder()
                .setFragmentManager(fm)
                .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                .setReference(BILL_ENTRY_REFERENCE)
                .setPlusMinusVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBillEntry() {
        npb.show();
    }

    @Override
    public void onBillNext(double billAmount) {
        FragmentTransaction ft = fm.beginTransaction();
        if (tipResultFragment == null) {
            tipResultFragment = new TipResultFragment();
        }
        tipResultFragment.setBill(billAmount);
        ft.replace(R.id.flFragment, tipResultFragment);
        ft.addToBackStack("tipResultFragmentTag");
        ft.commit();
    }

    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
        if (reference == BILL_ENTRY_REFERENCE) {
            billEntryFragment.setBillAmount(fullNumber);
        }
    }
}
