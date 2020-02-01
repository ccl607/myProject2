package com.group4.erp.dao;

import java.util.List;
import java.util.Map;

import com.group4.erp.BusinessTripDTO;
import com.group4.erp.BusinessTripSearchDTO;



public interface WorkDAO {

	//출장 신청 리스트
	List<Map<String,String>> getbusinessList(BusinessTripSearchDTO businessTripSearchDTO);
	
	//출장 신청 개수
	int getbusinessTripListAllCnt(BusinessTripSearchDTO businessTripSearchDTO);
	
	//출장 상세보기
	BusinessTripDTO getBusinessTripDTO(int work_outside_seq);
	
	//출장 신청
	int insertBusinessTrip(BusinessTripDTO businessTripDTO);
}
