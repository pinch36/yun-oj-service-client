package com.yun.oj.service.client.service;


import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import yun.oj.common.common.ErrorCode;
import yun.oj.common.exception.BusinessException;
import yun.oj.model.model.entity.User;
import yun.oj.model.model.enums.UserRoleEnum;
import yun.oj.model.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import static yun.oj.common.constant.UserConstant.USER_LOGIN_STATE;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: __yun
 * @Date: 2024/09/01/6:55
 * @Description:
 */
@FeignClient(name = "yun-oj-user", path = "/api/user/inner")
public interface UserFeignClient {
//    @PostMapping("/get")
//    User getLoginUser(HttpServletRequest request);

//    @PostMapping("/admin/request")
//    boolean isAdmin(HttpServletRequest request);
//
//    @PostMapping("/admin/user")
//    boolean isAdmin(@RequestBody User user);

    @GetMapping("/get/id")
    User getById(@RequestParam("userId") Long userId);
//    @PostMapping("/get/user_vo")
//    UserVO getUserVO(@RequestBody User user);
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 可以考虑在这里做全局权限校验
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    default UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
