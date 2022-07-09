package com.api.link.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.link.model.Ip;
import com.api.link.model.Url;
import com.api.link.repository.IpRepository;
import com.api.link.repository.UrlRepository;


@CrossOrigin
@RestController
@RequestMapping("")
public class GetUrlController {
    
	@Autowired
	private UrlRepository repository;
    @Autowired
	private IpRepository ipRepository;

    @GetMapping("/{id}")
	public ResponseEntity<String> getUrlbyId(@PathVariable("id") String id, HttpServletRequest request){
		// try {
            // System.out.println(request.getRemoteAddr());
            
            Url _url = repository.findById(id).get();
            String address = request.getRemoteAddr();
            Ip ip = new Ip();
            try {
               ip = ipRepository.findById(address).get();

               List<String> list = ip.getAccess();
            
                // System.out.println(list);
               if(list.indexOf(id) == -1){
                    _url.inCount();
                    ip.add(id);
                    ipRepository.save(ip);
                }

            } catch (Exception e) {
                // chua ton tai  luu đia chi ip
                ip = new Ip(address);
                // ip add id ( id link rut gon)
                ip.add(id);
                // count
                _url.inCount();
                ipRepository.save(ip);

            }

			return new ResponseEntity<>(repository.save(_url).getLinkDefault(), HttpStatus.OK);
        }
		
            @GetMapping("/ip")
            public ResponseEntity<List<Ip>> getAll(){
                return new ResponseEntity<>(ipRepository.findAll(), HttpStatus.OK);
            }
            @DeleteMapping("/del")
                public ResponseEntity<String> getAll2(){
                    ipRepository.deleteAll();
                    return new ResponseEntity<>("ok", HttpStatus.OK);
	}
}
