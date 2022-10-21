package tr.com.yavuzduran.pim.authorization.common;

public class CommonConstant {

    public static final String jwtPrefix = "Bearer ";
    public static final String loginAddress = "http://localhost:8887/sso/login";
    public static final String secret = "w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQfTjWmZq";
    public static final String jwtRole = "Roles";
    public static final int jwtExpireTimeInMinutes = 60 * 24;
    public static final String restUsername = "username";
    public static final String restPassword = "password";
    public static final String jwtAccess = "access_token";
    public static final String jwtRefresh = "refresh_token";


}
