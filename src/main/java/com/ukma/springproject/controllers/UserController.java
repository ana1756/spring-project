package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.KeyService;
import com.ukma.springproject.service.UserService;
import com.ukma.springproject.service.impl.DBUserDetailsService;
import com.ukma.springproject.utils.FileUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;
    private final DBUserDetailsService userDetailsService;
    private final KeyService keyService;

    public UserController(UserService userService, DBUserDetailsService userDetailsService, KeyService keyService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.keyService = keyService;
    }

    @GetMapping
    String showProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        User user = userDetailsService.loadUserByUsername(currentUsername).getUser();
        System.out.println("CURRENT USER: " + currentUsername);
        model.addAttribute("imageName", currentUsername.split("@")[0]);
        model.addAttribute("user", user);
        return "profile";
    }

    @ModelAttribute(value = "user")
    public User newUser() {
        return new User();
    }

    @GetMapping("/keys")
    public String showKeys(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        List<Key> keys = keyService.findByUser(user.getId());
        model.addAttribute("keys", keys);
        return "keys";
    }


    @PostMapping("/update")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        User storedUser = userService.findById(currentUser.getId());

        if (!multipartFile.isEmpty()) {
            String fileName = manageFileUpload(storedUser, multipartFile);
            storedUser.setAvatarName(fileName);
        }

        if (user.getUsername() != null) {
            storedUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            storedUser.setEmail(user.getEmail());
        }

        if (user.getBalance() != null) {
            storedUser.setBalance(user.getBalance());
        }

        userService.update(storedUser);
        return "redirect:/profile";
    }

    private String manageFileUpload(User user, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setAvatarName(fileName);
        String uploadDir = "target/classes/data/avatars/" + user.getId();
        FileUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }

}

