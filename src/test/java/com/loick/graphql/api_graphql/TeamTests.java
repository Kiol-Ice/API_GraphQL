package com.loick.graphql.api_graphql;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.loick.graphql.api_graphql.controller.TeamController;

@GraphQlTest(TeamController.class)
@Import(Graphqlconfiguration.class)
public class TeamTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
	public void testTeams() throws IOException {
		String query = """
			query teams ($count: Int, $offset: Int) {
				teams (count: $count, offset: $offset) {
					id
					name
					color
				}
			}
		""";

        graphQlTester.document(query)
			.variable("count", 4)
			.variable("offset", 2)
			.execute()
			.path("$")
			.matchesJson(BaseTests.expected("getTeams"));
	}

    @Test
	public void testTeam() throws IOException {
		String query = """
			query team ($id: ID) {
				team (id: $id) {
					id
					name
					color
					leagueLevel
					players {
                        firstName
                        lastName
					}
				}
			}
		""";

        graphQlTester.document(query)
			.variable("id", "1")
			.execute()
			.path("$")
			.matchesJson(BaseTests.expected("getTeam"));
	}

    @Test
	public void testCreateTeam() throws IOException {
		String query = """
			mutation createTeam (
				$name: String!, 
				$color: String!, 
				$leagueLevel: String!, 
				$playerId: [String],
				) {
				createTeam(
				  	name: $name
				  	color: $color
				  	leagueLevel: $leagueLevel
				  	playerId: $playerId
				) {
					id
				  	name
				  	color
                    leagueLevel
				  	players {
						firstName
                        lastName
					  }
				}
			  }
		""";

        String[] playerId = {"0", "1"};

        graphQlTester.document(query)
			.variable("name", "FooTSE")
			.variable("color", "green and red")
			.variable("leagueLevel", "regionnal")
			.variable("playerId", playerId)
			.execute()
			.path("$")
			.matchesJson(BaseTests.expected("createTeam"));
	}

    @Test
	public void testUpdateTeam() throws IOException {
		String query = """
			mutation updateTeam (
				$id: String!,
				$name: String, 
				$color: String, 
				$leagueLevel: String, 
				$playerId: [String],
				) {
				updateTeam(
					id: $id
                    name: $name
                    color: $color
                    leagueLevel: $leagueLevel
                    playerId: $playerId
				) {
					id
				  	name
				  	color
                    leagueLevel
                    players {
                        firstName
                        lastName
                    }
				}
			  }
		""";

        String[] playerId = {"6", "7"};

        graphQlTester.document(query)
			.variable("id", "9")
          	.variable("name", "fc girondins de bordeaux")
		  	.variable("color", "bleu fonce")
		  	.variable("leagueLevel", "regionnal")
		  	.variable("playerId", playerId)
          	.execute()
          	.path("$")
          	.matchesJson(BaseTests.expected("updateTeam"));
	}

    @Test
	public void testDeleteTeam() throws IOException {
		String query = """
			mutation deleteTeam (
				$id: String!
				) {
				deleteTeam(
					id: $id
				) {
					id
				}
			  }
		""";

        graphQlTester.document(query)
			.variable("id", "6")
          	.execute()
          	.path("$")
          	.matchesJson(BaseTests.expected("deleteTeam"));
	}
}
