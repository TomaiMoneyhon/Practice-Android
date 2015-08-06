package com.example.hackeru.customadapterexample;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by hackeru on 30/07/2015.
 */
public class TaxPayerAdapter implements ListAdapter,View.OnClickListener {

    TaxPayer[] taxPayers;
    Context context;

    public TaxPayerAdapter(TaxPayer[] taxPayers, Context context) {
        super();
        this.taxPayers = taxPayers;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return taxPayers.length;
    }

    @Override
    public Object getItem(int i) {
        return taxPayers[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.line,null);
        }
        else {
            root = view;
        }
        TextView name = (TextView)root.findViewById(R.id.line_name);
        TextView assets = (TextView)root.findViewById(R.id.line_equity);
        CheckBox rich = (CheckBox)root.findViewById(R.id.line_checkbox);
        Button taxBtn = (Button)root.findViewById(R.id.line_taxbtn);
        taxBtn.setTag(i);
        taxBtn.setOnClickListener(this);

        TaxPayer currentTP = taxPayers[i];
        name.setText(currentTP.getName());
        double total = currentTP.getCash() + currentTP.getPortfolio() + currentTP.getRealEstate();
        assets.setText(total+"");
        rich.setChecked(total>1000);
        taxBtn.setVisibility(currentTP.isTaxImmune() ? View.INVISIBLE : View.VISIBLE);

        return root;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return taxPayers.length==0;
    }

    @Override
    public void onClick(View view) {
        int index = (Integer)view.getTag();
        TaxPayer payer = taxPayers[index];
        payer.deductTax(0.1);

        LinearLayout linearLayout = (LinearLayout)view.getParent();
        TextView assetsTextView = (TextView)linearLayout.findViewById(R.id.line_equity);
        CheckBox isRichCheckBox = (CheckBox)linearLayout.findViewById(R.id.line_checkbox);
        double assets = payer.getCash() + payer.getRealEstate() + payer.getPortfolio();
        assetsTextView.setText(assets+"");
        isRichCheckBox.setChecked(assets>1000);

    }
}
