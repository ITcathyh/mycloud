package hyh.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    private Cache<String, AtomicInteger> passwordRetryCache;
    private static final short MAX_LOGIN_TIME = 5;

    MyCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        String username = (String) authcToken.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);

        if (retryCount == null) {
            retryCount = new AtomicInteger();
            passwordRetryCache.put(username, retryCount);
        }

        if (retryCount.incrementAndGet() > MAX_LOGIN_TIME) {
            throw new ExcessiveAttemptsException();
        }

        if (super.doCredentialsMatch(authcToken, info)) {
            passwordRetryCache.remove(username);
            return true;
        } else {
            return false;
        }
    }
}
