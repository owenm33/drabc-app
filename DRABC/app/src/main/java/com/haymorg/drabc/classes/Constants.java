package com.haymorg.drabc.classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constants {
    public enum cprDemoState { TEXT, TEXT_AUDIO, DEMO, DEMO_AUDIO }
    public final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    public final static int MY_PERMISSIONS_REQUEST_CAMERA = 0;
    public final static String[] SUGGESTED_ISSUES = {
            "Snakebite",
            "Drug Overdose",
            "Spider Bite",
            "Heart Attack",
            "Stroke",
            "Dehydration",
            "Asthma Emergency",
            "Seizure",
            "Heat Stroke",
            "Poisoning",
            "Allergic Reaction"
    };

    public final static HashMap<Integer, List<String>> PHONE_NUMBERS = new HashMap<Integer, List<String>>() {{
        put(0, Arrays.asList("EPA [for chemical spills]\n", "tel:131555"));
        put(1, Arrays.asList("NSW Poisons Info\n", "tel:131126"));
        put(2, Arrays.asList("Alcohol/Drug Info Sydney\n", "tel:93618000"));
        put(3, Arrays.asList("Flood, Storm or Tsunami\n", "tel:132500"));
        put(4, Arrays.asList("Triple Zero\n", "tel:000"));
    }};
}
