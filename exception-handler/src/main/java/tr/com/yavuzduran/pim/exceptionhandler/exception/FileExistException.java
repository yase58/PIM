package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class FileExistException extends Exception {

    public FileExistException(String file) {
        super(file);
    }

}
