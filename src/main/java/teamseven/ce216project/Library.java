package teamseven.ce216project;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        jsonPath = "C:\\Users\\ozgur\\Desktop\\CE216Project\\src\\main\\resources\\teamseven\\ce216project\\default.json";
    }

    private boolean isFound;

    public ArrayList<Book> getFoundBooks() {
        return foundBooks;
    }

    //Main Search Method
    public void search(String string){

        if (string == null) {
            foundBooks=books;
            return;
        }else {
            foundBooks.clear();

           for (Book b : books) {
                isFound = false;
                if (!foundBooks.isEmpty()) {
                    for (Book j : foundBooks) {
                        if (j == b) {
                            isFound = true;
                            break;
                        }
                    }
                }
                if (!isFound) {
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
    }



    // Complementary Search Methods
    private void searchForTitle(String string, Book book){
        if(book.getTitle()!=null) {
            if (book.getTitle().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    private void searchForISBN(String string, Book book){
        if(book.getIsbn()!=null) {
            if (book.getIsbn().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    private void searchForSubtitle(String string, Book book){
        if(book.getSubtitle()!=null) {
            if (book.getSubtitle().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    private void searchForPublisher(String string, Book book){
        if(book.getPublisher()!=null) {
            if (book.getPublisher().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    private void searchForLanguage(String string, Book book){
        if(book.getLanguage()!=null) {
            if (book.getLanguage().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    private void searchForDate(String string, Book book){
        if(book.getDate()!=null) {
            if (book.getDate().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    private void searchForRating(String string, Book book){
        if(book.getRating()!=null) {
            float rating = Float.parseFloat(string);
            float bookRating = Float.parseFloat(book.getRating());
            if (bookRating == rating && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    private void searchForEdition(String string, Book book){
        if(book.getEdition()!=null) {
            int edition = Integer.parseInt(string);
            int bookEdition = Integer.parseInt(book.getEdition());
            if (bookEdition == edition && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    private void searchForNumberOfPages(String string, Book book){
        if(book.getNumberOfPages()!=null) {
            int numberOfPages = Integer.parseInt(string);
            int bookPages = Integer.parseInt(book.getNumberOfPages());
            if (bookPages == numberOfPages && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    private void searchForAuthor(String string, Book book){
        if(book.getAuthors()!=null){
            for(int i = 0; i<book.getAuthors().size();i++) {
                 if (book.getAuthors().get(i).contains(string) && !isFound) {
                    foundBooks.add(book);
                    isFound = true;
            }
          }
        }
    }

    private void searchForTranslator(String string, Book book){
        if(book.getTranslators()!=null) {
            for (int i = 0; i < book.getTranslators().size(); i++) {
                if (book.getTranslators().get(i).contains(string) && !isFound) {
                    foundBooks.add(book);
                    isFound = true;
                }
            }
        }
    }

    //Tag Search
    //and Management

    private void addTag(ArrayList<String> tags){
        isFound=false;
        for (String s: tags){
            for (String uniqueS : uniqueTags){
                if(uniqueS.equals(s))isFound=true;
            }
            if(!isFound) {
                uniqueTags.add(s);
            }
        }
        isFound=false;
    }

    private void filterByTags(ArrayList<String> tags) {
        boolean fullyFound = false;
        for (Book book : books) {
            for (String s : tags) {
                isFound = false;
                for (String tag : book.getTags()) {
                    if (tag.equals(s)) isFound = true;
                }
                if (!isFound) break;
            }
            if (!isFound) break;
            else foundBooks.add(book);
        }
    }


    public void updateJson(){       // Saves the .json the default file path
        exportJson(jsonPath);
    }       //Saves the .json to the default path

    public void exportJson(String path) {       // Saves the .json to path that will be given from the FileChooser
        Gson gson = new Gson();
        String jsonFormat = gson.toJson(books);

        Formatter formatter = null;
        try {
            formatter = new Formatter(path);
            formatter.format("%s",jsonFormat);      // Overwrites the existing .json
        } catch (FileNotFoundException e) {
            System.err.println("File not found in path while exporting");
        }
        finally {
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

    public void addBook(String title, String subtitle, String isbn, String publisher, String date, String edition, String numberOfPages, String cover, String coverPath, String language, String rating, ArrayList<String> authors, ArrayList<String> translators, ArrayList<String> tags) {
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
}
