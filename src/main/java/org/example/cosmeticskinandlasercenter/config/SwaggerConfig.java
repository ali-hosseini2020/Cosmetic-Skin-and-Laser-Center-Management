package org.example.cosmeticskinandlasercenter.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${app.version}") // Assuming you have app.version in application.properties
    private String appVersion;

    @Bean
    public OpenAPI cosmeticCenterOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Cosmetic Skin and Laser Center Management API")
                        .description("API documentation for managing cosmetic center operations.")
                        .version(appVersion))
                .servers(List.of(new Server().url("http://localhost:8080").description("Development Server"))); // Adjust server URL as needed
    }
}
