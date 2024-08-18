package com.sx.userserver.adminTable.controller;


import com.sx.userserver.adminTable.dto.LoginUser;
import com.sx.userserver.adminTable.entity.AdminTable;
import com.sx.userserver.adminTable.service.AdminTableService;
import com.sx.userserver.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/adminTable")
public class AdminTableController {

        @Autowired
        private AdminTableService adminTableService;

        @RequestMapping("/login")
        public Result<?> login(@RequestBody LoginUser loginUser){

            //验证图片验证码 若验证码错误则直接返回错误结果给前端，如果验证码正确则不处理
           boolean result = adminTableService.checkVcode(loginUser.getVcode());
            if (!result){
                return new Result<>().error("验证码错误！");
            }

            //将接收到的前端的参数转发给service处理
            AdminTable adminTable = adminTableService.login(loginUser);
            //获取service处理结果并封装，将结果返回给前端
            if(ObjectUtils.isEmpty(adminTable)){
                return new Result<>().error("用户名或密码错误！");
            }else {
           /* //如果登录成功则生成JWT Token
            String token = jwtComponent.sign(loginUser.getAdmname(), loginUser.getAdmpassword());

            //返回值的封装
            JSONObject obj = new JSONObject();
            obj.put("token", token);
            obj.put("userId", user.getUserId());*/

                return new Result<>().success("正在登陆....").put(adminTable.getAdmName());
            }
        }

        @GetMapping("/vcode")
        public Result<?> getVcode(){
            String code = adminTableService.getVcode();
            return new Result<>().success().put(code);
        }


}
