package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.DiscardService;
import com.kc.vo.Discard;
import com.kc.vo.IngredientInfo;

@Controller
public class DiscardController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DiscardService service;
	
	@RequestMapping("/discard/discardlist.do")
	private ModelAndView DiscardList(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Discard> discardList = new ArrayList<>();
		discardList = service.findAll();
		mav.addObject("discardList", discardList);
		String path = "/discardlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/discard/adddiscard.do")
	private ModelAndView AddDiscard(HttpSession session
								    ,int ingred_no
								    ,int discard_quantity
								    ,String dc_prod_state_flag) {
		ModelAndView mav = new ModelAndView();
		
		Discard dc = new Discard();
		IngredientInfo ingredient = new IngredientInfo();
		ingredient.setIngred_no(ingred_no);
		dc.setIngredient(ingredient);
		dc.setDc_prod_state_flag(dc_prod_state_flag);
		dc.setDiscard_quantity(discard_quantity);
		
		service.addDiscard(dc);
		
		//오류처리 아직 안함
		mav.addObject("status", "ok");
		String path = "/adddiscardresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/discard/discardadd.do")
	private String discardAdd() {
		String path="/discardaddresult";
		return path;
	}
	
	@RequestMapping("/discard/discardremove.do")
	private ModelAndView discardRemove(HttpSession session
									   ,int discard_no) {
		ModelAndView mav = new ModelAndView();
		
		service.removeDiscard(discard_no);
		mav.addObject("status", "ok");
		String path="/discardresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/discard/discardmodify.do")
	private String discardModify(int discard_no,ModelMap model) {
		Discard discard = service.findByNo(discard_no);
		model.addAttribute("discard", discard);
		String path="/discardmodifyresult";
		return path;
	}
	
	@RequestMapping("/discard/modify.do")
	private ModelAndView modify(HttpSession session
								,Discard discard
								,IngredientInfo ingredient
								) {
		ModelAndView mav = new ModelAndView();
		System.out.println("들어오니?");
		discard.setIngredient(ingredient);
		System.out.println(discard);
		service.modifyDiscard(discard);
		
		
		mav.addObject("status","ok");
		String path="/discardresult";
		mav.setViewName(path);
		return mav;
	}
}
