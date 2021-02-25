package com.mindtree.codindinChallenge1.service;

import java.util.List;

import com.mindtree.codindinChallenge1.entity.Player;
import com.mindtree.codindinChallenge1.entity.Team;
import com.mindtree.codindinChallenge1.exceptions.PlayerIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.ServiceException;
import com.mindtree.codindinChallenge1.exceptions.TeamIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.TeamNameNotFoundException;

public interface ServiceInterface {

	void addTeamInfo(Team team) throws ServiceException;

	void addPlayersInfo(Player player, int teamId) throws TeamIdNotFoundException;

	List<Player> getPlayersInASpecificTeam(String teamName) throws TeamNameNotFoundException;

	Object updateLocation(int teamId, String location) throws TeamIdNotFoundException;

	void deletePlayer(int playerId) throws PlayerIdNotFoundException;

}
