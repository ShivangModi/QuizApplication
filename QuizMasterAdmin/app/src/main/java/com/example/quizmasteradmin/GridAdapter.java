package com.example.quizmasteradmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    public int sets = 0;
    private String category;
    private GridListner gridListner;

    public GridAdapter(int sets, String category, GridListner gridListner) {
        this.sets = sets;
        this.gridListner = gridListner;
        this.category = category;
    }


    @Override
    public int getCount() {
        return sets + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);
        } else {
            view = convertView;
        }

        if (position == 0) {
            ((TextView) view.findViewById(R.id.textview)).setText("+");
        } else {
            ((TextView) view.findViewById(R.id.textview)).setText(String.valueOf(position));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    //add code
                    gridListner.addSet();
                } else {
                    Intent questionsIntent = new Intent(parent.getContext(), QuestionsActivity.class);
                    questionsIntent.putExtra("category", category);
                    questionsIntent.putExtra("setNo", position);
                    parent.getContext().startActivity(questionsIntent);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (position != 0) {
                    gridListner.onLongClick(position);
                }

                return false;
            }
        });

        return view;
    }

    public interface GridListner {
        public void addSet();

        void onLongClick(int setNo);
    }
}
