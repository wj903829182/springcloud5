package com.jack.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * create by jack 2018/12/7
 *
 * @author jack
 * @date: 2018/12/7 22:20
 * @Description:
 */
public class IniRealmTest {

    public static void iniRealmTest1(){
        //配置文件中的用户权限信息，文件在类路径下
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(iniRealm);

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
        System.out.println("有admin角色");
        //检查是否有权限
        subject.checkPermissions("user:delete");
        System.out.println("有user:delete权限");

    }
    public static void main(String[] args) {
        //1,使用IniRealm
        iniRealmTest1();
    }
}
