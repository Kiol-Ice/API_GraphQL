package com.loick.graphql.api_graphql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.loick.graphql.api_graphql.controller.PlayerController;

@GraphQlTest(PlayerController.class)
@Import(Graphqlconfiguration.class)
class PlayerTests {

	@Autowired
    private GraphQlTester graphQlTester;

	@Test
	public void testPlayers() throws IOException {
		String query = """
			query players ($count: Int, $offset: Int) {
				players (count: $count, offset: $offset) {
					id
					firstName
					lastName
				}
			}
		""";

        graphQlTester.document(query)
			.variable("count", 4)
			.variable("offset", 2)
			.execute()
			.path("$")
			.matchesJson(expected("getPlayers"));
	}

	@Test
	public void testPlayer() throws IOException {
		String query = """
			query player ($id: ID) {
				player (id: $id) {
					id
					firstName
					lastName
					position
					team {
					name
					}
				}
			}
		""";

        graphQlTester.document(query)
			.variable("id", "1")
			.execute()
			.path("$")
			.matchesJson(expected("getPlayer"));
	}

	@Test
	public void testCreatePlayer() throws IOException {
		String query = """
			mutation createPlayer (
				$firstName: String!, 
				$lastName: String!, 
				$position: String!, 
				$teamId: String,
				) {
				createPlayer(
				  	firstName: $firstName
				  	lastName: $lastName
				  	position: $position
				  	teamId: $teamId
				) {
					id
				  	firstName
				  	lastName
				  	team {
						name
					  }
				}
			  }
		""";

        graphQlTester.document(query)
			.variable("firstName", "Loick")
			.variable("lastName", "Ramadier")
			.variable("position", "defender")
			.variable("teamId", "1")
			.execute()
			.path("$")
			.matchesJson(expected("createPlayer"));
	}

	@Test
	public void testUpdatePlayer() throws IOException {
		String query = """
			mutation updatePlayer (
				$id: String!,
				$firstName: String, 
				$lastName: String, 
				$position: String,
				$teamId: String
				) {
				updatePlayer(
					id: $id
				  	firstName: $firstName
				  	lastName: $lastName
					position: $position
				  	teamId: $teamId
				) {
					id
				  	firstName
				  	lastName
					position
				  	team {
						name
				  	}
				}
			  }
		""";

        graphQlTester.document(query)
			.variable("id", "0")
          	.variable("firstName", "Christiano")
		  	.variable("lastName", "Ronaldo")
		  	.variable("position", "Forward")
		  	.variable("teamId", "5")
          	.execute()
          	.path("$")
          	.matchesJson(expected("updatePlayer"));
	}

	@Test
	public void testDeletePlayer() throws IOException {
		String query = """
			mutation deletePlayer (
				$id: String!
				) {
				deletePlayer(
					id: $id
				) {
					id
				}
			  }
		""";

        graphQlTester.document(query)
			.variable("id", "9")
          	.execute()
          	.path("$")
          	.matchesJson(expected("deletePlayer"));
	}

	public static String expected(String fileName) throws IOException {
        Path path = Paths.get("src/test/resources/" + fileName + "_expected_response.json");
        return new String(Files.readAllBytes(path));
    }
}