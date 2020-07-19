package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.AllUsersModel;
import com.vyara.fantasy.services.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class AdminController {
    private final AdminService adminService;
    Logger logger = Logger.getLogger(AdminService.class.getName());

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ModelAndView getUsers(ModelAndView modelAndView) {
        List<AllUsersModel> admins = this.adminService.getAllUsersForRole("ADMIN", "ROOT");
        List<AllUsersModel> moderators = this.adminService.getAllUsersForRole("MODERATOR", "ADMIN");
        List<AllUsersModel> regularUsers = this.adminService.getAllUsersForRole("USER", "MODERATOR");
        modelAndView.setViewName("allUsers");
        modelAndView.addObject("admins", admins);
        modelAndView.addObject("moderators", moderators);
        modelAndView.addObject("regularUsers", regularUsers);

        return modelAndView;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/promote/{role}/{name}")
    public String promoteUser(@PathVariable String role, @PathVariable String name) {
        this.adminService.addRole(role, name.trim());


        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/demote/{role}/{name}")
    public String demoteUserUser(@PathVariable String role, @PathVariable String name) {
        this.adminService.removeRole(role, name.trim());


        return "redirect:/admin";
    }




}
