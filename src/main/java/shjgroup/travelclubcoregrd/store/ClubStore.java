package shjgroup.travelclubcoregrd.store;

import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;

import java.util.List;

public interface ClubStore {
    //
    String create(TravelClub club);
    TravelClub retrieve(String clubId);
    List<TravelClub> retrieveByName(String name);
    List<TravelClub> retrieveAll();
    void update(TravelClub club);
    void delete(String clubId);

    boolean exists(String clubId);
}