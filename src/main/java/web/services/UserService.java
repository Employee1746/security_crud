package web.services;

import web.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public void saveUser(User user, String[] chosenRoles);

    public void deleteUser(Long id);

    public void updateUser(User user, String[] updatedRoles);

    public User getUserById(Long id);

    public User getUserByName(String username);
}
