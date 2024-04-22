package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.exceptions.NotFoundException;
import BuildWeekTeam7.EpicEnergyServices.payloads.NewUserDTO;
import BuildWeekTeam7.EpicEnergyServices.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Page<User> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);
    }

    public User findById(long id) {
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " has not been found"));
    }

    public User findByIdAndUpdate(long id, NewUserDTO payload) {
        User found = this.findById(id);
        if (found.getEmail().equals(payload.email())) {
            if (found.getUsername().equals(payload.username())) {
                found.setName(payload.name());
                found.setSurname(payload.surname());
                found.setPassword(payload.password());
            } else if (!this.userDAO.existsByUsername(payload.username())) {
                found.setUsername(payload.username());
                found.setName(payload.name());
                found.setSurname(payload.surname());
                found.setPassword(payload.password());
            } else throw new BadRequestException("Username " + payload.username() + " is already taken");
            this.userDAO.save(found);
            return found;
        } else throw new BadRequestException("You are not allowed to change the email without permission");
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        this.userDAO.delete(found);
    }
}
