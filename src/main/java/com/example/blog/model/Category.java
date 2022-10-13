package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Entity
public class Category extends BaseEntity {
    @Column(name = "header")
    private String header;
    @Column(name = "description")
    private String description;
    @Column(name = "image_path")
    private String imagePath;
}
