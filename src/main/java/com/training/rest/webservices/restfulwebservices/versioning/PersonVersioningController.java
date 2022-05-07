package com.training.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 pv1(){
        return new PersonV1("NirmalTak" );
    }
    @GetMapping("v2/person")
    public PersonV2 pv2(){
        return new PersonV2(new Name("Nirmal", "Tak"));
    }
    //request parameter use for versioning
    @GetMapping(value="/person/param", params="version=1") //person/param?version=1
    public PersonV1 paramV1(){
        return new PersonV1("NirmalTak" );
    }
    @GetMapping(value="/person/param", params="version=2") //person/param?version=2
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Nirmal", "Tak"));
    }
    //using headers
    @GetMapping(value="/person/header", headers="X-API-VERSION=1") //person/param
    public PersonV1 headerV1(){
        return new PersonV1("NirmalTak" );
    }
    @GetMapping(value="/person/header", headers="X-API-VERSION=2") //person/param
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Nirmal", "Tak"));
    }
    // using produces, mime type, accept {header} versioning
    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json") //person/param
    public PersonV1 produceV1(){
        return new PersonV1("NirmalTak" );
    }
    // Accept | application/vnd.company.app-v1+json
    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json") //person/param
    public PersonV2 produceV2(){
        return new PersonV2(new Name("Nirmal", "Tak"));
    }
}
