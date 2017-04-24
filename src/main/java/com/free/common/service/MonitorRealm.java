package com.free.common.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.system.model.Authorize;
import com.free.system.model.Role;
import com.free.system.model.User;
import com.free.system.service.UserService;

@Service("monitorRealm")
public class MonitorRealm extends AuthorizingRealm {
	@Autowired
	private UserService service;
	@Autowired
	private Dao dao;
	
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired RoleService roleService;
	 * 
	 * @Autowired LoginLogService loginLogService;
	 */

	public MonitorRealm() {
		super();

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		Principal principal = (Principal) getAvailablePrincipal(principals);
		User user = dao.fetch(User.class,principal.getId());
		Role role = dao.fetch(Role.class,Cnd.where("id","in","( SELECT roleid FROM t_system_user_role WHERE userid="+user.getId()+" )"));
		List<Authorize> authorizes = dao.query(Authorize.class,Cnd.where("id","in","( SELECT auth_id t_system_role_authorize WHERE role_id ="+user.getId()+" )"));
		/* 这里编写授权代码 */
		Set<String> roleNames = new HashSet<String>();
	    Set<String> permissions = new HashSet<String>();
	    roleNames.add(role.getRolename());
	    for (Authorize authorize : authorizes) {
		    permissions.add(authorize.getModule());
		}
	   
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);
		return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
        String loginname = (String)token.getPrincipal();
        Map<String, Object> params = new HashMap<String, Object>();
		Condition c = Cnd.where("loginname","=",loginname);
        List<User> users = service.query(c);
        
        if(users == null || users.size()!=1) {
            throw new UnknownAccountException();//没找到帐号
        }
        User user = users.get(0);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		new Principal(user), //用户名
                user.getPassword(), //密码
                user.getXm()  //realm name
        );
        return authenticationInfo;
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Long id;
		private String loginname;
		private String xm;
		
		public Principal(User user) {
			this.id = user.getId();
			this.loginname = user.getLoginname();
			this.xm = user.getXm();
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getloginname() {
			return loginname;
		}
		public void setloginname(String loginname) {
			this.loginname = loginname;
		}
		public String getXm() {
			return xm;
		}
		public void setXm(String xm) {
			this.xm = xm;
		}
	
		
	}
}
