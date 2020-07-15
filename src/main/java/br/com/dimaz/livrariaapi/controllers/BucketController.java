package br.com.dimaz.livrariaapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dimaz.livrariaapi.services.AmazonClient;

@RestController
@RequestMapping("/v1/storage")
public class BucketController {
	
	@Autowired
	private AmazonClient amazonClient;

//	public BucketController(AmazonClient amazonClient) {
//		this.amazonClient = amazonClient;
//	}
	
	@PostMapping
	public String sendFile(@RequestPart(value = "file") MultipartFile file) {
		return this.amazonClient.uploadFile(file);
	}
	
	@DeleteMapping
	public String  deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3(fileUrl);
	}
	

}
