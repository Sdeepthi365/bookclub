package com.bookclub.bookclub.service.impl;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestBookDao implements BookDao {


	 public Object getBooksDoc(String isbnString) {
	        String openLibraryUrl = "https://openlibrary.org/api/books";

	        RestTemplate rest = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

	        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
	                .queryParam("bibkeys", isbnString)
	                .queryParam("format", "json")
	                .queryParam("jscmd", "details");

	        HttpEntity<?> entity = new HttpEntity<>(headers);
	        ResponseEntity<String> response = rest.exchange(
	                builder.toUriString(),
	                HttpMethod.GET,
	                entity,
	                String.class
	        );

	        String jsonBookList = response.getBody();
	        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBookList);
	    }

	    @Override
	    public List<Book> list() {
	        String isbnString = "ISBN:9780593099322,ISBN:9780261102361,ISBN:9780261102378,ISBN:9780590302715,ISBN:9780316769532";
	        Object doc = getBooksDoc(isbnString);
	        Map<String, Object> bookMap = JsonPath.read(doc, "$");
	        List<Book> books = new ArrayList<Book>();

	        for (Map.Entry<String, Object> entry : bookMap.entrySet()) {
	            String isbn = entry.getKey();
	            Map<String, Object> detailsMap = JsonPath.read(entry.getValue(), "$.details");
	            String title = (String) detailsMap.getOrDefault("title", "N/A");
	            String subtitle = (String) detailsMap.getOrDefault("subtitle", "");
	            Integer pages = (Integer) detailsMap.getOrDefault("number_of_pages", 0);
	            String infoUrl = (String) ((Map) entry.getValue()).getOrDefault("info_url", "");

	            books.add(new Book(isbn, title, subtitle, infoUrl, pages));
	        }

	        return books;
	    }

	    @Override
	    public Book find(String key) {
	        Object doc = getBooksDoc(key);
	        List<String> isbns = JsonPath.read(doc, "$..bib_key");
	        List<String> titles = JsonPath.read(doc, "$..title");
	        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
	        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
	        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

	        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
	        String title = titles.size() > 0 ? titles.get(0) : "N/A";
	        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
	        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
	        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

	        return new Book(isbn, title, desc, infoUrl, numOfPages);
	    }

}
