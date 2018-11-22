package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.BranchInfoService;
import com.kc.service.StatsService;
import com.kc.vo.BranchInfo;
import com.kc.vo.Stats;


@Controller
public class StatsController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StatsService service;
	
	@Autowired
	private BranchInfoService branch_service;
	
	
	@RequestMapping("/stats/all.do")
	private ModelAndView allList(){
		//현재 S0001, S0002 에 대한 테이블 밖에 없으므로 S0003, S0004, S0005가 없어서 에러남
		/*
		//branch_code만 추출하기 위함
		List<BranchInfo> branchs = new ArrayList<>();
		branchs = branch_service.findAll();
		
		List<String> branch_code_list = new ArrayList<>();

		for(BranchInfo b : branchs) {
			branch_code_list.add(b.getBranch_code());			
		}	
		
		System.out.println("allList () : branch_code_list \n" +branch_code_list);		
		*/
		
		
		//[TEST용] 현재 존재하는 S0001, S0002에 대해서만 일단 넘겨줌
		List<String> branch_code_list = new ArrayList<>();
		branch_code_list.add("S0001");
		branch_code_list.add("S0002");
		
		// test값 
		String tocharDate = "'YY/MM'";
		String period = "18/%";
		
		List<Stats> statsList = service.findAllBranchStats(branch_code_list, tocharDate, period);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("statsList",statsList);
		
		String path = "/statsresult";
		mav.setViewName(path);
		
		return mav;
	}
	
	@RequestMapping(path="/stats/sbtest.do")
	private String selectTeest(
	/*	private ModelAndView productlist(
		@RequestParam(name = "searchItem"
								, required = false
							//	, defaultValue="no"
					)String searchItem			
			
			, @RequestParam(name="searchValue"
								, required= false
							//	, defaultValue = ""			
					)String searchValue*/
			
			) {
		
		
		//System.out.println("in server searchItem=" + searchItem + ", searchValue="+ searchValue);
		/*
		ModelAndView mav = new ModelAndView();
		
		if(searchItem != null && searchValue != null &&
		  !searchValue.equals("")) { //조건검색
			ArrayList<Product> list = new ArrayList<>();
			if(searchItem.equals("no")) {//번호로 검색				
				try {
					Product p = service.findByNo(searchValue);
					list.add(p);					
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}else if(searchItem.equals("name")) {//이름으로 검색
				list = (ArrayList)service.findByName(searchValue);
								
			}
			//request.setAttribute("list", list);
			mav.addObject("list", list);
		}else { //전체검색
			List<Product> list = service.findAll();
			//request.setAttribute("list", list);
			mav.addObject("list", list);
			
		}*/
		String path = "/sbtest";		//mvc:jsp 
		
		/*mav.setViewName(path);
		
		System.out.println(mav);
		*/
		//return mav;
		return path;
	}
	

}
