package com.loick.graphql.api_graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.loick.graphql.api_graphql.dao.TeamDao;
import com.loick.graphql.api_graphql.entite.Team;

@Controller
public class TeamController {
@Autowired
	private TeamDao teamDao;

    public TeamController() {
    }

    @QueryMapping
    public List<Team> Teams() {
    	
        return this.teamDao.getTeams();
    }
}
