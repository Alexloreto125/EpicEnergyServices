package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.mailgun.EmailDTO;
import BuildWeekTeam7.EpicEnergyServices.mailgun.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/email")
public class EmailController {

    @Autowired
    private MailgunSender mailgunSender;

    @PostMapping ("/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void sendEmail(@RequestBody @Validated EmailDTO payload,  BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        Clienti recipient = new Clienti(payload.email());
        this.mailgunSender.sendEmail(recipient, payload.subject(), payload.text());
    }
}
