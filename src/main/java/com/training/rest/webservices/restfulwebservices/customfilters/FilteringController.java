package com.training.rest.webservices.restfulwebservices.customfilters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController
{
    // filter and hide field 2, filter for single object
    @GetMapping("/filtering")
     public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        return applyFilter(filter, someBean);
     }
     // filter and hide field 1 and 3, filter for multiple or list of objects
     @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfBeans(){
         List<SomeBean> someBeans = Arrays.asList(new SomeBean("val1", "val2", "val3"),
                 new SomeBean("val13", "val12", "val11"));
         SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
         return applyFilter(filter, someBeans);
     }
    //filter structure, same is used above
     public MappingJacksonValue applyFilter(SimpleBeanPropertyFilter f, Object o){
         FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", f);
         MappingJacksonValue mapping = new MappingJacksonValue(o);
         mapping.setFilters(filters);
        return mapping;
     }
}
