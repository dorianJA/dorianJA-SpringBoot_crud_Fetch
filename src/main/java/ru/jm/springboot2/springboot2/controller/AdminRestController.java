package ru.jm.springboot2.springboot2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.jm.springboot2.springboot2.dto.UserDto;
import ru.jm.springboot2.springboot2.model.User;
import ru.jm.springboot2.springboot2.service.UserService;
import ru.jm.springboot2.springboot2.validator.UserValidator;

import javax.validation.Valid;
import java.util.List;


@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @PostMapping("/admin/add_user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, Errors errors) {
        userValidator.validate(userDto,errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        } else {
            return new ResponseEntity<>(userService.addUser(userDto.toUser()), HttpStatus.OK);
        }

    }

    @GetMapping("/admin/get_users")
    public ResponseEntity<Object> getUsersInfo() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("/admin/edit_user")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserDto user,Errors errors) {

        if(errors.hasErrors()){
            return  ResponseEntity.badRequest().body(errors.getAllErrors());
        }else {
            User updateUser = user.toUser();
            updateUser.setId(user.getId());
            userService.addUser(updateUser);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
    }

    @DeleteMapping("/admin/delete_user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
    }

//    private User buildUser(ObjectNode objectNodeUser) {
//        User user = new User();
//        user.setFirstName(objectNodeUser.get("firstName").textValue());
//        user.setLastName(objectNodeUser.get("lastName").textValue());
//        user.setEmail(objectNodeUser.get("email").textValue());
//        user.setAge(objectNodeUser.get("age").textValue());
//        user.setPassword(objectNodeUser.get("password").textValue());
//        Role role = new Role();
//        role.setId(Long.valueOf(objectNodeUser.get("roles").textValue()));
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//        return user;
//    }

}
