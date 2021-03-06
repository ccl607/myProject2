package com.group4.erp.dao;

import java.util.List;

import com.group4.erp.AdApplyDTO;
import com.group4.erp.CorpOrderDTO;
import com.group4.erp.CorporationDTO;
import com.group4.erp.EventDTO;
import com.group4.erp.EventSearchDTO;
import com.group4.erp.OrderDTO;
import com.group4.erp.SalesInfoDTO;

public interface MarketingDAO {
	
	int getEventCnt(EventSearchDTO eventSearchDTO);
	
	int getEventAllCnt();
	
	List<EventDTO> getEventList(EventSearchDTO eventSearchDTO);
	
	int getAdApplyCnt();
	
	List<AdApplyDTO> getAdApplyList();
	
	int insertEvent(EventDTO eventDTO);
	
	List<CorporationDTO> getCorpList();
	
	int insertAd(AdApplyDTO adApplyDTO);
	
	int getOnlineOrderCnt(SalesInfoDTO salesSearchDTO);
	
	int deleteEvnt(String[] evnt_no);
	
	int updateEvntState();
	
	List<OrderDTO> getOnlineOrderList(SalesInfoDTO salesSearchDTO);
	
	int getTotRevenue();
	
	int getCorpOrderTotCnt();
	
	int getCorpTotRevenue();
	
	List<SalesInfoDTO> getOrderInfoChart();
	
	int updateEventInfo(EventDTO eventDTO);
	
	int updateAdInfoProc(AdApplyDTO adApplyDTO);
	
	List<CorpOrderDTO> getCorpOrderCntChart();
	
	List<SalesInfoDTO> getDailyOrderCnt();
	
	List<SalesInfoDTO> getDailyCorpOrderCnt();
	
	EventDTO getMyEventInfoApproval(String document_no);

	int updateEvntApprovalState(EventDTO eventDTO);
	
}
