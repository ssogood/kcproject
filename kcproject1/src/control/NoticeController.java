package control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kc.service.NoticeService;
import com.kc.vo.Employees;
import com.kc.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	@RequestMapping("/notice/noticeall.do")
	private ModelAndView noticeAll(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		List<Notice> allNoticeList = new ArrayList<>();
		
		allNoticeList = service.findAll();
		
		
		mav.addObject("allNoticeList",allNoticeList);
		String path = "/noticeallresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticereadonly.do")
	private ModelAndView noticeReadOnly(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Notice> allNoticeList = new ArrayList<>();
		
		allNoticeList = service.findAll();
		
		
		mav.addObject("allNoticeList",allNoticeList);
		String path = "/noticereadonlyresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticedetail.do")
	private ModelAndView noticeDetail(HttpSession session, int text_no) {
		ModelAndView mav = new ModelAndView();
		Notice notice = new Notice();
		notice = service.findByNo(text_no);
		mav.addObject("notice", notice);
		String path = "/noticedetailresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticereaddetail.do")
	private ModelAndView noticeReadDetail(HttpSession session, int text_no) {
		ModelAndView mav = new ModelAndView();
		Notice notice = new Notice();
		notice = service.findByNo(text_no);
		mav.addObject("notice", notice);
		String path = "/noticereaddetailresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticewritepage.do")
	private String noticeWritePage(HttpSession session) {
		
		String path = "/noticewritepage";
		return path;
	}
	
	@RequestMapping("/notice/noticewrite.do")
	private ModelAndView noticeWrite(HttpSession session
									/*,String text_title
									,String text_content
									,String pwd*/
									,Notice notice
									) {
		ModelAndView mav = new ModelAndView();
		String employee_id = (String)session.getAttribute("bonsaLoginInfo");
		if(!(employee_id==null)) {
			if(notice.getText_pwd()==""||notice.getText_title()==""||notice.getText_content()=="") {
				mav.addObject("status","null");
				
			}else {
				Employees employee = new Employees();
				employee.setEmployee_id(employee_id);
				notice.setEmployee(employee);				
				service.add(notice);
				mav.addObject("status","ok");
			}
		}else {
			mav.addObject("status","id_null");
		}
		
		String path="/noticeresult";
		mav.setViewName(path);
		return mav;
		
	}
	
	@RequestMapping("/notice/noticemodify.do")
	private ModelAndView noticeModify(HttpSession session
									/*,String text_title
									,String text_content
									,String pwd*/
									,Notice notice
									) {
		ModelAndView mav = new ModelAndView();
		String employee_id = (String)session.getAttribute("bonsaLoginInfo");
		
		if(!(employee_id==null)) {
			if(notice.getText_pwd()==""||notice.getText_title()==""||notice.getText_content()=="") {
				mav.addObject("status","null");
				
			}else {
				service.modify(notice);
				mav.addObject("status","ok");
			}
		}else {
			mav.addObject("status","id_null");
		}
		
		String path="/noticeresult";
		mav.setViewName(path);
		return mav;
		
	}
	
	
	
	@RequestMapping("/notice/noticedelete.do")
	private ModelAndView noticeDelete(HttpSession session
									  ,int text_no
									  ,String text_pwd) {
		ModelAndView mav = new ModelAndView();
		if(text_pwd=="") {
			mav.addObject("status","pwd_null");
		}else {
			if(service.findByNo(text_no).getText_pwd().equals(text_pwd)) {
				service.remove(text_no);
				mav.addObject("status", "ok");
			}else {
				mav.addObject("status","error");
			}
		}	
		String path="/noticeresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticeupdate.do")
	private ModelAndView noticeUpdate(HttpSession session
									  ,int text_no
									  ,String text_pwd) {
		ModelAndView mav = new ModelAndView();
		if(text_pwd=="") {
			mav.addObject("status","pwd_null");
		}else {
			if(service.findByNo(text_no).getText_pwd().equals(text_pwd)) {
				mav.addObject("status", "ok");
			}else {
				mav.addObject("status","error");
			}
		}	
		String path="/noticeresult";
		mav.setViewName(path);
		return mav;
	}
	
	@RequestMapping("/notice/noticeupdatepage.do")
	private String noticeUpdatePage(HttpSession session
									,int text_no
									,ModelMap model) {
		Notice notice = service.findByNo(text_no);
		model.addAttribute("oldNotice", notice);
		String path = "/noticeupdatepage";
		return path;
	}
	
		
}
