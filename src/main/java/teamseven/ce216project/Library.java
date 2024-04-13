package teamseven.ce216project;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Book> foundBooks;
    private ArrayList<String> uniqueTags;
    private String jsonPath;
    private boolean isFound;

    public Library() {
        books = new ArrayList<Book>();
        foundBooks = new ArrayList<Book>();
        uniqueTags = new ArrayList<String>();
        String documentsPath;
        File checkingFile = new File(System.getProperty("user.home") + File.separator + "Belgeler");
        if(checkingFile.exists()) {
            documentsPath = System.getProperty("user.home") + File.separator + "Belgeler" + File.separator + "TempLibrary";
        }
        else {
            documentsPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TempLibrary";
        }

        File f = new File(documentsPath);
        jsonPath = f.getAbsolutePath() + File.separator + "default.json";
        if(!f.exists()){
            f = new File(System.getProperty("user.home") + File.separator + "Documents", "TempLibrary");
            f.mkdir();
            updateJson();
        }
    }


    public ArrayList<Book> getFoundBooks() {
        return foundBooks;
    }

    public void setFoundBooks(ArrayList<Book> foundBooks) {
        this.foundBooks = foundBooks;
    }

    //Main Search Method
    public void search(String string) {
        if (string == null || string.isBlank()) {
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
        if (book.getTitle() != null&&book.getTitle().contains(string)&& !book.getFound()) {
            foundBooks.add(book);
            book.setFound(true);
        }
    }

    private void searchForISBN(String string, Book book) {
        if (book.getIsbn() != null&& !book.getFound()) {
            if (book.getIsbn().contains(string)) {
                foundBooks.add(book);
                book.setFound(true);
            }
        }
    }

    private void searchForSubtitle(String string, Book book) {
        if (book.getSubtitle() != null&& !book.getFound()) {
            if (book.getSubtitle().contains(string)){
                foundBooks.add(book);
                book.setFound(true);
            }
        }
    }

    private void searchForPublisher(String string, Book book) {
        if (book.getPublisher() != null&& !book.getFound()) {
            if (book.getPublisher().contains(string)) {
                foundBooks.add(book);
                book.setFound(true);
            }
        }
    }

    private void searchForLanguage(String string, Book book) {
        if (book.getLanguage() != null&& !book.getFound()) {
            if (book.getLanguage().contains(string)) {
                book.setFound(true);
                foundBooks.add(book);
            }
        }
    }

    private void searchForDate(String string, Book book) {
        if (book.getDate() != null&& !book.getFound()) {
            if (book.getDate().contains(string)){
                book.setFound(true);
                foundBooks.add(book);
            }
        }
    }

    private void searchForRating(String string, Book book) {
        if (book.getRating() != null&& !book.getFound()) {
            if (book.getRating().contains(string)){
                book.setFound(true);
                foundBooks.add(book);
            }
        }
    }

    private void searchForEdition(String string, Book book) {
        if (book.getEdition() != null&& !book.getFound()) {
            if (book.getEdition().contains(string)){
                book.setFound(true);
                foundBooks.add(book);
            }
        }
    }

    private void searchForNumberOfPages(String string, Book book) {
        if (book.getNumberOfPages() != null&& !book.getFound()) {
            if (book.getNumberOfPages().contains(string)){
                book.setFound(true);
                foundBooks.add(book);
            }
        }
    }

    private void searchForAuthor(String string, Book book) {
        if (book.getAuthors() != null) {
            for (int i = 0; i < book.getAuthors().size(); i++) {
                if (book.getAuthors().get(i).contains(string)&& !book.getFound()) {
                    book.setFound(true);
                    foundBooks.add(book);
                }
            }
        }
    }

    private void searchForTranslator(String string, Book book) {
        if (book.getTranslators() != null) {
            for (int i = 0; i < book.getTranslators().size(); i++) {
                if (book.getTranslators().get(i).contains(string)&& !book.getFound()) {
                    book.setFound(true);
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
                if (uniqueS.equals(s)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                uniqueTags.add(s);
            }
        }
        isFound = false;
    }

    public void filterByTags(ArrayList<String> tags, Book book) {
        isFound =true;
        ArrayList<Boolean> checkList =new ArrayList<>();
        if(book.getTags()!=null&&tags!=null){
            for(String tag : tags){
                System.out.println(tag);
                checkList.add(book.getTags().contains(tag));
            }
            for (int i = 0;i<checkList.size();i++){
                if (!checkList.get(i)){
                    isFound=false;
                    break;
                }
            }
            if (isFound) foundBooks.add(book);
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
        if(tagsToCheck != null) {
            tagChecker:
            for (String tag : tagsToCheck) {
                for (Book book : books) {
                    if (book.getTags().contains(tag)) {
                        continue tagChecker;
                    } // Proceed to check other tags if a tag is found in any existing book
                }
                uniqueTags.remove(tag); //Remove a tag if it has not been found in any existing book.
            }


        }
        updateJson();

    }

    public String getJsonPath() {
        return jsonPath;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getSameBookIndex(Book book){
        for (int i = 0; i < books.size(); i++) {
            if(Objects.equals(books.get(i), book)){
                return i;
            }
        }
        return -1;
    }
}
