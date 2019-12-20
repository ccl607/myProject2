package com.group4.erp.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.group4.erp.SalaryDTO;
import com.group4.erp.HrListSearchDTO;

@Repository
public class HrDAOImpl implements HrDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	String mapper_namespace = "com.group4.erp.dao.HrDAO.";

	@Override
	public List<SalaryDTO> getEmpSalList() {
		// TODO Auto-generated method stub
		System.out.println("getEmpSalList() �޼ҵ�� DB ���� ����");
		List<SalaryDTO> empSalList = this.sqlSession.selectList(mapper_namespace + "getEmpSalList");
		System.out.println("getEmpSalList() �޼ҵ�� DB ���� ��");
		
		return empSalList;
	
	}
	
	public int getEmpListAllCnt(HrListSearchDTO hrListSearchDTO) {
		
		int getEmpBoardListCnt = this.sqlSession.selectOne(
				"com.group4.erp.dao.HrDAO.getEmpListCnt"
				,hrListSearchDTO
				);
		
		return getEmpBoardListCnt;
	}
	
	public List<Map<String, String>> getEmpList(HrListSearchDTO hrListSearchDTO){

		List<Map<String, String>> getEmpBoardList = this.sqlSession.selectList(
				"com.group4.erp.dao.HrDAO.getEmpList"
				,hrListSearchDTO
				);
		//System.out.println("DAO=>" + getEmpBoardList);
		return getEmpBoardList;

	}
	
	
	@Override
	public int getDayOffListCnt(HrListSearchDTO hrListSearchDTO) {
		int getDayOffListCnt = this.sqlSession.selectOne(
				"com.group4.erp.dao.HrDAO.getDayOffListCnt"
				,hrListSearchDTO
				);
		
		return getDayOffListCnt;
	}

	@Override
	public List<Map<String, String>> getDayOffList(HrListSearchDTO hrListSearchDTO) {
		List<Map<String, String>> getDayOffList = this.sqlSession.selectList(
				"com.group4.erp.dao.HrDAO.getDayOffList"
				,hrListSearchDTO
				);
		//System.out.println("DAO=>" + getDayOffList);
		return getDayOffList;
	}
	
	
	

}
