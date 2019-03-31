package com.tian.controller;


import com.tian.model.User;
import com.tian.service.UserService;
import com.tian.service.impl.StudentServiceImpl;
import com.tian.util.DateTime;
import com.tian.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private  static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    /**跳转注册页面
     * @return
     */
    @RequestMapping(value = "/registpage",method = RequestMethod.GET)
    public String loginPage(){
        log.info("*********进入注册页面******");
        return "regist";
    }

    /**
     * 注册
     * @param user
     * @param password1
     * @param password2
     * @param model
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(User user, String password1, String password2, ModelMap model ) throws Exception {

        log.info("********开始注册********"+" username is "+ user.getUserName()+"password1 is "+ password1 +" password2 is "+password2);
        if (password1.equals(password2)){
            log.info("两次密码是否一致 "+ password1.equals(password2));
            user.setUserPassword(password1);
            user.setCreatedAt(DateTime.getTime());
            log.info(""+user);
            int  regist = userService.regist(user);
            if (regist != 0) {
                //注册成功后跳转登陆页面
                return "redirect:/u/loginpage";
            }
            model.addAttribute("msg","昵称被占用");
            return "regist";
        }
        model.addAttribute("msg","两次密码不一致");
        return "regist";
    }






    //跳转登陆页面
    @RequestMapping(value = "/loginpage",method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request,
                            String userName,String userPassword ){
        log.info("*********进入登陆页面******");
        return "login";
    }

    /**
     * 使用cookie登陆
     * @param request
     * @param response
     * @param userName
     * @param userPassword
     * @param model
     * @return
     */
    // RedirectAttributes 重定向之后还能带参数跳转的
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        String userName,String userPassword ,RedirectAttributes model) throws Exception {

        log.info("**********开始登陆**********8");
        boolean login = userService.login(userName,userPassword);
        //如果用户名和密码对应则保存cookie信息到客户端并跳转学员页面

        log.info(" 控制类 "+login);
        if (login == true){
            Cookie cookie = new Cookie("user",userName+System.currentTimeMillis());

            //cookie过期时间
            cookie.setMaxAge(30 * 60);

            //使用cookie的路径
            cookie.setPath("/");

            //添加cookie到服务器端
            //一旦执行 服务器端发回消息头set-cookie给浏览器，set-cookie是会携带cookie键值对的，userName+System.currentTimeMillis()。创建的cookie就会保存在浏览器。
            //先放到response中了，然后去客户端.放到客户端的机器上.
            //然后它要是再访问你的服务器时就会带着这些cookie并在request中放置着来找你.然后你就可以在request中找到这些cookie拿到以后想干嘛这就是你自己的事儿了
            response.addCookie(cookie);
            //客户端保存cookie信息后跳转学员页
            return "studentBody";
        }else {
            model.addFlashAttribute("error","用户名不存在/密码错误");
            //登陆失败后重定向至登陆页面
            return "login";
        }

    }


    /**
     * 使用cookie保存token信息进行登陆
     * @param request
     * @param response
     * @param userName
     * @param userPassword
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/logintoken",method = RequestMethod.POST)
    public String loginByToken(HttpServletRequest request, HttpServletResponse response,
                               String userName,String userPassword ,RedirectAttributes redirectAttributes,HttpSession session) throws Exception {

        log.info("**********开始登陆**********8");

        boolean login = userService.login(userName,userPassword);

        //如果用户名和密码对应则保存cookie信息到客户端并跳转学员页面

        log.info(" 控制类 "+login);
        if (login == true){
            String token = JwtUtil.createJWT(System.currentTimeMillis(),userName,userPassword);
            log.info("token is "+ token);

            Cookie cookie = new Cookie("token",token);

            //cookie过期时间
            cookie.setMaxAge(30 * 60);

            //使用cookie的路径
            cookie.setPath("/");
            session.setAttribute("userName",userName);
            response.addCookie(cookie);
//            redirectAttributes.addFlashAttribute("userName",userName);
            return "redirect:/u/stu";
        }else {
            redirectAttributes.addAttribute("msg","用户名不存在/密码错误");
            //登陆失败后重定向至登陆页面
            return "login";
        }

    }


    /**
     * 登出（退出登陆）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response){

        HttpSession session = request.getSession();
        log.info("session is"+session);
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            log.info("sfsdfsdfsdfsd"+cookie.getName());
            // 通过cookie的键值比较是否相等
            if (cookie.getName().equals("token")){
                cookie.setValue(null);
                cookie.setMaxAge(0);//立即销毁cookie
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "redirect:/user/loginpage";

    }




}
