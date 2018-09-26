package chun.xi.lin.shiro.chapter2.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 * 只能有一个验证成功，多个即会报错
 */
// 只能有一个验证成功，多个即会报错
public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo();//返回一个权限的认证信息
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;//返回之前合并的
    }


    /**
     * realm 当前realm
     * token 当前登录用户的令牌
     * singleRealmInfo 本次单个Realm得到的AuthenticationInfo
     * aggregateInfo 合并Realm得到的AuthenticationInfo
     * */
    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
                                           AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info;        // 返回对象
        if (singleRealmInfo == null) {  // 本次认证返回的AuthenticationInfo为空的话
            info = aggregateInfo;   // 不需要合并，直接之前合并的AuthenticationInfo就作为这次返回数据可以理解成sum=sum+0，即sum=sum
        } else {
            if (aggregateInfo == null) {    // 之前得到的合并对象都为空的话
                info = singleRealmInfo; // 返回info为本次单个realm得到的信息
            } else {    // 如果本次验证的realm不为空的话
                info = merge(singleRealmInfo, aggregateInfo);   // 合并这些info
                if(info.getPrincipals().getRealmNames().size() > 1) {   // 若长度大于1，报错，违背了onlyOne
                    System.out.println(info.getPrincipals().getRealmNames());
                    throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                            "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                            "authenticate these tokens.");
                }
            }
        }


        return info;
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;   // 返回所有合并的
    }
}
