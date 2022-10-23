package tr.com.yavuzduran.pim.security.common;

public class CommonConstant {

    private CommonConstant() {
    }

    public static final String JWT_PREFIX = "Bearer ";
    public static final String LOGIN_ADDRESS = "http://localhost:8887/sso/login";
    public static final String SECRET = "w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQfTjWmZq";
    public static final String JWT_ROLE = "Roles";
    public static final int JWT_EXPIRE_TIME_IN_MINUTES = 60 * 24;
    public static final String REST_USERNAME = "username";
    public static final String REST_PASSWORD = "password";
    public static final String JWT_ACCESS = "access_token";
    public static final String JWT_REFRESH = "refresh_token";


}
