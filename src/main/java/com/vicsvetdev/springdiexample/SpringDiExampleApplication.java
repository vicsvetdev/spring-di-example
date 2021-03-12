package com.vicsvetdev.springdiexample;

import com.vicsvetdev.springdiexample.config.SpringDiExampleConfig;
import com.vicsvetdev.springdiexample.config.SpringDiExampleConstructorConfig;
import com.vicsvetdev.springdiexample.controllers.*;
import com.vicsvetdev.springdiexample.datasource.FakeDataSource;
import com.vicsvetdev.springdiexample.services.PrototypeBean;
import com.vicsvetdev.springdiexample.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiExampleApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringDiExampleApplication.class, args);

        PetController petController = ctx.getBean("petController", PetController.class);
        System.out.println("--- The Best Pet is ---");
        System.out.println(petController.whichPetIsTheBest());

        I18nController i18nController = (I18nController) ctx.getBean("i18nController");
        System.out.println(i18nController.sayHello());

        MyController myController = (MyController) ctx.getBean("myController");

        System.out.println("--------- Primary Bean");
        System.out.println(myController.sayHello());

        System.out.println("-------------- Property");

        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
        System.out.println(propertyInjectedController.getGreeting());

        System.out.println("--------------- Setter");

        SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
        System.out.println(setterInjectedController.getGreeting());

        System.out.println("--------------- Constructor");

        ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
        System.out.println(constructorInjectedController.getGreeting());


        System.out.println("--------------- Bean Scopes");
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean1.getMyScope());
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean2.getMyScope());

        PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1.getMyScope());
        PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean2.getMyScope());

        System.out.println("----------------- Fake Data Source");
        FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUsername());
        System.out.println(fakeDataSource.getPassword());
        System.out.println(fakeDataSource.getJdbcurl());

        System.out.println("---------------------- Config Props Bean");
        SpringDiExampleConfig fakeConfigDataSource = ctx.getBean(SpringDiExampleConfig.class);
        System.out.println(fakeConfigDataSource.getUsername());
        System.out.println(fakeConfigDataSource.getPassword());
        System.out.println(fakeConfigDataSource.getJdbcurl());

        System.out.println("---------------------- Constructor Props Bean");
        SpringDiExampleConstructorConfig springDiExampleConfig = ctx.getBean(SpringDiExampleConstructorConfig.class);
        System.out.println(springDiExampleConfig.getUsername());
        System.out.println(springDiExampleConfig.getPassword());
        System.out.println(springDiExampleConfig.getJdbcurl());
    }

}
