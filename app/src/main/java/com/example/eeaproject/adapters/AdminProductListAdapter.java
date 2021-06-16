package com.example.eeaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaproject.AdminMainActivity;
import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.R;

import java.util.ArrayList;

public class AdminProductListAdapter extends BaseAdapter
{
    ArrayList<ProductDTO> theDTOs;

    Context context;
    Context context2;

    LayoutInflater inflater;

    private static class ViewHandler
    {
        Button button;
    }

    public AdminProductListAdapter(Context context, Context context2, ArrayList<ProductDTO> theDTOs)
    {
        this.context = context;
        this.context2 = context2;
        this.theDTOs = theDTOs;
        inflater = (LayoutInflater.from(context));

    }



    @Override
    public int getCount() {
        return theDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return theDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return theDTOs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final ViewHandler theHandler = new ViewHandler();
        convertView = inflater.inflate(R.layout.list_item_admin, null);
        TextView itemText = (TextView)convertView.findViewById(R.id.itemText);
        Button changePrice = (Button)convertView.findViewById(R.id.changePriceButton);
        EditText enterText = (EditText)convertView.findViewById(R.id.changePriceText);

        ProductDTO theDTO = theDTOs.get(position);
        itemText.setText(theDTO.getName()+" - £"+theDTO.getUnitprice());
        changePrice.setTag(theDTO);

        changePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get = enterText.getText().toString();

                if (get.equals(""))
                {
                    ((AdminMainActivity)context2).changePrice(v, 0, theDTO.getId());
                }
                else
                {
                    double price = Double.parseDouble(enterText.getText().toString());
                    ((AdminMainActivity)context2).changePrice(v, price, theDTO.getId());
                }

            }
        });
        return convertView;
    }
}
