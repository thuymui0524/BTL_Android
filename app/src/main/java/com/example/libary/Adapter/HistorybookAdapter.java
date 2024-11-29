package com.example.libary.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libary.BookDetailActivity;
import com.example.libary.ClassAdapterHistory;
import com.example.libary.R;

import java.util.ArrayList;

public class HistorybookAdapter extends RecyclerView.Adapter<HistorybookAdapter.HistoryViewHolder> {
    private Context context;
    private ArrayList<ClassAdapterHistory> bookList;

    public HistorybookAdapter(Context context,
                              ArrayList<ClassAdapterHistory> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_read, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder,
                                 int position)
    {
        ClassAdapterHistory classAdapterHistory = bookList.get(position);
        holder.img.setImageResource(classAdapterHistory.getImg());
        holder.time.setText(classAdapterHistory.getTime());
        holder.nameb.setText(classAdapterHistory.getNameBook());

        // set lệnh click vào hình ảnh
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("id_book", classAdapterHistory.getId_book());
                intent.putExtra("title", classAdapterHistory.getNameBook());
                intent.putExtra("imageResourceId",classAdapterHistory.getImg());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameb, time;
        ImageView img;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameb = itemView.findViewById(R.id.namebook);
            time = itemView.findViewById(R.id.times);
            img=itemView.findViewById(R.id.imghtr);
        }
    }
}
