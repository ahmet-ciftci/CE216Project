package teamseven.ce216project;

import java.util.ArrayList;

public class Book {
    private final String title;
    private final String subtitle;
    private final String isbn;
    private final String publisher;
    private final String date;
    private final String edition;
    private final String numberOfPages;
    private final String cover;
    private final String coverPath;
    private final String language;
    private final String rating;
    private final ArrayList<String> authors;
    private final ArrayList<String> translators;
    private final ArrayList<String> tags;

    public Book(String title, String subtitle, String isbn, String publisher, String date, String edition, String numberOfPages, String cover, String coverPath, String language, String rating, ArrayList<String> authors, ArrayList<String> translators, ArrayList<String> tags) {
        this.title = title;
        this.subtitle = subtitle;
        this.isbn = isbn;
        this.publisher = publisher;
        this.date = date;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.cover = cover;
        this.coverPath = coverPath;
        this.language = language;
        this.rating = rating;
        this.authors = authors;
        this.translators = translators;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public String getEdition() {
        return edition;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public String getCover() {
        return cover;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public String getLanguage() {
        return language;
    }

    public String getRating() {
        return rating;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public ArrayList<String> getTranslators() {
        return translators;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
