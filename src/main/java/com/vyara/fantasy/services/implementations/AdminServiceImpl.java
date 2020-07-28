package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.entities.secondary.Role;
import com.vyara.fantasy.data.models.ViewModels.AllUsersViewModel;
import com.vyara.fantasy.repositories.RoleRepository;
import com.vyara.fantasy.repositories.UserRepository;
import com.vyara.fantasy.services.AdminService;
import com.vyara.fantasy.services.AuthenticatedUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public AdminServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository, AuthenticatedUserService authenticatedUserService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticatedUserService = authenticatedUserService;
    }


    @Override
    public List<AllUsersViewModel> getAllUsersForRole(String searchRole, String excludeRole){
       List <AllUsersViewModel> usersModels = new ArrayList<>();

       this.userRepository.getAllByAuthorities(
               this.roleRepository.getByAuthority(searchRole)
       ).forEach(user -> {
           if (!user.getAuthorities().contains(this.roleRepository.getByAuthority(excludeRole))) {
               AllUsersViewModel model = new AllUsersViewModel(user.getUsername(), user.getEmail(), searchRole);
               usersModels.add(model);
           }
       });

       return usersModels;
    }

    @Override
    public void removeRole(String roleIndex, String userName) throws AuthenticationException {

        User user = this.userRepository.getByUsername(userName);
        if (user.getAuthorities().contains(this.roleRepository.getByAuthority("ROOT"))){
            throw new AuthenticationException();
        }
        if (!this.authenticatedUserService.getCurrentUser()
                .getAuthorities().contains(this.roleRepository.getByAuthority("ROOT"))
                && user.getAuthorities().contains(this.roleRepository.getByAuthority("ADMIN"))){
            throw new AuthenticationException();
        }
        user.getAuthorities().
                remove(getRoleByIndex(roleIndex));

        this.userRepository.save(user);

    }

    @Override
    public void addRole(String roleIndex, String userName){
        User user = this.userRepository.getByUsername(userName);
        user.getAuthorities().
                add(getRoleByIndex(roleIndex));

        this.userRepository.save(user);

    }

    private Role getRoleByIndex (String roleIndex){
        Role[] availableRoles = new Role[3];
        availableRoles[0]= this.roleRepository.getByAuthority("MODERATOR");
        availableRoles[1] = this.roleRepository.getByAuthority("ADMIN");
        return availableRoles[Integer.parseInt(roleIndex)];

    }

}
