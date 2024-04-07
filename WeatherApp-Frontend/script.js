function getWeatherSummary() {
    var location = document.getElementById("locationInput").value;
    fetchWeatherData("/forecast-summary", location);
}

function getHourlyForecast() {
    var location = document.getElementById("locationInput").value;
    fetchWeatherData("/hourly-forecast", location);
}

function fetchWeatherData(endpoint, location) {
    console.log("fetchWeatherData");
    var xhr = new XMLHttpRequest();
    var requestUrl = "weather-app-production-d1e0.up.railway.app" + endpoint + "?location=" + location;

    xhr.open("GET", requestUrl, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("customClientId", "#Rakshika");
    xhr.setRequestHeader("customClientSecret", "159753");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                displayWeatherData(response);
            } else {
                displayError("Error: " + xhr.statusText);
            }
        }
    };
    xhr.send();
}

function displayWeatherData(data) {
    console.log("data");
    var weatherResult = document.getElementById("weatherResult");

    weatherResult.innerHTML = ""; // Clear previous content

    // Create elements to display weather data
    var ul = document.createElement("ul");
    for (var key in data) {
        if (data.hasOwnProperty(key)) {
            var li = document.createElement("li");
            li.textContent = key + ": " + data[key];
            ul.appendChild(li);
        }
    }
    weatherResult.appendChild(ul);
}

function displayError(message) {
    var weatherResult = document.getElementById("weatherResult");
    weatherResult.innerHTML = "<p>" + message + "</p>";
}
