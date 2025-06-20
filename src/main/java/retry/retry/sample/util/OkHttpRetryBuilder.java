package retry.retry.sample.util;


import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static retry.retry.sample.util.Constants.GET;


public class OkHttpRetryBuilder {

    private OkHttpClient client = new OkHttpClient();
    private int maxAttempts = 3;
    private long delayMillis = 2000;
    private Set<Integer> retryableStatusCodes = new HashSet<>();
    private Map<String, String> headers = new HashMap<>();
    private String requestMethod = GET;
    private String url;
    private Object requestBody;

    public OkHttpRetryBuilder setBody(Object requestBody){
        this.requestBody = requestBody;
        return this;
    }

    public OkHttpRetryBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public OkHttpRetryBuilder setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public OkHttpRetryBuilder addDefaultHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public OkHttpRetryBuilder setDefaultHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
        return this;
    }

    public OkHttpRetryBuilder client(OkHttpClient client) {
        this.client = client;
        return this;
    }

    public OkHttpRetryBuilder maxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public OkHttpRetryBuilder delayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
        return this;
    }

    public OkHttpRetryBuilder retryOnStatusCodes(Integer... codes) {
        for (Integer code : codes) {
            retryableStatusCodes.add(code);
        }
        return this;
    }



    public OkHttpRetryClient build() {
        return new OkHttpRetryClient(client, maxAttempts, delayMillis, retryableStatusCodes, requestMethod, url, headers, requestBody);
    }
}