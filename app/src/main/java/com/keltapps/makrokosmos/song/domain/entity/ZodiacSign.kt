package com.keltapps.makrokosmos.song.domain.entity

sealed class ZodiacSign(val element: Element, val name: String) {

    data class Aries(val zodiacName: String) : ZodiacSign(Element.Fire, zodiacName)
    data class Taurus(val zodiacName: String) : ZodiacSign(Element.Earth, zodiacName)
    data class Gemini(val zodiacName: String) : ZodiacSign(Element.Air, zodiacName)
    data class Cancer(val zodiacName: String) : ZodiacSign(Element.Water, zodiacName)
    data class Leo(val zodiacName: String) : ZodiacSign(Element.Fire, zodiacName)
    data class Virgo(val zodiacName: String) : ZodiacSign(Element.Earth, zodiacName)
    data class Libra(val zodiacName: String) : ZodiacSign(Element.Air, zodiacName)
    data class Scorpio(val zodiacName: String) : ZodiacSign(Element.Water, zodiacName)
    data class Sagittarius(val zodiacName: String) : ZodiacSign(Element.Fire, zodiacName)
    data class Capricorn(val zodiacName: String) : ZodiacSign(Element.Earth, zodiacName)
    data class Aquarius(val zodiacName: String) : ZodiacSign(Element.Air, zodiacName)
    data class Pisces(val zodiacName: String) : ZodiacSign(Element.Water, zodiacName)
}