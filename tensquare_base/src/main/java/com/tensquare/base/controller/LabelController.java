package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.base.controller
 * @author:MartinKing
 * @createTime:2021/3/8 11:30
 * @version:1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * description  查询所有标签
     * param []
     * return entity.Result
     * author MartinKing
     * createTime 2021/3/8 14:52
     **/
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @PostMapping()
    public Result addLabel(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{labelId}")
    public Result getLabelById(@PathVariable("labelId") String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findLabelById(labelId));
    }

    @DeleteMapping("/{labelId}")
    public Result deleteLabelById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/{labelId}")
    public Result updateLabelById(@RequestBody Label label,@PathVariable("labelId")String labelId) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

}
