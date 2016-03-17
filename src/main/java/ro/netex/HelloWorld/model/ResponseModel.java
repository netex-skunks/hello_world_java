package ro.netex.HelloWorld.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Response model for Hello World api
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModel {

    /**
     * Possible tokens needed to split the response
     */
    private static final List<String> splitTokens = Arrays.asList("\\s+", ",", "-","，");

    @JsonProperty(value = "message")
    private String message;

    public ResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Optional<String> get2ndElement(){
        if(message != null && !message.isEmpty()) {
            return splitTokens.stream()
                       .filter(token -> message.split(token).length > 1)
                       .map(token -> message.split(token)[1])
                       .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "message='" + message + '\'' +
                '}';
    }
}
