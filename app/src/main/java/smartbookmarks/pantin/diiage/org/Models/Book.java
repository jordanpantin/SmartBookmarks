package smartbookmarks.pantin.diiage.org.Models;

public class Book
{
    /**
     * Identifiant du livre
     */
    private int Id;

    /**
     * Titre du livre
     */
    private String title;

    /**
     * Auteur du livre
     */
    private String Author;

    /**
     * Genre du livre
     */
    private String Genre;

    /**
     * Constructeur par initialisation
     * @param id identifiant du livre
     * @param title titre du livre
     * @param author auteur du livre
     * @param genre genre du livre
     */
    public Book(int id, String title, String author, String genre) {
        Id = id;
        this.title = title;
        Author = author;
        Genre = genre;
    }

    /**
     * Obtient l'identifiant du livre.
     * @return L'identifiant du livre.
     */
    public int getId() {
        return Id;
    }

    /**
     * Définit l'identifiant du livre.
     * @param id l'identifiant du livre.
     */
    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
