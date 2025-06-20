package retry.retry.sample.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static retry.retry.sample.util.Constants.*;

@Component
public class HttpClientUtil {

    @Value("${max.attempt}")
    int maxAttempts;

    @Value("${retry.delay.milis}")
    long retryDelayMilis;

    public String retryTemplate(String url, String method, Object
            requestBody, Map<String, String> headers){
        OkHttpRetryClient client = new OkHttpRetryBuilder()
                .maxAttempts(maxAttempts)
                .delayMillis(retryDelayMilis)
                .retryOnStatusCodes(REQUEST_TIMEOUT, BAD_GATEWAY, GATEWAY_TIMEOUT,
                        SERVICE_UNAVAILABLE, TOO_MANY_REQUEST)
                .setDefaultHeaders(headers)
                .setUrl(url)
                .setRequestMethod(method)
                .setBody(requestBody)
                .build();
        return client.callWithRetry();
    }


}