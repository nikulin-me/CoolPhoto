package nikulin.app.service;

import javassist.NotFoundException;
import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(name);
        if (user==null){
            throw  new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}
