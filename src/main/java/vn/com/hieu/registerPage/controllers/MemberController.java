package vn.com.hieu.registerPage.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.hieu.registerPage.entities.Member;
import vn.com.hieu.registerPage.factories.MemberConverter;
import vn.com.hieu.registerPage.models.MemberJsonResponseModel;
import vn.com.hieu.registerPage.models.MemberLoginModel;
import vn.com.hieu.registerPage.models.MemberModel;
import vn.com.hieu.registerPage.services.MemberService;
import vn.com.hieu.registerPage.validators.MemberLoginValidator;
import vn.com.hieu.registerPage.validators.MemberValidator;
import vn.com.hieu.registerPage.validators.StringCleaningAdvice.StringModelCleaner;

@Controller
public class MemberController {

	private static final String PAGE_REGISTRATION = "registration";
	private static final String PAGE_HOME_REDIRECT = "redirect:/home";
	private static final String PAGE_ERROR = "error";
	private static final Logger LOGGER = Logger.getLogger(MemberController.class);
	public static final String ATTR_HEADER_MESSAGE = "headerMessage";
	public static final String ATTR_MEMBER = "memberModel";
	public static final String ATTR_MESSAGE = "message";

	private MemberValidator memberValidator;
	private MemberLoginValidator memberLoginValidator;
	private MemberService memberService;
	private MessageSource messageSource;

	public MemberController() {

	}

	@Autowired
	public MemberController(MemberService memberService, MemberValidator memberValidator,
			MemberLoginValidator memberLoginValidator, MessageSource messageSource) {
		this.memberService = memberService;
		this.memberValidator = memberValidator;
		this.messageSource = messageSource;
		this.memberLoginValidator = memberLoginValidator;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

//		binder.addValidators(memberValidator);

		StringModelCleaner cleaner = new StringModelCleaner();
		binder.registerCustomEditor(String.class, "firstName", cleaner);
		binder.registerCustomEditor(String.class, "lastName", cleaner);
		binder.registerCustomEditor(String.class, "email", cleaner);

	}

	@GetMapping(value = { "/home" })
	public ModelAndView goHomePage(HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@GetMapping(value = "/allMembers")
	public ModelAndView displayAllMember() {
		LOGGER.info("Member page requested: All members");
		ModelAndView mv = new ModelAndView();
		List<Member> list = memberService.getAllMembers();
		mv.addObject("memberList", new MemberConverter().createFromEntities(list));
		mv.setViewName("allMembers");
		return mv;
	}

	@GetMapping(value = {"/", "/registration"})
	public ModelAndView goRegisterPage() {
		LOGGER.info("Member page requested: Register page");
		ModelAndView mv = new ModelAndView();
		mv.addObject(ATTR_HEADER_MESSAGE, "Member Registration page");
		mv.addObject(ATTR_MEMBER, new MemberModel(true));
		mv.setViewName(PAGE_REGISTRATION);
		return mv;
	}

	@PostMapping(value = "/registration")
	public ModelAndView doMemberRegistration(@ModelAttribute MemberModel memberModel, BindingResult result) {
		ModelAndView mv = new ModelAndView();

		memberValidator.validate(memberModel, result);
		if (result.hasErrors()) {
			mv.setViewName(PAGE_REGISTRATION);
			return mv;
		}

		Member memberByMobileNumber = memberService.getMemberByMobileNumber(memberModel.getMobileNumber());
		if (memberByMobileNumber != null) {
			result.rejectValue("mobileNumber", "member.mobileNumber.duplicate");
			mv.setViewName(PAGE_REGISTRATION);
			return mv;
		}

		Member memberByEmail = memberService.getMemberByEmail(memberModel.getEmail());
		if (memberByEmail != null) {
			result.rejectValue("email", "member.email.duplicate");
			mv.setViewName(PAGE_REGISTRATION);
			return mv;
		}

		memberModel.constructDateOfBirth();
		boolean isRegistered = memberService.saveMember(new MemberConverter().convertFromDto(memberModel));
		//boolean isRegistered = true;
		if (isRegistered) {
			mv.addObject(ATTR_MESSAGE, messageSource.getMessage("member.register.success", null, null));
			mv.addObject("success", 1);
		} else {
			mv.setViewName(PAGE_ERROR);
			return mv;
		}

		mv.setViewName(PAGE_REGISTRATION);
		return mv;
	}

	@GetMapping(value = "/profile/{id}")
	public ModelAndView goMemberProfilePage(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Member member = memberService.getMemberById(id);
		mv.addObject(ATTR_HEADER_MESSAGE, "Edit Member profile");
		mv.addObject(ATTR_MEMBER, new MemberConverter().convertFromEntity(member));
		mv.setViewName("editProfile");
		return mv;
	}

	@PostMapping(value = "/profile/{id}")
	public ModelAndView doEditMemberProfile(@ModelAttribute MemberModel memberModel, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			LOGGER.error(result.toString());
			mv.setViewName(PAGE_ERROR);
			return mv;
		}
		boolean isSaved = memberService.saveMember(new MemberConverter().convertFromDto(memberModel));
		if (!isSaved) {
			mv.setViewName(PAGE_ERROR);
			return mv;
		}
		mv.setViewName(PAGE_HOME_REDIRECT);
		return mv;
	}

	@GetMapping(value = "/deleteMember/{id}")
	public ModelAndView doDeleteMember(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		boolean isDeleted = memberService.deleteMemberById(id);
		LOGGER.info("Member deletion response: " + isDeleted);
		mv.setViewName(PAGE_HOME_REDIRECT);
		return mv;
	}

	@PostMapping(value = "/doLogin", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MemberJsonResponseModel doLogin(@RequestBody MemberLoginModel memberModel, BindingResult result) {

		memberLoginValidator.validate(memberModel, result);

		MemberJsonResponseModel responseModel = new MemberJsonResponseModel();

		Map<String, String> errors = new HashMap<>();
		if (!result.hasErrors()) {
			Member member = memberService.getMemberByEmail(memberModel.getUsername());
			if ((member == null) || !memberModel.getPassword().equals(member.getMobileNumber())) {
				result.reject("member.login.failed");
			}
		}
		
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				if (fieldError != null) {
					errors.put(fieldError.getField(), messageSource.getMessage(fieldError, null));
				}
			}
			List<ObjectError> globalErrors = result.getGlobalErrors();
			for (ObjectError objectError : globalErrors) {
				if (objectError != null) {
					errors.put(objectError.getObjectName(), messageSource.getMessage(objectError, null));
				}
			}
			responseModel.setValidated(false);
			responseModel.setErrorMessages(errors);
		} else {
			responseModel.setValidated(true);
		}
		return responseModel;
	}
}
