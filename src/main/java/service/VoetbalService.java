package service;

import java.util.List;

import domain.WedstrijdTicket;

public interface VoetbalService {

	public List<String> getStadiumList();

	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium);

	public WedstrijdTicket getWedstrijd(String id);

	public int ticketsBestellen(String id, int teBestellen);

}