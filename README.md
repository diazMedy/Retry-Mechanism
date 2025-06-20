# 🛡️ external-api-retry-mechanism

A reusable retry mechanism for calling **external APIs** using **OkHttp** with custom configuration, retry rules, and support for various HTTP methods.

> 🚧 Under Development – currently tested for basic `GET` requests. `POST` and `PUT` method testing is in progress.

---

## 🔧 Features

- ✅ Retry on configurable HTTP status codes (e.g. 408, 502, 503, 504)
- 🔄 Custom delay and max retry attempts
- 🌐 Supports `GET`, `POST`, `PUT`, and more (depends on `setRequestMethod`)
- 📦 Easy-to-use builder pattern
- 🧪 Ready for unit/integration test setup

---

## 🧪 Usage

### Method: `retryTemplate(...)`

```java
public String retryTemplate(String url, String method, Object requestBody, Map<String, String> headers){
    OkHttpRetryClient client = new OkHttpRetryBuilder()
            .maxAttempts(maxAttempts)
            .delayMillis(retryDelayMilis)
            .retryOnStatusCodes(REQUEST_TIMEOUT, BAD_GATEWAY, GATEWAY_TIMEOUT,
                    SERVICE_UNAVAILABLE, TOO_MANY_REQUEST)
            .setDefaultHeaders(headers)
            .setUrl(url)
            .setRequestMethod(method) // GET, POST, PUT, DELETE, etc.
            .setBody(requestBody)
            .build();
    return client.callWithRetry();
}
⚙️ Configuration
You can easily configure:

maxAttempts: how many times to retry

retryDelayMillis: delay between retries

retryOnStatusCodes: which HTTP status codes to trigger a retry on

headers: default/custom headers for the request

body: request body (if applicable)

🚀 Next Goals
 Add unit test for POST and PUT

 Add exponential backoff strategy

 Add timeout and circuit breaker fallback (optional)

 Publish to Maven local