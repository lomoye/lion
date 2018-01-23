package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.UserMapper;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.UserManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserManagerImpl extends AbstractManager<User> implements UserManager{

    @Autowired
    private UserMapper mapper;


    @Override
    protected BasicMapper<Long, User> getMapper() {
        return mapper;
    }

    @Override
    public User findByMobile(String mobile) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mobile));
        User condition = new User();
        condition.setMobile(mobile);

        return getByCondition(condition);
    }

    @Override
    public User findByMobileAndPassword(String mobile, String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mobile) && !Strings.isNullOrEmpty(password));
        User condition = new User();
        condition.setMobile(mobile);
        condition.setPassword(password);

        return getByCondition(condition);
    }
}
