package shjgroup.travelclubcoregrd.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shjgroup.travelclubcoregrd.aggregate.club.CommunityMember;
import shjgroup.travelclubcoregrd.service.MemberService;
import shjgroup.travelclubcoregrd.service.sdo.MemberCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;
import shjgroup.travelclubcoregrd.store.MemberStore;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {

    private MemberStore memberStore;
    @Autowired
    public MemberServiceLogic(MemberStore memberStore) {
        this.memberStore = memberStore;  // 등호(=) 왼쪽의 this.memberStore은 private MemberStore memberStore;의 memberStore 이고, 등호(=) 오른쪽의 memberStore은 public MemberServiceLogic(MemberStore memberStore)의 매개변수인 memberStore 이다.
    }

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