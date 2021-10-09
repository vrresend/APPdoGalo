package com.viniciusapp.appdamassa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class myadapterer  extends RecyclerView.Adapter<myadapterer.myviewholder>
{
    ArrayList<model> datalist;
    Context context;
    private RecyclerItemClick itemClick;

    public myadapterer(Context context, ArrayList<model> datalist, RecyclerItemClick itemClick) {
        this.datalist = datalist;
        this.context = context;
        this.itemClick =itemClick;

    }

    @NonNull
    @Override
    public myadapterer.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        model user =datalist.get(position);

        holder.txtnome.setText(datalist.get(position).getNome());
        Glide.with(context).load(datalist.get(position).getImagem()).into(holder.walpeper);


            


        holder.dwloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(user);
            }
        });



    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView txtnome;
        ImageView walpeper;
        Button dwloadbtn;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txtnome = itemView.findViewById(R.id.txtnome);
            walpeper = itemView.findViewById(R.id.walpeper);
            dwloadbtn =itemView.findViewById(R.id.dwloadbtn);


        }

    }

    public interface RecyclerItemClick{

        void itemClick(model item);


    }

}