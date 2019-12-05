package com.company.util;

/**
 *
 */
public class myList<T> {
    private T[] typeArray;
    private int counter;//counter acts as an index for the last added element of the array

    public myList() {
        counter = -1;
        typeArray = (T[]) new Object[25]; //casting the generic type to Object
    }

    public void add(T element) {
        counter++;
        typeArray[counter] = element;
    }

    //The delete method removes an element at a given index
    public void delete(int index) {
        for (int i = index; i < counter; i++) {
            typeArray[i] = typeArray[i + 1];
        }
        typeArray[counter] = null;
        counter--;
    }

    public int size() {
        return counter + 1;
    }

    //the get method returns an element at a given index
    public T get(int index) {
        return typeArray[index];
    }
}