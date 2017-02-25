package com.haksunkim.blog.service.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haksunkim.blog.service.article.dao.ArticleDAO;
import com.haksunkim.blog.service.article.domain.Article;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@RequestMapping(value="/articles",method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Article> getArticles() {
		return articleDAO.findAll();
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Article> createArticle(@RequestBody Article article) {
		Article savedArticle = articleDAO.save(article);
		
		if (savedArticle.getId() > 0) {
			return new ResponseEntity<Article>(savedArticle, HttpStatus.OK);
		}
		
		return new ResponseEntity<Article>(article, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/articles/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
		Article updatedArticle = articleDAO.findOne(article.getId());
		if (updatedArticle.getId() == id) {
			updatedArticle = articleDAO.save(article);
			
			return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
		}
		
		return new ResponseEntity<Article>(article, HttpStatus.BAD_REQUEST);
	}
}
