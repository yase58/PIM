package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class FileEmptyException extends PIMException {

    private final String fileName;

    public FileEmptyException(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
