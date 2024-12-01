package com.bcit.myminiapp.data

enum class Endpoints(val url: String) {
    BASE_URL("https://api.artic.edu/api/v1"),
    ARTWORKS("${BASE_URL.url}/artworks"),
    SEARCH("https://api.artic.edu/api/v1/artworks/search?q=%s&fields=id,title,image_id"),
    FIELDS("${ARTWORKS.url}?fields=id,title,image_id"),
    IMAGE_ENDPOINT("https://www.artic.edu/iiif/2/%s/full/843,/0/default.jpg");

    fun format(str: String?): String {
        if (str == null) return ""
        return url.format(str)
    }
}