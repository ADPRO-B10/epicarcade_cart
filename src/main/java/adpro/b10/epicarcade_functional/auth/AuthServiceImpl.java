package adpro.b10.epicarcade_functional.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserDao userDao;

    public UserDto createUser(SignupReqeust signupReqeust) {
        UserEntity user = new UserEntity();
        user.setUsername(signupReqeust.getUsername());
        user.setPassword(signupReqeust.getPassword());
        user.setRole("BUYER");
        userDao.save(user);
        return new UserDto(user);
    }
}
