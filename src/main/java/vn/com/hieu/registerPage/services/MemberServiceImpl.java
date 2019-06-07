package vn.com.hieu.registerPage.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.hieu.registerPage.entities.Member;
import vn.com.hieu.registerPage.repositories.MemberRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private MemberRepository repository;

	public MemberServiceImpl() {
		super();
	}

	@Autowired
	public MemberServiceImpl(MemberRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Member getMemberById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean saveMember(Member member) {
		
		try {
			repository.save(member);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMemberById(Long id) {

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Member getMemberByMobileNumber(String mobileNumber) {
		return repository.findByMobileNumber(mobileNumber);
	}

	@Override
	public Member getMemberByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	

}
