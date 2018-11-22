package control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.ReturnService;
import com.kc.vo.ReturnInfo;

@Controller
public class ReturnController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReturnService service;
	
	@RequestMapping("/return/returnlist.do")
	private ModelAndView allList(HttpSession session){
		String branch_code = "S0001";
		List<ReturnInfo> returnList = service.findAll(branch_code);
		ModelAndView mav = new ModelAndView();
		mav.addObject("returnList",returnList);
		String path = "/returnlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	
	
	
	
	
}
