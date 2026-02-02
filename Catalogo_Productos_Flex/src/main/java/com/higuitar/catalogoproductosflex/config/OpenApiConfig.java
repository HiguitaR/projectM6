package com.higuitar.catalogoproductosflex.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the OpenAPI documentation for the application.
 * Provides an OpenAPI bean that defines the general information for the API
 * including title, description, version, contact, and license details.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Provides the OpenAPI configuration for the Catalogo Productos Flex API.
     * This method defines the general information about the API including the title,
     * description, version, contact details, and license information.
     *
     * @return an {@link OpenAPI} instance containing metadata for the API, such as title,
     *         version, description, contact information, and license details.
     */
    @Bean
    public OpenAPI catalogoProductosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalogo Productos Flex API")
                        .description("REST API for product catalog management (CRUD + search).")
                        .version("v1")
                        .contact(new Contact()
                                .name("Your Name / Team")
                                .email("dev-team@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}

