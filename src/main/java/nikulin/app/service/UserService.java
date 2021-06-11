package nikulin.app.service;

import javassist.NotFoundException;
import nikulin.app.model.Role;
import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("userRepo") UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(name);
        if (user==null){
            throw  new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
        return true;
    }

    public void update(User user, String username, String password) {
        String usernameFromDB = user.getUsername();
        String passwordFromDB=user.getPassword();
        if (!usernameFromDB.equals(username)){
            user.setUsername(username);
        }
        if (!passwordFromDB.equals(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
