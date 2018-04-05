package smartbookmarks.pantin.diiage.org;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.Adapter.CommentAdapter;
import smartbookmarks.pantin.diiage.org.BDD.CommentHelper;
import smartbookmarks.pantin.diiage.org.BDD.SmartBookmarksDbHelper;
import smartbookmarks.pantin.diiage.org.Models.Comment;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);

        // La liste de tous les commentaires
        ArrayList<Comment> comments = CommentHelper.getAllComments(helper);
        ListView listViewComments = findViewById(R.id.lvComments);
        listViewComments.setAdapter(new CommentAdapter(this, comments));
    }
}
