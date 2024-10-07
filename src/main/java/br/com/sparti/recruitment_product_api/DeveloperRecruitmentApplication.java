package br.com.sparti.recruitment_product_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.sparti.recruitment_product_api.model"})
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.sparti.recruitment_product_api.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableCaching
public class DeveloperRecruitmentApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperRecruitmentApplication.class, args);
	}

	// Mapeamento  Global de Origens
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("**")
				.allowedMethods("*")
				.allowedOrigins("*");
	}
}
