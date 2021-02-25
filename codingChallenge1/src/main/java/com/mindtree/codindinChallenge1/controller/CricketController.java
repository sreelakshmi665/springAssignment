package com.mindtree.codindinChallenge1.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.codindinChallenge1.entity.Player;
import com.mindtree.codindinChallenge1.entity.Team;
import com.mindtree.codindinChallenge1.exceptions.PlayerIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.ServiceException;
import com.mindtree.codindinChallenge1.exceptions.TeamIdNotFoundException;
import com.mindtree.codindinChallenge1.exceptions.TeamNameNotFoundException;
import com.mindtree.codindinChallenge1.service.ServiceInterface;

@RestController
@RequestMapping("/challenge")
public class CricketController {
	@Autowired
	private ServiceInterface service;
	@PostMapping("/addTeamDetails")
	public ResponseEntity<?> addTeam(@RequestBody Team team) {
		Map<String,Object> map=new LinkedHashMap<>();
		try {
			service.addTeamInfo(team);
			map.put("message: "," added!");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			map.put("message: "," Failed");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/addPlayerInfo/{id}")
	public ResponseEntity<?> addPlayer(@RequestBody Player player,@PathVariable("id") int teamId) {
		Map<String,Object> map=new LinkedHashMap<>();
		try {
			service.addPlayersInfo(player,teamId);
			map.put("message: "," added!");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			map.put("message: "," Failed");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
	}
	@GetMapping("/getPlayers/{teamName}")
	public ResponseEntity<?> getMindsinTrack(@PathVariable("teamName") String teamName) {
		
	List<Player> players=new ArrayList<>();
		Map<String,Object> map=new LinkedHashMap<>();
		try {
			players=service.getPlayersInASpecificTeam(teamName);
			map.put("message: ",players);
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		} catch (TeamNameNotFoundException e) {
			try {
				throw new ServiceException(e.getMessage());
			}
			catch(ServiceException exception)
			{
			System.out.println(exception.getMessage());
			map.put("message:","Failed");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);}
		}
	}
	@PutMapping("/updateLocation/{teamId}/{location}")
	public ResponseEntity<?> updateLocation(@PathVariable("teamId") int teamId,@PathVariable("location") String location) 
	{
		
		Map<String,Object> map=new LinkedHashMap<>();
		try {
			
			map.put("message:",service.updateLocation(teamId,location));
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		} catch (TeamIdNotFoundException e) {
			try {
				throw new ServiceException(e.getMessage());
			}
			catch(ServiceException exception)
			{
			System.out.println(exception.getMessage());
			map.put("message:","Failed");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);}
		}
	}
	@DeleteMapping("/deletePlayer/{playerId}")
	public ResponseEntity<?> deletePlayer(@PathVariable("playerId") int playerId) {
		
			
			Map<String,Object> map=new LinkedHashMap<>();
			try {
				service.deletePlayer(playerId);
				map.put("message: ","Successfully deleted");
				return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
			} catch (PlayerIdNotFoundException e) {
				try {
					throw new ServiceException(e.getMessage());
				}
				catch(ServiceException exception)
				{
				System.out.println(exception.getMessage());
				map.put("message:","Failed");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);}
		
		
	}
}
}