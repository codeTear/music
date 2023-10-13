package com.hyh.controller;
import com.hyh.domain.PageResult;
import com.hyh.domain.User;
import com.hyh.service.MailService;
import com.hyh.service.SongSingerService;
import com.hyh.service.UserService;
import com.hyh.service.impl.SongSingerServiceImpl;
import com.hyh.util.CheckCodeUtil;
import com.hyh.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private SongSingerService songSingerService;
    /*
     * @description: 用户的注册
     * @param: User
     * @return:
     * @author
     * @date: 2023/9/28 10:03
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user ,HttpSession session) {
        String code = (String) session.getAttribute("code");
        String email = (String) session.getAttribute("email");
        User user_email = userService.selectEmail(email);
        if(!code.equals(user.getCheCode())||email==null){
            return new Result(Code.Err,null,"验证码错误");
        }else if(user_email!=null){
            return new Result(Code.Err,null,"邮箱已注册");
        }
        boolean is_exist = userService.selectByName(user.getUsername());
        if(is_exist){
            return new Result(Code.Err,null,"用户名已经存在 请重试");
        }else{
            boolean flag = userService.save(user);
            return new Result(flag?Code.Ok:Code.SAVE_ERR,flag,"注册成功");        }
    }
    /*
     * @description: 用户的登录
     * @param: User user
     * @return:result
     * @author
     * @date: 2023/9/28 10:03
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user,HttpSession httpSession,HttpServletResponse response){
        String checkCode = (String) httpSession.getAttribute("checkCode");
        System.out.println(checkCode);
        if(!(user.getCheCode().equalsIgnoreCase(checkCode))||user.getCheCode()==null){
            return new Result(Code.Err,null,"验证码错误");
        }
        boolean flag = userService.selectUsernamePwd(user);
        boolean is_exist = userService.selectByName(user.getUsername());
        if (flag) {
            JwtUtil jwtUtil = new JwtUtil();
            User user1 = userService.selectByNameToken(user.getUsername());
            String token = jwtUtil.getToken(user1);
            user.setToken(token);
            Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
            cookie.setMaxAge(7 * 24 * 60 * 60); // 一周过期时间
            cookie.setPath("/");
            cookie.setDomain("staryh.me"); // 设置为顶级域名
            response.addCookie(cookie);

            httpSession.setAttribute("username", user.getUsername());
            System.out.println(httpSession.getId());
            return new Result(Code.Ok,user,"登录成功");
        }else if(!is_exist){
            return new Result(Code.Err,user,"登录失败 用户不存在");
        }else{
            return new Result(Code.Err,user,"登录失败");
        }
    }
    /*
     * @description: 回密码
     * @param: User use session
     * @return: result 对象
     * @author
     * @date: 2023/9/28 10:04
     */
    @GetMapping("/back")
    public Result getBack(@RequestBody User user,HttpSession session){
        String code = (String) session.getAttribute("code");
        String email = (String) session.getAttribute("email");
        User user_email = userService.selectEmail(email);
        boolean byName = userService.selectByName(user.getUsername());
        if(!code.equals(user.getCheCode())||email==null){
            return new Result(Code.SAVE_OK,null,"验证码错误");
        }else if(user_email==null){
            return new Result(Code.SAVE_OK,null,"邮箱不存在");
        }else if(!byName){
            return new Result(Code.SAVE_OK,null,"用户不存在");
        }
        user.setId(user_email.getId());
        boolean flag = userService.updatePwd(user);
        System.out.println(user);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,"修改成功");
    }

    /*
     * @description: 验证码校验
     * @param: response httpsession
     * @return:
     * @author
     * @date: 2023/9/28 10:04
     */
    @GetMapping("/generate")
    public void generateCaptcha(HttpServletResponse response, HttpSession httpSession) throws IOException {
        OutputStream fos = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, fos, 4);
        httpSession.setAttribute("checkCode",checkCode);
        //System.out.println(httpSession.getAttribute("checkCode"));
    }

    /*
     * @description: 用户头像的上传
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 17:45
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file,@RequestParam("username") String username){
        User user = new User();
        System.out.println(username);
        // 处理文件上传逻辑
        if (!file.isEmpty()) {
            try {
                // 设置保存路径
                String uploadPath = "/usr/share/nginx/html/uploads/";
                String url = "uploads/"+file.getOriginalFilename();
                // 获取原始文件名
                String originalFilename = file.getOriginalFilename();
                // 构造保存文件的完整路径
                String filePath = uploadPath + originalFilename;
                System.out.println(filePath);
                // 保存文件到指定路径
                file.transferTo(new File(filePath));
                user.setUsername(username);
                user.setUserImg(url);
                User user1 = userService.updateInfoByName(user);
                return new Result(Code.Ok,user1,"头像上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                return new Result(Code.Err,user,"头像上传失败");
            }
        } else {
            return new Result(Code.Err,user,"图片不能为空");
        }
    }
    /*
     * @description: 获取用户信息
     * @param:
     * @return:
     * @author
     * @date: 2023/9/29 23:23
     */
    @GetMapping("/info")
    public Result getInfo(@RequestParam("username") String username){
        User user = userService.selectByNameToken(username);
        return new Result(Code.Ok,user,"获取成功");
    }
    /*
     * @description: 修改用户信息
     * @param:
     * @return: result 对象
     * @date: 2023/9/29 23:23
     */
    @PostMapping("/update")
    public Result updateInfo(@RequestBody User user){
        boolean flag = userService.updateInfo(user);
        System.out.println(user);
        return new Result(flag?Code.Ok:Code.Err,flag,"修改成功");
    }

    /*
     * @description: 分页查询用户信息
     * @param:
     * @return: result 对象
     * @date: 2023/9/29 23:23
     */
    @GetMapping("/page")
    public Result selectAllUser(@RequestParam("page") int page, @RequestParam("size") int size){
        PageResult<User> users = songSingerService.selectAllUser(page,size);
        Integer code = users != null ? Code.Ok : Code.Err;
        String msg = users != null ? "" : "数据查询失败 请重试";
        return new Result(code, users, msg);
    }

    /*
     * @description: 删除用户信息
     * @param:
     * @return: result 对象
     * @date: 2023/9/29 23:23
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteUserById(@PathVariable Integer id){
        boolean flag = songSingerService.deleteUserById(id);
        return new Result(flag?Code.Ok:Code.Err,flag,"删除成功");
    }
}