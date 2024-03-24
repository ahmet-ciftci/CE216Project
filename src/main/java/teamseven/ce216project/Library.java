package teamseven.ce216project;

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

    public void importJson(String path){
        Gson gson = new Gson();
        Scanner scanner = null;
        String jsonFormat;

        try {
            scanner = new Scanner(Paths.get(path));
            jsonFormat = scanner.nextLine();        // Gets the .json as string

            Type foundListType = new TypeToken<ArrayList<Book>>(){}.getType();
            books = gson.fromJson(jsonFormat, foundListType);                   //  Parses the string into books array
        }
        catch (JsonSyntaxException e) {
            System.err.println("Json has syntax error exception");
        } catch (IOException e) {
            System.err.println("Error occurred while opening .json file");
        } finally {
            if (scanner  != null) {
                scanner.close();
            }
        }
    }
}
