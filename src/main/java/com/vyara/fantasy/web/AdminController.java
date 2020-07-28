package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.ViewModels.AllUsersViewModel;
import com.vyara.fantasy.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ModelAndView getUsers(ModelAndView modelAndView) {
        List<AllUsersViewModel> admins = this.adminService.getAllUsersForRole("ADMIN", "ROOT");
        List<AllUsersViewModel> moderators = this.adminService.getAllUsersForRole("MODERATOR", "ADMIN");
        List<AllUsersViewModel> regularUsers = this.adminService.getAllUsersForRole("USER", "MODERATOR");
        modelAndView.setViewName("allUsers");
        modelAndView.addObject("admins", admins);
        modelAndView.addObject("moderators", moderators);
        modelAndView.addObject("regularUsers", regularUsers);

        return modelAndView;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/promote/{role}/{name}")
    public String promoteUser(@PathVariable String role, @PathVariable String name) {
        this.adminService.addRole(role, name);


        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/demote/{role}/{name}")
    public String demoteUserUser(@PathVariable String role, @PathVariable String name) throws AuthenticationException {
        this.adminService.removeRole(role, name);


        return "redirect:/admin";
    }




}
