package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.models.ViewModels.CheckUserResponseModel;
import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import com.vyara.fantasy.repositories.UserRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.HashingService;
import com.vyara.fantasy.services.RoleService;
import com.vyara.fantasy.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final RoleService roleService;
    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, HashingService hashingService, RoleService roleService, AuthenticatedUserService authenticatedUserService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.roleService = roleService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Override
    public void register(UserRegisterServiceModel model) throws Exception {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            throw new Exception("Password do not match");
        }
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(model.getPassword()));
        setRoles(user);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.getByUsername(s);
    }

    private void setRoles(User user) {
        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesInDb();
            user.setAuthorities(this.roleService.getAllRoles());
        } else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleService.findByRoleName("USER"));
        }
    }

    @Override
    public void changePassword(ChangePasswordServiceModel changePasswordServiceModel) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();
        if (!changePasswordServiceModel.getNewPassword().equals(changePasswordServiceModel.getConfirmPassword())
                || hashingService.hash(changePasswordServiceModel.getCurrentPassword()).equals(user.getPassword())) {
            throw new Exception("Password does not match");
        }

        user.setPassword(hashingService.hash(changePasswordServiceModel.getNewPassword()));
        this.userRepository.save(user);


    }

    @Override
    public void changeEmail(ChangeEmailServiceModel changeEmailServiceModel) throws Exception {
        User user = this.authenticatedUserService.getCurrentUser();
        if (!changeEmailServiceModel.getNewEmail().equals(changeEmailServiceModel.getConfirmEmail())
                || hashingService.hash(changeEmailServiceModel.getPassword()).equals(user.getPassword())) {
            throw new Exception("Password does not match");
        }

        user.setEmail(changeEmailServiceModel.getNewEmail());
        this.userRepository.save(user);

    }

    @Override
    public List<CheckUserResponseModel> getCheckUsers(){
        List<CheckUserResponseModel> models = new ArrayList<>();
        this.userRepository.findAll().forEach(u->{
            CheckUserResponseModel model = new CheckUserResponseModel();
            model.setUsername(DigestUtils.sha256Hex(u.getUsername()));
            model.setEmail(DigestUtils.sha256Hex(u.getEmail()));
            models.add(model);

        });


        return models;
    };


}