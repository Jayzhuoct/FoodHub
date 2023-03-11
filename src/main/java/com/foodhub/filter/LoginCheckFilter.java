package com.foodhub.filter;

import com.alibaba.fastjson.JSON;
import com.foodhub.common.BaseContext;
import com.foodhub.common.R;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 * 创建自定义过滤器，实现Filter接口
 * 1、创建一个类，实现Filter接口
 * 2、在类上添加@WebFilter注解，指定过滤器名称和过滤路径
 * 3、实现Filter接口中的方法
 * 4、在启动类上添加@ServletComponentScan注解，扫描自定义过滤器
 * 5、在需要使用过滤器的地方，使用@Autowired注解注入
 * 6、在需要使用过滤器的地方，使用@Qualifier注解指定过滤器名称
 * 7、在需要使用过滤器的地方，使用@Order注解指定过滤器执行顺序
 * 8、在需要使用过滤器的地方，使用@ServletComponentScan注解，扫描自定义过滤器
 * 9、在需要使用过滤器的地方，使用@WebFilter注解，指定过滤器名称和过滤路径
 * 10、在需要使用过滤器的地方，使用@WebInitParam注解，指定过滤器初始化参数
 * 11、在需要使用过滤器的地方，使用@WebListener注解，指定监听器名称和监听器类型
 * 12、在需要使用过滤器的地方，使用@WebServlet注解，指定servlet名称和servlet路径
 * 13、完善过滤器的处理逻辑
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();// /backend/index.html

        log.info("拦截到请求：{}",requestURI);

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //4-1、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        //4-2、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }

        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
