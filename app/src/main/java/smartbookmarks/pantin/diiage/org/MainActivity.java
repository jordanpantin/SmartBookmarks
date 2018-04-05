package smartbookmarks.pantin.diiage.org;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.BDD.BookHelper;
import smartbookmarks.pantin.diiage.org.BDD.CommentHelper;
import smartbookmarks.pantin.diiage.org.BDD.SmartBookmarksDbHelper;
import smartbookmarks.pantin.diiage.org.Models.Book;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get le bouton permettant d'ajouter un commentaire.
        Button btnAddComment = findViewById(R.id.btnAddComment);
        // Gestion des évènements suite au clic sur le bouton d'ajout de commentaire.
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fenêtre d'ajout d'un commentaire.
                Intent intentAddComment = new Intent(MainActivity.this, AddCommentActivity.class);

                // Ouvre la fenetre.
                startActivity(intentAddComment);
            }
        });

        // Gestion du clique sur le bouton permettant d'accéder à la liste des commentaires
        Button btnComments = findViewById(R.id.btnComments);
        btnComments.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                // Fenêtre d'ajout d'un commentaire.
                Intent intentComments = new Intent(MainActivity.this, CommentsActivity.class);

                // Ouvre la fenetre.
                startActivity(intentComments);
            }
        });

        // Initialisation de la bae de données.
        SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);

        // le nombre de livre.
        double nbBooks = BookHelper.getBookCount(helper);

        // Le nombre de commentaires.
        double nbCommentaires = CommentHelper.getBookCount(helper);

        // Text view des stats
        TextView textViewStats = findViewById(R.id.txtStats);

        // Evite la division par 0.
        if (nbBooks > 0)
        {
            // Deux chiffres après la virgule
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
            double avgComments = nbCommentaires / nbBooks;
            String avg = df.format(avgComments);

            String stats = "Il y a " + String.valueOf(nbBooks) +" livre(s), " + String.valueOf(nbCommentaires) +"  commentaire(s), et une moyenne de " + avg + " commentaire(s) par livre";

            // Rempli la TextView
            textViewStats.setText(stats);
        }
        else
        {
            textViewStats.setText("Aucun livre");
        }
    }
}