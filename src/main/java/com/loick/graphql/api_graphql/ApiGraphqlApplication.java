package com.loick.graphql.api_graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGraphqlApplication.class, args);
	}


	// type Player {
	// 	id: ID!
	// 	firstName: String!
	// 	lastName: String!
	// 	position: String!
	// 	team: Team!
	// }
}
