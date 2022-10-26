package tr.com.yavuzduran.pim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserServiceImp userService, RoleServiceImp roleService) {
//        return args -> {
//            roleService.save("ROLE_USER");
//            roleService.save("ROLE_ADMIN");
//
//            userService.save(UserDto.builder()
//                    .name("yavuz")
//                    .surname("duran")
//                    .username("yavuz.duran")
//                    .password("1234")
//                    .roles(new ArrayList<>()).build());
//
//            userService.addRoleTo("yavuz.duran", "ROLE_USER");
//        };
//    }
}
