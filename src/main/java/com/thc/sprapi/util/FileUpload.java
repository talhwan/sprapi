package com.thc.sprapi.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileUpload {
	@Value("${aws.s3.bucket_name}") private static String BUCKET_NAME;
	@Value("${aws.s3.read_s3_url}") private static String READ_S3_URL;
	@Value("${aws.s3.project_folder_name}") private static String PROJECT_FOLDER_NAME;
	@Value("${aws.s3.access_key}") private static String ACCESS_KEY;
	@Value("${aws.s3.secret_key}") private static String SECRET_KEY;

	public static String s3(MultipartFile mf) throws IOException {
		String returnValue = "";
		try {
			String filename = setFileName(mf);

			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			AmazonS3 s3 = AmazonS3ClientBuilder
	        		.standard()
	        		.withCredentials(new AWSStaticCredentialsProvider(credentials))
	        		.withRegion(Regions.AP_NORTHEAST_2)
	        		.build();

			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(mf.getSize());
			metadata.setContentType(mf.getContentType());

			PutObjectRequest putObjectRequest =
					new PutObjectRequest(
							BUCKET_NAME + "/" + PROJECT_FOLDER_NAME
							,filename
							,mf.getInputStream()
							,metadata
					);

			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission
			s3.putObject(putObjectRequest);
			returnValue = READ_S3_URL + PROJECT_FOLDER_NAME + "/" + filename;

		} catch (Exception e) {
			System.out.println("======================Exception : " + e);
		}
		return returnValue;
	}
	public static String local(
			MultipartFile mf, HttpServletRequest request
	) throws IOException {
		String returnValue = "";
		try {
			String root_path = path(request);
			setDir(root_path);
			String filename = setFileName(mf);

			//mf => 유효한지 확인도 해봄!
			// 용량 너무 큰데? 거절해야지!
			//실제로는 하나의 이미지 파일을 3개인가 6개정도의 형태로 저장
			/*
			1. 썸네일 용... : 크기를 극단적으로 줄임 가로세로50픽셀정도? 에서도 잘보이도록
			2. 중간정도 해상도
			3. 고해상도
			4. 원본
			~6개까지?
			우리는 그냥 놔두기로!!
			 */

			FileCopyUtils.copy(mf.getBytes(), new File(root_path + filename));
			returnValue = "/uploadfile/" + filename;
		} catch (Exception e) {
			System.out.println("======================Exception : " + e);
		}
		return returnValue;
	}
	public static String path(HttpServletRequest request) throws IOException {
		String root_path = request.getSession().getServletContext().getRealPath("/");

		//서버 업로드 이후를 위한 코드
		// /home/centos/apache-tomcat-8.5.72/webapps/abc
		root_path = root_path.replace("wtpwebapps", "uploadfiles");
		root_path = root_path.replace("webapps", "uploadfiles");

		//역슬레시 넘어오는 경우, /로 바꿔준다.
		root_path = root_path.replace("\\", "/");

		//로컬 서버를 위한 코드
		if(root_path.indexOf("C:/") == 0) {
			//윈도우 로컬을 위한 코드
			//C:/Users/01/AppData/Local/Temp/tomcat-docbase.8080.12040726205565672866/
			root_path = "C:/workspace/";
		} else if(root_path.indexOf("Users/") == 0) {
			//맥 로컬을 위한 코드
			// Users/01/Downloads/
			root_path = "Users/workspace/";
		}
		String attach_path = "uploadfiles/";
		return root_path + attach_path;
	}
	public static String setFileName(MultipartFile mf){
		String result = "";
		if (mf == null || "".equals(mf.getOriginalFilename() + "")) {
		} else {
			Date date = new Date();
			String temp_date = date.getTime() + "";
			String filename = mf.getOriginalFilename();
			// sky_vew12.jpg
			String extension = FilenameUtils.getExtension(filename);
			// jpg
			if(extension == null || "".equals(extension)) {
				extension = "nnn";
			}
			filename = filename.replaceAll(" ", "");
			/*
			filename = "file" + "." + extension;
			 */
			// file.jpg
			result = temp_date + "_" + filename;
			// 92032037230952_file.jpg
		}
		return result;
	}
	public static void setDir(String root_path){
		// 디랙토리 주소에 대해서, File 객체에 담음.(파일이 아니라, 폴더!!)
		File newfile = new File(root_path);
		// File 객체에 담긴 폴더가 존재하는지 물어봄!
		if(!newfile.exists()) {
			// File 객체에 담긴 폴더가 존재안하면 강제 생성!!
			newfile.mkdirs();
		}
	}
	
}