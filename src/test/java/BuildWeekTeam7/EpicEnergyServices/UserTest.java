package BuildWeekTeam7.EpicEnergyServices;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.enums.UserRole;
import BuildWeekTeam7.EpicEnergyServices.payloads.UserLoginDTO;
import BuildWeekTeam7.EpicEnergyServices.repositories.UserDAO;
import BuildWeekTeam7.EpicEnergyServices.services.AuthService;
import BuildWeekTeam7.EpicEnergyServices.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    private final String putPayload = "{\"email\":\"email@email.com\",\"password\":\"password\", \"username\":\"username1\",\"name\":\"name1\",\"surname\":\"surname1\"}";

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthService authService;

    @Test
    void UserFindAll() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        tryUser.setRole(UserRole.ADMIN);
        this.userDAO.save(tryUser);
        String adminToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));
        mockMvc.perform(get("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isNotEmpty());
        this.userDAO.delete(tryUser);
    }

    @Test
    void UserFindById() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        tryUser.setRole(UserRole.ADMIN);
        this.userDAO.save(tryUser);
        String adminToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));
        mockMvc.perform(get("/users/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"));
        this.userDAO.delete(tryUser);
    }

    @Test
    void UserFindByIdAndDelete() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        tryUser.setRole(UserRole.ADMIN);
        this.userDAO.save(tryUser);
        String adminToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));

        User deleteUser = new User("delete", "delete@delete.com", encoder.encode("delete"), "delete", "delete");
        this.userDAO.save(deleteUser);
        User deleteFound = this.userService.findByEmail("delete@delete.com");
        long id = deleteFound.getId();

        mockMvc.perform(delete("/users/" + id)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
                .andExpect(status().isNoContent());
        this.userDAO.delete(tryUser);
    }

    @Test
    void UserFindMe() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        this.userDAO.save(tryUser);
        String normalToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));
        mockMvc.perform(get("/users/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + normalToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("email@email.com"));
        this.userDAO.delete(tryUser);
    }

    @Test
    void UserModifyMe() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        this.userDAO.save(tryUser);
        String normalToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));
        mockMvc.perform(put("/users/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + normalToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(putPayload))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("username1"))
                .andExpect(jsonPath("$.name").value("name1"))
                .andExpect(jsonPath("$.surname").value("surname1"));
        this.userDAO.delete(tryUser);
    }

    @Test
    void UserDeleteMe() throws Exception {
        User tryUser = new User("username", "email@email.com", encoder.encode("password"), "name", "surname");
        this.userDAO.save(tryUser);
        String normalToken = this.authService.login(new UserLoginDTO("email@email.com", "password"));
        mockMvc.perform(delete("/users/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + normalToken))
                .andExpect(status().isNoContent());
    }
}
