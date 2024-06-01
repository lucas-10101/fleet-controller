package localhostdev.controledefrota.services.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import localhostdev.controledefrota.data.dto.identity.UserDetailsObject;
import localhostdev.controledefrota.data.repository.identity.RealmRepository;
import localhostdev.controledefrota.data.repository.identity.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RealmRepository realmRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = this.userRepository
                .findByUsernameAndRealm(username,
                        realmRepository.getReferenceById(TenantIdentifierResolver.getTenant()))
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var authorities = new java.util.ArrayList<GrantedAuthority>();

        return new UserDetailsObject(
                user.getRealm().getTenant(),
                username,
                user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                authorities);
    }

    public static UserDetailsObject getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetailsObject) {
            return ((UserDetailsObject) authentication.getPrincipal());
        }

        return null;
    }
}
