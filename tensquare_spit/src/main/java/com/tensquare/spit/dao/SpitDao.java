package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import sun.security.provider.ConfigFile;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.spit.dao
 * @author:MartinKing
 * @createTime:2021/3/10 14:46
 * @version:1.0
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    public Page<Spit> findByParentid(String parentId, Pageable pageable);
}
