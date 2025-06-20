package retry.retry.sample.util;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retry.retry.sample.model.response.GetDataUserFromApiCalling;

import java.util.HashMap;
import java.util.Map;

import static retry.retry.sample.util.Constants.*;

@Component
@RequiredArgsConstructor
public class ApiCommunicationUtil {


    private final HttpClientUtil httpClientUtil;
    public GetDataUserFromApiCalling getUserDataById(String idUser){
        Map<String, String> headers = new HashMap<>();
        headers.put(API_KEY, "ya-baru-test");
        headers.put(AUTHORIZATION, "ya token terserah");
        String url = "http://localhost:9090/test/find-by-id-user?idUser="+idUser;
        String resultHttp = httpClientUtil.retryTemplate(url, GET, null, headers);

        //convert to object
        Gson gson = new Gson();
        return gson.fromJson(resultHttp, GetDataUserFromApiCalling.class);
    }
}
