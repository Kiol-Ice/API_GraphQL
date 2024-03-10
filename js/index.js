const { request, gql } = require('graphql-request');

const apiUrl = 'http://localhost:8080/graphql';

const players = gql`
    query players {
        players {
            id
            firstName
            lastName
            position
            team {
                id
                name
            }
        }
    }
`;

const createPlayer = gql`
    mutation createPlayer {
        createPlayer(
            firstName: "Loick"
            lastName: "Ramadier"
            position: "Defender"
            teamId: "0"
            ) {
            id
            firstName
            lastName
            position
            team {
                id
                name
                players{
                    id
                    firstName
                    lastName
                }
            }
          }
    }
`;

const updatePlayer = gql`
    mutation updatePlayer {
        updatePlayer(
            id: "10"
            firstName: "Malcom"
            lastName: "Bokele"
            position: "Defender"
            teamId: "9"
            ) {
            id
            firstName
            lastName
            position
            team {
                id
                name
                players{
                    id
                    firstName
                    lastName
                }
            }
          }
    }
`;

const team = gql`
    query team {
        team (id: "0") {
            id
            name
            color
            leagueLevel
            players {
                id
                firstName
                lastName
            }
        }
    }
`;

const createTeam = gql `
    mutation createTeam {
        createTeam(
            color: "green and red"
            leagueLevel: "regionnal"
            name: "FooTSE"
            playerId: ["0", "1"]
            ) {
            id
            name
            color
            leagueLevel
            players {
                id
                firstName
                lastName
            }
          }
    }
`;

const updateTeam = gql `
    mutation updateTeam {
        updateTeam(
            id: "10"
            color: "gold and silver"
            leagueLevel: "international"
            name: "Foo'TSE"
            playerId: ["0", "1", "10"]
            ) {
            id
            name
            color
            leagueLevel
            players {
                id
                firstName
                lastName
            }
          }
    }
`;

const deletePlayer = gql `
    mutation deletePlayer {
        deletePlayer(
            id: "10") {
                id
            }
        }
`;

const deleteTeam = gql `
    mutation deleteTeam {
        deleteTeam(
            id: "10") {
                id
            }
        }
`;

const teams = gql `
    query teams {
        teams {
            id
            name
            leagueLevel
            color
            players {
                id
                firstName
                lastName
            }
        }
    }
`;

const displayPlayerAndTeam = (player) => {
    if (player != null && player.team != null)
        console.log(`[#${player.id}] ${player.firstName} ${player.lastName} (${player.position}) in team [#${player.team.id}] ${player.team.name} !`);
    else if (player != null)
        console.log(`[#${player.id}] ${player.firstName} ${player.lastName} (${player.position}) in team N/A !`);
    else
        console.log("null or deleted")
}

const displayPlayer = (player) => {
    if (player != null)
        console.log(`[#${player.id}] ${player.firstName} ${player.lastName}`);
    else
        console.log("null or deleted")
}

const displayTeam = (team) => {
    if (team != null)
        console.log(`[#${team.id}] ${team.name} with color(s) : ${team.color}, at ${team.leagueLevel} level`);
    else
        console.log("null or deleted")
}

const listPlayersTask = async () => {
    console.log("\n --------------")
    console.log("List of all players")
    await request(apiUrl, players).then(
        (data) => {
            console.log("Found Players : \n")
            data.players.forEach(element => {
                displayPlayerAndTeam(element);
            });
        });
}

const createPlayerTask = async () => {
    console.log("\n --------------")
    console.log("creating a player in team PSG ...")
    await request(apiUrl, createPlayer).then(
        (data) => {
            console.log("created Player : \n")
            let player = data.createPlayer;
            let teamPlayers = player.team.players;
            displayPlayerAndTeam(player);
            console.log("auto updated team players :");
            teamPlayers.forEach(element => {
                displayPlayer(element);
            });
        });
}

const updatePlayerTask = async () => {
    console.log("\n --------------")
    console.log("updating new player and switching team  ...")
    await request(apiUrl, updatePlayer).then(
        (data) => {
            console.log("updated Player : \n")
            let player = data.updatePlayer;
            let teamPlayers = player.team.players;
            displayPlayerAndTeam(player);
            console.log("auto updated team players :");
            teamPlayers.forEach(element => {
                displayPlayer(element);
            });
        });
}

const getTeamTask = async () => {
    console.log("\n --------------")
    console.log("Old team of updated player with removed player")
    await request(apiUrl, team).then(
        (data) => {
            console.log("Found team : \n")
            let team = data.team;
            displayTeam(team);
            console.log('Updated team player list (old player removed) : ')
            team.players.forEach(element => {
                displayPlayer(element); 
            });
        });
}

const createTeamTask = async () => {
    console.log("\n --------------")
    console.log("creating a team with player from other team (PSG) ...")
    await request(apiUrl, createTeam).then(
        (data) => {
            console.log("created team : \n")
            let team = data.createTeam;
            let teamPlayers = team.players;
            displayTeam(team);
            console.log("auto updated team players :");
            teamPlayers.forEach(element => {
                displayPlayer(element); 
            });
        });
}

const getTeamAfterCreateTask = async () => {
    console.log("\n --------------")
    console.log("Old team of players in new team (PSG -> FooTSE)")
    await request(apiUrl, team).then(
        (data) => {
            console.log("Found team : \n")
            let team = data.team;
            displayTeam(team);
            console.log('Updated team player list empty (player moved to other team) : ')
            team.players.forEach(element => {
                displayPlayer(element); 
            });
        });
}

const updateTeamTask = async () => {
    console.log("\n --------------")
    console.log("Update team (FooTSE) ...")
    await request(apiUrl, updateTeam).then(
        (data) => {
            console.log("updated team : \n")
            let team = data.updateTeam;
            displayTeam(team);
            console.log('Updated team player list : ')
            team.players.forEach(element => {
                displayPlayer(element); 
            });
        });
}

const deletePlayerTask = async () => {
    console.log("\n --------------")
    console.log("Delete player [#10] ...")
    await request(apiUrl, deletePlayer).then(
        (data) => {
            console.log("deleted player : \n")
            let player = data.deletePlayer;
            displayPlayer(player); 
        });
}

const deleteTeamTask = async () => {
    console.log("\n --------------")
    console.log("Delete team [#10] ...")
    await request(apiUrl, deleteTeam).then(
        (data) => {
            console.log("deleted team : \n")
            let team = data.deleteTeam;
            displayTeam(team); 
        });
}

const listTeamsTask = async () => {
    console.log("\n --------------")
    console.log("List of all teams")
    await request(apiUrl, teams).then(
        (data) => {
            console.log("Found Teams : \n")
            data.teams.forEach(element => {
                displayTeam(element);
            });
        });
}

const testGraphqlApi = async () => {
    await listPlayersTask();
    await createPlayerTask();
    await updatePlayerTask();
    await getTeamTask();
    await createTeamTask();
    await getTeamAfterCreateTask();
    await updateTeamTask();
    await deletePlayerTask();
    await deleteTeamTask();

    await listPlayersTask();
    await listTeamsTask();
}

testGraphqlApi();