package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
    /**
     * description 根据热门状态获取企业列表
     * param [ishot]
     * return java.util.List<com.tensquare.recruit.pojo.Enterprise>
     * author MartinKing
     * createTime 2021/3/9 15:01
     **/
	public List<Enterprise> findByIshot(String ishot);
}
