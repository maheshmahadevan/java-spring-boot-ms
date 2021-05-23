package com.example.catalogserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalogserver.model.Book;
import com.example.catalogserver.model.Catalog;

@SpringBootApplication
@EnableResourceServer
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class CatalogServerApplication {
	
		@Autowired
	    BookClient client;
		
		
	    @RequestMapping("/catalog")
	    public Catalog catalog() {
	        //List<Book> books = client.findAll();
	    		Book book = client.findOne(1L);
	        Catalog catalog = new Catalog();
	        List<Book> books = new ArrayList<>();
	        books.add(book);
	        catalog.setBooks(books);
	        return catalog;
	    }
	

		public static void main(String[] args) {
			SpringApplication.run(CatalogServerApplication.class, args);
		}


		@FeignClient(name= "book-service",configuration = AuthConfigurationInterceptor.class)
	    interface BookClient {
	        @GetMapping(value = "/books")
	        List<Book> findAll();
	        @GetMapping(value = "/books/{id}")
	        Book findOne(@PathVariable("id")Long id);
	    }

}
