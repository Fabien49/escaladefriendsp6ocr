package com.fabienIT.escaladefriendsp6ocr;

import com.fabienIT.escaladefriendsp6ocr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class EscaladeApplication implements CommandLineRunner{

	@Value("${chemin.site.image}")
	private String siteImageRoot;


	
	private static final Logger logger = LoggerFactory.getLogger(EscaladeApplication.class);
	
	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	public static void main(String[] args) {
		SpringApplication.run(EscaladeApplication.class, args);
		logger.info("Start Les Amis de l'Escalade Application ...");
	}

	public void run(String... args) throws Exception {
		logger.info("Le chemin des images des sites est : " +siteImageRoot);
		testCrudTopo();
		testReservationTopo();
		createUser();

	}

	private void testCrudTopo(){
		System.out.println("je suis en train de tester");
	}

	private void testReservationTopo(){
		System.out.println("je suis en train de tester testReservationTopo");
	}

	private void createUser(){
		System.out.println("je suis en train de tester createUser");
		/*List<String> userdata = Arrays.asList("ADMIN", "ADMIN", "admin@test.com","admin2017");
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
		userRepository.save(user);*/
	}

}
