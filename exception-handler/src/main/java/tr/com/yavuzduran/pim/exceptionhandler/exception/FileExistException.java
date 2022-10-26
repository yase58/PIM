package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class FileExistException extends PIMException {

    public FileExistException(String file) {
        super(file);
    }

}
