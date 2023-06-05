package com.example.onetomany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Postswithcommentsdto {

    private Long id;
    private String title;

    private List<String> text;

}
