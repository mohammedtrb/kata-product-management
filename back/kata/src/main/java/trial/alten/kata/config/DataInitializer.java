package trial.alten.kata.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import trial.alten.kata.models.Role;
import trial.alten.kata.services.RoleService;
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleService roleService) {

        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        Role userRole = new Role();
        userRole.setName("USER");

        return args -> {
            if (roleService.createRole(adminRole) != null) {
                System.out.println("Role ADMIN created");
            }
            if (roleService.createRole(userRole) != null) {
                System.out.println("Role USER created");
            }
        };
    }
}
