package com.example.jfsd.controller;

import com.example.jfsd.model.Link;
import com.example.jfsd.model.User;
import com.example.jfsd.service.LinkService;
import com.example.jfsd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
@CrossOrigin(origins = "http://localhost:3000") // Adjust to match your frontend URL
public class LinkController {
    private final LinkService linkService;
    private final UserService userService;

    public LinkController(LinkService linkService, UserService userService) {
        this.linkService = linkService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addLink(@RequestParam String username, @RequestBody Link link) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        link.setUser(user);
        linkService.saveLink(link);
        return ResponseEntity.ok("Link added successfully.");
    }

    @GetMapping
    public ResponseEntity<List<Link>> getLinks(@RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(linkService.getLinksByUser(user));
    }

    @GetMapping("/public/{username}")
    public ResponseEntity<List<Link>> getPublicLinks(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(linkService.getLinksByUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLink(@PathVariable Long id, @RequestBody Link updatedLink) {
        Link link = linkService.findLinkById(id);
        if (link == null) {
            return ResponseEntity.badRequest().body("Link not found.");
        }
        link.setAppName(updatedLink.getAppName());
        link.setUrl(updatedLink.getUrl());
        linkService.saveLink(link);
        return ResponseEntity.ok("Link updated successfully.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Link> getLinkById(@PathVariable Long id) {
        Link link = linkService.findLinkById(id);
        if (link == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(link);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLink(@PathVariable Long id) {
        Link link = linkService.findLinkById(id);
        if (link == null) {
            return ResponseEntity.badRequest().body("Link not found.");
        }
        linkService.deleteLink(link);
        return ResponseEntity.ok("Link deleted successfully.");
    }
}
