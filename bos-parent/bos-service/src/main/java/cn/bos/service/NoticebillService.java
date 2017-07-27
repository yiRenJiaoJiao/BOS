package cn.bos.service;

import cn.bos.domain.qupai.Noticebill;

public interface NoticebillService {

	void save(String province, String city, String district, Noticebill model);

}
