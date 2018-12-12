package control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kc.service.EmployeesService;

@Controller
public class EmployeesController {

	@Autowired
	private EmployeesService service;
	
	@RequestMapping("/employees/login.do")
	private String login(HttpSession session
			              , String employee_id
			              , String employee_pwd
			              , ModelMap model) {
		
		System.out.println(employee_id);
		System.out.println(employee_pwd);
		String result = service.login(employee_id, employee_pwd);
		
		if(result.equals("ok")) {
			model.addAttribute("result", "ok");
			//session.removeAttribute("branchLoginInfo"); //지점 로그인 세션삭제
			session.setAttribute("bonsaLoginInfo",employee_id);
		}else {
			model.addAttribute("result", "error");
		}
		
		String path = "/loginresult";
		return path;
	}
	
	@RequestMapping("/employees/loginpage.do")
	private String loginPage(HttpSession session){
		System.out.println("들어옴");
		String path ="/headlogin";
		return path;
	}
	
	@RequestMapping("/employees/logout.do")
	private String logout(HttpSession session){
		
		session.invalidate(); //invalidate : 아예 session자체를 없앤다.
		String path ="/logoutresult";
		return path;
	}
	
	
	
	
	
}
