package retry.retry.sample.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.Set;

import static retry.retry.sample.util.Constants.POST;
import static retry.retry.sample.util.Constants.PUT;


@Slf4j
@RequiredArgsConstructor
public class OkHttpRetryClient {


    private final OkHttpClient client;
    private final int maxAttempts;
    private final long delayMillis;
    private final Set<Integer> retryableStatusCodes;
    private final String requestMethod;
    private final String url;
    private final Map<String, String> headers;
    private final Object requestBody;


    public String callWithRetry() {
        Request request = requestMethod();
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                Response response = client.newCall(request).execute();
                int code = response.code();
                log.info("Attempt " + i);

                if (response.isSuccessful()) {
                    log.info("Success getting response after " + i + " Attempt");
                    String result = response.body().string();
                    // relese connection and memory
                    response.close();
                    return result;
                } else
                if (retryableStatusCodes.contains(code)) {
                    response.close();
                    log.info("Retrying Status Code After Http " + code);
                    Thread.sleep(delayMillis);
                } else {
                    response.close();
                    log.warn("Not Retrying because status code " + code);
                    break;
                }

            } catch (Exception e) {
                /* ini untuk kasus exception (tanpa status code) misal
                 no internet / DNS error , connection refused*/
                log.error("Network Error", e.getMessage());
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ignored) {
                    log.error("Intrupted Thread", ignored);
                    /* Clean up whatever needs to be handled before interrupting  */
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null;
    }

    @SneakyThrows
    public Request requestMethod(){
        Request.Builder builder = new Request.Builder().url(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        if (requestMethod.equals(POST)){
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(requestBody);

            RequestBody body = RequestBody.create(
                    json,
                    MediaType.parse("application/json")
            );
            builder.post(body);
        } else if (requestMethod.equals(PUT)) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(requestBody);

            RequestBody body = RequestBody.create(
                    json,
                    MediaType.parse("application/json")
            );
            builder.put(body);
        }
        return builder.build();
    }
}