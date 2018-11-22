package control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.IngredientService;
import com.kc.vo.IngredientInfo;

@Controller
public class IngredientController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IngredientService service;
	
	@RequestMapping("/ingredient/ingalllist.do")
	//@ResponseBody
	//private String ingAllList() {
	private ModelAndView ingAllList() {
		ModelAndView mav = new ModelAndView();
		List<IngredientInfo> allList = new ArrayList<>();
		allList = service.findAll();
		//return allList.toString();
		mav.addObject("allList", allList);
		String path = "/allingredresult";
		mav.setViewName(path);
		return mav;
	}

}
