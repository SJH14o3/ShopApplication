package source.exceptions;

public class PassedDeadLineException extends Exception{
    public PassedDeadLineException(String msg) {
        super(msg);
    }
    public PassedDeadLineException() {
        super();
    }
}
