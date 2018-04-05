package smartbookmarks.pantin.diiage.org.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.BDD.BookHelper;
import smartbookmarks.pantin.diiage.org.BDD.SmartBookmarksDbHelper;
import smartbookmarks.pantin.diiage.org.Models.Book;
import smartbookmarks.pantin.diiage.org.Models.Comment;
import smartbookmarks.pantin.diiage.org.R;

public class CommentAdapter extends BaseAdapter {
    ArrayList<Comment> comments;
    Activity activity;

    public CommentAdapter(Activity activity, ArrayList<Comment> comments){
        this.activity = activity;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
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
            view = inflater.inflate(R.layout.item_comment, viewGroup, false);

            holder = new ViewHolder();
            //holder.txtDate = (TextView)view.findViewById(R.id.txtDate);
            holder.txtBookTitle = (TextView)view.findViewById(R.id.txtBookTitle);
            holder.txtComment = (TextView)view.findViewById(R.id.txtComment);
            holder.txtDateComment = (TextView)view.findViewById(R.id.txtDateComment);
            holder.txtPageNumber = (TextView)view.findViewById(R.id.txtPageNumber);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Comment comment = (Comment)getItem(i);

        if (comment != null){

            // Obtient le livre associé au commentaire.
            SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this.activity);
            Book book = BookHelper.getBookById(helper, comment.getBookId());

            //holder.txtDate.setText(score.);
            holder.txtComment.setText(String.valueOf(comment.getComment()));
            holder.txtBookTitle.setText(book.getTitle());
            holder.txtPageNumber.setText(String.valueOf(comment.getPage()));
            holder.txtDateComment.setText(comment.getDate().toString());
        }

        return view;
    }

    private static class ViewHolder {
        //public TextView txtDate;
        public TextView txtComment;
        public TextView txtBookTitle;
        public TextView txtPageNumber;
        public TextView txtDateComment;
    }
}
