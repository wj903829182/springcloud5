package com.jack.test;

import com.jack.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * create by jack 2018/12/8
 *
 * @author jack
 * @date: 2018/12/8 09:49
 * @Description:
 */
public class CutomRealmTest {


    /**
     * 自定义用户认证
     */
    public static void cutomRealmTest1(){

        //使用自定义Realm
        CustomRealm customRealm = new CustomRealm();

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(customRealm);

        //进行加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //采用mdf加密
        matcher.setHashAlgorithmName("md5");
        //设置加密次数
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);


        //2,主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        //进行登入
        subject.login(token);
        System.out.println("是否权限认证：isAuthenticated=" + subject.isAuthenticated());
        //检查是否有角色
        //subject.checkRoles("admin");
        //System.out.println("有admin角色");
        //检查是否有权限
        //subject.checkPermissions("user:delete");
        //System.out.println("有user:delete权限");

    }

    /**
     * 自定义用户授权，权限
     */
    public static void cutomRealmTest2(){

        //使用自定义Realm
        CustomRealm customRealm = new CustomRealm();

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(customRealm);

        //2,主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        //进行登入
        subject.login(token);
        System.out.println("是否权限认证：isAuthenticated=" + subject.isAuthenticated());
        //检查是否有角色
        subject.checkRoles("admin");
        //System.out.println("有admin角色");
        //检查是否有权限
        subject.checkPermissions("user:delete","user:add");
        //System.out.println("有user:delete权限");

    }

    public static void main(String[] args) {
        cutomRealmTest1();
        //cutomRealmTest2();
    }
}
