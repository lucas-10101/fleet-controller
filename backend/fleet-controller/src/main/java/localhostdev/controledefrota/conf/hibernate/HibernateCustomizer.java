package localhostdev.controledefrota.conf.hibernate;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import localhostdev.controledefrota.services.identity.TenantIdentifierResolver;

@Component
public class HibernateCustomizer implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, TenantIdentifierResolver.class);
    }
}
