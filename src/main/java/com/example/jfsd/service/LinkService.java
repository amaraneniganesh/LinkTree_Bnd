package com.example.jfsd.service;

import com.example.jfsd.model.Link;
import com.example.jfsd.model.User;
import com.example.jfsd.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link saveLink(Link link) {
        return linkRepository.save(link);
    }

    public List<Link> getLinksByUser(User user) {
        return linkRepository.findByUser(user);
    }

    public Link findLinkById(Long id) {
        Optional<Link> link = linkRepository.findById(id);
        return link.orElse(null);
    }

    public void deleteLink(Link link) {
        linkRepository.delete(link);
    }
}
