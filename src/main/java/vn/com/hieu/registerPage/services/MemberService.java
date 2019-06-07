package vn.com.hieu.registerPage.services;

import java.util.List;

import vn.com.hieu.registerPage.entities.Member;

public interface MemberService {

	List<Member> getAllMembers();

	Member getMemberById(Long id);

	boolean saveMember(Member member);

	boolean deleteMemberById(Long id);
	
	Member getMemberByMobileNumber(String mobileNumber);
	
	Member getMemberByEmail(String email);

}
