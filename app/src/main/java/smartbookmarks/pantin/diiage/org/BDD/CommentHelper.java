package smartbookmarks.pantin.diiage.org.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import smartbookmarks.pantin.diiage.org.Models.Book;
import smartbookmarks.pantin.diiage.org.Models.Comment;

/**
 * Permet la gestion CRUD des commentaires (table Comment).
 */
public class CommentHelper
{

    /**
     * Obtient le nombre de commentaires en base de données
     * @param helper lien vers la base de données.
     * @return le nombre de commentaires en base de données
     */
    public static long getBookCount(SmartBookmarksDbHelper helper)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, SmartBookmarksDbHelper.NAME_TABLE_COMMENT);
        db.close();

        return count;
    }

    /**
     * Récupère tous les commentaires trié par date descendante.
     * @param helper
     * @return
     */
    public static ArrayList<Comment> getAllComments(SmartBookmarksDbHelper helper)
    {
        // Obtient un connecteur à la base de données
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursorComment = db.query(SmartBookmarksDbHelper.NAME_TABLE_COMMENT,
                new String[]{"id","bookId","page","comment", "date"},
                null,
                null,
                null,
                null,
                "date asc");

        ArrayList<Comment> comments = new ArrayList<>();

        // Parcours du curseur
        // Tant que le curseur peut avancer
        while(cursorComment.moveToNext())
        {
            // Obtient les données des colonnes via leurs indexes
            int idComment = cursorComment.getInt(0);
            int bookId = cursorComment.getInt(1);
            int page = cursorComment.getInt(2);
            String comment = cursorComment.getString(3);
            Date date = new Date(cursorComment.getString(4));

            Comment c = new Comment(idComment, bookId, page, comment, date);
            comments.add(c);
        }

        return comments;
    }

    public static void addComment(SmartBookmarksDbHelper helper, Comment comment)
    {
        // Obtient un connecteur à la base de données
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", comment.getBookId());
        contentValues.put("page", comment.getPage());
        contentValues.put("comment", comment.getComment());
        contentValues.put("date", Calendar.getInstance().getTime().toString());

        db.insert(SmartBookmarksDbHelper.NAME_TABLE_COMMENT, null, contentValues);
    }
}
