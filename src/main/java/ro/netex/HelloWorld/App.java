package ro.netex.HelloWorld;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import ro.netex.HelloWorld.interceptor.HttpRequestInterceptor;
import ro.netex.HelloWorld.model.ResponseModel;
import ro.netex.HelloWorld.util.AppConstants;
import ro.netex.HelloWorld.util.AppUtil;

import java.util.*;
import java.util.logging.Logger;


/**
 * Hello world!
 *
 */
public class App {
    public static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        String token = DigestUtils.md5DigestAsHex(AppConstants.NETEX.getBytes());
        String internationalStringApiUri = AppUtil.buildUri(AppConstants.BEFORE_TRANSLATION_API, Collections.singletonMap("token", token), true);

        ResponseEntity<ResponseModel> internationalString = getRestTemplateWithJsonMessageConverter()
                .getForEntity(internationalStringApiUri, ResponseModel.class);

        LOG.info("International string: "+internationalString.getBody().getMessage());

        Optional<String> secondElement = internationalString.getBody().get2ndElement();

        if (secondElement.isPresent()) {
            String translationApiUri = AppUtil.buildUri(AppConstants.TRANSLATION_API, Collections.singletonMap("word", secondElement.get()), false);
            ResponseEntity<String> translation = new RestTemplate()
                    .getForEntity(translationApiUri, String.class);

            LOG.info("Response is:"+translation.getBody());
        }

    }


    private static RestTemplate getRestTemplateWithJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(converter));
        restTemplate.setInterceptors(Collections.singletonList(new HttpRequestInterceptor()));

        return restTemplate;
    }


}
