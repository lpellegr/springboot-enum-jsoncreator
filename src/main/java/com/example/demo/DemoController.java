package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
public class DemoController {

    @GetMapping(value = "/demo/{id}")
    public OutputModel demo(
            @PathVariable("id") @NotEmpty @NotNull @Valid String id,
            @Valid InputModel input) {

        System.out.println(input);
        return new OutputModel(EveryEnum.HOURS);
    }

}