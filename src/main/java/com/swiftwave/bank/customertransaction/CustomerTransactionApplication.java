/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction;


import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.swiftwave.bank.customertransaction.annotation.SwiftWaveCusTranController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
		title = "Customer Transaction App",
		contact = @Contact(
			name = "Mahaboob Subahan Jalaludeen",
			email = "subahanih@gmail.com",
			url = "https://www.linkedin.com/in/mahaboob-subahan"
		),
		version = "1.0.0",
		description = "Interview task project for Spring Boot - Checking cumulative & monthly balalnces"
	)
)
@SpringBootApplication
public class CustomerTransactionApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CustomerTransactionApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		return args -> {
			System.out.println("### Swift Wave Bank App - Beans inspection ###");
			printNames("Customer Transaction - Endpoints", ctx, SwiftWaveCusTranController.class);
		};
	}
	
	private void printNames(String name, ApplicationContext ctx, Class<? extends Annotation> annotation) {
		System.out.println(name);
		String[] beanNames = ctx.getBeanNamesForAnnotation(annotation);
		Arrays.sort(beanNames);
		Arrays.stream(beanNames).forEach(beanName -> System.out.println("   - Bean: '" + beanName + "'."));
	}

}
