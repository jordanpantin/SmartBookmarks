package smartbookmarks.pantin.diiage.org.BDD;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SmartBookmarksDbHelper extends SQLiteOpenHelper
{
    private static final String SMARTBOOKMARKS_DB = "smartBookmarks.db";
    public static final int VERSION = 2;

    public static final String NAME_TABLE_BOOK = "Books";
    public static final String NAME_TABLE_COMMENT = "Comments";

    public SmartBookmarksDbHelper(Context context) {
        super(context, SMARTBOOKMARKS_DB, null, VERSION);
    }

    /**
     * Si la base n'es pas déjà créée.
     */
   @Override
    public void onCreate(SQLiteDatabase db)
   {
        // Création de la table des livres
        db.execSQL("CREATE TABLE "+ NAME_TABLE_BOOK + " (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , title TEXT NOT NULL , author TEXT NOT NULL , genre TEXT NOT NULL );");

       // Création de la table des commentaires
       db.execSQL("CREATE TABLE "+ NAME_TABLE_COMMENT + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , bookId INTEGER NOT NULL, page INTEGER NOT NULL , comment TEXT NOT NULL , date DATETIME NOT NULL );");

        // Insert des data en base dans la table books
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(2,'Germinal','Emile Zola','Roman');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(3,'Les misérables','Victor Hugo','Roman');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(4,'1984','George Orwell','Science-Fiction');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure');");
        db.execSQL("INSERT INTO " + NAME_TABLE_BOOK + " VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure');");

        // Pour la version 2
       // Ajout d'une table contenant les auteurs des livres.
       db.execSQL("CREATE TABLE Authors (" +
               "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
               "nom TEXT NOT NULL , " +
               "DateNaissance DATETIME);");

       // Ajout d'une colonne dans la table book.
       db.execSQL("ALTER TABLE " + NAME_TABLE_BOOK + " ADD COLUMN authorId int;");
   }

    /**
     * Mise à jour de la base lorsque la version est trop vieilles.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion == 1)
        {
            // Ajout d'une table contenant les auteurs des livres.
            db.execSQL("CREATE TABLE Authors (" +
                    "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
                    "nom TEXT NOT NULL , " +
                    "DateNaissance DATETIME);");

           // Ajout d'une colonne dans la table book.
           db.execSQL("ALTER TABLE " + NAME_TABLE_BOOK + " ADD COLUMN authorId int;");
        }
    }
}
