package tr.com.yavuzduran.pim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserServiceImp userService) {
//        return args -> {
//            userService.save(RoleDto.builder().name("ROLE_USER").build());
//            userService.save(RoleDto.builder().name("ROLE_ADMIN").build());
//
//            userService.save(UserDto.builder()
//                    .name("yavuz")
//                    .surname("duran")
//                    .username("yavuz.duran")
//                    .password("1234")
//                    .roles(new HashSet<>()).build());
//
//            userService.addRoleTo("yavuz.duran", "ROLE_USER");
//        };
//    }
}
