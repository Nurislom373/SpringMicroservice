package og.khasanof.grafana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 3:36 PM
 */
@Configuration
public class GrafanaWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private RestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

}
