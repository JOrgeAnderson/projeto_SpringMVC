package spring.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "spring.project.springboot.model")
@ComponentScan(basePackages = {"spring.*"})//tonando visivel todas as package's do projeto
@EnableJpaRepositories(basePackages = {"spring.project.springboot.repository"})//tornando visivel todas as package's que envolven persistencia
@EnableTransactionManagement
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String result =
		 * encoder.encode("admin"); System.out.println(result);
		 */
	}

}
