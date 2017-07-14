package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value={"/calculate", "/calculate/"})
    public String getResult(CalculateInfo calcInfo) {
        return MathService.calcResult(calcInfo);
    }

    @PostMapping(value={"/sum", "/sum/"})
    public String postSum(@RequestParam MultiValueMap<String, String> mapValues) {
        return MathService.calcSum(mapValues);
    }

    @GetMapping(value={"/volume/{v1}/{v2}/{v3}", "/volume/{v1}/{v2}/{v3}/"})
    public String getVolume(Volumes volume) {
        return MathService.calcVolume(volume);
    }

    @PostMapping(value={"/area", "/area/"})
    public String postArea(AreaObj areaObj) {
        return MathService.calcArea(areaObj);
    }

}
