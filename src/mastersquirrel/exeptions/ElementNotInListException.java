package mastersquirrel.exeptions;

public class ElementNotInListException extends RuntimeException{
    public ElementNotInListException(){super();}
    public ElementNotInListException(String s) {super(s);}
}