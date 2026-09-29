package co.edu.escuelaing.virtualizationlab;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the application.
 *
 * <p>The listening port is not written in the code: it comes from the
 * {@code PORT} environment variable, which is what lets the same image run on
 * the laptop, inside a container and on EC2 without rebuilding anything.</p>
 */
@SpringBootApplication
public class RestServiceApplication {

    /**
     * Port used when PORT is not set.
     *
     * <p>Chrome and Firefox block port 6000 (ERR_UNSAFE_PORT), so the default of
     * the workshop code, 9000, is the one that can actually be opened in a
     * browser.</p>
     */
    private static final String DEFAULT_PORT = "9000";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RestServiceApplication.class);

        application.setDefaultProperties(
                Map.of("server.port", System.getenv().getOrDefault("PORT", DEFAULT_PORT)));

        application.run(args);
    }
}
