package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kc.exception.DataAccessException;
import com.kc.exception.NotFoundException;
import com.kc.service.BaljuService;
import com.kc.service.IngredientService;
import com.kc.vo.BaljuInfo;
import com.kc.vo.BaljuLine;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;


@Controller
public class BaljuController { // extends HttpServlet {	
	private static final long serialVersionUID = 1L;



	@Autowired
	private BaljuService service;

	@Autowired
	private IngredientService service2;

	/*
	 * 출고 콘트롤로넘김
	@RequestMapping({"/forwarding/baljugrandlist.do",""})
	private ModelAndView grandall(
			HttpSession ss
			,@RequestParam(required=false) String searchItem
			,@RequestParam(required=false) String searchValue) {			

		ModelAndView mav = new ModelAndView();

		List<Map<String,Object>> grandall = service.findGrandAll();
		mav.addObject("grandall", grandall);	

		String path = "/baljugrandlistresult";
		mav.setViewName(path);
		return mav;
	}
*/



	//branch
	@RequestMapping("/balju/baljulist.do")
	private ModelAndView all(
			HttpSession ss
			,@RequestParam(required=false) String searchItem
			,@RequestParam(required=false) String searchValue) {			

		ModelAndView mav = new ModelAndView();			
		try {
			//TODO: String branch_code = (String)ss.getAttribute("loginInfo");
			String branch_code = "S0001";
			List<BaljuInfo> all = service.findAll(branch_code);
			mav.addObject("all", all);		
			mav.addObject("status", "ok");
			
		}catch(Exception e) {
			mav.addObject("status", "err");
		}
		String path = "/baljulistresult";
		mav.setViewName(path);
		return mav;
	}




	@ResponseBody
	@RequestMapping("/balju/addbalju.do")
	private ModelAndView addBalju(
			HttpSession ss
			, @RequestBody String paramData){

		//String loginId = (String)ss.getAttribute("loginInfo");		
		ModelAndView mav = new ModelAndView();
		try {
			//			if(loginId == null) {
			//				throw new DataAccessException("-1"); //idfail
			//			}
			//			BaljuInfo baljuinfo = new BaljuInfo();			
			//			BranchInfo branchinfo = new BranchInfo();
			//			String loginId = "S0001";
			//			branchinfo.setBranch_code(loginId);
			//			String branch_code = loginId;			



			List<BaljuLine> baljuLines = new ArrayList<>();

			//String branch_code = null;
			
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray)parser.parse(paramData);         
			
			
			for(int i=0; i<jsonArray.size(); i++) {            
				JSONObject obj = (JSONObject)jsonArray.get(i);                        
				System.out.println(obj.get("branch_code")+":"+obj.get("ingred_no")+":"+obj.get("balju_quantity"));  
				//branch_code = (String)obj.get("branch_code");
				int ingred_no = Integer.parseInt((String)obj.get("ingred_no"));
				int balju_quantity = Integer.parseInt((String)obj.get("balju_quantity"));
				BaljuLine line = new BaljuLine();
				IngredientInfo ingr = service2.findByNoingr(ingred_no);

				//line.setBalju_no(baljuinfo.getBalju_no());
				line.setIngredient(ingr);
				line.setBalju_quantity(balju_quantity);
				baljuLines.add(line);	
			}
			BaljuInfo baljuinfo = new BaljuInfo();			
			baljuinfo.setBaljuLines(baljuLines);
			String branch_code = "S0001";//TODO:나중에로그인으로
			service.addBalju(branch_code, baljuinfo);
			mav.addObject("status", "ok");
			mav.addObject("msg", "발주시작");			

		}catch(DataAccessException | ParseException | NotFoundException e) {		
			/*if(e.getMessage().equals("-1")) {
				mav.addObject("status", "idfail");
				mav.addObject("msg", "로그인하세요");
			}else {
				mav.addObject("status", "error");
				mav.addObject("msg", e.getMessage());
			}*/			
			mav.addObject("status", "error");
			mav.addObject("msg", e.getMessage());
		}
		String path = "/addbaljuresult";
		mav.setViewName(path);
		return mav;
	}




	@RequestMapping({"/balju/updatebaljuflag.do"})
	private ModelAndView updatebaljuflag(HttpSession session
			,String branch_code
			,int balju_no
			,String newflag
			) {

		ModelAndView mav = new ModelAndView();
		System.out.println(branch_code+":"+balju_no+":"+newflag);
		try {
			service.changeState(branch_code, balju_no, newflag);
			mav.addObject("status", "ok");
			
		}catch(Exception e) {
			mav.addObject("status", "err");
		}
		
		String path="/updatebaljuflagresult";
		mav.setViewName(path);
		return mav;
	}

}
