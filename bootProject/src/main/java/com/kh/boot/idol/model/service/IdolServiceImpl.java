package com.kh.boot.idol.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.boot.idol.model.dao.IdolMapper;
import com.kh.boot.idol.model.vo.Idol;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdolServiceImpl implements IdolService{

	private final IdolMapper idolMapper;
	
	@Override
	public List<Idol> selectAll() {
		return idolMapper.selectAll(); 
	}

	
	
	
}
