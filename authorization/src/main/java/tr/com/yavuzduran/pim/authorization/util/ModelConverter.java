package tr.com.yavuzduran.pim.authorization.util;

import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.security.model.Role;
import tr.com.yavuzduran.pim.security.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelConverter {

    private ModelConverter() {
    }

    public static User convert(UserDto userDto) {
        return User.builder().
                name(userDto.getName())
                .roles(convertAllRoleEntity(userDto.getRoles()))
                .surname(userDto.getSurname())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

    public static UserDto convert(User user) {
        return UserDto.builder()
                .name(user.getName())
                .roles(convertAllRole(user.getRoles()))
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static Role convert(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.getName())
                .build();
    }

    public static RoleDto convert(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }

    public static List<UserDto> convertAllUser(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(convert(user));
        }
        return userDtoList;
    }

    public static List<RoleDto> convertAllRole(List<Role> roleList) {
        if (roleList == null || roleList.isEmpty()) {
            return new ArrayList<>();
        }
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roleList) {
            roleDtoList.add(convert(role));
        }
        return roleDtoList;
    }


    public static List<Role> convertAllRoleEntity(List<RoleDto> roleDtoSet) {
        if (roleDtoSet == null || roleDtoSet.isEmpty()) {
            return new ArrayList<>();
        }
        List<Role> roleDtoList = new ArrayList<>();
        for (RoleDto role : roleDtoSet) {
            roleDtoList.add(convert(role));
        }
        return roleDtoList;
    }

}
