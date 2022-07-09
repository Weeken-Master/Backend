package com.api.link.controllers;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.api.link.handel.CreateURL;
import com.api.link.model.Url;
import com.api.link.repository.UrlRepository;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UrlController {

	@Autowired
	private UrlRepository repository;
	private CreateURL createURL = new CreateURL();
	private int count =1000000000;
	public static String percentDecode(String encodeMe) {
		if (encodeMe == null) {
			return "";
		}
		String decoded = encodeMe.replace("%21", "!");
		decoded = decoded.replace("%20", " ");
		decoded = decoded.replace("%23", "#");
		decoded = decoded.replace("%24", "$");
		decoded = decoded.replace("%26", "&");
		decoded = decoded.replace("%27", "'");
		decoded = decoded.replace("%28", "(");
		decoded = decoded.replace("%29", ")");
		decoded = decoded.replace("%2A", "*");
		decoded = decoded.replace("%2B", "+");
		decoded = decoded.replace("%2C", ",");
		decoded = decoded.replace("%2F", "/");
		decoded = decoded.replace("%3A", ":");
		decoded = decoded.replace("%3B", ";");
		decoded = decoded.replace("%3D", "=");
		decoded = decoded.replace("%3F", "?");
		decoded = decoded.replace("%40", "@");
		decoded = decoded.replace("%5B", "[");
		decoded = decoded.replace("%5D", "]");
		decoded = decoded.replace("%25", "%");
		return decoded;
	}


	
	@GetMapping("/all")
	public ResponseEntity<List<Url>> getUsers() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	


	
	@PostMapping("/{id}")
	public ResponseEntity<Url> getUrlbyIds(@PathVariable("id") String id, HttpServletRequest request){

		// trả về 1 đối tượng url
		Url _url = repository.findById(id).get();
	return new ResponseEntity<>(repository.save(_url), HttpStatus.OK);	
	}


	
	// @PostMapping("/add")
	@PostMapping(value = "/add")
    public ResponseEntity<Url> addURL(@RequestBody String link){

		String md = createURL.encode(count++);
        
		String cut = link.substring(0,link.length()-1);
		String request = percentDecode(cut);
		
		// truyen vao ham khoi tao url( string id, string d)
		Url _url = new Url(md, request);
	
		return new ResponseEntity<>(repository.save(_url), HttpStatus.CREATED);
    }

	@PostMapping("/user-add")
    public ResponseEntity<Object> userURL(@RequestBody Url url){
		try {
			repository.findById(url.getID()).get();
			return new ResponseEntity<>("URL already exist", HttpStatus.EXPECTATION_FAILED);
		
		} catch (Exception e) {
			String id = url.getID();
			
			if(id.length() > 0 && id.length() < 17){
				url.setTime();
				return new ResponseEntity<>(repository.save(url), HttpStatus.CREATED);
			}
			else
				return new ResponseEntity<>("URL length from 1 to 16 characters", HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	@DeleteMapping("del")
	public ResponseEntity<Url> delAll(){
		repository.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
