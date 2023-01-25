package org.example.exceptions;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(String message){
        super("Нет такого кота: "+message);
    }
}
