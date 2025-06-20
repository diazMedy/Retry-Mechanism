package retry.retry.sample.util;

import lombok.Getter;

@Getter
public final class Constants {
    private Constants() {
        throw new UnsupportedOperationException("This is a constant class and cannot be instantiated");
    }

    public static final String SUCCESS = "SUCCESS";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String GET = "GET";
    public static final String API_KEY = "api-key";
    public static final String AUTHORIZATION = "Authorization";
    public static final Integer REQUEST_TIMEOUT = 408;
    public static final Integer BAD_GATEWAY = 502;
    public static final Integer GATEWAY_TIMEOUT = 504;
    public static final Integer SERVICE_UNAVAILABLE = 503;
    public static final Integer TOO_MANY_REQUEST = 429;
}