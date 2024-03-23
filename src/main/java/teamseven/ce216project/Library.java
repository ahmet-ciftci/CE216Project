package teamseven.ce216project;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Book> foundBooks;
    private ArrayList<String> uniqueTags;
    private String jsonPath;
    public void addBook(String title, String subtitle, String isbn, String publisher, String date, String edition, String numberOfPages, String cover, String coverPath, String language, String rating, ArrayList<String> authors, ArrayList<String> translators, ArrayList<String> tags) {
        books.add(new Book(title, subtitle, isbn, publisher, date, edition, numberOfPages, cover, coverPath, language, rating, authors, translators, tags));
        addTag(tags);
        updateJson();
    }

    public void deleteBook(Book bookToDelete) {
        books.remove(bookToDelete);

        // Check if deleted book's tags appear in any existing book
        ArrayList<String> tagsToCheck = bookToDelete.getTags();
        tagChecker:
        for (String tag : tagsToCheck) {
            for (Book book : books) {
                if (book.getTags().contains(tag)) {continue tagChecker;} // Proceed to check other tags if a tag is found in any existing book
            }
            uniqueTags.remove(tag); //Remove a tag if it has not been found in any existing book.
        }

        updateJson();
    }
}
