package com.krab.stfbase.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("com.krab.stfbase")
@PropertySources(@PropertySource("classpath:application.properties"))
public class StfBootstrapper {
}
