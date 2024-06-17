package ru.hometask1.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RunnableBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Arrays.asList(bean.getClass().getInterfaces()).contains(Runnable.class)) {
            Runnable runnable = (Runnable) bean;
            new Thread(runnable).start();
        }
        return bean;
    }
}
