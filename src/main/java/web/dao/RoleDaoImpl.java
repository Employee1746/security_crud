package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getRolesList() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Set<Role> getRolesFromArray(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            if (role.equals("ROLE_USER")) {
                roleSet.add(new Role(1L, "ROLE_USER"));
            } else if (role.equals("ROLE_ADMIN")) {
                roleSet.add(new Role(2L, "ROLE_ADMIN"));
            }
        }
        return roleSet;
    }
}
