package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.models.AllUsersModel;
import com.vyara.fantasy.repositories.RoleRepository;
import com.vyara.fantasy.repositories.UserRepository;
import com.vyara.fantasy.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<AllUsersModel> getAllUsersForRole(String searchRole, String excludeRole){
       List <AllUsersModel> usersModels = new ArrayList<>();

       this.userRepository.getAllByAuthorities(
               this.roleRepository.getByAuthority(searchRole)
       ).forEach(user -> {
           if (!user.getAuthorities().contains(this.roleRepository.getByAuthority(excludeRole))) {
               AllUsersModel model = new AllUsersModel(user.getUsername(), user.getEmail(), searchRole);
               usersModels.add(model);
           }
       });

       return usersModels;
    }

    @Override
    public void removeRole(String role, String userName){
        User user = this.userRepository.getByUsername(userName);
        user.getAuthorities().
                remove(this.roleRepository.getByAuthority(role));

        this.userRepository.save(user);

    }

    @Override
    public void addRole(String role, String userName){
        User user = this.userRepository.getByUsername(userName);
        user.getAuthorities().
                add(this.roleRepository.getByAuthority(role));

        this.userRepository.save(user);

    }

}
