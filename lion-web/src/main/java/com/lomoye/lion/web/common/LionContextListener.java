package com.lomoye.lion.web.common;




import com.lomoye.common.web.SpringWebContextListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;

@Component
public class LionContextListener extends SpringWebContextListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }
}
