package com.example.libary.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.libary.Book;
import com.example.libary.BookDetailActivity;
import com.example.libary.BorrowBookActivity;
import com.example.libary.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;

//    public BookAdapter(ArrayList<Book> bookList) {
//        this.bookList = bookList;
//        this.mbookListOld=bookList;
//    }
    private ArrayList<Book> bookList;
    private ArrayList<Book> mbookListOld;

    public BookAdapter(Context context, ArrayList<Book> bookList ) {
        this.context = context;
        this.bookList = bookList;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new BookViewHolder(view);
    }
//ham nay dung de load du lieu cho BookDeilActivity
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.imageView.setImageResource(book.getImageId());
        holder.titleTextView.setText(book.getTitle());

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("imageResourceId", book.getImageId());
            // chuyen id lay tu co so du lieu
            intent.putExtra("id_book", book.getId_book());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
        public void bind(final Book book) {
            titleTextView.setText(book.getTitle());
            // Giả sử bạn đã load ảnh cho `coverImageView`

            imageView.setOnClickListener(v -> {
                // Chuyển sang `BookDetailActivity` với ID của sách
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("title", book.getTitle());// truyền ID sách sang activity chi tiết
                // chuyen id thật
                intent.putExtra("id_book", book.getId_book());
                context.startActivity(intent);
            });
        }
    }
//    public Filter getFilter(){
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String strSearch=charSequence.toString();
//                if(strSearch.isEmpty()){
//                    bookList=mbookListOld;
//                }else{
//                    List<Book> list=new ArrayList<>();
//                    for(Book book : mbookListOld){
//                        if(book.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
//                            list.add(book);
//                        }
//                    }
//                    bookList = (ArrayList<Book>) list;
//                }
//                FilterResults filterResults=new FilterResults();
//                filterResults.values=bookList;
//
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                bookList = (ArrayList<Book>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


}
