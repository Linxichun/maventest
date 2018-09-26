package chun.xi.lin.shiro.chapter2.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 * 至少两个验证成功，多于两个没有关系,成功的全部返回
 */
// 至少两个验证成功，多于两个没有关系,成功的全部返回
public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy {

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
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info;        // 返回对象
        if (singleRealmInfo == null) {  // 本次认证返回的AuthenticationInfo为空的话
            info = aggregateInfo;   // 不需要合并，直接之前合并的AuthenticationInfo就作为这次返回数据可以理解成sum=sum+0，即sum=sum
        } else {
            if (aggregateInfo == null) {    // 之前得到的合并对象都为空的话
                info = singleRealmInfo; // 返回info为本次单个realm得到的信息
            } else {    // 之前合并的对象不为空的话，
                info = merge(singleRealmInfo, aggregateInfo);   // 尽管合并
            }
        }

        return info;
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals()) || aggregate.getPrincipals().getRealmNames().size() < 2) {
            // 最后这些只要总长度一小于2，就会报错
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that at least two realm can " +
                    "authenticate these tokens.");
        }

        return aggregate;
    }
}
