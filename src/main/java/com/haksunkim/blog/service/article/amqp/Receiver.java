package com.haksunkim.blog.service.article.amqp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haksunkim.blog.service.article.dao.ArticleDAO;
import com.haksunkim.blog.service.article.domain.Article;

@Component
public class Receiver {
	final static Logger log = LoggerFactory.getLogger(Receiver.class);
	final static JsonParser jsonParser = JsonParserFactory.getJsonParser();
	final static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private ArticleDAO articleDao;
	
	public String receiveMessage(String message) {
		log.info("Received > " + message);
		String response = "";
		
		// parse JSON formatted message
		Map<String, Object> result = jsonParser.parseMap(message);
		String route = (String) result.get("route");
		String method = (String) result.get("method");
		
		if (route.equals("/articles") && method.equals("get")) {
			Iterable<Article> articles = articleDao.findAll();
			try {
				response = mapper.writeValueAsString(articles);
				log.info("Response > " + response);
			} catch (JsonProcessingException e) {
				log.error("JSON processing exception", e);
				response = "{\"error\":\"" + e.getMessage() + "\"}";
			}
		}
		
		return response;
	}
}
