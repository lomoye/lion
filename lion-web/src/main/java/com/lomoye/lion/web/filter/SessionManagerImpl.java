package com.lomoye.lion.web.filter;

import com.lomoye.lion.core.constant.SessionConstant;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.UserManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2017/7/1.
 *
 */
@Component("sessionManagerImpl")
public class SessionManagerImpl implements SessionManager {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SessionManagerImpl.class);

    @Autowired
    private UserManager userManager;

    @Override
    public boolean checkLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstant.USER);
        if (user == null || user.getId() == null) {
            return false;
        }
        User newestUser = userManager.getById(user.getId());
        if (newestUser == null) {
            return false;
        }

        request.getSession().setAttribute(SessionConstant.USER, newestUser);
        return true;
    }
}
