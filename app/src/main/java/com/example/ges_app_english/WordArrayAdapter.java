package com.example.ges_app_english;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WordArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public WordArrayAdapter(Context context, String[] values) {
        super(context, R.layout.word, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        ViewHolder viewHolder;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.word, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = rowView.findViewById(R.id.txt);
            viewHolder.imageView = rowView.findViewById(R.id.logo);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.textView.setText(values[position]);
        viewHolder.imageView.setImageResource(R.drawable.speaker);

        return rowView;
    }

    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}