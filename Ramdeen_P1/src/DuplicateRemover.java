/*
    Zahid Ramdeen
    COP3330-19Fall 0002
    Programming Assignment 4 - Problem 1
 */

import java.util.*;
import java.io.*;

public class DuplicateRemover {

    private HashSet<String> uniqueWords;
    private FileInputStream fileInputByteStream;
    private Scanner fileScanner;
    private FileOutputStream fileOutputByteStream;
    private PrintWriter filePrinter;

    public DuplicateRemover(){
        uniqueWords = new HashSet<String>();
    }

    // removes duplicate words from a text file (takes file path as argument)
    public void remove(String dataFile){
        try {
            loadFileReader(dataFile); // Throws IOException if file cannot be loaded.
            collectUniqueWords(); // stores the File Information into uniqueWords HashSet
            closeFileInput(); // Throws IOException if fails to close the file
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }

    // create a file and loads the data from uniqueWords into it
    public void write(String dataFile){
        try {
            loadFileWriter(dataFile); // Throws IOException if fails
            writeUniqueWords();
            closeFileWriter(); // Throws IOException
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }

    // fills the HashMap with values from the file
    private void collectUniqueWords(){
        while(fileScanner.hasNext()){
            uniqueWords.add(fileScanner.next().trim());
        }
    }

    // writes the uniqueWords to the file.
    // removes all punctuation from the words.
    private void writeUniqueWords(){
        Object myArr[] = uniqueWords.toArray(); // convert HashSet to an array in order to read the values
        for(int i = 0; i < uniqueWords.size(); i++)
            filePrinter.println(myArr[i].toString().trim().replaceAll("[^a-zA-Z ]", "") + " ");
        filePrinter.flush();
    }

    /* Methods to handle files were created to make exception handling cleaner */
    // load file and initialize instance variables
    private void loadFileReader(String dataFile) throws Exception{
        fileInputByteStream = new FileInputStream(dataFile);
        fileScanner = new Scanner(fileInputByteStream);
    }

    // create objects to write to file
    private void loadFileWriter(String dataFile) throws Exception{
        fileOutputByteStream = new FileOutputStream(dataFile);
        filePrinter = new PrintWriter(fileOutputByteStream);
    }

    // close file objects used
    private void closeFileInput() throws Exception{
        fileInputByteStream.close();
        fileScanner.close();
    }

    private void closeFileWriter() throws Exception{
        fileOutputByteStream.close();
        filePrinter.close();
    }
}
