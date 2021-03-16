package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JwtUtil jwtUtil;


	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		User loginUser = this.userService.login(user);
		if (loginUser == null) {
			return new Result(false, StatusCode.LOGINERROR, "登陆失败");
		}
		String token = jwtUtil.createJWT(loginUser.getId(), loginUser.getNickname(), "user");
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("roles", "user");

		//添加登录状态逻辑
		return new Result(true, StatusCode.OK, "登陆成功",map);
	}

	/**
	 * description 发送短信验证码
	 * param [mobile]
	 * return entity.Result
	 * author MartinKing
	 * createTime 2021/3/15 14:05
	 **/
	@PostMapping("/sendsms/{mobile}")
	public Result sendsms(@PathVariable String mobile) {
		this.userService.sendSms(mobile);
		return new Result(true, StatusCode.OK, "发送成功");
	}

	@PostMapping("/register/{code}")
	public Result register(@PathVariable String code,@RequestBody User user) {
		//获取缓存中的验证码
		String checkCode = (String)this.redisTemplate.opsForValue().get("checkCode_"+user.getMobile());
		if (StringUtils.isBlank(code)) {
			return new Result(false, StatusCode.ERROR, "请获取的验证码");
		}
		if (!checkCode.equals(code)) {
			return new Result(false, StatusCode.ERROR, "请输入获取的验证码");
		}
		this.userService.add(user);
		return new Result(true, StatusCode.OK, "注册成功");
	}
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除 必须有admin角色才能删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		/* 直接从请求头中获取token并进行验证
		String header = request.getHeader("Authorization");//获取头信息
		if (header == null || StringUtils.isBlank(header)) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		if (!header.startsWith("Bearer ")) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		String token = header.substring(7);
		Claims claims = null;
		try {
			claims = this.jwtUtil.parseJWT(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		if (claims == null) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		if (!"admin".equals(claims.get("roles"))) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}*/
		String token = (String) request.getAttribute("admin_claims");
		if (token == null || StringUtils.isBlank(token)) {
//			throw new RuntimeException("权限不足");
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
