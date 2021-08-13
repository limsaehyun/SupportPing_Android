package com.example.supportping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    Context Ctx;
    ArrayList<MainData> arraylist;
    public static int pos;
    String activity;

    public MainAdapter(ArrayList<MainData> arraylist, Context Ctx, String activity) {
        this.arraylist = arraylist;
        this.Ctx = Ctx;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {

        holder.tv_title.setText(arraylist.get(position).getTitle());
        holder.tv_nickname.setText("글쓴이 : " + arraylist.get(position).getNickname());
        holder.tv_place.setText(arraylist.get(position).getPlace());
        holder.tv_mp.setText("인원 : " + arraylist.get(position).getMp());
        holder.tv_id.setText("Feed ID : " + arraylist.get(position).getId());

        holder.btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.equals("HomeActivity")) {
                    pos = Integer.parseInt(arraylist.get(position).getId());
                    v.getContext().startActivity(new Intent(v.getContext(), SeeActivity.class));
                } else if(activity.equals("MyPageActivity")) {
                    pos = Integer.parseInt(arraylist.get(position).getId());
                    v.getContext().startActivity(new Intent(v.getContext(), MyPostActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

//    public void remove(int position) {
//        try {
//            arraylist.remove(position);
//            notifyItemRemoved(position);
//        } catch (IndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        }
//    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected TextView tv_nickname;
        protected TextView tv_mp;
        protected TextView tv_place;
        protected TextView tv_id;
        protected Button btn_check;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            this.tv_place = (TextView) itemView.findViewById(R.id.tv_place);
            this.tv_mp = (TextView) itemView.findViewById(R.id.tv_personnel);
            this.tv_id = (TextView) itemView.findViewById(R.id.tv_ID);
            this.btn_check = (Button) itemView.findViewById(R.id.btn_check);
        }
    }
}
