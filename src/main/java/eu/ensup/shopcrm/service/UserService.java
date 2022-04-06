package eu.ensup.shopcrm.service;

import eu.ensup.shopcrm.domain.Users;
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

    public Users getUser(String name){
        return this.userRepository.findByusername(name);
    }

    public Boolean register(Users user) {
        return this.userRepository.save(user) != null;
    }
}
