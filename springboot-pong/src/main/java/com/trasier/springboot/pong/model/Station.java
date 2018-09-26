package com.trasier.springboot.pong.model;

public class Station {
    private String code;
    private String name;
    private String elevation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
    //    "station": {
//        "code": "STG",
//                "name": "St. Gallen",
//                "ch1903Y": 747861,
//                "ch1903X": 254586,
//                "lat": 47.4254611175553,
//                "lng": 9.39846161236304,
//                "elevation": 775
//    },
}
