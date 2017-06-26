package com.example.demo;

import org.springframework.util.MultiValueMap;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by taylorhessel on 6/26/17.
 */
public class MathService {
    public static String getCalcResult(CalculateInfo calcInfo) {
        if (calcInfo.getOperation().equals("add")) {
            return calcInfo.getX() + " + " + calcInfo.getY() + " = " + (calcInfo.getX() + calcInfo.getY());
        } else if (calcInfo.getOperation().equals("multiply")) {
            return calcInfo.getX() + " * " + calcInfo.getY() + " = " + (calcInfo.getX() * calcInfo.getY());
        } else if (calcInfo.getOperation().equals("subtract")) {
            return calcInfo.getX() + " - " + calcInfo.getY() + " = " + (calcInfo.getX() - calcInfo.getY());
        } else if (calcInfo.getOperation().equals("divide")) {
            return calcInfo.getX() + " / " + calcInfo.getY() + " = " + (calcInfo.getX() / calcInfo.getY());
        } else {
            return calcInfo.getX() + " + " + calcInfo.getY() + " = " + (calcInfo.getX() + calcInfo.getY());
        }
    }

    public static String getSum(MultiValueMap<String, String> mapValues) {
        String output = "";
        int i = 0;
        int sum = 0;
        for (MultiValueMap.Entry<String, List<String>> entry : mapValues.entrySet()) {
            for (String val : entry.getValue()) {
                sum += parseInt(val);
                if (i++ == entry.getValue().size() - 1) {
                    // last iteration
                    output += val + " = " + sum;
                } else {
                    output += val + " + ";
                }
            }
        }
        return output;
    }
}
