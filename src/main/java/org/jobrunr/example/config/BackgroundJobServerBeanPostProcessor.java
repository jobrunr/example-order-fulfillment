package org.jobrunr.example.config;

import org.jobrunr.example.tasks.filters.OrderFulfilmentTasksFilter;
import org.jobrunr.server.BackgroundJobServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BackgroundJobServerBeanPostProcessor implements BeanPostProcessor {
    private final OrderFulfilmentTasksFilter orderFulfilmentTasksFilter;

    public BackgroundJobServerBeanPostProcessor(OrderFulfilmentTasksFilter orderFulfilmentTasksFilter) {
        this.orderFulfilmentTasksFilter = orderFulfilmentTasksFilter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof BackgroundJobServer backgroundJobServer) {
            backgroundJobServer.setJobFilters(Collections.singletonList(orderFulfilmentTasksFilter));
        }
        return bean;
    }
}
