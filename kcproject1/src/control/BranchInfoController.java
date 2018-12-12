package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.BranchInfoService;
import com.kc.vo.BranchInfo;

@Controller
public class BranchInfoController {
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private BranchInfoService branch_service;
	
		
	@RequestMapping("/stats/all.do")
	private ModelAndView allList(HttpSession session){
		
		
		List<BranchInfo> branchs = new ArrayList<>();
		branchs = branch_service.findAll();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("branchs",branchs);					
		
		String path = "/";			//필요한 페이지값 넣으면 됨
		mav.setViewName(path);
		
		return mav;
	}
	
	
	
	@RequestMapping("addbranch.do")
	private ModelAndView addbranch(
			HttpSession session, BranchInfo branchinfo){		
		ModelAndView mav = new ModelAndView();
		branch_service.addBF(branchinfo);
		String path = "/addbranchresult";
		mav.setViewName(path);
		return mav;
	}

}
