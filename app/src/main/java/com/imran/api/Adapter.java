package com.imran.api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder>
{
    private List<Getdata> dataClass;

    public Adapter(List<Getdata> dataClass) {
        this.dataClass = dataClass;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext( )).inflate(R.layout.card, viewGroup, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position)
    {
        String Uid = dataClass.get(position).getUid();
        String Name=dataClass.get(position).getName();
        String Address=dataClass.get(position).getAddress();
        holder.setData(Uid,Name,Address);
    }

    @Override
    public int getItemCount() {
        return dataClass.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            textView1= itemView.findViewById(R.id.tv1);
            textView2= itemView.findViewById(R.id.tv2);
            textView3= itemView.findViewById(R.id.tv3);
        }
        private void  setData(String uid,String name,String address){
            textView1.setText("UID:-"+uid);
            textView2.setText("NAME:-"+name);
            textView3.setText("ADDRESS:-"+address);
        }
    }
}
