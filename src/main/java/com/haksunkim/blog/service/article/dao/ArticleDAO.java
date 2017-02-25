package com.haksunkim.blog.service.article.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.haksunkim.blog.service.article.domain.Article;

@Transactional
public interface ArticleDAO extends CrudRepository<Article, Long> {
	
}
