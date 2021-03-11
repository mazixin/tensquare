package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.ConfigFile;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.spit.controller
 * @author:MartinKing
 * @createTime:2021/3/10 14:52
 * @version:1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", this.spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable("spitId") String id) {
        return new Result(true, StatusCode.OK, "查询成功", this.spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        this.spitService.save(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable("spitId") String id) {
        spit.set_id(id);
        this.spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        this.spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findPageByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size) {
        Page<Spit> spitPage = this.spitService.findPageByParentId(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        String userid = "1";
        if(this.redisTemplate.opsForValue().get("thumbup_"+userid+"_"+spitId)!=null){
            return new Result(false, StatusCode.REPERROR, "不能重复点赞哦!");
        }
        this.redisTemplate.opsForValue().set("thumbup_"+userid+"_"+spitId,1);
        this.spitService.thumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");

    }
}
