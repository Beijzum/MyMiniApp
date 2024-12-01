package com.bcit.myminiapp.data

enum class Endpoints(val url: String) {
    BASE_URL("https://api.artic.edu/api/v1"),
    ARTWORKS("${BASE_URL.url}/artworks"),
    SEARCH("https://api.pokemontcg.io/v2/cards?q=name=%s"),
    FIELDS("${ARTWORKS.url}?fields=id,title,image_id"),
    IMAGE_ENDPOINT("https://images.pokemontcg.io/%s.png");

    fun format(str: String?): String {
        if (str == null) return ""
        return url.format(str)
    }
}