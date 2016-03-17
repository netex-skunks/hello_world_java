package ro.netex.HelloWorld.util;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Utility class for encoding the request token
 * Created on 3/17/16.
 */
public class AppUtil {

    public static final Logger LOG = Logger.getLogger(AppUtil.class.getName());

    public static String buildUri(String url, Map<String, String> params, boolean encoded){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for(Map.Entry<String, String> entry : params.entrySet()){
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return builder.build(encoded).toString();
    }
}