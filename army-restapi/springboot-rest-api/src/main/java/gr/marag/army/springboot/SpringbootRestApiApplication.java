package gr.marag.army.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Army REST API" ,
				description = "Spring Boot Army REST API Documentation" ,
				version = "v1.0",
				contact = @Contact(
						name = "Grigorios",
						email = "grmarag@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Army REST API Documentation"
		)

)
public class SpringbootRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}

}
