package teamseven.ce216project;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Book> foundBooks;
    private ArrayList<String> uniqueTags;
    private String jsonPath;


    public Library() {
        books = new ArrayList<Book>();
        foundBooks = new ArrayList<Book>();
        uniqueTags = new ArrayList<String>();
        jsonPath = "";
    }

    private boolean isFound;

    public ArrayList<Book> getFoundBooks() {
        return foundBooks;
    }

    //Main Search Method
    public void search(String string) {
        if (string == null) {
            foundBooks = books;
        } else {
            foundBooks = new ArrayList<>();
            for (Book b : books) {
                searchForAuthor(string, b);
                searchForDate(string, b);
                searchForISBN(string, b);
                searchForEdition(string, b);
                searchForLanguage(string, b);
                searchForNumberOfPages(string, b);
                searchForPublisher(string, b);
                searchForRating(string, b);
                searchForSubtitle(string, b);
                searchForTitle(string, b);
                searchForTranslator(string, b);
            }
        }
    }


    // Complementary Search Methods
    private void searchForTitle(String string, Book book) {
        if (book.getTitle() != null) {
            if (book.getTitle().contains(string)) foundBooks.add(book);
        }
    }

    private void searchForISBN(String string, Book book) {
        if (book.getIsbn() != null) {
            if (book.getIsbn().contains(string)) foundBooks.add(book);
        }
    }

    private void searchForSubtitle(String string, Book book) {
        if (book.getSubtitle() != null) {
            if (book.getSubtitle().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForPublisher(String string, Book book) {
        if (book.getPublisher() != null) {
            if (book.getPublisher().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForLanguage(String string, Book book) {
        if (book.getLanguage() != null) {
            if (book.getLanguage().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForDate(String string, Book book) {
        if (book.getDate() != null) {
            if (book.getDate().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForRating(String string, Book book) {
        if (book.getRating() != null) {
            if (book.getRating().contains(string)) foundBooks.add(book);
        }
    }

    private void searchForEdition(String string, Book book) {
        if (book.getEdition() != null) {
            if (book.getEdition().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForNumberOfPages(String string, Book book) {
        if (book.getNumberOfPages() != null) {
            if (book.getNumberOfPages().contains(string)) foundBooks.add(book);

        }
    }

    private void searchForAuthor(String string, Book book) {
        if (book.getAuthors() != null) {
            for (int i = 0; i < book.getAuthors().size(); i++) {
                if (book.getAuthors().get(i).contains(string)) {
                    foundBooks.add(book);

                }
            }
        }
    }

    private void searchForTranslator(String string, Book book) {
        if (book.getTranslators() != null) {
            for (int i = 0; i < book.getTranslators().size(); i++) {
                if (book.getTranslators().get(i).contains(string)) {
                    foundBooks.add(book);

                }
            }
        }
    }

    //Tag Search
    //and Management

    public void addTag(Book book) {
        isFound = false;
        for (String s : book.getTags()) {
            for (String uniqueS : uniqueTags) {
                if (uniqueS.equals(s)) isFound = true;
            }
            if (!isFound) {
                uniqueTags.add(s);
            }
        }
        isFound = false;
    }

    public void filterByTags(ArrayList<String> tags) {
        foundBooks.clear();
        ArrayList<Boolean> bool = new ArrayList<>();
        for (Book book : books) {
            if (book.getTags() != null) {
                for (String s : book.getTags()) {
                    for (String tag : tags) {
                        if (s.equals(tag)) isFound = true;
                    }
                    bool.add(isFound);
                }

                for (Boolean b : bool) {
                    if (!b) {
                        isFound = false;
                        break;
                    }
                }
                if (isFound) foundBooks.add(book);
            }
        }
    }




    public void updateJson() {       // Saves the .json the default file path
        exportJson(jsonPath);
    }       //Saves the .json to the default path

    public void exportJson(String path) {       // Saves the .json to path that will be given from the FileChooser
        Gson gson = new Gson();
        String jsonFormat = gson.toJson(books);

        Formatter formatter = null;
        try {
            formatter = new Formatter(path);
            formatter.format("%s", jsonFormat);      // Overwrites the existing .json
        } catch (FileNotFoundException e) {
            System.err.println("File not found in path while exporting");
        } finally {
            if (formatter != null) {
                formatter.close();
            }
        }
    }

    public void importJson(String path) {
        Gson gson = new Gson();
        Scanner scanner = null;
        String jsonFormat;

        try {
            scanner = new Scanner(Paths.get(path));
            jsonFormat = scanner.nextLine();        // Gets the .json as string

            Type foundListType = new TypeToken<ArrayList<Book>>() {
            }.getType();
            books = gson.fromJson(jsonFormat, foundListType);                   //  Parses the string into books array
        } catch (JsonSyntaxException e) {
            System.err.println("Json has syntax error exception");
        } catch (IOException e) {
            System.err.println("Error occurred while opening .json file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void addBook(String title, String subtitle, String isbn, String publisher, String date, String
            edition, String numberOfPages, String cover, String coverPath, String language, String
                                rating, ArrayList<String> authors, ArrayList<String> translators, ArrayList<String> tags) {
        books.add(new Book(title, subtitle, isbn, publisher, date, edition, numberOfPages, cover, coverPath, language, rating, authors, translators, tags));
        //addTag(tags);
        updateJson();
        search(null);
    }

    public void deleteBook(Book bookToDelete) {
        books.remove(bookToDelete);

        // Check if deleted book's tags appear in any existing book
        ArrayList<String> tagsToCheck = bookToDelete.getTags();
        tagChecker:
        for (String tag : tagsToCheck) {
            for (Book book : books) {
                if (book.getTags().contains(tag)) {
                    continue tagChecker;
                } // Proceed to check other tags if a tag is found in any existing book
            }
            uniqueTags.remove(tag); //Remove a tag if it has not been found in any existing book.
        }

        updateJson();


    }

}
