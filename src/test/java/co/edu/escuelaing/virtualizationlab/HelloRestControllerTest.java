package co.edu.escuelaing.virtualizationlab;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloRestController.class)
class HelloRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greetingUsesTheNameFromTheQueryString() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Pedro"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Pedro!"));
    }

    @Test
    @DisplayName("without a name the greeting falls back to World")
    void greetingHasADefaultName() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void greetingAcceptsAccentedNames() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Ana María"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Ana María!"));
    }

    @Test
    @DisplayName("/whoami says which instance answered")
    void whoamiReportsTheInstance() throws Exception {
        mockMvc.perform(get("/whoami"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.host").exists())
                .andExpect(jsonPath("$.port").exists());
    }
}
