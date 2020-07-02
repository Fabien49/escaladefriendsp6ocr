package com.macrosoftas.archijee.service;



import com.macrosoftas.archijee.model.Utilisateur;
import com.macrosoftas.archijee.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {

    @Autowired
    InscriptionRepository inscriptionRepository;

    public void ajouter (Utilisateur utilisateur){

        inscriptionRepository.save(utilisateur);
    }

   /* public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        HashSet<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setRole("ADMIN");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }*/
}
