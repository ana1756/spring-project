package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.UserService;
import com.ukma.springproject.service.impl.DBUserDetailsService;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;


@RestController()
public class ImageController {

    private final DBUserDetailsService userDetailsService;

    public ImageController(DBUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(path = "/getImage", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDetailsService.loadUserByUsername(username).getUser();

        File fi = new File("target/classes/data/avatars/" + user.getId() + "/" + user.getAvatarName());
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(fi.toPath());
        } catch (NoSuchFileException e) {
            return Files.readAllBytes(new File("target/classes/data/avatars/default-avatar.png").toPath());
        }
        return bytes;
    }

}
