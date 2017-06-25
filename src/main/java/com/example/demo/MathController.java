package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taylorhessel on 6/25/17.
 */

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping(value={"/pi", "/pi/"})
    public String getPi() {
        return "3.141592653589793";
    }

}
