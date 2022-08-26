package com.monuapis.blog;

import java.util.List;

import javax.management.relation.Role;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.monuapis.blog.entities.Roles;
import com.monuapis.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication  implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo rRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("Ankit@1010"));
		
		try {
			Roles rol = new Roles();
			rol.setId(501);
			rol.setName("ADMIN_USER");
			
			Roles roles1 = new Roles();
			roles1.setId(502);
			roles1.setName("NORMAL_USER");
			
			List<Roles> roles = List.of(rol,roles1);
			
			List<Roles> saveRoles = this.rRepo.saveAll(roles);
			
			saveRoles.forEach(r->{
				System.out.println(r.getName());
			});
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
