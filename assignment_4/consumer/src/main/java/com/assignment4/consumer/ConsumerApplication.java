package com.assignment4.consumer;

import com.assignment4.consumer.example.CountryClient;
import com.assignment4.consumer.example.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {

		new ActivityController();
		System.out.println("Let's go");
		SpringApplication.run(ConsumerApplication.class, args);


	}

	@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			String country = "Poland";

			if (args.length > 0) {
				country = args[0];
			}
			GetCountryResponse response = quoteClient.getCountry(country);
			System.err.println(response.getCountry().getCurrency());
		};
	}

	@Bean
	CommandLineRunner login(ActivityClient quoteClient) {
		return args -> {
			String username = "gui";
			String password = "000";


			GetLoginResponse response = quoteClient.getLogin(username, password);
			List<PatientXsd> patientXsdList = response.getPatients();
			System.err.println(response.getPatients());
		};
	}

	@Bean
	CommandLineRunner activities(ActivityClient quoteClient) {
		return args -> {
			String patientId = "86268229-aedb-47b2-8257-7a2b15244af8";

			GetActivityResponse response = quoteClient.getActivities(patientId);
			System.err.println(response.getActivities());
		};
	}

	@Bean
	CommandLineRunner medications(ActivityClient quoteClient) {
		return args -> {
			String patientId = "4aec508f-ab9c-4e7e-8dec-7984ecfbd58d";

			GetMedicationPlanResponse response = quoteClient.getMedications(patientId);
			System.err.println(response.getMedicationPlans());
		};
	}

}
