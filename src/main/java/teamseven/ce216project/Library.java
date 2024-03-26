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
    public void search(String string){

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
        if(book.getTitle()!=null) {
            if (book.getTitle().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    public void searchForISBN(String string, Book book){
        if(book.getIsbn()!=null) {
            if (book.getIsbn().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    public void searchForSubtitle(String string, Book book){
        if(book.getSubtitle()!=null) {
            if (book.getSubtitle().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    public void searchForPublisher(String string, Book book){
        if(book.getPublisher()!=null) {
            if (book.getPublisher().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    public void searchForLanguage(String string, Book book){
        if(book.getLanguage()!=null) {
            if (book.getLanguage().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    public void searchForDate(String string, Book book){
        if(book.getDate()!=null) {
            if (book.getDate().contains(string) && !isFound) {
                foundBooks.add(book);
                isFound = true;

            }
        }
    }

    public void searchForRating(String string, Book book){
        if(book.getRating()!=null) {
            float rating = Float.parseFloat(string);
            float bookRating = Float.parseFloat(book.getRating());
            if (bookRating == rating && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    public void searchForEdition(String string, Book book){
        if(book.getEdition()!=null) {
            int edition = Integer.parseInt(string);
            int bookEdition = Integer.parseInt(book.getEdition());
            if (bookEdition == edition && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    public void searchForNumberOfPages(String string, Book book){
        if(book.getNumberOfPages()!=null) {
            int numberOfPages = Integer.parseInt(string);
            int bookPages = Integer.parseInt(book.getNumberOfPages());
            if (bookPages == numberOfPages && !isFound) {
                foundBooks.add(book);
                isFound = true;
            }
        }
    }

    public void searchForAuthor(String string, Book book){
        if(book.getAuthors()!=null){
            for(int i = 0; i<book.getAuthors().size();i++) {
                 if (book.getAuthors().get(i).contains(string) && !isFound) {
                    foundBooks.add(book);
                    isFound = true;
            }
          }
        }
    }

    public void searchForTranslator(String string, Book book){
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

    public void addTag(Book book){
        isFound=false;
        for (String s: book.getTags() ){
            for (String uniqueS : uniqueTags){
                if(uniqueS.equals(s))isFound=true;
            }
            if(!isFound) {
                uniqueTags.add(s);
                break;
            }
        }
    }

    public void filterByTags(ArrayList<String> tags){
        boolean fullyFound = false;
        for (Book book : books){
            for (String s :tags){
                isFound=false;
                for (String tag : book.getTags()){
                    if (tag.equals(s))isFound=true;
                }
                if (!isFound)break;
            }
            if(!isFound)break;
            else foundBooks.add(book);
        }
    }


}
