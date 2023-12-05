package com.kh.boot.idol.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.boot.idol.model.vo.Idol;

@Mapper // sqlSessionTemplate를 자동으로 주입해주기 때문에 내가 만들 필요가 없음
public interface IdolMapper {

	List<Idol> selectAll();
}
