package com.labs.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.ws.rs.core.Application;

@SecurityScheme(
        securitySchemeName = "JWT",
        description = "JWT authentication with bearer token",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "Bearer [token]"
)
@OpenAPIDefinition(
        info = @Info(
                title = "bubble API",
                description = "Mohamed Chabbouh",
                contact = @Contact(name = "med chabbouh", email = "mohamed.chabbouh@esprit.tn"),
                version = "1.0.0-SNAPSHOT"
        ),
        security = @SecurityRequirement(name = "JWT")
)
public class OpenApiConfig extends Application {
}
