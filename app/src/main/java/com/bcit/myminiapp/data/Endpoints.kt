package com.bcit.myminiapp.data

enum class Endpoints(val url: String) {
    BASE_URL("https://api.pokemontcg.io/v2"),
    SEARCH("${BASE_URL.url}/cards?name=%s&fields=id,name"),
    IMAGE_ENDPOINT("https://images.pokemontcg.io/%s.png");

    fun format(str: String?): String {
        if (str == null) return ""
        return url.format(str)
    }
}