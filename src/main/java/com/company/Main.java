package com.company;

import com.company.model.Atlas;
import com.company.model.Book;
import com.company.model.Magazine;
import com.company.util.IllegalTypeException;
import com.company.util.myList;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        myList<Book> list = new myList<>();
        HashSet<Book> hash = new HashSet<>();

        String currentUserDir = System.getProperty("user.dir") + "\\test.txt";
        try(FileReader file = new FileReader(currentUserDir);
            BufferedReader objReader = new BufferedReader(file)){           //try-with-resources implementation
            String strCurrentLine;                                          //A string containing the entire line read from the file
            while ((strCurrentLine = objReader.readLine()) != null) {
                String[] currentLineArray = strCurrentLine.split("//");// A string array in which each indexed element is the AuthorName/NameOfTheBook/etc.
                if (currentLineArray.length > 1) {
                    String BookType=currentLineArray[2];                             //The third element of the array always holds the Book Type
                    switch (BookType) {
                        case "Atlas":
                            Book a = new Atlas();
                            a.setNameOfTheBook(currentLineArray[0]);
                            a.setAuthorName(currentLineArray[1]);
                            Double d1=0.0;
                            try{
                                d1=parseDouble(currentLineArray[3]);
                            }catch (IllegalTypeException e){
                                System.out.println(e.getMessage());
                            }
                            a.setPriceInEuros(d1);
                            list.add(a);
                            break;
                        case "Magazine":
                            Book r = new Magazine();
                            r.setNameOfTheBook(currentLineArray[0]);
                            r.setAuthorName(currentLineArray[1]);
                            Double d2=0.0;
                            try{
                                d2=parseDouble(currentLineArray[3]);
                            }catch (IllegalTypeException e){
                                System.out.println(e.getMessage());
                            }
                            r.setPriceInEuros(d2);
                            list.add(r);
                            break;
                        default:
                            System.out.println("Nu se incadreaza!!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        int size = list.size();                 //size of the list equals the number of lines in the file
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i).getNameOfTheBook() + " " + list.get(i).getAuthorName() + " " +
                    list.get(i).getPrice());
        }

        // adaugarea in HashSet a elementelor din lista
        int lineCounter=size;
        for(int i=0;i<size;i++){
            try {
                hash.add(list.get(i));
            }catch (NullPointerException e){
                System.out.println("Duplicate encountered! ");
                lineCounter--;
            }

        }

        //Scrierea in ArrayStr a elementelor, fara duplicate
        String[] ArrayStr = new String[lineCounter];
        System.out.println(" ");
        Iterator itr= hash.iterator();
        int i=0;
        while(itr.hasNext()){
            Book element=(Book) itr.next();
            ArrayStr[i] = element.getNameOfTheBook() +" " + element.getAuthorName()+" "+element.getPrice();
            i++;
            //each indexed element of ArrayStr contains a concatenated line of list elements
        }

        //apelarea metodei de scriere in fisier
        //se paseaza ca parametrii ArrayStr si nr de linii din fisierul citit, fara duplicate
        writeUsingBufferedWriter(ArrayStr, lineCounter);

    }


    /**
     * Method to write in a file
     * .getProperty("line.separator") adds end line to the current line
     *
     * @param ArrayStr
     * @param noOfLines
     */
    private static void writeUsingBufferedWriter(String[] ArrayStr, int noOfLines) {
        String currentUserDir = System.getProperty("user.dir") + "\\FileToWrite.txt";
        File file = new File(currentUserDir);
        try (FileWriter fr = new FileWriter(file);
             BufferedWriter br = new BufferedWriter(fr)){           //try-with-resources implementation
            for (int i = 0; i < noOfLines; i++) {
                String dataWithNewLine = ArrayStr[i] + System.getProperty("line.separator");
                br.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
        Method to parse from string to double
        Also checks the validity of the input from the user
     */
    public static Double parseDouble(String d)throws IllegalTypeException{
        Double value;
        try{
            value= Double.valueOf(d);
        }catch (NumberFormatException e){
            throw new IllegalTypeException("Value should be of type double");
        }
        return value;
    }

}






