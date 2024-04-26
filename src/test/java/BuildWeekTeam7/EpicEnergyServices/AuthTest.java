package BuildWeekTeam7.EpicEnergyServices;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.repositories.UserDAO;
import BuildWeekTeam7.EpicEnergyServices.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthTest {

    private final String loginPayload = "{\"email\":\"email@email.com\",\"password\":\"password\"}";

    private final String registerPayload = "{\"email\":\"email@email.com\",\"password\":\"password\", \"username\":\"username\",\"name\":\"name\",\"surname\":\"surname\"}";

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void UserLogin() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        userDAO.save(tryUser);
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
        userDAO.delete(tryUser);
    }

    @Test
    void UserRegister() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.surname").value("surname"));
        User found = this.userService.findByEmail("email@email.com");
        this.userDAO.delete(found);
    }
}
