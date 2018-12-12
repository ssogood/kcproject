package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.exception.NotFoundException;
import com.kc.service.BranchInfoService;
import com.kc.service.ReturnService;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;
import com.kc.vo.ReturnInfo;
import com.kc.vo.ReturnLine;

@Controller
public class ReturnController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReturnService service;
	@Autowired
	private BranchInfoService branch_service;
	
	
	@RequestMapping("/return/returnadd.do")
	private String returnAdd(HttpSession session) {
		String path="/returnadd";
		return path;
	}
	
	@RequestMapping("/return/returnalllist.do")
	private ModelAndView allList(HttpSession session){
		//String branch_code = "S0001";
		List<BranchInfo> branchList = branch_service.findAll();
		//List<ReturnInfo> returnAllList = new ArrayList<>();
		Map<String,List> returnMap = new HashMap<>();
		//본사일때 권한 예비로 줌
		//session.setAttribute("bonsaLoginInfo","id1");
		ModelAndView mav = new ModelAndView();
		try {
			for(BranchInfo branch : branchList) {
				String branch_code = branch.getBranch_code();
				List<ReturnInfo> returnList = new ArrayList<>();
				returnList = service.findAll(branch_code);
				returnMap.put(branch_code, returnList);
				mav.addObject("returnMap",returnMap);
				mav.addObject("status", "ok");
			}
		}catch(Exception e) {
			mav.addObject("status", "error");
		}
		
		String path = "/returnalllistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/returnlistbranch.do")
	private ModelAndView returnListBranch(HttpSession session,String branch_code){
		//String branch_code = "S0001";
		ModelAndView mav = new ModelAndView();
		String branchInfo = (String) session.getAttribute("branchLoginInfo");
		
		try {
			if(branchInfo==null) { //본사!
				
				//session.setAttribute("branch_code", branch_code);
				List<ReturnInfo> returnList = service.findAll(branch_code);
				
				mav.addObject("returnList",returnList);
				mav.addObject("branch_code",branch_code);
				mav.addObject("status", "head_ok");
			}else { //지점
				//branch_code = "S0001"; //로그인 연결하면 
				branch_code=(String) session.getAttribute("branchLoginInfo");
				//session.setAttribute("branch_code", branch_code);
				List<ReturnInfo> returnList = service.findAll(branch_code);
				
				mav.addObject("returnList",returnList);
				mav.addObject("branch_code",branch_code);
				mav.addObject("status", "branch_ok");
			}
		}catch(Exception e) {
			mav.addObject("status", "error");
		}
		
		String path = "/returnbranchlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/addreturn.do")
	private ModelAndView addReturn(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		//String branch_code = "S0001";
		String branch_code = (String)session.getAttribute("branchLoginInfo");
		
		ReturnInfo info = new ReturnInfo();
		List<ReturnLine> lines = new ArrayList<>();
		Map<ReturnLine,Integer> cart = (Map)session.getAttribute("cart");
		for(ReturnLine line : cart.keySet()) {
			int quantity = cart.get(line);
			line.setReturn_quantity(quantity);
			lines.add(line);
		}
		info.setReturn_lines(lines);
		
		try {
			service.addReturn(branch_code, info);
			//일단 주문 성공만!
			mav.addObject("status","ok");
		}catch(Exception e) {
			mav.addObject("status", "error");
		}
		
		session.removeAttribute("cart");
		String path = "/addreturnresult";
		mav.setViewName(path);
		return mav;
	}
	

	@RequestMapping(path="/return/addrtncart.do")
	private String addRtnCart(HttpSession session
									,int ing_no
									,int quantity
									,String prod_state) {
		
		Map<ReturnLine,Integer> cart = (Map)session.getAttribute("cart");
		if(cart==null) {
			cart = new HashMap<>();
			session.setAttribute("cart",cart);
		}
		
		ReturnLine line = new ReturnLine();
		IngredientInfo ingredient = new IngredientInfo();
		ingredient.setIngred_no(ing_no);
		line.setIngredient(ingredient);
		//line.setReturn_quantity(quantity);
		line.setRtl_prod_state_flag(prod_state);
		Integer inte = cart.get(line);
		
		if(inte !=null) {
			inte +=quantity;
		}else {
			inte=quantity;
		}cart.put(line, inte);
		System.out.println(line);
		String path="/addcartresult";
		return path;
	}
	
	@RequestMapping("/return/updateflagok.do")
	private ModelAndView updateFlagOk(HttpSession session
									  ,String branch_code
									  ,int return_no) throws InterruptedException {
		ModelAndView mav = new ModelAndView();
		
		//예외처리 아직 안함..
		service.modifyRtnFlag(branch_code, "3", return_no);
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/updateflagx.do")
	private ModelAndView updateFlagX(HttpSession session
									  ,String branch_code
									  ,int return_no) {
		ModelAndView mav = new ModelAndView();

		//예외처리 아직 안함..
		
		
		service.modifyRtnFlag(branch_code, "4", return_no);
		
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/updateflag2.do")
	private ModelAndView updateFlag2(HttpSession session
									  ,String branch_code
									  ,int return_no) {
		ModelAndView mav = new ModelAndView();

		//예외처리 아직 안함..
		
		
		service.modifyRtnFlag(branch_code, "2", return_no);
		
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	
	/*@RequestMapping("/return/updateflag.do")
	private ModelAndView updateFlag(HttpSession session
									  ,String branch_code
									  ,int return_no
									  ,String return_state_flag) {
		ModelAndView mav = new ModelAndView();

		//예외처리 아직 안함..
		
		
		service.modifyRtnFlag(branch_code, return_state_flag, return_no);
		
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}*/
	
	
	@RequestMapping("/return/updateflagtrs.do")
	private ModelAndView updateFlagTrs(HttpSession session
									  ,String branch_code
									  ,int return_no) {
		ModelAndView mav = new ModelAndView();

		//예외처리 아직 안함..
		
		
		service.modifyRtnFlag(branch_code, "5", return_no);
		
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	
	
	@RequestMapping("/return/updateflagrst.do")
	private ModelAndView updateFlagRst(HttpSession session
									  ,String branch_code
									  ,int return_no) {
		ModelAndView mav = new ModelAndView();
		
		//예외처리 아직 안함..
		service.modifyRtnFlag(branch_code, "6", return_no);
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	@RequestMapping("/return/returnbyflag.do")
	private ModelAndView returnByFlag(HttpSession session
									  ,String return_state_flag) {
		ModelAndView mav = new ModelAndView();
		List<BranchInfo> branchList = branch_service.findAll();
		Map<String,List> returnMap = new HashMap<>();
		
		for(BranchInfo branch : branchList) {
			String branch_code = branch.getBranch_code();
			List<ReturnInfo> returnList = new ArrayList<>();
			returnList = service.findRtnFlag(branch_code, return_state_flag);
			returnMap.put(branch_code, returnList);
			mav.addObject("returnMap",returnMap);
			mav.addObject("status", "ok");
		}
		String path = "/returnflaglistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/returnbybranchflag.do")
	private ModelAndView returnByBranchFlag(HttpSession session
									  		,String branch_code
											,String return_state_flag) {
		ModelAndView mav = new ModelAndView();
		List<ReturnInfo> returnList = new ArrayList<>();
		System.out.println("returnbybranchflag에 들어온 branch_code:"+branch_code);
		returnList = service.findRtnFlag(branch_code, return_state_flag);
		
		
		mav.addObject("returnList", returnList);
		mav.addObject("status", "ok");
		mav.addObject("branch_code", branch_code);
		String path = "/returnbranchlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/returnflag.do")
	private ModelAndView returnFlag(HttpSession session
									  		,String branch_code
											,String return_state_flag) {
		ModelAndView mav = new ModelAndView();
		List<ReturnInfo> returnList = new ArrayList<>();
		returnList = service.findRtnFlag(branch_code, return_state_flag);
		
		
		mav.addObject("returnListBranch", returnList);
		mav.addObject("status", "ok");
		mav.addObject("branch_code", branch_code);
		String path = "/returnflaglistresult";
		mav.setViewName(path);
		return mav;
	}
	
	
	

}
