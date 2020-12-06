package com.fabienIT.escaladefriendsp6ocr;

import com.fabienIT.escaladefriendsp6ocr.model.Role;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.repository.TopoRepository;
import com.fabienIT.escaladefriendsp6ocr.repository.UserRepository;
import com.fabienIT.escaladefriendsp6ocr.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@SpringBootApplication
public class EscaladeApplication implements CommandLineRunner{

	@Value("${chemin.site.image}")
	private String siteImageRoot;


	
	private static final Logger logger = LoggerFactory.getLogger(EscaladeApplication.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	TopoService topoService;

	@Autowired
	TopoRepository topoRepository;
	
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	public static void main(String[] args) {
		SpringApplication.run(EscaladeApplication.class, args);
		logger.info("Start Les Amis de l'Escalade Application ...");
	}

	public void run(String... args) throws Exception {
		logger.info("Le chemin des images des sites est : " +siteImageRoot);

		testReservationTopo();
		ajouterTopo();
		//createUser();
	}




	private void testReservationTopo(){
		System.out.println("je suis en train de tester testReservationTopo");
	}

	private void ajouterTopo (){

	    topoService.ajouter(new Topo(null, "Les Alpes", "Jean Philippe","Alpes", 18, 156, "4a", "9b+", false, false, false));
		topoService.ajouter(new Topo(null, "Les 1000 Vaches", "Stéphanie","Centre", 7, 39, "4a", "7c+", false, false, false));
		topoService.ajouter(new Topo(null, "La Pente", "Clarisse","Bretagne", 10, 86, "4a", "8b", false, false, false));
		topoService.ajouter(new Topo(null, "Les Pyrénées", "Fabien","Occitanie", 19, 147, "4a", "9b+", false, false, false));
		topoService.ajouter(new Topo(null, "L'île de beauté", "Fabrice","Corse", 9, 52, "4a", "9b+", false, false, false));


	}

	/*private void createUser(){
		System.out.println("je suis en train de tester createUser");
		List<String> userdata = Arrays.asList("ADMIN", "ADMIN", "admin@test.com","admin2017");
        logger.debug("**** Create default user  {}", userdata);

		User user = new User();
		user.setName("ADMIN");
		user.setLastName("ADMIN");
		user.setEmail("admin@test.com");
		//BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
		//String encodepwd = bCryptPasswordEncoderLocal.encode("admin2017");
		//user.setPassword(encodepwd);
		//System.out.println("admin2017  encoder = " +encodepwd);
		user.setPassword("$2a$10$fE7BKQcc.tesDzaptjL8luXZB6MV5rvUJ13ub5aVYKqnoPmMqYd8m");
		user.setActive(true);
		//Role
		HashSet<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRole("ADMIN");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
	}*/

}
