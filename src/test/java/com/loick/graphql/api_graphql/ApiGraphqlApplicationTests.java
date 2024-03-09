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
class ApiGraphqlApplicationTests {

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

	public static String expected(String fileName) throws IOException {
        Path path = Paths.get("src/test/resources/" + fileName + "_expected_response.json");
        return new String(Files.readAllBytes(path));
    }
}