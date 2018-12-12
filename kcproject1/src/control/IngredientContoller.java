package control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.IngredientService;
import com.kc.vo.IngredientInfo;
import com.kc.exception.NotFoundException;


@Controller
public class IngredientContoller {
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
	
	
	
	
	
	
	
	
	
	
	@RequestMapping({"/balju/baljuingrlist.do","ingredient/searchingrlist.do"})
	private ModelAndView ingrlist(
			@RequestParam(required=false) String searchItem
           ,@RequestParam(required=false) String searchValue) {
		
		ModelAndView mav = new ModelAndView();
		
		//List<IngredientInfo> all = new ArrayList<>();
		//all = service.findAllingr();
		//mav.addObject("all", all);
		
		List<IngredientInfo> list = new ArrayList<>();
		if(searchItem != null && searchValue != null && !searchValue.equals("")) {
			
			if(searchItem.equals("no")) {//번호로 검색				
				try {
					IngredientInfo i = service.findByNoingr(Integer.parseInt(searchValue));
					list.add(i);					
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}else if(searchItem.equals("name")) {//이름으로 검색				
				list = service.findByNameingr(searchValue);
			}
			mav.addObject("list", list);
		}else { //전체검색
			list = service.findAllingr();
			mav.addObject("list", list);
		}
		
		String path = "/baljuingrlistresult";
		mav.setViewName(path);
		return mav;
	}
	
	
	/*@RequestMapping("/balju/baljuingrfind.do")
	private ModelAndView baljuingrfind(int ingred_no){
		ModelAndView mav = new ModelAndView();		
		try {
			IngredientInfo ii = service.findByNoingr(ingred_no);
			mav.addObject("ii", ii);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		String path = "/baljuingrlistresult";
		mav.setViewName(path);
		return mav;	
	}*/

}
