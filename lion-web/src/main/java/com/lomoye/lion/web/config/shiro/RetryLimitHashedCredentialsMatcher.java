package com.lomoye.lion.web.config.shiro;

import com.lomoye.lion.core.constant.SessionConstant;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private final ConcurrentHashMap<String, LoginCacheInfo> passwordRetryCache = new ConcurrentHashMap<>();

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();

        LoginCacheInfo loginCacheInfo = passwordRetryCache.get(username);
        if (loginCacheInfo == null) {
            loginCacheInfo = new LoginCacheInfo();
            passwordRetryCache.put(username, loginCacheInfo);
        }

        if (inLock(loginCacheInfo)) {
            loginCacheInfo.setLockAnchor(System.currentTimeMillis());//超过限制次数以后如果还登录，每次都重新刷新锁定时间锚点
            throw new ExcessiveAttemptsException();
        }

        if (loginCacheInfo.getRetryNum().incrementAndGet() > SessionConstant.LIMIT_RETRY_LOGIN) {
            loginCacheInfo.setLocked(true);
            loginCacheInfo.setLockAnchor(System.currentTimeMillis());
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    private boolean inLock(LoginCacheInfo loginCacheInfo) {
        if (!loginCacheInfo.isLocked()) {
            return false;
        }

        long now = System.currentTimeMillis();
        long anchor = loginCacheInfo.getLockAnchor();

        if (anchor + SessionConstant.LIMIT_RETRY_LOGIN_TIME < now) {
            //锁定时间到了 解锁
            loginCacheInfo.setLocked(false);
            loginCacheInfo.setRetryNum(new AtomicInteger(0));
        }
        return loginCacheInfo.isLocked();
    }


    private final class LoginCacheInfo {
        private AtomicInteger retryNum;

        private Long lockAnchor;//锁定时候的时间戳

        private boolean locked;

        public LoginCacheInfo() {
            retryNum = new AtomicInteger(0);
            lockAnchor = System.currentTimeMillis();
        }

        public AtomicInteger getRetryNum() {
            return retryNum;
        }

        public void setRetryNum(AtomicInteger retryNum) {
            this.retryNum = retryNum;
        }

        public Long getLockAnchor() {
            return lockAnchor;
        }

        public void setLockAnchor(Long lockAnchor) {
            this.lockAnchor = lockAnchor;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }
    }
}
