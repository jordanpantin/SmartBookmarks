package smartbookmarks.pantin.diiage.org;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.pantin.diiage.org.Adapter.SpinnerBookAdapter;
import smartbookmarks.pantin.diiage.org.BDD.BookHelper;
import smartbookmarks.pantin.diiage.org.BDD.CommentHelper;
import smartbookmarks.pantin.diiage.org.BDD.SmartBookmarksDbHelper;
import smartbookmarks.pantin.diiage.org.Models.Book;
import smartbookmarks.pantin.diiage.org.Models.Comment;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        final SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);

        // La liste de tous les livres
        ArrayList<Book> books = BookHelper.getAllBooks(helper);

        Spinner spinnerBook = findViewById(R.id.spnBooks);

        // Applique un adapter au spinner
        spinnerBook.setAdapter(new SpinnerBookAdapter(this, books));


        // Gestion du clique sur le bouton permettant d'ajouter un commentaire
        Button btnComments = findViewById(R.id.btnValidComment);
        btnComments.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                // Fenêtre d'ajout d'un commentaire.
                Intent intentMainActivity = new Intent(AddCommentActivity.this, MainActivity.class);

                // Get le texte commentaire
                EditText editTextComment = findViewById(R.id.editTextComment);
                String texteComment = editTextComment.getText().toString();

                // Get la page
                EditText editTextPage = findViewById(R.id.editTextNumeroPage);
                Integer page =  Integer.parseInt(editTextPage.getText().toString());

                // Get le nom du livre choisit
                Spinner spinnerBook = findViewById(R.id.spnBooks);

                // Récupère le livre sélectionné
                Book book = (Book)spinnerBook.getSelectedItem();

                // Initialisation du commentaire
                Comment comment = new Comment(0, book.getId(), page, texteComment, null);

                // Ajout du commentaire en base
                CommentHelper.addComment(helper, comment);

                // Ouvre la fenetre.
                startActivity(intentMainActivity);
            }
        });

    }
}
