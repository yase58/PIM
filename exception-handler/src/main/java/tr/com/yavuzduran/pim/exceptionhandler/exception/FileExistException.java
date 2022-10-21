package tr.com.yavuzduran.pim.exceptionhandler.exception;

import java.nio.file.FileAlreadyExistsException;

public class FileExistException extends Exception {

    public FileExistException(String file) {
        super(file);
    }


}
