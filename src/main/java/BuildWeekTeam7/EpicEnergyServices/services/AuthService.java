package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.exceptions.UnauthorizedException;
import BuildWeekTeam7.EpicEnergyServices.payloads.NewUserDTO;
import BuildWeekTeam7.EpicEnergyServices.payloads.UserLoginDTO;
import BuildWeekTeam7.EpicEnergyServices.repositories.UserDAO;
import BuildWeekTeam7.EpicEnergyServices.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public User register(NewUserDTO payload) {
        if (this.userDAO.existsByUsernameAndEmail(payload.username(), payload.email()))
            throw new BadRequestException("Email " + payload.email() + " and Username " + payload.username() + " are already taken");
        if (this.userDAO.existsByEmail(payload.email()))
            throw new BadRequestException("Email ".concat(payload.email()).concat(" is already taken"));
        if (this.userDAO.existsByUsername(payload.username()))
            throw new BadRequestException("Username " + payload.username() + " is already taken");
        User newUser = new User(payload.username(), payload.email(), encoder.encode(payload.password()), payload.name(), payload.surname());
        return this.userDAO.save(newUser);
    }

    public String login(UserLoginDTO payload) {
        User found = this.userService.findByEmail(payload.email());
        if (!encoder.matches(found.getPassword(), payload.password()))
            throw new UnauthorizedException("Password is wrong");
        return jwtTools.createToken(found);
    }
}
