package br.com.dimaz.livrariaapi.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonClient {
	
	private AmazonS3 s3client;
	
	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	
	@Value("${amazonProperties.secretKey}")
	private String secretKey;
	
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
	}
	
	private File convertMultiPartToFile(MultipartFile mpFile) throws IOException {
		File file = new File(mpFile.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(mpFile.getBytes());
		fos.close();
		
		return file;
	}
	
	private String generateName(MultipartFile mpFile) {
		return String.format("%s-%s", new Date().getTime(), mpFile.getOriginalFilename().replace(" ", "_"));
	}
	
	private void uploadFileToS3(String fileName, File file) {
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	public String uploadFile(MultipartFile mpFile) {
		String url = "";
		try {
			File file = convertMultiPartToFile(mpFile);
			String fileName = generateName(mpFile);
			url = String.format("%s/%s/%s",endpointUrl,bucketName,fileName);
			uploadFileToS3(fileName, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	public String deleteFileFromS3(String url) {
		try {
			String fileName = url.substring(url.lastIndexOf("/")+1);
			s3client.deleteObject(new DeleteObjectRequest(bucketName+"/", fileName));
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "400";
	}
}
