package ru.jm.springboot2.springboot2.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.jm.springboot2.springboot2.dto.UserDto;
import ru.jm.springboot2.springboot2.model.User;
import ru.jm.springboot2.springboot2.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto user = (UserDto) o;
        User checkEmail = userService.getUserByName(user.getEmail());




        if(userService.getUserByName(user.getEmail())!=null){
            errors.rejectValue("email","","This email already exists");
        }
    }
}
