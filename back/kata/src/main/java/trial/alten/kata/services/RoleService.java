package trial.alten.kata.services;

import org.springframework.stereotype.Service;
import trial.alten.kata.models.Role;
import trial.alten.kata.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
