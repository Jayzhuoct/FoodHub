package com.foodhub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foodhub.common.R;
import com.foodhub.entity.Employee;
import com.foodhub.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
/**
 * 员工控制器
 */
public class EmployeeController {
    @Autowired
    private EmployeeService employService;

    /**
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employService.getOne(queryWrapper);
        //3、如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("登录失败");
        }
        //4、密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }
        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }
    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //1、清除Session中的员工id
        request.getSession().removeAttribute("employee");
        //2、返回退出成功结果
        return R.success("退出成功");
    }
    /**
     * 添加员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> add(@RequestBody Employee employee,HttpServletRequest request){
        //将初始密码password进行md5加密处理
        String password = "123456";
        employee.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //获取当前登录的员工id
        Long employeeId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(employeeId);
        employee.setUpdateUser(employeeId);
        //2、调用service层的方法，将员工信息保存到数据库

        employService.save(employee);
        log.info("添加员工成功,员工信息为:{}",employee);
        //3、返回添加成功结果
        return R.success("添加成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("查询员工列表,当前页:{},每页显示条数:{},员工姓名:{}",page,pageSize,name);
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
        //查询条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName, name);
        //排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //调用service层的方法，查询员工列表
        employService.page(pageInfo, queryWrapper);
        log.info("查询员工列表成功,员工列表为:{}",pageInfo);
        //返回查询结果
        return R.success(pageInfo);
    }
    /**
     * 更新员工信息
     * @param
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee,HttpServletRequest request){
        //获取当前登录的员工id
        Long employeeId = (Long) request.getSession().getAttribute("employee");
        //employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(employeeId);
        //调用service层的方法，更新员工信息
        employService.updateById(employee);
        log.info("更新员工成功,员工信息为:{}",employee);
        //返回更新成功结果
        return R.success("更新成功");
    }
    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        //调用service层的方法，根据id查询员工信息
        Employee employee = employService.getById(id);
        log.info("查询员工成功,员工信息为:{}",employee);
        //返回查询结果
        if (employee != null)
        {
            return R.success(employee);
        }
        return R.error("没有查询到相关员工信息！");
    }

}
