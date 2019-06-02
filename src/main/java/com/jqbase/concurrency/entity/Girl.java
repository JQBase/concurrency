package com.jqbase.concurrency.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/3 23:26
 */
@Component
@ConfigurationProperties(prefix = "girl")
public class Girl {

    private String cupSize;

    private Integer age;

    @Override
    public String toString() {
        return "Girl{" +
                "cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }

    public String getCupSize() {
        return cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }


}
