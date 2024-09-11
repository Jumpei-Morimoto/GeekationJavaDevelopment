package com.example.demo.API;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemsReferenceRepository;


@Service
public class APISchedule {
	
	
	
	@Autowired
	  private InventoryRepository inventoryRepository;
	
	@Autowired
	  private ItemsReferenceRepository itemsReferenceRepository;
	
	@Scheduled(cron = "0 0 2 * * *", zone = "Asia/Tokyo" )
    public void CallApi1() throws URISyntaxException{

	    String url ="http://localhost:8080/API";
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,null,String.class);
	    String body = response.getBody();
	    
	    System.out.println("タスク実行成功"+body);
	    
	}	
	@Scheduled(cron = "0 0 2 * * *", zone = "Asia/Tokyo" )
    public void CallApi2() throws URISyntaxException{
		
	    String url ="http://localhost:8080/API2";
	    
	    RestTemplate restTemplate2 = new RestTemplate();
	    
	    ResponseEntity<String> response2 = restTemplate2.exchange(url,HttpMethod.GET,null,String.class);
	    String body = response2.getBody();
	    
	    System.out.println("タスク実行成功"+body);
	    
	}	
}

