package smartbookmarks.pantin.diiage.org.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.Models.Book;
import smartbookmarks.pantin.diiage.org.R;

public class SpinnerBookAdapter extends BaseAdapter {
    ArrayList<Book> books;
    Activity activity;

    public SpinnerBookAdapter(Activity activity, ArrayList<Book> books){
        this.activity = activity;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            LayoutInflater inflater = this.activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_book_spinner, viewGroup, false);

            holder = new ViewHolder();
            holder.txtBookTitle = (TextView)view.findViewById(R.id.txtBookTitle);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Book book = (Book)getItem(i);

        if (book != null){
            holder.txtBookTitle.setText(book.getTitle());
        }

        return view;
    }

    private static class ViewHolder {
        public TextView txtBookTitle;
    }
}