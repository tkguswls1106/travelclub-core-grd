package shjgroup.travelclubcoregrd.store.mapstore;

import shjgroup.travelclubcoregrd.aggregate.club.CommunityMember;
import shjgroup.travelclubcoregrd.store.MemberStore;

import java.util.List;

public class MemberMapStore implements MemberStore {

    @Override
    public String create(CommunityMember member) {
        return null;
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        return null;
    }

    @Override
    public CommunityMember retrieveByEmail(String email) {
        return null;
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        return null;
    }

    @Override
    public void update(CommunityMember member) {

    }

    @Override
    public void delete(String email) {

    }

    @Override
    public boolean exists(String memberId) {
        return false;
    }
}
