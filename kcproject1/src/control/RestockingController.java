package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.RestockingService;
import com.kc.service.ReturnService;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;
import com.kc.vo.RestockingInfo;
import com.kc.vo.RestockingLine;
import com.kc.vo.ReturnInfo;
import com.kc.vo.ReturnLine;

@Controller
public class RestockingController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RestockingService service;
	
	@Autowired
	private ReturnService returnservice;
	
	@RequestMapping("/restocking/restockinglist.do")
	private ModelAndView RestockingList(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<RestockingInfo> restockingList = service.findAll();
		mav.addObject("restockingList",restockingList);
		String path="/restockinglistresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/restocking/addrestocking.do")
	private ModelAndView AddRestocking(HttpSession session
									   ,String branch_code, int return_no) {
		ModelAndView mav = new ModelAndView();
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo = returnservice.findRtnNo(branch_code, return_no);
		List<ReturnLine> rtn_lines = new ArrayList<>();
		rtn_lines = returnInfo.getReturn_lines();
		RestockingInfo info = new RestockingInfo();
		List<RestockingLine> rst_lines = new ArrayList<>();
		for(ReturnLine rtn_line:rtn_lines) {
			RestockingLine rst_line = new RestockingLine();
			IngredientInfo ingredient = new IngredientInfo();
			ingredient = rtn_line.getIngredient();
			String rsl_prod_state_flag = rtn_line.getRtl_prod_state_flag();
			int restocking_quantity = rtn_line.getReturn_quantity();
			rst_line.setIngredient(ingredient);
			rst_line.setRestocking_quantity(restocking_quantity);
			rst_line.setRsl_prod_state_flag(rsl_prod_state_flag);
			rst_lines.add(rst_line);
		}
		
		info.setRestocking_lines(rst_lines);
		info.setReturn_no(return_no);
		BranchInfo branch = new BranchInfo();
		branch.setBranch_code(branch_code);
		info.setBranch(branch);
		service.addRestocking(info);
		mav.addObject("status", "ok");
		String path = "/addrestockingresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/restocking/updateflagdc.do")
	private ModelAndView UpdateFlagDc(HttpSession session
									   ,@ModelAttribute(name="line")RestockingLine line
									   ,@ModelAttribute(name="ingredient")IngredientInfo ingredient) {
		
		ModelAndView mav = new ModelAndView();
		//System.out.println(line.getRestocking_no());
		//System.out.println(ingredient.getIngred_no());
		line.setIngredient(ingredient);
		service.modifyFalgDc(line);
		mav.addObject("status", "ok");
		String path="/updateflagdcresult";
		mav.setViewName(path);
		return mav;
	}
	

}
