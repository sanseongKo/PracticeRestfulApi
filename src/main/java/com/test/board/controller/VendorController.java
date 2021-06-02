package com.test.board.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.board.dao.MypageDaoImpl;
import com.test.board.domain.ContentVO;
import com.test.board.domain.MemberVO;
import com.test.board.domain.OrderVO;
import com.test.board.domain.ReplyVO;
import com.test.board.domain.VendorVO;
import com.test.board.service.MypageService;
import com.test.mypage.domain.AuthInfo;

import com.test.mypage.domain.IdPasswordNotMatchingException;
import com.test.mypage.domain.LoginCommand;
import com.test.mypage.domain.LoginCommandValidator;



@Controller
public class VendorController {

	private MypageService mypageService;
	

	@Autowired 
	public VendorController( MypageService mypageService) {
		this.mypageService = mypageService; 

	}

	
	@RequestMapping(value="/vendorLogin", method=RequestMethod.GET)
	public String vendorHome(LoginCommand loginComand ) {		
		return "vendorLogin";

	}

	
	@RequestMapping(value="/vendorLogin",method=RequestMethod.POST) 
	public String submit(LoginCommand loginCommand, Errors errors,
			HttpSession session) {
		
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "vendorLogin";
		}
		try {
			
			String rightPass = mypageService.selectVendorPass(loginCommand.getEmail());

			//AuthInfo authInfo = authService.authenticate(loginCommand);
			if (loginCommand.checkPassword(rightPass)) {
				int uid = mypageService.selectUID(loginCommand.getEmail());
				AuthInfo authInfo = new AuthInfo(loginCommand.getEmail(), uid);
				//TODO세션에 authInfo 저장
				session.setAttribute("authInfo", authInfo);
				return "redirect:vendor/myClass/"+authInfo.getUid();
				
				
			}else {
				throw new IdPasswordNotMatchingException();
				
			}
	
			
			
		}catch (IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordMatching");
			return "vendorLogin";
		}
	
	}
	
	
	@RequestMapping(value="/vendor/myClass/{uid}", method=RequestMethod.GET)
	public String myClass(@PathVariable int uid, Model model) {
		
		System.out.println("myClass 시작");
		List<ContentVO> list =mypageService.selectContents(uid);
		System.out.println("myClass 의 list :"+ list);
		for (ContentVO contentVO : list) {
			System.out.println("LIST 확인 :"+ contentVO.getTitle());
			
		}
		
		model.addAttribute("list", list);
		
		return "vendor/myClass";
		
	}
	
	
	@RequestMapping(value="/vendor/myOrder/{uid}",  method=RequestMethod.GET)
	public String myOrder(@PathVariable int uid, Model model) {
		List<ContentVO> list =mypageService.selectContents(uid);
		System.out.println("myOrder의 list :"+ list);
		for (ContentVO contentVO : list) {
			System.out.println("LIST 확인 :"+ contentVO.getTitle());
			
		}
		
		model.addAttribute("list", list);
		
		return "vendor/myOrder";
	}
	
	//클래스별 예약현황 리스트
	@RequestMapping(value="/vendor/resState/{cid}",  method=RequestMethod.GET)
	public String resState(@PathVariable int cid, Model model) {
		try {
			List<OrderVO> list = mypageService.orderAll(cid);
			model.addAttribute("list", list);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "vendor/resState";
	}
	
	
	//판매자 수정
	@RequestMapping(value="/vendor/changeComplete", method=RequestMethod.POST)
	public String changeComplete(VendorVO vendorVO) {
		System.out.println("vendorVO 확인 :"+  vendorVO);
		//db에서 수정
		try {
			mypageService.updateVendor(vendorVO);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "vendor/changeComplete";
		
	}
	
	
	//판매자 수정
	@RequestMapping(value="/vendor/changeComment/{uid}", method=RequestMethod.GET)
	public String vendorHome(@PathVariable int uid, Model model) {	
		try {
			VendorVO vendorVO = mypageService.selectVendor(uid);
			System.out.println("vendorVO : "+ vendorVO);
			model.addAttribute("vendorVO", vendorVO);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "vendor/changeComment";

	}
	
	
	
	
	@RequestMapping("/vendor/logout") 
	public String logout(HttpSession session) {
		
		session.invalidate();//세션 해제
		return "redirect:/vendorLogin";
		
	}
	
	
	
	@RequestMapping(value="/vendor/request")
	public String registerRequest() {
		return "vendor/request";
	}
	
	
	
	
	
	
	
	
	
	
//////////////////구매자 마이페이지
	
	
	// 구매자 마이페이지
	@RequestMapping("/mypageMain")
	public String mypageMain(Model model) throws Exception {

		return "mypage/mypageMain";
	}

	
	// 구매자 구매목록 리스트
	@RequestMapping("/mypageOrderList")
	public String orderList(Model model, @RequestParam int uid) throws Exception {
		List<OrderVO> orderlist = mypageService.orderList(uid);
		model.addAttribute("orderlist", orderlist);
		System.out.println(orderlist);

		return "mypage/mypageOrderList";
	}

	
	// 구매자 취소목록 리스트
	@RequestMapping("/mypageCancleList")
	public String mypageCancleList(Model model, @RequestParam int uid) throws Exception {
		List<OrderVO> canclelist = mypageService.cancleList(uid);
		model.addAttribute("canclelist", canclelist);

		System.out.println(canclelist);

		return "mypage/mypageCancleList";
	}

	
	// 구매자 후기목록 리스트
	@RequestMapping("/mypageReplyList")
	public String mypageReplyList(Model model, int uid) throws Exception {
		List<ReplyVO> replylist = mypageService.replyList(uid);
		model.addAttribute("replylist", replylist);

		System.out.println(replylist);

		return "mypage/mypageReplyList";
	}

	@RequestMapping("/updateStep1")
	public String mypagePassCheck(int uid) throws Exception {

		return "mypage/updateStep1";
		
	}
	
	
	
	@RequestMapping("/updateStep2")
	public String mypageManageInfo(@RequestParam String password, HttpSession session)throws Exception {
		MemberVO member= (MemberVO)session.getAttribute("member");
		String realPass = member.getPassword();
		if (realPass.equals(password)) {
			return "mypage/updateStep2";
			
		}else {
			return "mypage/failed";
		}
	
		
	}
	
	@RequestMapping(value="/updateStep3", method=RequestMethod.POST)
	public String mypageManageComplete(MemberVO memberVO) throws Exception {
		mypageService.updateMember(memberVO);
		return "mypage/updateStep3";
		
	}

	@RequestMapping(value="/mypage/logout")
	public String mypageLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		//이건 현정씨 프로젝트에서 메인을 말함
		
	}
	
	
	
	
	
	//환불은 PayController 쪽이다



/////////////////////////////




















/////////////////////////
	
	
	
	
	
	
}
