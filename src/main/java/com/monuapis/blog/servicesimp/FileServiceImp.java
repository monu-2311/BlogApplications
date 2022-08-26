package com.monuapis.blog.servicesimp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.monuapis.blog.services.FileService;

@Service
public class FileServiceImp implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//Find File Name
		String name = file.getOriginalFilename();
		
		//Random Genrate file
		String random = UUID.randomUUID().toString();
		String fileName1 =random.concat(name.substring(name.lastIndexOf(".")));
		
		//fullPath
		String filePath = path + File.separator +fileName1;
		
		//Create folder if not create
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}	
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
		//db logic to return imputStream
		return is;
	}

}
