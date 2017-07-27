package cn.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.bos.domain.qupai.Workbill;

public interface WorkbillDao extends JpaRepository<Workbill, String>,JpaSpecificationExecutor<Workbill>{

}
