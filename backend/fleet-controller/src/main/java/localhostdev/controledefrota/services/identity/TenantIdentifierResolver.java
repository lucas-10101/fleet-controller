package localhostdev.controledefrota.services.identity;

import java.util.logging.Logger;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Service;

@Service
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    private static final ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    public static void setRequestTenant(String tenantName) {
        TenantIdentifierResolver.tenantHolder.set(tenantName);
    }

    private static String getTenantFromRequest() {
        return TenantIdentifierResolver.tenantHolder.get();
    }

    private static String getTenantFromUser() {
        var realm = AuthenticationService.getCurrentUser();
        return realm == null ? null : realm.getTenant();
    }

    public static String getTenant() {
        var tenant = TenantIdentifierResolver.getTenantFromUser();
        tenant = tenant != null ? tenant : TenantIdentifierResolver.getTenantFromRequest();

        if (tenant == null) {
            Logger.getLogger(TenantIdentifierResolver.class.getName()).warning("Tenant is null");
        }
        return tenant;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        var tenant = TenantIdentifierResolver.getTenant();
        return tenant != null ? tenant : "";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
