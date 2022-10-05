package shjgroup.travelclubcoregrd.service;

import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.service.sdo.TravelClubCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;

import java.util.List;

public interface ClubService {
    //
    String registerClub(TravelClubCdo club);
    TravelClub findClubById(String id);
    List<TravelClub> findClubsByName(String name);
    void modify(String clubId, NameValueList nameValues);
    void remove(String clubId);
}