package com.trasier.springboot.pong.model;


public class WeatherData {
    private String temperature;
    private String windSpeed;
    private String humidity;
    private String qfePressure;
    private Station station;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getQfePressure() {
        return qfePressure;
    }

    public void setQfePressure(String qfePressure) {
        this.qfePressure = qfePressure;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature='" + temperature + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", qfePressure='" + qfePressure + '\'' +
                ", station=" + station +
                '}';
    }

    /*
    {
"station": {
"code": "STG",
"name": "St. Gallen",
"ch1903Y": 747861,
"ch1903X": 254586,
"lat": 47.4254611175553,
"lng": 9.39846161236304,
"elevation": 775
},
"code": "STG",
"dateTime": "2018-09-26T20:00:00.000Z",
"temperature": "12.1",
"sunshine": "0",
"precipitation": "0.0",
"windDirection": "166",
"windSpeed": "3.6",
"qnhPressure": "1034.6",
"gustPeak": "5.0",
"humidity": "82",
"qfePressure": "943.1",
"qffPressure": null
}
     */
}
