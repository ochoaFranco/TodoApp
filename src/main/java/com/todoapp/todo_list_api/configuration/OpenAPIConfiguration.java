package com.todoapp.todo_list_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");
        Contact myContact = new Contact();
        myContact.setName("Franco Ochoa");
        myContact.setEmail("myEmail.com.ar");
        Info info = new Info()
                .title("To-do System API")
                .version("1.0")
                .description("This API exposes endpoints for managing users tasks")
                .contact(myContact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
