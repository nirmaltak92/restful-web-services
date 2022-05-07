package com.training.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    //help us pickup properties from message bundle
    //@RequestMapping(method = RequestMethod.GET, path="/helloworld")
    @GetMapping(path = "/helloworld")
    //@GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/helloworldbean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    //get with path variable or path parameter
    @GetMapping(path = "/helloworld/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/helloworld-i18n")
    //@GetMapping("/helloworld")
    public String helloWorldI18n(
        //@RequestHeader(name="Accept-Language", required = false) Locale locale
    ){
        return messageSource.getMessage("good.morning.message", null, "Very Good Morning",
                LocaleContextHolder.getLocale());
            //  locale); LocaleContextHolder directly fetches locale, we don't need to pass parameters in Req Header
    }
}
