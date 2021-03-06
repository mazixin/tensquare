package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.spit.service
 * @author:MartinKing
 * @createTime:2021/3/10 14:47
 * @version:1.0
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setShare(0);
        spit.setState("1"); //状态
        //判断该吐槽是否有父节点
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            //如果有，则在父节点的吐槽出新增一条回复数
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findPageByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return this.spitDao.findByParentid(parentId, pageable);
    }

    public void thumbup(String spitId) {
       /* 方式一：效率问题
       Spit spit = this.spitDao.findById(spitId).get();
        spit.setThumbup((spit.getThumbup()==null? 0 : spit.getThumbup())+1);
        spitDao.save(spit);*/
       //方式二： 使用原生的mongo命令 db.spit.update({"_id":"1"},{$inc:{thumbup:NumberInt(1)}})

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        this.mongoTemplate.updateFirst(query,update,"spit");
    }
}
