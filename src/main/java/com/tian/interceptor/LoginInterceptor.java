package com.tian.interceptor;

import com.tian.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 使用Cookie的自定义HandlerInterceptor(处理器拦截器)
 */
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {


        JwtUtil jwtUtil = new JwtUtil();

        //获取请求的RUi:去除http:localhost:8080这部分之后的部分
        String url = httpServletRequest.getRequestURI();

        System.out.println("url = " + url);

        //获取request的cookie
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null){
            System.out.println("用户未登陆，验证失败");
        }else
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return true;
                }
            }

        }
        // 没有找到登录状态则重定向到登录页，返回false，不执行原来controller的方法
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");//这句话是解决乱码的
        httpServletResponse.getWriter().write("未登录，请重新登录后操作");
        httpServletRequest.getRequestDispatcher("/user/loginpage").forward(httpServletRequest, httpServletResponse);
        return false;
}


    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {


}
}
