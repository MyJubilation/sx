package com.sx.userserver.adminTable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sx.userserver.adminTable.dto.LoginUser;
import com.sx.userserver.adminTable.entity.AdminTable;
import com.sx.userserver.adminTable.mapper.AdminTableMapper;
import com.sx.userserver.adminTable.service.AdminTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
@Service
@MultipartConfig
public class AdminTableServiceImpl extends ServiceImpl<AdminTableMapper, AdminTable> implements AdminTableService {

    @Autowired
    private AdminTableMapper adminTableMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public AdminTable login(LoginUser loginUser) {
        //模拟登录

        QueryWrapper<AdminTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adm_name",loginUser.getAdmname()).eq("adm_password",loginUser.getAdmpassword());
/*
        queryWrapper.and(qw -> qw.eq("username",loginUser.getUsername()).or().eq("password",loginUser.getPassword()));
*/
        return adminTableMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean checkVcode(String vcode) {
        //根据参数的验证码到redis中获取数据，如果能获取到，则表示验证成功。否则验证失败
        String text = stringRedisTemplate.opsForValue().get(vcode);
        if (ObjectUtils.isEmpty(text)) {
            return false;
        }
        return true;
    }

    /*1.生成验证码文本
     * 2.根据验证码文本生成图片
     * 3.验证码文本存入redis
     * 3.验证码图片转化为Base64编码
     * 5.将Base64编码返回*/
    @Override
    public String getVcode() {
        //1.
        String text = defaultKaptcha.createText();
        //2.
        BufferedImage image = defaultKaptcha.createImage(text);
        //3.
        stringRedisTemplate.opsForValue().set(text,text,15, TimeUnit.MINUTES);
        //4.
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ImageIO.write(image,"jpg",bo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String code = "data:image/jpeg;base64,"+ Base64Utils.encodeToString(bo.toByteArray());
        //5.
        return code;

    }
}
