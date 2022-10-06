package shjgroup.travelclubcoregrd.service;

import shjgroup.travelclubcoregrd.aggregate.club.CommunityMember;
import shjgroup.travelclubcoregrd.service.sdo.MemberCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;

import java.util.List;

public interface MemberService {
    //
    String registerMember(MemberCdo member);
    CommunityMember findMemberById(String memberId);
    CommunityMember findMemberByEmail(String memberEmail);
    List<CommunityMember> findMembersByName(String name);
    void modifyMember(String memberId, NameValueList member);
    void removeMember(String memberId);
}