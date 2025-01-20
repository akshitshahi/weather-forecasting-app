// WeatherForecastApp.java

import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class WeatherForecastApp {

    public static void getWeather(String city) {
        try {
            String apiKey = "YOUR_API_KEY";  // Replace with your actual API key from OpenWeather
            String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
            String urlString = baseUrl + city + "&appid=" + apiKey + "&units=metric";

            // Create URL object
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject data = new JSONObject(response.toString());

            if (data.getInt("cod") == 404) {
                System.out.println("City not found.");
            } else {
                JSONObject main = data.getJSONObject("main");
                JSONObject wind = data.getJSONObject("wind");
                String cityName = data.getString("name");
                String country = data.getJSONObject("sys").getString("country");
                double temp = main.getDouble("temp");
                int pressure = main.getInt("pressure");
                int humidity = main.getInt("humidity");
                double windSpeed = wind.getDouble("speed");
                String description = data.getJSONArray("weather").getJSONObject(0).getString("description");

                System.out.println("\nWeather Information for " + cityName + ", " + country + ":");
                System.out.println("Temperature: " + temp + "°C");
                System.out.println("Pressure: " + pressure + " hPa");
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Wind Speed: " + windSpeed + " m/s");
                System.out.println("Weather: " + description.substring(0, 1).toUpperCase() + description.substring(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter city name: ");
            String city = reader.readLine();
            getWeather(city);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
