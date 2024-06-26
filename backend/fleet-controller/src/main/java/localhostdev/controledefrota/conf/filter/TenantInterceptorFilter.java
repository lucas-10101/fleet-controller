package localhostdev.controledefrota.conf.filter;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import localhostdev.controledefrota.services.identity.TenantIdentifierResolver;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantInterceptorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            var subdomainParts = request.getServerName().split("\\.");
            if (subdomainParts.length < 3) {
                TenantIdentifierResolver.setRequestTenant(null);
            }

            TenantIdentifierResolver.setRequestTenant(subdomainParts[0]);
        } catch (Exception e) {
            TenantIdentifierResolver.setRequestTenant(null);
        }
        chain.doFilter(request, response);
    }
}
