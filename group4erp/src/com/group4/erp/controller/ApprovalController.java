package com.group4.erp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.group4.erp.ApprovalDTO;
import com.group4.erp.ApprovalSearchDTO;
import com.group4.erp.CorpSearchDTO;
import com.group4.erp.CorporationDTO;
import com.group4.erp.DayOffApplyDTO;
import com.group4.erp.EventDTO;
import com.group4.erp.EventSearchDTO;
import com.group4.erp.service.ApprovalService;
import com.group4.erp.service.HRService;
import com.group4.erp.service.MarketingService;
import com.group4.erp.service.MyWorkService;

@Controller
public class ApprovalController {
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	MarketingService marketingService;
	
	@Autowired
	MyWorkService myWorkService;
	
	@Autowired
	HRService hrService;
	
	@RequestMapping(value="/viewApprovalList.do")
	public ModelAndView viewApprovalList(HttpSession session, ApprovalSearchDTO approvalSearchDTO) {
		
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("eventScheduleForm.jsp");
		
		String emp_no = (String)session.getAttribute("emp_id");
		String emp_name = (String)session.getAttribute("emp_name");
		int jikup_cd = (Integer)session.getAttribute("jikup_cd");
		
		int my_emp_no = Integer.parseInt(emp_no);
		approvalSearchDTO.setEmp_no(my_emp_no);
		
		System.out.println("viewApprovalList emp_no==="+emp_no);
		
		mav.setViewName("main.jsp");
		mav.addObject("subMenu", "viewApprovalList");
		mav.addObject("navigator", "[업무관리]-[문서 결재]");
		
		int approvalCnt = 0;
		int approvalResCnt = 0;
		
		List<ApprovalDTO> approvalReqList;
		List<ApprovalDTO> approvalResList;
		
		if(jikup_cd==1) {
			System.out.println("대표이사 결재");
		
			approvalResCnt = this.approvalService.getApprovalCEOResCnt(approvalSearchDTO);
			approvalResList = this.approvalService.getApprovalCEOResList(approvalSearchDTO);
			
		} else {
			System.out.println("부서장 결재");
						
			approvalCnt = this.approvalService.getApprovalReqCnt(approvalSearchDTO);
			approvalResCnt = this.approvalService.getApprovalResCnt(approvalSearchDTO);
			approvalResList = this.approvalService.getApprovalResList(approvalSearchDTO);
		}
		
		
		
		approvalReqList = this.approvalService.getApprovalReqList(approvalSearchDTO);
		
		
		mav.addObject("approvalCnt", approvalCnt);
		mav.addObject("approvalReqList", approvalReqList);
		mav.addObject("approvalResCnt", approvalResCnt);
		mav.addObject("approvalResList", approvalResList);
		

		return mav;
	}
	
	@RequestMapping(value="/viewApprovalDoc.do")
	public ModelAndView viewApprovalDoc(HttpSession session, 
			ApprovalDTO approvalDTO, 
			EventSearchDTO eventSearchDTO, 
			DayOffApplyDTO dayOffApplyDTO, 
			String document_no) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("main.jsp");
		mav.addObject("subMenu", "viewApprovalDoc");
		mav.addObject("navigator", "[업무관리]-[문서 결재]-[결재할 문서 검토]");
		
		//String approvalType = approvalDTO.getDocument_no();
		String approvalDoc = "";
		String tableName = "";
		
		String emp_no = (String)session.getAttribute("emp_id");
		String mgr = (String)session.getAttribute("mgr");
		String mgr_no = (String)session.getAttribute("mgr_emp_no");
		String ceo_no = (String)session.getAttribute("ceo_no");
		String ceo_name = (String)session.getAttribute("ceo_name");
		
		int my_emp_no = Integer.parseInt(emp_no);
		System.out.println("emp_no==="+my_emp_no);
	
				
		if(document_no.indexOf("EV") >=0 ) {
			approvalDoc="EV";
			tableName = "event_info";
			eventSearchDTO.setSearchKeyword(document_no);
			eventSearchDTO.setTableName(tableName);
			
			int approvalState = this.approvalService.updateApprovalState(document_no);
			
			int event_state = this.approvalService.updateEventState(document_no);
			
			EventDTO myEventInfo = this.marketingService.getMyEventInfoApproval(document_no);
			mav.addObject("approvalInfoList", myEventInfo);
			mav.addObject("approvalCategory", "이벤트 행사 신청 기획안입니다.");
			
			
		} else if(document_no.indexOf("DO") >=0) {
			System.out.println("휴가 신청 결재화면 보기");
			
			String doc_num = document_no.substring(3);
			
			int my_dayoff_approval_no = Integer.parseInt(doc_num);
			
			System.out.println("my_dayoff_approval_no==="+my_dayoff_approval_no);
		
			approvalDoc = "DO";
			tableName = "emp_day_off_apply";
			
			DayOffApplyDTO myDayOffApplyInfo = this.myWorkService.getMyDayOffApproval(my_dayoff_approval_no);
			
			System.out.println("myDayOffApplyInfo.getDayoff_category()==="+myDayOffApplyInfo.getDayoff_category());
			System.out.println("myDayOffApplyInfo.getEmp_name()==="+myDayOffApplyInfo.getEmp_name());
			
			mav.addObject("myDayOffApplyInfo", myDayOffApplyInfo);
			mav.addObject("approvalCategory", "휴가 신청 결재 바랍니다.");
		}
			
