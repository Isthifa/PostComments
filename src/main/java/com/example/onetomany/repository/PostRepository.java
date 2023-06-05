package com.example.onetomany.repository;

import com.example.onetomany.dto.PostDetailsDto;
import com.example.onetomany.dto.Postswithcommentsdto;
import com.example.onetomany.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p.title,p.description,p.content,c.text from Post p join Comment c on p.id=c.post.id")
    public List<Object[]> findalldetails();

    @Query("SELECT p.id,p.title,c.text from Post p left join Comment c")
    List<Object[]> listall();

}
