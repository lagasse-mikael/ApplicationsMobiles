package ca.qc.cstj.s08bottomnavigation.core

object Constants {

    const val METEO_REFRESH_INTERVAL = 120000L
    const val API_KEY = "6272e7e8ce70b2dc82fa696689029fec"
    // le %s est remplacer par le cityName qu'on met dans le format.
    const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=${API_KEY}&units=metric"
}