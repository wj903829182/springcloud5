package com.jack.test;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import javax.sql.DataSource;

/**
 * create by jack 2018/12/8
 *
 * @author jack
 * @date: 2018/12/8 08:52
 * @Description:
 */
public class JdbcRealmTest {
    public static void jdbcRealmTest1() {
        //配置数据源：
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("数据库连接名");
        dataSource.setUsername("数据库用户名");
        dataSource.setUsername("数据库密码");

        //配置文件中的用户权限信息，文件在类路径下
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //使用JdbcRealm下面的值需要为true不然无法查询用户权限
        jdbcRealm.setPermissionsLookupEnabled(true);

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(jdbcRealm);

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

    public static void jdbcRealmTest2() {
        //配置数据源：
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("数据库连接名");
        dataSource.setUsername("数据库用户名");
        dataSource.setUsername("数据库密码");

        //配置文件中的用户权限信息，文件在类路径下
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //使用JdbcRealm下面的值需要为true不然无法查询用户权限
        jdbcRealm.setPermissionsLookupEnabled(true);
        //使用自定义sql查询用户信息
        String sql="select password from test_user where username = ?";
        jdbcRealm.setAuthenticationQuery(sql);
        String roleSql = "select role_name from test_user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        //1,构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置Realm
        defaultSecurityManager.setRealm(jdbcRealm);

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

    public static void main(String[] args) {

    }
}
