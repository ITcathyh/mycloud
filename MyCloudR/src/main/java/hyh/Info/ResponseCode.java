package hyh.Info;

public class ResponseCode {
    /* 通配请求响应 */
    public static final int DONE_CODE = 200;
    public static final int NONEXISTENCE_CODE = 404;
    public static final int INTERNAL_SERVER_CODE = 500;

    /* 登录、注册请求响应 */
    public static final int LOGIN_LOCKED_CODE = 600;
    public static final int LOGIN_START_LOCKING_CODE = 601;
    public static final int PASSWORD_ERROR_CODE = 602;
    public static final int USER_EXIST_CODE = 603;
    public static final int INVITCODE_INVILID_CODE = 604;
}
