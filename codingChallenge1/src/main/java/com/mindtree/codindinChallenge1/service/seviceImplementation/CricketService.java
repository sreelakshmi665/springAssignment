package com.mindtree.codindinChallenge1.service.seviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.codindinChallenge1.entity.Player;
import com.mindtree.codindinChallenge1.entity.Team;
import com.mindtree.codindinChallenge1.exceptions.PlayerIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.ServiceException;
import com.mindtree.codindinChallenge1.exceptions.TeamIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.TeamNameNotFoundException;
import com.mindtree.codindinChallenge1.repository.PlayerRepository;
import com.mindtree.codindinChallenge1.repository.TeamRepository;
import com.mindtree.codindinChallenge1.service.ServiceInterface;
@Service
public class CricketService implements ServiceInterface{
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void addTeamInfo(Team team) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			teamRepository.save(team);
			}catch(Exception e) {
				throw new ServiceException(e.getMessage());}
		}

	@Override
	public void addPlayersInfo(Player player, int teamId) throws TeamIdNotFoundException {
		// TODO Auto-generated method stub
		Team team=teamRepository.findById(teamId).orElse(null);
			if(team!=null)
			{
			player.setTeam(team);
			playerRepository.save(player);
			team.getPlayer().add(player);
			teamRepository.save(team);
			}
			else
			throw new TeamIdNotFoundException("No such team found");
		
	}

	@Override
	public List<Player> getPlayersInASpecificTeam(String teamName) throws TeamNameNotFoundException {
		// TODO Auto-generated method stub
		List<Player> players;
		try {
		Team team=teamRepository.getTeamByTeamName(teamName);
		 players=team.getPlayer();
		}catch(Exception e) {
			throw new TeamNameNotFoundException("No such team");
			}
		return players;
		
	}

	@Override
	public Object updateLocation(int teamId, String location) throws TeamIdNotFoundException {
		// TODO Auto-generated method stub
		Team team=teamRepository.findById(teamId).orElse(null);
		try {
			team.setLocation(location);
			teamRepository.save(team);
		}catch(Exception e) {
			throw new TeamIdNotFoundException("no such team");}
		return team;
		
	}

	@Override
	public void deletePlayer(int playerId) throws PlayerIdNotFoundException {
		// TODO Auto-generated method stub
		Player player=playerRepository.findById(playerId).orElse(null);
		try {
			playerRepository.delete(player);
		}catch(Exception e) {
			throw new PlayerIdNotFoundException("no such player");}
		
	}

}
