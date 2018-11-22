package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.BranchInfoService;
import com.kc.service.StatsService;
import com.kc.vo.BranchInfo;
import com.kc.vo.Stats;

@Controller
public class BranchInfoController {
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private BranchInfoService branch_service;
	
	
	@RequestMapping("/branch/info.do")
	private ModelAndView allList(HttpSession session){
		
		
		List<BranchInfo> branchs = new ArrayList<>();
		branchs = branch_service.findAll();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("branchs",branchs);					
		
		String path = "/";			//필요한 페이지값 넣으면 됨
		mav.setViewName(path);
		
		return mav;
	}

}
