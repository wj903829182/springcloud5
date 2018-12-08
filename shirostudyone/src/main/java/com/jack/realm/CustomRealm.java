package com.jack.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * create by jack 2018/12/8
 *
 * @author jack
 * @date: 2018/12/8 09:31
 * @Description:
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {
    /**
     * 模拟数据库中的用户名和密码
     */
    private Map<String, String> userMap = new HashMap<String, String>(16);

    /**
     * 使用代码块初始化数据
     */
    {
        //userMap.put("jack", "123456");
        //密码是123456的md5值
        //userMap.put("jack", "e10adc3949ba59abbe56e057f20f883e");
        //密码是123456的md5值,盐是jack
        userMap.put("jack", "66cb87e4e66a825d10cf4227e0e82eee");
        super.setName("customRealm");
    }
    /**
     * 用于授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUsername(username);
        //从数据库或者缓存中获取权限数据
        Set<String> permissions = getPerminssionsByUsername(username);
        //创建AuthorizationInfo，并设置角色和权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 模拟通过数据库获取权限数据
     * @param username
     * @return
     */
    private Set<String> getPerminssionsByUsername(String username) {
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:delete");
        permissions.add("user:add");
        return permissions;
    }

    /**
     * 模拟通过数据库获取用户角色信息
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username) {
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("user");
        return roles;
    }

    /**
     * 用于认证
     * @param authenticationToken 传过来的认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从主体传过来的认证信息获取登入的用户名
        String username = (String) authenticationToken.getPrincipal();
        //通过用户名到数据库获取凭证
        String password = getPasswordByUsername(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,"customRealm");
        //设置盐的值
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("jack"));
        return authenticationInfo;
    }

    /**
     * 通过用户名查询密码，模拟数据库查询
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        return userMap.get(username);
    }

    public static void main(String[] args) {
        //md5值
        //Md5Hash md5Hash = new Md5Hash("123456");

        //md5加盐
        Md5Hash md5Hash = new Md5Hash("123456","jack");
        System.out.println("123456的md5值="+md5Hash.toString());
    }
}
