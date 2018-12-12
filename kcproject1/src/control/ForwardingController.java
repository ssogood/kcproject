package control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.BaljuService;
import com.kc.service.BranchInfoService;
import com.kc.service.ForwardingService;
import com.kc.service.IngredientService;
import com.kc.vo.BranchInfo;
import com.kc.vo.ForwardingInfo;


@Controller
public class ForwardingController { // extends HttpServlet {	
	private static final long serialVersionUID = 1L;


	@Autowired
	private ForwardingService forservice;

	@Autowired
	private BaljuService balservice;
	
	@Autowired
	private IngredientService ingrservice;
	
	@Autowired
	private BranchInfoService brcservice;

	@RequestMapping({"/forwarding/baljugrandlist.do",""})
	private ModelAndView grandall(
			HttpSession ss
			,@RequestParam(required=false) String searchItem
			,@RequestParam(required=false) String searchValue) {			

		ModelAndView mav = new ModelAndView();

		List<Map<String,Object>> grandall = balservice.findGrandAll();
		mav.addObject("grandall", grandall);	

		String path = "/forwardbaljulistresult";
		mav.setViewName(path);
		return mav;
	}

	@RequestMapping("/forwarding/forwardinglist.do")
	private ModelAndView list(
			HttpSession ss
			,@RequestParam(required=false) String searchItem
			,@RequestParam(required=false) String searchValue) {			

		ModelAndView mav = new ModelAndView();			
		try {
			//TODO: (String)ss.getAttribute("loginInfo");
			List<ForwardingInfo> list = forservice.findAll();
			mav.addObject("list", list);		
			mav.addObject("status", "ok");
			
		}catch(Exception e) {
			mav.addObject("status", "err");
		}
		String path = "/forwardinglistresult";
		mav.setViewName(path);
		return mav;
	}
	  
	@ResponseBody
	@RequestMapping("/forwarding/addforwarding.do")	
	private ModelAndView addforwarding(HttpSession ss, String branch_code, int balju_no){
		System.out.println("test:" + branch_code + ", "+ balju_no);
		ForwardingInfo finfo = new ForwardingInfo();
		ModelAndView mav = new ModelAndView();
		
		try {
			BranchInfo brcinfo = new BranchInfo();
			brcinfo.setBranch_code(branch_code);//  지점코드
			finfo.setBranch(brcinfo);			
			finfo.setBalju_no(balju_no); //발주번호
			forservice.addForwarding(finfo);
			mav.addObject("status", "ok");
			
		}catch(Exception e) {
			if(e.getMessage().equals("-1")) {
				mav.addObject("status", "idfail");
				mav.addObject("msg", "로그인하세요");
			}else {
				mav.addObject("status", "error");
				mav.addObject("msg", e.getMessage());
			}			
			mav.addObject("status", "error");
			mav.addObject("msg", e.getMessage());
		}
		String path = "/addforwardingresult";
		mav.setViewName(path);
		return mav;
	}
	
/*	//ajax balju쪽으로 요청해서 처리해보림
  	@RequestMapping("/forwarding/updatebaljuflag.do")
	private ModelAndView updateforwardingflag(HttpSession session
			,String branch_code
			,int balju_no
			//,int forwarding_no //여기서할까, sql에서 트리거로 처리할까 고민
			,String newflag
			) {

		ModelAndView mav = new ModelAndView();
		System.out.println(branch_code+":"+balju_no+":"+newflag);
		try {
			balservice.changeState(branch_code, balju_no, newflag);
			mav.addObject("status", "ok");
		}catch(Exception e) {
			mav.addObject("status", "err");
		}
		String path="/updatebaljuflagresult";
		mav.setViewName(path);
		return mav;
	}*/
	
	//지점에서 수취확인
	@RequestMapping("/forwarding/updateforwardingflag.do")
	private ModelAndView updateforwardingflag(
			HttpSession session
			,String branch_code
			,int balju_no
			,String newflag ) {
		
		ModelAndView mav = new ModelAndView();		
		System.out.println(branch_code+":"+balju_no+":"+newflag);
		
		try {
			int forwarding_no = forservice.findNo(branch_code, balju_no);//지점번호랑 발주번호로 출고번호를 찾아라
			forservice.changeState(forwarding_no, newflag);
			mav.addObject("status", "ok");
			
		}catch(Exception e) {
			mav.addObject("status", "err");
		}
		String path="/updateforwardingflagresult";
		mav.setViewName(path);
		return mav;	
	}

}
