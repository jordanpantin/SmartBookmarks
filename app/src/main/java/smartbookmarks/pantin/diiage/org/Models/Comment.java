package smartbookmarks.pantin.diiage.org.Models;

import java.util.Date;

public class Comment
{
    /**
     * Identifiant du commentaire
     */
    private int Id;

    /**
     * Identifiant du livre associée au commentaire.
     */
    private int bookId;

    /**
     * Numéro de la page du livre sur laquelle le commentaire est posé
     */
    private int Page;

    /**
     * Corps du commentaire.
     */
    private String comment;

    /**
     * Date du commentaire
     */
    private Date Date;

    public Comment(int id, int bookId, int page, String comment, java.util.Date date) {
        this.Id = id;
        this.bookId = bookId;
        this.Page = page;
        this.comment = comment;
        this.Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
