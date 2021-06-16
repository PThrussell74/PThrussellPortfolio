package com.example.eeaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.eeaproject.DTO.FulfilledOrderDTO;
import com.example.eeaproject.DTO.FulfilledOrderlineDTO;
import com.example.eeaproject.DTO.OrderlineDTO;
import com.example.eeaproject.R;

import java.util.ArrayList;

public class FulfilledDetailsAdapter extends BaseAdapter
{
    ArrayList<FulfilledOrderlineDTO> theDTOs;

    Context context;
    Context context2;

    LayoutInflater inflater;

    private static class ViewHandler
    {
        Button button;
        Button button2;
    }

    public FulfilledDetailsAdapter(Context context, Context context2, ArrayList<FulfilledOrderlineDTO> theDTOs)
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
        convertView = inflater.inflate(R.layout.list_justtext, null);
        TextView itemText = (TextView)convertView.findViewById(R.id.itemText);

        FulfilledOrderlineDTO theDTO = theDTOs.get(position);
        Double combined = ((theDTO.getQuantity()*theDTO.getUnitprice())/100)*100;
        itemText.setText("Orderline ID: "+theDTO.getId()+"\n"+theDTO.getQuantity()+" x "+theDTO.getProductName()+"\n£"+theDTO.getUnitprice()+" each\n Total: "+combined);

        return convertView;
    }
}
