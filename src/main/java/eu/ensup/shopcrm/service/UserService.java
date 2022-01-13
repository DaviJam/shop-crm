package eu.ensup.shopcrm.service;

import eu.ensup.shopcrm.domain.User;
import eu.ensup.shopcrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String name){
        return this.userRepository.findByName(name);
    }

    public Boolean register(User user) {
        return this.userRepository.save(user) != null;
    }
}
