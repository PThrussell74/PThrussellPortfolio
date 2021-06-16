package com.example.eeaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.eeaproject.AdminMainActivity;
import com.example.eeaproject.DTO.CurrentOrderSummaryDTO;
import com.example.eeaproject.DTO.ProductDTO;
import com.example.eeaproject.R;
import com.example.eeaproject.UserMainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CurrentListAdapter extends BaseAdapter
{
    ArrayList<CurrentOrderSummaryDTO> theDTOs;

    Context context;
    Context context2;

    LayoutInflater inflater;

    private static class ViewHandler
    {
        Button button;
        Button button2;
    }

    public CurrentListAdapter(Context context, Context context2, ArrayList<CurrentOrderSummaryDTO> theDTOs)
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
        convertView = inflater.inflate(R.layout.list_item_currentorder, null);
        TextView itemText = (TextView)convertView.findViewById(R.id.itemText);
        Button viewButton = (Button)convertView.findViewById(R.id.viewButton);
        Button sendButton = (Button)convertView.findViewById(R.id.sendButton);

        CurrentOrderSummaryDTO theDTO = theDTOs.get(position);
        itemText.setText("ID: "+theDTO.getId()+"\n"+theDTO.getCustomer().getName()+"\n"+theDTO.getOrderdate().getTime());
        viewButton.setTag(theDTO);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AdminMainActivity)context2).viewEntry(v, theDTO.getId());
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AdminMainActivity)context2).sendEntry(v, theDTO.getId());
            }
        });
        return convertView;
    }
}
