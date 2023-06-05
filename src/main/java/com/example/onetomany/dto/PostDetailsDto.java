package com.example.onetomany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailsDto {
        private String Title;
        private String Description;
        private String Content;
        private String CommentText;

}
