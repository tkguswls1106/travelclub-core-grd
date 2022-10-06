package shjgroup.travelclubcoregrd.service.logic;

import org.springframework.stereotype.Service;
import shjgroup.travelclubcoregrd.aggregate.club.CommunityMember;
import shjgroup.travelclubcoregrd.service.MemberService;
import shjgroup.travelclubcoregrd.service.sdo.MemberCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {

    @Override
    public String registerMember(MemberCdo member) {
        return null;
    }

    @Override
    public CommunityMember findMemberById(String memberId) {
        return null;
    }

    @Override
    public CommunityMember findMemberByEmail(String memberEmail) {
        return null;
    }

    @Override
    public List<CommunityMember> findMembersByName(String name) {
        return null;
    }

    @Override
    public void modifyMember(String memberId, NameValueList member) {

    }

    @Override
    public void removeMember(String memberId) {

    }
}
