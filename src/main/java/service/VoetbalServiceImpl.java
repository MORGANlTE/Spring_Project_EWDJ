package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Wedstrijd;
import domain.WedstrijdTicket;

public class VoetbalServiceImpl implements VoetbalService{
	
	//zelfde als bij VoetbalService commentaar. Als we werken met db data vragen we deze op via de Dao's en niet meer via dit object.

//    private List<String> stadiumList = new ArrayList<>();
//    
//    @PersistenceContext private EntityManager em;
//
//    //zonder databank
//    //--------------------
//    //mapWedstrijdenByStadium, per stadium, een lijst van wedstrijden
//    
//    private final Map<String, List<WedstrijdTicket>> mapWedstrijdenByStadium = new HashMap<>();
//
//    //mapWedstrijdById, per id een wedstrijdTicket
//    private final Map<String, WedstrijdTicket> mapWedstrijdById = new HashMap<>();
//
//    public VoetbalServiceImpl() {
//        //zonder databank
//        stadiumList = new ArrayList<>(Arrays.asList(new String[]{"Al Bayt Stadium", "Al Thumama Stadium", "Ghelamco Arena"}));
//        
////        mapWedstrijdById.put("1", new WedstrijdTicket(new Wedstrijd(1L, new String[]{"België", "Canada"}, 26, 21), 35));
////        mapWedstrijdById.put("2", new WedstrijdTicket(new Wedstrijd(2L, new String[]{"Brazilië", "Zwitserland"}, 27, 18), 21));
////        mapWedstrijdById.put("3", new WedstrijdTicket(new Wedstrijd(3L, new String[]{"Marroko", "Kroatië"}, 28, 15), 5));
////        mapWedstrijdById.put("4", new WedstrijdTicket(new Wedstrijd(4L, new String[]{"Spanje", "Duitsland"}, 28, 18), 95));
////        mapWedstrijdById.put("5", new WedstrijdTicket(new Wedstrijd(5L, new String[]{"Frankrijk", "Denemarken"}, 30, 15), 45));
////        mapWedstrijdById.put("6", new WedstrijdTicket(new Wedstrijd(6L, new String[]{"Argentinië", "Mexico"}, 30, 18), 10));
////        mapWedstrijdById.put("7", new WedstrijdTicket(new Wedstrijd(7L, new String[]{"Engeland", "USA"}, 31, 18), 22));
////        mapWedstrijdById.put("8", new WedstrijdTicket(new Wedstrijd(8L, new String[]{"Nederland", "Qatar"}, 31, 21), 16));
////        mapWedstrijdById.put("9", new WedstrijdTicket(new Wedstrijd(9L, new String[]{"Oostenrijk", "Frankrijk"}, 20, 16), 12));
//
//        mapWedstrijdenByStadium.put(stadiumList.get(0),
//                new ArrayList<>(Arrays.asList(new WedstrijdTicket[]{
//            mapWedstrijdById.get("1"),
//            mapWedstrijdById.get("2"),
//            mapWedstrijdById.get("3"),
//            mapWedstrijdById.get("6"),
//            mapWedstrijdById.get("7")
//        })));
//
//        mapWedstrijdenByStadium.put(stadiumList.get(1),
//                new ArrayList<>(Arrays.asList(new WedstrijdTicket[]{
//            mapWedstrijdById.get("4"),
//            mapWedstrijdById.get("5"),
//            mapWedstrijdById.get("8")
//        })));
//        
//        mapWedstrijdenByStadium.put(stadiumList.get(2),
//                new ArrayList<>(Arrays.asList(new WedstrijdTicket[]{
//            mapWedstrijdById.get("9")
//        })));
//
//    }
//
//    public List<String> getStadiumList() {
//        return Collections.unmodifiableList(stadiumList);
//    }
//
//    //geeft de lijst "tickets per wedstrijden" terug volgens stadium
//    public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium) {
//        return mapWedstrijdenByStadium.get(stadium);
//    }
//
//    //geeft aantal tickets voor een wedstrijd terug volgens id
//    public WedstrijdTicket getWedstrijd(String id) {
//        return mapWedstrijdById.get(id);
//    }
//
//    public int ticketsBestellen(String id, int teBestellen) {
//        WedstrijdTicket wedstrijdTicket = mapWedstrijdById.get(id);
//        return wedstrijdTicket.ticketsKopen(teBestellen);
//    }
//
//	//geeft stadium terug
//	public String getStadium(String id) {
//    	String stadium = getStadiumList().stream().filter(
//    			stad -> {
//    				return getWedstrijdenByStadium(stad).contains(getWedstrijd(id));
//    			}
//    			).collect(Collectors.toList()).get(0);
//		return stadium;
//	}
}
