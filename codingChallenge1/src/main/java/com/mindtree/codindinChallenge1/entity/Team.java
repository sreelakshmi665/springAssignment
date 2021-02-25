package com.mindtree.codindinChallenge1.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Team {
@Id
private int teamId;
private String teamName;
private String location;
@OneToMany(mappedBy="team")
private List<Player> player;
public int getTeamId() {
	return teamId;
}
public void setTeamId(int teamId) {
	this.teamId = teamId;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}

public List<Player> getPlayer() {
	return player;
}
public void setPlayer(List<Player> player) {
	this.player = player;
}
public Team() {
	super();
}
public Team(int teamId, String teamName, String location) {
	super();
	this.teamId = teamId;
	this.teamName = teamName;
	this.location = location;
}
public Team(int teamId, String teamName, String location, List<Player> player) {
	super();
	this.teamId = teamId;
	this.teamName = teamName;
	this.location = location;
	this.player = player;
}

}