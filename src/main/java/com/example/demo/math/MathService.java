package com.example.demo.math;

import org.springframework.util.MultiValueMap;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by taylorhessel on 6/26/17.
 */
public class MathService {
    public static String calcResult(CalculateInfo calcInfo) {
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

    public static String calcSum(MultiValueMap<String, String> mapValues) {
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

    public static String calcVolume(Volumes volume) {
     return "The volume of a " + volume.getV1() + "x" + volume.getV2() + "x" + volume.getV3() + " rectangle is " + volume.getV1() * volume.getV2() * volume.getV3();
    }

    public static String calcArea(AreaObj areaObj) {
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.FLOOR);

        if (areaObj.getType().equals("circle") && areaObj.getRadius() != null) {
            return "Area of a circle with a radius of " + areaObj.getRadius() + " is " + df.format(Math.PI * Math.pow(areaObj.getRadius(), 2));
        } else if (areaObj.getType().equals("rectangle") && areaObj.getWidth() != null && areaObj.getHeight() != null) {
            return "Area of a " + areaObj.getWidth() + "x" + areaObj.getHeight() + " rectangle is " + areaObj.getWidth() * areaObj.getHeight();
        } else {
            return "Invalid";
        }
    }
}
