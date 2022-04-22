package mastersquirrel.Exeptions;

public class ElementAlreadyExistsException extends RuntimeException{
    public  ElementAlreadyExistsException(){super();}
    public ElementAlreadyExistsException(String s) {super(s);}
}