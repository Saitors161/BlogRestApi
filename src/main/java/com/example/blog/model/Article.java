package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
@Entity
public class Article extends BaseEntity{
    @Column(name = "header")
    private String header;
    @Column(name = "description")
    private String description;
    @Column(name = "image_path")
    private String imagePath;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
