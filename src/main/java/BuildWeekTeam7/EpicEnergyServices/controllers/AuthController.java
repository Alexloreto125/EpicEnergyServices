package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.payloads.NewUserDTO;
import BuildWeekTeam7.EpicEnergyServices.payloads.UserLoginDTO;
import BuildWeekTeam7.EpicEnergyServices.payloads.UserLoginResponseDTO;
import BuildWeekTeam7.EpicEnergyServices.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO payload,
                                      BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return new UserLoginResponseDTO(this.authService.login(payload));
    }

    @PostMapping("/register")
    public User register(@RequestBody @Validated NewUserDTO payload,
                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.authService.register(payload);
    }
}
