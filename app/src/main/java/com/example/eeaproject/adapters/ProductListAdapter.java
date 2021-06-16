package com.example.eeaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.R;
import com.example.eeaproject.UserMainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter
{
    ArrayList<ProductDTO> theDTOs;

    Context context;
    Context context2;
    ArrayList<Integer> values;
    ArrayList<Integer> listAppearanceID;
    int globalCounter;

    LayoutInflater inflater;

    private static class ViewHandler
    {
        Button button;
    }

    public ProductListAdapter(Context context, Context context2, ArrayList<ProductDTO> theDTOs)
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
        convertView = inflater.inflate(R.layout.list_item, null);
        TextView itemText = (TextView)convertView.findViewById(R.id.itemText);
        Button addItem = (Button)convertView.findViewById(R.id.addCart);
        Button removeItem = (Button)convertView.findViewById(R.id.removeCart);
        TextView smallNum = (TextView)convertView.findViewById(R.id.itemCount);

        ProductDTO theDTO = theDTOs.get(position);
        itemText.setText(theDTO.getName()+" - £"+theDTO.getUnitprice());
        addItem.setTag(theDTO);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(smallNum.getText().toString());
                value = value + 1;
                smallNum.setText(String.valueOf(value));
                ((UserMainActivity)context2).addToCart(v);
            }
        });

        removeItem.setTag(theDTO);
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(smallNum.getText().toString());
                if (value!= 0)
                {
                    value = value - 1;
                    smallNum.setText(String.valueOf(value));
                    ((UserMainActivity)context2).removeFromCart(v);
                }

            }
        });

        return convertView;
    }
}
