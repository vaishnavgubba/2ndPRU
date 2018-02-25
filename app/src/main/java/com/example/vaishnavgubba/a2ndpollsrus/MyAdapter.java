package com.example.vaishnavgubba.a2ndpollsrus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by vaishnav.gubba on 2/24/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<questionData> mDataset;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter(Context context, ArrayList<questionData> myDataset){
        this.context = context;
        this.mDataset = myDataset;
    }

    public MyAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        questionData q = mDataset.get(position);

        holder.mTextView.setText(q.question);
    }

    public int getItemCount(){
        return mDataset.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView questionTextView;

        public CustomViewHolder(View view){
            super(view);
            this.questionTextView = (TextView) view.findViewById(R.id.myTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    questionData q = mDataset.get(getAdapterPosition());
                    Toast.makeText(context, q.question, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
