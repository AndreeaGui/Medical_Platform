package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.UserDTO;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Doctor;
import com.example.springdemo.entities.User;

public class UserMapper {

    public static UserDTO generateUserDTOFromUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .build();
    }

    public static User generateUserFromUserDTO(UserDTO user){
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .build();
    }

    public static User generateUserWithIdFromUserDTO(UserDTO user){
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .id(user.getId())
                .build();
    }

    public static Caregiver generateCaregiverFromUserDTO(UserDTO userDTO){
        return Caregiver.caregiverFromUserBuilder().
                user(UserMapper.generateUserFromUserDTO(userDTO))
                .build();
    }

    public static Doctor generateDoctorFromUserDTO(UserDTO userDTO){
        return Doctor.doctorFromUserBuilder().
                user(UserMapper.generateUserFromUserDTO(userDTO))
                .build();
    }


}
