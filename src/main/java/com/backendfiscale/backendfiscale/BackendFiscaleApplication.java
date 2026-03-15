package com.backendfiscale.backendfiscale;

import com.backendfiscale.backendfiscale.Entites.Admin;
import com.backendfiscale.backendfiscale.Entites.Role;
import com.backendfiscale.backendfiscale.Entites.User;
import com.backendfiscale.backendfiscale.Repository.IAdminRepository;
import com.backendfiscale.backendfiscale.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor

@EnableScheduling

@ComponentScan(basePackages = {"com.backendfiscale.backendfiscale","com.backendfiscale.backendfiscale.CorsCongiguration"})
public class BackendFiscaleApplication implements CommandLineRunner {
    private final IUserRepository userRepository;
    private final IAdminRepository adminRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendFiscaleApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (adminAccount == null) {
            Admin admin = new Admin();
            admin.setEmail("admin@esprit.tn");
            admin.setNom("admin");
            admin.setPrenom("admin");
            admin.setRole(Role.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminRepository.save(admin);
        }
    }
}
