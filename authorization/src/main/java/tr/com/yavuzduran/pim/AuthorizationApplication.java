package tr.com.yavuzduran.pim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.authorization.service.RoleServiceImp;
import tr.com.yavuzduran.pim.authorization.service.UserServiceImp;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserServiceImp userService, RoleServiceImp roleService) {
        return args -> {
            roleService.save("ROLE_USER");
            roleService.save("ROLE_ADMIN");

            userService.save(UserDto.builder()
                    .name("yavuz")
                    .surname("duran")
                    .username("yavuz.duran")
                    .password("1234")
                    .roles(new ArrayList<>()).build());

            userService.addRoleTo("yavuz.duran", "ROLE_USER");
        };
    }
}
