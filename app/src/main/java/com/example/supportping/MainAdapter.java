package com.example.supportping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    Context Ctx;
    ArrayList<MainData> arraylist;

    public MainAdapter(ArrayList<MainData> arraylist, Context Ctx) { // 생성자
        this.arraylist = arraylist;
        this.Ctx = Ctx;
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
        holder.tv_nickname.setText(arraylist.get(position).getNickname());
        holder.tv_personnel.setText(arraylist.get(position).getPersonnel());
        holder.tv_place.setText(arraylist.get(position).getPlace());
        holder.tv_price.setText(arraylist.get(position).getPrice());
        holder.tv_personnel.setText(arraylist.get(position).getPersonnel());

        holder.btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현명히 뷰 자세히 보기
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public void remove(int position) {
        try {
            arraylist.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected TextView tv_nickname;
        protected TextView tv_personnel;
        protected TextView tv_place;
        protected TextView tv_price;
        protected Button btn_check;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            this.tv_personnel = (TextView) itemView.findViewById(R.id.tv_personnel);
            this.tv_place = (TextView) itemView.findViewById(R.id.tv_place);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.btn_check = (Button) itemView.findViewById(R.id.btn_check);
        }
    }
}
