package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


public class ToDoAdapter extends BaseAdapter {

    private List<Item> data;


    public ToDoAdapter(List<Item> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckBox checkBoxDone;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            checkBoxDone = (CheckBox) convertView.findViewById(R.id.checkBoxDone);

            checkBoxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.get((Integer) buttonView.getTag()).setStatus(isChecked);
                }
            });

        }

        TextView textViewListItem = (TextView) convertView.findViewById(R.id.textViewListItem);
        checkBoxDone = (CheckBox) convertView.findViewById(R.id.checkBoxDone);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);


        textViewListItem.setText(data.get(position).getItem());
        checkBoxDone.setTag(position);
        checkBoxDone.setChecked(data.get(position).isStatus());
        textViewDate.setText(data.get(position).getDate() + " "+ data.get(position).getTime());

        return convertView;
    }

    public void add(Item item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void addData(List<Item> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Item> getData(){
        return data;
    }
}
