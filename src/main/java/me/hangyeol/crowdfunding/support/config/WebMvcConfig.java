package me.hangyeol.crowdfunding.support.config;

import me.hangyeol.crowdfunding.support.utils.PasswordHashUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class WebMvcConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public PasswordHashUtil passwordHashUtil() {
//        return new PasswordHashUtil();
//    }

}
