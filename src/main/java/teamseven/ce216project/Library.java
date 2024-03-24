package teamseven.ce216project;

import java.io.IOException;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Book> foundBooks;
    private ArrayList<String> uniqueTags;
    private String jsonPath;

    private boolean isFound;

    //Main Search Method
    public void search(String string, ArrayList<Book> books){

        if (string == null) {
            foundBooks=books;
            return;
        }else {
            foundBooks = null;
            for (Book b : books) {
                isFound = false;
                if (foundBooks != null) {
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
    public void searchForTitle(String string, Book book){
        if (book.getTitle().contains(string)&&!isFound){
            foundBooks.add(book);
            isFound=true;
        }
    }

    public void searchForISBN(String string, Book book){
        if (book.getIsbn().contains(string)&&!isFound){
            foundBooks.add(book);
            isFound=true;

        }
    }

    public void searchForSubtitle(String string, Book book){
        if (book.getSubtitle().contains(string)&&!isFound){
            foundBooks.add(book);
            isFound=true;

        }
    }

    public void searchForPublisher(String string, Book book){
        if (book.getPublisher().contains(string)&&!isFound) {
            foundBooks.add(book);
            isFound=true;

        }
    }

    public void searchForLanguage(String string, Book book){
        if (book.getLanguage().contains(string)&&!isFound){
            foundBooks.add(book);
            isFound=true;

        }
    }

    public void searchForDate(String string, Book book){
        if (book.getDate().contains(string)&&!isFound){
            foundBooks.add(book);
            isFound=true;

        }
    }

    public void searchForRating(String string, Book book){
       float rating = Float.parseFloat(string);
       float bookRating = Float.parseFloat(book.getRating());
       if (bookRating==rating&&!isFound){
           foundBooks.add(book);
           isFound=true;
       }
    }

    public void searchForEdition(String string, Book book){
        int edition = Integer.parseInt(string);
        int bookEdition = Integer.parseInt(book.getEdition());
        if (bookEdition==edition&&!isFound){
            foundBooks.add(book);
            isFound=true;
        }
    }

    public void searchForNumberOfPages(String string, Book book){
        int numberOfPages = Integer.parseInt(string);
        int bookPages = Integer.parseInt(book.getNumberOfPages());
        if (bookPages==numberOfPages&&!isFound){
            foundBooks.add(book);
            isFound=true;
        }
    }

    public void searchForAuthor(String string, Book book){
        for(int i = 0; i<book.getAuthors().size();i++){
            if(book.getAuthors().get(i).contains(string)&&!isFound){
                foundBooks.add(book);
                isFound=true;
            }
        }
    }

    public void searchForTranslator(String string, Book book){
        for(int i = 0; i<book.getTranslators().size();i++){
            if(book.getTranslators().get(i).contains(string)&&!isFound){
                foundBooks.add(book);
                isFound=true;
            }
        }
    }
}
