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
				//TODO????????? authInfo ??????
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
		
		System.out.println("myClass ??????");
		List<ContentVO> list =mypageService.selectContents(uid);
		System.out.println("myClass ??? list :"+ list);
		for (ContentVO contentVO : list) {
			System.out.println("LIST ?????? :"+ contentVO.getTitle());
			
		}
		
		model.addAttribute("list", list);
		
		return "vendor/myClass";
		
	}
	
	
	@RequestMapping(value="/vendor/myOrder/{uid}",  method=RequestMethod.GET)
	public String myOrder(@PathVariable int uid, Model model) {
		List<ContentVO> list =mypageService.selectContents(uid);
		System.out.println("myOrder??? list :"+ list);
		for (ContentVO contentVO : list) {
			System.out.println("LIST ?????? :"+ contentVO.getTitle());
			
		}
		
		model.addAttribute("list", list);
		
		return "vendor/myOrder";
	}
	
	//???????????? ???????????? ?????????
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
	
	
	//????????? ??????
	@RequestMapping(value="/vendor/changeComplete", method=RequestMethod.POST)
	public String changeComplete(VendorVO vendorVO) {
		System.out.println("vendorVO ?????? :"+  vendorVO);
		//db?????? ??????
		try {
			mypageService.updateVendor(vendorVO);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "vendor/changeComplete";
		
	}
	
	
	//????????? ??????
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
		
		session.invalidate();//?????? ??????
		return "redirect:/vendorLogin";
		
	}
	
	
	
	@RequestMapping(value="/vendor/request")
	public String registerRequest() {
		return "vendor/request";
	}
	
	
	
	
	
	
	
	
	
	
//////////////////????????? ???????????????
	
	
	// ????????? ???????????????
	@RequestMapping("/mypageMain")
	public String mypageMain(Model model) throws Exception {

		return "mypage/mypageMain";
	}

	
	// ????????? ???????????? ?????????
	@RequestMapping("/mypageOrderList")
	public String orderList(Model model, @RequestParam int uid) throws Exception {
		List<OrderVO> orderlist = mypageService.orderList(uid);
		model.addAttribute("orderlist", orderlist);
		System.out.println(orderlist);

		return "mypage/mypageOrderList";
	}

	
	// ????????? ???????????? ?????????
	@RequestMapping("/mypageCancleList")
	public String mypageCancleList(Model model, @RequestParam int uid) throws Exception {
		List<OrderVO> canclelist = mypageService.cancleList(uid);
		model.addAttribute("canclelist", canclelist);

		System.out.println(canclelist);

		return "mypage/mypageCancleList";
	}

	
	// ????????? ???????????? ?????????
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
		//?????? ????????? ?????????????????? ????????? ??????
		
	}
	
	
	
	
	
	//????????? PayController ?????????



/////////////////////////////




















/////////////////////////
	
	
	
	
	
	
}
