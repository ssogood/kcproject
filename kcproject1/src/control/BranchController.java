package control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kc.service.BranchInfoService;

@Controller
public class BranchController {

	@Autowired
	private BranchInfoService service;
	
	@RequestMapping("/branch/login.do")
	private String login(HttpSession session
						,String branch_code
						,String branch_pwd
						,ModelMap model) {
		//System.out.println("controller들어옴");
		
		String result = service.login(branch_code, branch_pwd);
		if(result.equals("ok")) {
			model.addAttribute("result", "ok");
			session.setAttribute("branchLoginInfo",branch_code);
		}else {
			model.addAttribute("result", "error");
		}
		
		String path = "/loginresult";
		return path;
	}
	
	@RequestMapping("/branch/info.do")
	private ModelAndView allList(HttpSession session){
		
		
		List<BranchInfo> branchs = new ArrayList<>();
		branchs = service.findAll();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("branchs",branchs);					
		
		String path = "/";			//필요한 페이지값 넣으면 됨
		mav.setViewName(path);
		
		return mav;
	}
}
