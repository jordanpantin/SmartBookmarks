package smartbookmarks.pantin.diiage.org.BDD;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.Models.Book;

/**
 * Permet la gestion CRUD des livres (table Book).
 */
public class BookHelper
{
    /**
     * Obtient le nombre de livres en base de données
     * @param helper la base de données initialisées.
     * @return le nombre de livres en base de données
     */
    public static long getBookCount(SmartBookmarksDbHelper helper)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, SmartBookmarksDbHelper.NAME_TABLE_BOOK);
        db.close();

        return count;
    }

    /**
     * Obtient tous les livres de la base de données trié selon le nom.
     * @param helper la base de données initialisées
     * @return Tous les livres de la base de données.
     */
    public static ArrayList<Book> getAllBooks(SmartBookmarksDbHelper helper)
    {
        // Obtient un connecteur à la base de données
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursorBook = db.query(SmartBookmarksDbHelper.NAME_TABLE_BOOK,
                new String[]{"id","title","author","genre"},
                null,
                null,
                null,
                null,
                "title asc",
                null);

        ArrayList<Book> books = new ArrayList<>();

        // Parcours du curseur
        // Tant que le curseur peut avancer
        while(cursorBook.moveToNext())
        {
            // Obtient les données des colonnes via leurs indexes
            int idBook = cursorBook.getInt(0);
            String title = cursorBook.getString(1);
            String author = cursorBook.getString(2);
            String genre = cursorBook.getString(3);

            Book book = new Book(idBook, title, author, genre);
            books.add(book);
        }

        return books;
    }

    /**
     * Retourne un livre selon son identifiant
     * @param helper la base de données initialisées
     * @param idBook identifiant du livre recherché
     * @return un livre
     */
    public static Book getBookById (SmartBookmarksDbHelper helper, int idBook)
    {
        // Obtient un connecteur à la base de données
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursorBook = db.query(SmartBookmarksDbHelper.NAME_TABLE_BOOK,
                new String[]{"id","title","author","genre"},
                "id =?",
                new String[]{String.valueOf(idBook)},
                null,
                null,
                "title asc",
                null);

        // Le premier élément du curseur
        cursorBook.moveToFirst();

        // Obtient les données du livre
        int id = cursorBook.getInt(0);
        String title = cursorBook.getString(1);
        String author = cursorBook.getString(2);
        String genre = cursorBook.getString(3);

        return new Book(id, title, author, genre);
    }
}
