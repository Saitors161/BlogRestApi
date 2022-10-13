package com.example.blog.repository;

import com.example.blog.model.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @EntityGraph(attributePaths = {"category"})
    List<Article> findAll();
}
