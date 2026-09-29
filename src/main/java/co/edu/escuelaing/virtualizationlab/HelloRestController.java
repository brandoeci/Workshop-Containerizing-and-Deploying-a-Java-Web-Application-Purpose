package co.edu.escuelaing.virtualizationlab;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The service of the workshop: a greeting built with the name in the query string. */
@RestController
public class HelloRestController {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    /**
     * Says which instance answered.
     *
     * <p>Inside a container the host name is the container id, so calling this
     * on 34000, 34001 and 34002 shows three different isolated processes
     * running the same image.</p>
     */
    @GetMapping("/whoami")
    public Map<String, String> whoami() throws UnknownHostException {
        return Map.of(
                "host", InetAddress.getLocalHost().getHostName(),
                "port", System.getenv().getOrDefault("PORT", "9000"),
                "java", System.getProperty("java.version"));
    }
}
