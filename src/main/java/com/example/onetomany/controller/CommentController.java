package com.example.onetomany.controller;

import com.example.onetomany.entity.Comment;
import com.example.onetomany.repository.CommentRepository;
import com.example.onetomany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private PostRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/post/{pid}/comments")
    public Page<Comment> getallcommentbypostid(@PathVariable Long pid, Pageable pageable)
    {
        return commentRepository.findByPostId(pid,pageable);
    }
    @PostMapping("/post/{postid}/comments")
    public Comment createComment(@PathVariable long postid, @RequestBody Comment comment)
    {
        return repository.findById(postid).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(()->new RuntimeException("Not found"));
    }
}
