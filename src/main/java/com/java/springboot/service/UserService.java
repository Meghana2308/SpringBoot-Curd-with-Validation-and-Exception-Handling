package com.java.springboot.service;

import com.java.springboot.dto.UserDto;
import com.java.springboot.entity.User;
import com.java.springboot.exception.UserNotFoundException;
import com.java.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDto userDto){
        User user = User.build(0,userDto.getName(),userDto.getEmail(),userDto.getMobile(),userDto.getGender(),userDto.getAge(),userDto.getNationality() );
        return userRepository.save(user);
    }

    public List<User> saveAllUsers(List<UserDto> userDtos) {
        List<User> users = userDtos.stream()
                .map(userDto -> User.build(0, userDto.getName(), userDto.getEmail(), userDto.getMobile(),
                        userDto.getGender(), userDto.getAge(), userDto.getNationality()))
                .toList();
        return userRepository.saveAll(users);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(int id) throws UserNotFoundException {
        User user= userRepository.findById(id);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("user not found with id : "+id);
        }
    }

    public List<User> findByName(String name) throws UserNotFoundException {
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with name: " + name);
        }
        return users;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return user;
    }


    public User updateUser(int id, UserDto userDto) throws UserNotFoundException {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setMobile(userDto.getMobile());
        existingUser.setGender(userDto.getGender());
        existingUser.setAge(userDto.getAge());
        existingUser.setNationality(userDto.getNationality());

        // Save the updated user
        return userRepository.save(existingUser);
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(int id) throws UserNotFoundException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
