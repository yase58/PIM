package tr.com.yavuzduran.pim.authorization.exception;

public class JWTException extends RuntimeException {

    public JWTException(Exception e) {
        super(e);
    }

}
