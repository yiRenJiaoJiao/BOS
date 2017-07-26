package cn.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.bos.domain.base.DecidedZone;

public interface DecidedZoneDao extends JpaRepository<DecidedZone, String>,JpaSpecificationExecutor<DecidedZone>{

}
