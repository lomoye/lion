package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends PagedMapper<Long,User> {
}
