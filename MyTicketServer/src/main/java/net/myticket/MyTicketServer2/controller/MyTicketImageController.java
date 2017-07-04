package net.myticket.MyTicketServer2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:3000")
@PropertySource("classpath:myticket.properties")
public class MyTicketImageController {
	
	@Value("${ticket.basedir}")
	private String baseDir;
	@Value("${ticket.image}")
	private String imageDir;
	@Value("${ticket.thumbnail}")
	private String thumbnailDir;
	
	@ResponseBody
	@RequestMapping(value = "/{image}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getImage(@PathVariable String image) {

	   StringBuffer filePath = new StringBuffer(baseDir + File.separator + 
			   thumbnailDir + File.separator + image);
	   if(filePath.indexOf(".jpg")==-1)
		  filePath.append(".jpg"); 
	   
	   FileInputStream fin;
		try {
			fin = new FileInputStream(filePath.toString());
		} catch (FileNotFoundException e) {
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("image/jpeg"))
	            .body(new InputStreamResource(fin));
	}

}
