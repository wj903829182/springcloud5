package com.jack.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;


/**
 * create by jack 2018/12/7
 *
 * @author jack
 * @date: 2018/12/7 21:37
 * @Description: 权限认证测试
 */
public class AuthenticationTest {
    /**
     * 1，认证
     */
    public static void testAuthentication1() {
        //创建一个SimpleAccountRealm
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        //添加一个账号
        simpleAccountRealm.addAccount("jack", "123456");
        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2,主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        //进行登入
        subject.login(token);
        System.out.println("是否权限认证：isAuthenticated=" + subject.isAuthenticated());
        //登出logout
        subject.logout();
        System.out.println("是否权限认证：isAuthenticated=" + subject.isAuthenticated());
    }

    /**
     * 2，授权
     */
    public static void testAuthentication2() {
        //创建一个SimpleAccountRealm
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        //添加一个账号,并赋予一个权限
        //simpleAccountRealm.addAccount("jack", "123456","admin");
        simpleAccountRealm.addAccount("jack", "123456","admin","user");
        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2,主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        //进行登入
        subject.login(token);
        System.out.println("是否权限认证：isAuthenticated=" + subject.isAuthenticated());
        //登入成功以后，检查是否有某个权限
        //subject.checkRole("admin");
        //检查多个权限
        subject.checkRoles("admin","user");
        //System.out.println("是否权限认证：isAuthenticated=" + subject.checkRole("admin"));
    }

    public static void main(String[] args) {
        //1,权限认证小demo
        //testAuthentication1();
        //2,授权
        testAuthentication2();
    }

}
