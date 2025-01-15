package jblog.vaild;

import jblog.service.UserService;
import jblog.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignFormValidator implements Validator {
    private final UserService userService;

    public SignFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserVo.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserVo userVo = (UserVo) object;

        System.out.println(userVo.getId());

        if (userService.checkUserById(userVo.getId())) {
            errors.rejectValue("id","invalid.id",new Object[]{userVo.getId()},"이미 사용중인 이메일입니다.");
        }
    }
}
