package ro.netex.HelloWorld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity = template.getForEntity("http://www.google.com/", String.class);

        LOG.log(Level.INFO, "Received response from Google [{0}]", responseEntity);
    }
}
