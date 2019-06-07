package vn.com.hieu.registerPage.factories;

import vn.com.hieu.registerPage.entities.Member;
import vn.com.hieu.registerPage.models.MemberModel;

public class MemberConverter extends Converter<MemberModel, Member> {

	public MemberConverter() {
		super(memberModel -> new Member(memberModel.getMemberId(), memberModel.getMobileNumber(),
				memberModel.getFirstName(), memberModel.getLastName(), memberModel.getDateOfBirth(),
				memberModel.getGender() == 1, memberModel.getEmail()),
				member -> new MemberModel(member.getMemberId(), member.getMobileNumber(), member.getFirstName(),
						member.getLastName(), member.getDateOfBirth(), member.isGender() ? 1 : 0, member.getEmail()));
	}

}
