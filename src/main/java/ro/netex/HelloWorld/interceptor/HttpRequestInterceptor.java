package ro.netex.HelloWorld.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Request interceptor for logging purpose
 * Created on 3/17/16.
 */
public class HttpRequestInterceptor implements ClientHttpRequestInterceptor{

    public static final Logger LOG = Logger.getLogger(HttpRequestInterceptor.class.getName());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOG.log(Level.INFO, "Request: " + request.getMethod() + " " + request.getHeaders() + " " + request.getURI());
        return execution.execute(request, body);
    }
}