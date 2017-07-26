package cn.bos.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;

public interface StandardService {

	Page<Standard> findAll(PageRequest pageAble);

	void save(Standard model);

	void updateDelTagById(String[] arr);

	//void restoreDelTagById(int parseInt);

	Standard findOne(int id);

	void updateDelTagByIds(String[] arr);

	Standard findOneByName(Standard model);

	List<Standard> findList();


}
