package com.newframe.controllers.api;

import com.newframe.controllers.BaseController;
import com.newframe.controllers.JsonResult;
import com.newframe.dto.OperationResult;
import com.newframe.entity.test.TestUser;
import com.newframe.services.test.TestManageService;
import com.newframe.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:wangdong
 * @description:测试Jpa和druid是否配置成功的类
 */
@RestController
@RequestMapping("/api/testJpa/")
public class TestJpaController extends BaseController {

    @Autowired
    private TestManageService testManageService;

    /**
     * 接收实体类，对应于数据库的表
     * 通过主库进行数据库操作
     *
     * @param testUser
     * @return
     */
    @RequestMapping("saveTestUserByMaster")
    private JsonResult saveTestUserByMaster(TestUser testUser) {

        OperationResult<TestUser> result = testManageService.saveTestUserByMaster(testUser);
        if (result.getSucc()) {
            return success(result.getEntity());
        }
        return error(result.getCode(), result.getMessage());
    }

    /**
     * 通过从库进行数据库的操作
     *
     * @param testUser
     * @return
     */
    @RequestMapping("saveTestUserBySlave")
    private JsonResult saveTestUserBySlave(TestUser testUser) {

        OperationResult<TestUser> result = testManageService.saveTestUserBySlave(testUser);
        if (result.getSucc()) {
            return success(result.getEntity());
        }
        return error(result.getCode(), result.getMessage());
    }


    /**
     * 单条件查询，根据条件，利用Query进行查询
     *
     * @param age
     * @return
     */
    @RequestMapping("listTestUserByAge")
    private JsonResult listTestUserByAge(Integer age) {

        OperationResult<List<TestUser>> result = testManageService.listTestUserByAge(age);

        if (result.getSucc()) {
            return success(result.getEntity());
        }
        return error(result.getCode(), result.getMessage());
    }

    /**
     * 根据多条件查询
     *
     * @param age
     * @param name
     * @return
     */
    @RequestMapping("listTestUserByAgeAndName")
    private JsonResult listTestUserByAgeAndName(Integer age, String name) {

        OperationResult<List<TestUser>> result = testManageService.listTestUserByAgeAndName(age, name);

        if (result.getSucc()) {
            return success(result.getEntity());
        }
        return error(result.getCode(), result.getMessage());
    }

    /**
     * 分页查询
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("listTestUser")
    private JsonResult listTestUser(String name, Integer currentPage, Integer pageSize) {

        OperationResult<Page<TestUser>> result = testManageService.listTestUser(name, currentPage, pageSize);

        if (result.getSucc()){
            return success(result.getEntity());
        }

        return error(result.getCode(),result.getMessage());
    }


    @RequestMapping("setPasswordByOldPwd")
    public JsonResult setPasswordByOldPwd(String mobile, Long uid, Integer role) {

        if (null == mobile || null == uid || null == role){

        }
        return null;
    }
}