		mav.addObject("approvalDoc", approvalDoc);
		
		
		return mav;
		
	}
	
	@RequestMapping(value="/updateEventApproavalProc.do", 
			method=RequestMethod.POST, 
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public int updateEvntApprovalProc(ApprovalDTO approvalDTO, EventDTO eventDTO, String approvalYn, String document_no, String e_work_comment) {
	
		int approvalUpCnt = 0;
		int evntUpCnt = 0;
		
		try {
			
			System.out.println("approvalYn==="+approvalYn);
			System.out.println("document_no==="+document_no);
			System.out.println("반려사유==="+e_work_comment);
			
			approvalDTO.setE_works_state_cd(approvalYn);
			approvalDTO.setDocument_no(document_no);
			approvalDTO.setE_work_comment(e_work_comment);
			
			eventDTO.setEvnt_state_cd(approvalYn);
			eventDTO.setEvnt_no(document_no);

			approvalUpCnt = this.approvalService.updateApprovalProc(approvalDTO);
			evntUpCnt = this.marketingService.updateEvntApprovalState(eventDTO);
			
			/*if(upDel.equals("up")) {
				upDelCnt = this.boardService.updateBoard(boardDTO);
			}
			
			//만약 삭제 모드이면 삭제 실행하고 삭제 적용행의 개수를 저장
			else {
				upDelCnt = this.boardService.deleteBoard(boardDTO);
			} */
			
		} catch(Exception e) {
			System.out.println("deleteCorpProc() 메소드에서 예외 발생 >>> "+e);
		}
				
		return approvalUpCnt;
	}
	
	@RequestMapping(value="/updateDayOffApproavalProc.do", 
			method=RequestMethod.POST, 
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public int updateDayOffApprovalProc(ApprovalDTO approvalDTO, DayOffApplyDTO dayOffApplyDTO, String approvalYn, String document_no) {
	
		int approvalUpCnt = 0;
		int dayOffUpCnt = 0;
		
		try {
			
			System.out.println("approvalYn==="+approvalYn);
			System.out.println("document_no==="+document_no);
			
			approvalDTO.setE_works_state_cd(approvalYn);
			approvalDTO.setDocument_no("DO-"+document_no);
			approvalDTO.setDoc_no(Integer.parseInt(document_no));
			
			String confirm = "";
			
			if(approvalYn=="7" || approvalYn.equals("7") || approvalYn=="5" || approvalYn.equals("5")) {	//승인되었을 때				
				confirm="Y";
				approvalDTO.setConfirm(confirm);
				approvalUpCnt = this.approvalService.updateApprovalProc(approvalDTO);
				dayOffUpCnt = this.hrService.updateDayOffApprovalProc(approvalDTO);
				
			} else if(approvalYn=="6" || approvalYn.equals("6")) {	//반려되었을 때
				confirm="C";
				approvalDTO.setConfirm(confirm);
				approvalUpCnt = this.approvalService.updateApprovalProc(approvalDTO);
				dayOffUpCnt = this.hrService.updateDayOffApprovalProc(approvalDTO);
			}

			
			//approvalUpCnt = this.approvalService.updateApprovalProc(approvalDTO);
			//dayOffUpCnt = this.marketingService.updateEvntApprovalState(eventDTO);
			
			/*if(upDel.equals("up")) {
				upDelCnt = this.boardService.updateBoard(boardDTO);
			}
			
			//만약 삭제 모드이면 삭제 실행하고 삭제 적용행의 개수를 저장
			else {
				upDelCnt = this.boardService.deleteBoard(boardDTO);
			} */
			
		} catch(Exception e) {
			System.out.println("updateDayOffApprovalProc() 메소드에서 예외 발생 >>> "+e);
		}
				
		return approvalUpCnt;
	}

}
