# weather_forecast.py

import requests

def get_weather(city):
    API_KEY = "YOUR_API_KEY"  # Replace with your actual API key from OpenWeather
    BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"

    # Constructing the complete URL
    url = BASE_URL + "q=" + city + "&appid=" + API_KEY + "&units=metric"

    response = requests.get(url)
    data = response.json()

    if data["cod"] == "404":
        print("City Not Found")
    else:
        main_data = data["main"]
        wind_data = data["wind"]
        weather_data = data["weather"][0]

        city_name = data["name"]
        country_name = data["sys"]["country"]
        temperature = main_data["temp"]
        pressure = main_data["pressure"]
        humidity = main_data["humidity"]
        wind_speed = wind_data["speed"]
        weather_desc = weather_data["description"]

        print(f"\nWeather Information for {city_name}, {country_name}:")
        print(f"Temperature: {temperature}°C")
        print(f"Pressure: {pressure} hPa")
        print(f"Humidity: {humidity}%")
        print(f"Wind Speed: {wind_speed} m/s")
        print(f"Weather: {weather_desc.capitalize()}")

def main():
    city = input("Enter city name: ")
    get_weather(city)

if __name__ == "__main__":
    main()
