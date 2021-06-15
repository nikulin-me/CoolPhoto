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
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;



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
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        if (!StringUtils.hasLength(user.getEmail())){
            String message=String.format(
                    "Hi, %s \n"+
                            "Welcome to CoolPhoto. Please, activate in this link:" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"Activation Code",message);
        }
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

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user!=null){
            user.setActivationCode(null);
            userRepo.save(user);
            return true;
        }
        return false;
    }
}
