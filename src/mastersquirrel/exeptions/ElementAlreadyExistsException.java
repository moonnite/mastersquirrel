package mastersquirrel.exeptions;

public class ElementAlreadyExistsException extends RuntimeException{
    public  ElementAlreadyExistsException(){super();}
    public ElementAlreadyExistsException(String s) {super(s);}
}