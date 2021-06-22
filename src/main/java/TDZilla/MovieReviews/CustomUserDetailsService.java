package TDZilla.MovieReviews;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        if(username.equalsIgnoreCase("tharindu")){
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("tharindu","srisunsteel",authList);
        }
        else{
            throw new UsernameNotFoundException("User name Could not be Found");
        }
    }
}
