package com.bcit.myminiapp.data

enum class Endpoints(val url: String) {
    BASE_URL("https://api.pokemontcg.io/v2"),
    SEARCH("${BASE_URL.url}/cards?q=name:%s*"),

    BASE_IMAGE_URL("https://images.pokemontcg.io"),
    IMAGE_ENDPOINT("${BASE_IMAGE_URL.url}/%s.png"),
    HIRES_IMAGE_ENDPOINT("${BASE_IMAGE_URL.url}/%s_hires.png");

    fun format(str: String?): String {
        if (str == null) return ""
        return url.format(str)
    }
}