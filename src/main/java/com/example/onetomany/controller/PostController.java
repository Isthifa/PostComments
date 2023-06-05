package com.example.onetomany.controller;

import com.example.onetomany.dto.PostDetailsDto;
import com.example.onetomany.entity.Comment;
import com.example.onetomany.entity.Post;
import com.example.onetomany.repository.CommentRepository;
import com.example.onetomany.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/showall")
    public Page<Post> showall(Pageable pageable)
    {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public Post createpost(@Valid @RequestBody Post post)
    {
        return postRepository.save(post);
    }

    @PutMapping("/update/{uid}")
    public Post update(@PathVariable long uid,@Valid @RequestBody Post postrequest)
    {
        return postRepository.findById(uid).map(post -> {
            post.setTitle(postrequest.getTitle());
            post.setDescription(postrequest.getDescription());
            post.setContent(postrequest.getContent());
        return postRepository.save(post);}
        ).orElseThrow(()->new RuntimeException("Not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletebyid(@PathVariable long id)
    {
        return postRepository.findById(id).map(post -> {
             postRepository.delete(post);
             return ResponseEntity.ok().build();
        }).orElseThrow(()->new RuntimeException("Not found"));
    }

    @GetMapping("/getalldetails")
    public List<?> findalldetails()
    {
        List<Object[]> posts= postRepository.findalldetails();
        List<PostDetailsDto> postDetailsDtos=new ArrayList<>();
        for(Object[] post:posts)
        {
            PostDetailsDto postDetails = new PostDetailsDto((String)post[0],
                    (String)post[1],
                    (String)post[2],
                    (String)post[3]
            );
            postDetailsDtos.add(postDetails);
        }
        return postDetailsDtos;

    }

    @GetMapping("/byid/{id}")
    public Post findbyid(@PathVariable long id)
    {
        return postRepository.findById(id).filter(p->p.getId()==id).orElseThrow(()->new RuntimeException("not foudnd"));
    }
}
