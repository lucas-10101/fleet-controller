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

    private static final ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RealmRepository realmRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = this.userRepository
                .findByUsernameAndRealm(username, realmRepository.getReferenceById(AuthenticationService.getTenant()))
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

    private static String getTenantFromRequest() {
        return AuthenticationService.tenantHolder.get();
    }

    private static String getTenantFromUser() {
        var realm = AuthenticationService.getCurrentUser();
        return realm == null ? null : realm.getTenant();
    }

    public static String getTenant() {
        var tenant = AuthenticationService.getTenantFromUser();
        tenant = tenant != null ? tenant : AuthenticationService.getTenantFromRequest();

        if (tenant == null) {
            throw new RuntimeException("Tenant is null");
        }
        return tenant;
    }

    public static void setRequestTenant(String tenantName) {
        AuthenticationService.tenantHolder.set(tenantName);
    }
}
