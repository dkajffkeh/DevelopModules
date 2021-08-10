package com.basicmodule.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/main")
public class MainpageController {


    @GetMapping("/")
    public String forwardMainpage(){


        return "home";
    }
}
