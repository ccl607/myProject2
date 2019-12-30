package com.group4.erp.dao;

import com.group4.erp.*;
import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvenDAOImpl implements InvenDAO{

   @Autowired
   private SqlSessionTemplate sqlSession;
   
   
   
	@Override
	public List<Map<String, String>> getBranch(InvenSearchDTO invenSearchDTO) {
		List<Map<String, String>> branch = this.sqlSession.selectList(
				"com.group4.erp.dao.invenSearchDAO.getBranch"
				,invenSearchDTO
				);
		//System.out.println("DAO"+branch);
		return branch;
	}
	
	
	public List<Map<String, String>> getPublisher(InvenSearchDTO invenSearchDTO){
		List<Map<String, String>> publisher = this.sqlSession.selectList(
				"com.group4.erp.dao.invenSearchDAO.getPublisherList"
				,invenSearchDTO
				);
		
		return publisher;
	}

	public List<Map<String,String>> getBookList(InvenSearchDTO invenSearchDTO){
		
		List<Map<String,String>> getBookList = this.sqlSession.selectList(
					"com.group4.erp.dao.invenSearchDAO.getBookList"
					,invenSearchDTO
				);
		
		return getBookList;
		
	}
	
	public List<Map<String, String>> getInvenLoc(InvenSearchDTO invenSearchDTO){
		
		List<Map<String,String>> getInventory_loc = this.sqlSession.selectList(
				"com.group4.erp.dao.invenSearchDAO.getInven_loc"
				,invenSearchDTO
			);
		
		return getInventory_loc;
	}
	
	public int getBookListCnt(InvenSearchDTO invenSearchDTO) {
		
		int bookListCnt = this.sqlSession.selectOne(
				"com.group4.erp.dao.invenSearchDAO.getBookListCnt"
				,invenSearchDTO
				);
		
		return bookListCnt;
	}

	public int getReleaseListCnt(InvenSearchDTO invenSearchDTO) {
		
		int releaseListCnt = this.sqlSession.selectOne(
				"com.group4.erp.dao.invenSearchDAO.getReleaseListCnt"
				,invenSearchDTO
				);
		
		return releaseListCnt;
	}
	
	public List<Map<String,String>> getReleaseList(InvenSearchDTO invenSearchDTO){
		
		List<Map<String,String>> getReleaseList = this.sqlSession.selectList(
				"com.group4.erp.dao.invenSearchDAO.getReleaseList"
				,invenSearchDTO
			);
	
		return getReleaseList;
	}
	
	public Cus_releaseInfoDTO getReleaseCusInfo(int all_order_no) {

		Cus_releaseInfoDTO cus_order = this.sqlSession.selectOne(
				"com.group4.erp.dao.invenSearchDAO.getReleaseCusInfo"
				,all_order_no);
		
		return cus_order;
	}
	
	public Cus_releaseInfoDTO getReleaseCorpInfo(int all_order_no) {

		Cus_releaseInfoDTO corp_order = this.sqlSession.selectOne(
				"com.group4.erp.dao.invenSearchDAO.getReleaseCorpInfo"
				,all_order_no);
		
		return corp_order;
	}
	
	public int getReturnOrderCnt() {	
		int returnOrderCnt = this.sqlSession.selectOne("com.group4.erp.dao.invenSearchDAO.getReturnOrderCnt");

		
		return returnOrderCnt;
	}
	
	public List<ReturnOrderDTO> getReturnOrderList(ReturnSearchDTO returnSearchDTO) {
		List<ReturnOrderDTO> returnOrderList = this.sqlSession.selectList("com.group4.erp.dao.invenSearchDAO.getReturnOrderList", returnSearchDTO);
		
		return returnOrderList;
	}
	

	public int getReturnOrderCnt(ReturnSearchDTO returnSearchDTO) {	
		int returnOrderCnt = this.sqlSession.selectOne("com.group4.erp.dao.invenSearchDAO.getReturnOrderCnt", returnSearchDTO);
		return returnOrderCnt;
	}
	
	public int getSignUpCnt(InvenDTO invenDTO) {
		
		int insertSignUpBookCnt = this.sqlSession.insert(
				"com.group4.erp.dao.invenSearchDAO.getSignUpBook"
				,invenDTO
				);
		
		return insertSignUpBookCnt;
	}
	
	public BookInfoDTO getBookInfo(String isbn13_search) {
		
		BookInfoDTO bookInfo = this.sqlSession.selectOne(
				"com.group4.erp.dao.invenSearchDAO.getBookInfo"
				,isbn13_search
				);
		
		return bookInfo;
		
	}
}
