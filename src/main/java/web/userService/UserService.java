package web.userService;

import web.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public void updateUser(User user);

    public User getUserById(Long id);
}
