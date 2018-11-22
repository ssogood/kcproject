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

import com.kc.service.ReturnService;
import com.kc.vo.IngredientInfo;
import com.kc.vo.ReturnInfo;
import com.kc.vo.ReturnLine;

@Controller
public class ReturnController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReturnService service;
	
	
	@RequestMapping("/return/returnlist.do")
	private ModelAndView allList(HttpSession session){
		String branch_code = "S0001";
		//본사일때 권한 예비로 줌
		//session.setAttribute("bonsa", "bonsa");
		//session.removeAttribute("bonsa");
		//branch_code 예비로 s0001로 
		session.setAttribute("branch_code", branch_code);
		List<ReturnInfo> returnList = service.findAll(branch_code);
		ModelAndView mav = new ModelAndView();
		mav.addObject("returnList",returnList);
		String path = "/returnlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/return/addreturn.do")
	private ModelAndView addReturn(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		String branch_code = "S0001";
		ReturnInfo info = new ReturnInfo();
		List<ReturnLine> lines = new ArrayList<>();
		Map<ReturnLine,Integer> cart = (Map)session.getAttribute("cart");
		for(ReturnLine line : cart.keySet()) {
			int quantity = cart.get(line);
			line.setReturn_quantity(quantity);
			lines.add(line);
		}
		info.setReturn_lines(lines);
		service.addReturn(branch_code, info);
		//일단 주문 성공만!
		mav.addObject("status","ok");
		
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
									  ,int return_no) {
		ModelAndView mav = new ModelAndView();
		//"3" = 승인완료로 변경.
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
		//"3" = 승인완료로 변경.
		//예외처리 아직 안함..
		service.modifyRtnFlag(branch_code, "4", return_no);
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
		//"3" = 승인완료로 변경.
		//예외처리 아직 안함..
		service.modifyRtnFlag(branch_code, "6", return_no);
		mav.addObject("status", "ok");
		
		String path="/updateflagresult"; //다른 flag update도 사용하기
		mav.setViewName(path);
		return mav;
	}
	
	

}
