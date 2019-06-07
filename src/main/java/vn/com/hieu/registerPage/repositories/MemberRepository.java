package vn.com.hieu.registerPage.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.com.hieu.registerPage.entities.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>  {

	Member findByMobileNumber(String mobileNumber);
	Member findByEmail(String email);
	
}
