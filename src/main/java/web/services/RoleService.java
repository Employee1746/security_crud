package web.services;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> getRolesList();

    public Set<Role> getRolesFromArray(String[] roles);
}