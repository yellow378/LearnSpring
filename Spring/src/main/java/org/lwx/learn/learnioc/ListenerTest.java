package org.lwx.learn.learnioc;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListenerTest {
    //Test for ApplicationContext prepareRefresh
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"},false);

        // 第一次 refresh()
        context.addApplicationListener((ApplicationEvent event) ->
                System.out.println("[Listener 1] Event: " + event));
        context.refresh(); // 此时 earlyApplicationListeners 会备份 Listener 1

        // 在第一次 refresh() 后，第二次 refresh() 前添加新的 Listener
        context.addApplicationListener((ApplicationEvent event) ->
                System.out.println("[Listener 2] Event: " + event));
        context.publishEvent(new ContextStoppedEvent(context));
        // 第二次 refresh()
        context.refresh();

        // 发布一个事件，观察哪些监听器生效
        context.publishEvent(new ContextStoppedEvent(context));
        System.out.println("aaaa");
    }
}
