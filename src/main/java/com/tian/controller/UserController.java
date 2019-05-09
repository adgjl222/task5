package com.tian.controller;


import com.tian.model.User;
import com.tian.service.UploadService;
import com.tian.service.UserService;
import com.tian.service.impl.StudentServiceImpl;
import com.tian.util.DateTime;
import com.tian.util.JwtUtil;
import com.tian.util.SMS;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private UploadService uploadService;

    @Resource
    private SMS sms;


    /**
     * 跳转注册页面
     *
     * @return
     */
    @RequestMapping(value = "/registpage", method = RequestMethod.GET)
    public String loginPage() {
        log.info("*********进入注册页面******");
        return "regist";
    }

    /**
     * 使用手机号码进行注册
     * @param user
     * @param password1
     * @param password2
     * @param model
     * @return
     */
    @RequestMapping(value = "/regist/phone", method = RequestMethod.POST)
    public String regist(User user, String password1, String password2, String msgCode, ModelMap model) throws Exception {

        String phoneNumber = user.getPhoneNumber();
        int regist = 0;
        log.info("********开始注册 进入注册页面********" + " username is " + user.getUserName() + "password1 is " + password1 + " password2 is " + password2);
        if (password1.equals(password2)) {

            log.info("两次密码是否一致 " + password1.equals(password2));

            user.setUserPassword(password1);
            user.setCreatedAt(DateTime.getTime());
            log.info("" + user);

            if (msgCode == null) {
                model.addAttribute("msg", "请输入验证码");
                return "regist";
                //比对验证码是否一致 0为一致
            } else if (userService.checkPhoneCode(phoneNumber, msgCode) == 0) {

                user.setPhoneNumber(phoneNumber);
                regist = userService.regist(user);
                if (regist != 0) {
                    //注册成功后跳转登陆页面
                    return "redirect:/user/loginpage";
                } else {
                    model.addAttribute("msg", "昵称被占用");
                    return "regist";
                }

            } else {
                model.addAttribute("msg", "验证码错误");
                return "regist";

            }

        }
        model.addAttribute("msg", "两次密码不一致");
        return "regist";
    }


    /**
     * 使用邮箱注册
     * @param user
     * @param password1
     * @param password2
     * @param msgCode
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/regist/email", method = RequestMethod.POST)
    public String registByEmail(User user, String password1, String password2, String msgCode, ModelMap model) throws Exception {

        String email = user.getEmail();
        int regist = 0;
        log.info("********开始注册 进入注册页面********" + " username is " + user.getUserName() + "password1 is " + password1 + " password2 is " + password2);
        if (password1.equals(password2)) {

            log.info("两次密码是否一致 " + password1.equals(password2));

            user.setUserPassword(password1);
            user.setCreatedAt(DateTime.getTime());
            log.info("" + user);

            if (msgCode == null) {
                model.addAttribute("msg", "请输入验证码");
                return "regist";
                //比对验证码是否一致 0为一致
            } else if (userService.checkEmailCode(email, msgCode) == 0) {

                user.setEmail(email);
                regist = userService.regist(user);
                if (regist != 0) {
                    //注册成功后跳转登陆页面
                    return "redirect:/user/loginpage";
                } else {
                    model.addAttribute("msg", "昵称被占用");
                    return "regist";
                }

            } else {
                model.addAttribute("msg", "邮箱验证码错误");
                return "regist";

            }

        }
        model.addAttribute("msg", "两次密码不一致");
        return "regist";
    }

    /**
     * 跳转个人主页
     * @param
     * @param request
     * @return  在个人主页展示
     */
    @RequestMapping(value = "/{userName}")
    public ModelAndView userPage(@PathVariable ("userName")String userName, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("userPage");
        log.info(""+userName);
        User user = userService.getUserByName(userName);
        log.info(""+user);
        mav.addObject("user",user);
        mav.addObject("img",user.getSalt());
        return mav;
    }

    /**
     * 获取数据库中的用户头像
     * @param userName
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/img")
    public String userImg(String userName,HttpServletRequest request,Model model){

        User user = userService.getUserByName(userName);
        // 用salt字段存头像url了
        String imgURL = user.getSalt();
        model.addAttribute("img",imgURL);
        return imgURL;
    }


    @RequestMapping("/upload/{userName}")
    @ResponseBody
    public String upload(MultipartFile multipartFile, @PathVariable String userName, HttpServletRequest request, HttpSession session)throws Exception {
        log.info("上传修改用户的头像信息");
        // COSUtils cosUtils = new COSUtils();
        String imgURL = uploadService.AliyunUploadFile(multipartFile);
        log.info("图片的对象存储链接地址：{}", imgURL);
        User user = userService.getUserByName(userName);
        user.setSalt(imgURL);
        log.info("user:{}", user.toString());
        if (imgURL != null && imgURL.length() > 0) {
            userService.updateUser(user);

            // 更新session值
            session.setAttribute("img",user.getSalt());
        }
        return imgURL;
    }



    //跳转登陆页面
        @RequestMapping(value = "/loginpage", method = RequestMethod.GET) public String loginPage (HttpServletRequest
        request, String userName, String userPassword ){
            log.info("*********进入登陆页面******");
            return "login";
        }






<<<<<<< Updated upstream
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
=======
>>>>>>> Stashed changes

        /**
         * 使用cookie保存token信息进行登陆
         * @param request
         * @param response
         * @param userName
         * @param userPassword
         * @param
         * @return
         */
        @RequestMapping(value = "/logintoken", method = RequestMethod.POST) public String loginByToken
        (HttpServletRequest request, HttpServletResponse response, String userName, String
        userPassword, Model model, HttpSession session) throws Exception {

            log.info("**********开始登陆**********8");

            User user = null;

            // 输入的是邮箱
            if (userName.contains("@")) {
                log.info("邮箱登录：{}", userName);
                user = userService.getUserByEmail(userName);
                log.info("----------1------------user:" + user);

            } else

                //输入的是手机号
                if (userName.length() == 11 && StringUtils.isNumeric(userName)) {
                    log.info("手机号登录：{}", userName);
                    user = userService.getUserByPhone(userName);
                    log.info("----------2------------user:" + user);
                } else  if (!userName.isEmpty()) {
                        log.info("用户名登录：{}", userName);
                        boolean login = userService.login(userName, userPassword);
                        //如果用户名和密码对应则保存cookie信息到客户端并跳转学员页面
                        log.info(" 控制类 " + login);
                        if (login == true) {
                            String token = JwtUtil.createJWT(System.currentTimeMillis(), userName, userPassword);
                            log.info("token is " + token);

                            Cookie cookie = new Cookie("token", token);

                            //cookie过期时间
                            cookie.setMaxAge(30 * 60);

                            //使用cookie的路径
                            cookie.setPath("/");
                            log.info("+++++++++++++++"+user.getSalt());
                            session.setAttribute("img",user.getSalt());
                            session.setAttribute("userName", userName);
                            response.addCookie(cookie);
                            return "redirect:/user/stu";
                        } else {
                            model.addAttribute("msg", "用户名不存在/密码错误");
                            //登陆失败后重定向至登陆页面
                            return "login";
                        }
                    }

                    if (user != null ){
                userName = user.getUserName();
                userPassword = user.getUserPassword();
                        String token = JwtUtil.createJWT(System.currentTimeMillis(), userName, userPassword);
                        log.info("token is " + token);

                        Cookie cookie = new Cookie("token", token);

                        //cookie过期时间
                        cookie.setMaxAge(30 * 60);

                        //使用cookie的路径
                        cookie.setPath("/");
                        session.setAttribute("img",user.getSalt());
                        session.setAttribute("userName", userName);
                        response.addCookie(cookie);
                        return "redirect:/user/stu";
                    }

            else  {
                model.addAttribute("msg", "用户不存在");
                log.info("账号不存在");

                return "login";
            }

        }


    /**
     * 发送邮箱验证码
     * @param email
     * @return  发送成功（状态为0），发送失败(状态为1），默认发送失败
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/email",method = RequestMethod.POST,produces = "text/xml;charset=UTF-8")
    public String emailCode(String email) throws IOException {
        log.info("使用邮箱{}注册：",email);
        //生成随机验证码
        //String msgCode = DataUtils.getNumber(6);
        // 为测试方便 验证码固定为111111
        String msgCode = "222222";
        log.info("验证码："+msgCode);
        int status = userService.sendEmai(email,msgCode);
        log.info("email-status:{}",status);
        String message = "邮箱格式不正确";
        if(status == 0){
            message = "验证码发送成功";
        }
        return message;
    }

        /**
         * 发送手机验证码
         * @param phoneNumber
         * @return 0：发送成功 2:发送过于频繁 3：超过每天最大次数 1：其他
         */
        @ResponseBody
        @RequestMapping(value = "/phone", method = RequestMethod.POST, produces = "text/xml;charset=UTF-8")
        public String phoneCode (String phoneNumber){
            log.info("使用手机号:{}注册", phoneNumber);
            // String msgCode = DataUtils.getNumber(6);
            // 为测试方便 验证码固定为111111
            String msgCode = "111111";
            log.info("验证码：" + msgCode);
            int status = userService.sendPhone(phoneNumber, msgCode);
            log.info("phone-status:{}", status);
            String message = null;
            if (status == 0) {
                message = "验证码发送成功";
            }
            if (status == 1) {
                message = "请稍后再试";
            }
            if (status == 2) {
                message = "发送过于频繁";
            }
            if (status == 3) {
                message = "超过每天最大次数";
            }
            log.info("手机验证码的信息:{}", message);
            return message;
        }


        /**
         * 登出（退出登陆）
         * @param request
         * @param response
         * @return
         */
        @RequestMapping(value = "/logout", method = RequestMethod.GET) public String logout (HttpServletRequest
        request, HttpServletResponse response){

            HttpSession session = request.getSession();
            log.info("session is" + session);
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                log.info("sfsdfsdfsdfsd" + cookie.getName());
                // 通过cookie的键值比较是否相等
                if (cookie.getName().equals("token")) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);//立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            return "redirect:/user/loginpage";

        }


    }
