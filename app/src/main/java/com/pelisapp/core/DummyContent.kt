package com.pelisapp.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.ArrayList

object DummyContent {

    val ITEMS: MutableList<Movie> = ArrayList()
    val ITEM_MAP: MutableMap<String, Movie> = HashMap()

    private val objectMapper = jacksonObjectMapper()

    init {
        addItem(guardiansOfTheGalaxy())
        addItem(matrix())
        addItem(shrek())
    }

    private fun shrek(): Movie {
        return objectMapper.readValue("{\n" +
                "      \"Title\":\"Shrek\",\n" +
                "      \"Year\":\"2001\",\n" +
                "      \"Rated\":\"PG\",\n" +
                "      \"Released\":\"18 May 2001\",\n" +
                "      \"Runtime\":\"90 min\",\n" +
                "      \"Genre\":\"Animation, Adventure, Comedy, Family, Fantasy\",\n" +
                "      \"Director\":\"Andrew Adamson, Vicky Jenson\",\n" +
                "      \"Writer\":\"William Steig (based upon the book by), Ted Elliott, Terry Rossio, Joe Stillman, Roger S.H. Schulman, Cody Cameron (additional dialogue), Chris Miller (additional dialogue), Conrad Vernon (additional dialogue)\",\n" +
                "      \"Actors\":\"Mike Myers, Eddie Murphy, Cameron Diaz, John Lithgow\",\n" +
                "      \"Plot\":\"A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.\",\n" +
                "      \"Language\":\"English\",\n" +
                "      \"Country\":\"USA, Japan\",\n" +
                "      \"Awards\":\"Won 1 Oscar. Another 39 wins & 60 nominations.\",\n" +
                "      \"Poster\":\"https://m.media-amazon.com/images/M/MV5BOGZhM2FhNTItODAzNi00YjA0LWEyN2UtNjJlYWQzYzU1MDg5L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\",\n" +
                "      \"Ratings\":[\n" +
                "         {\n" +
                "            \"Source\":\"Internet Movie Database\",\n" +
                "            \"Value\":\"7.8/10\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Rotten Tomatoes\",\n" +
                "            \"Value\":\"88%\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Metacritic\",\n" +
                "            \"Value\":\"84/100\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"Metascore\":\"84\",\n" +
                "      \"imdbRating\":\"7.8\",\n" +
                "      \"imdbVotes\":\"620,107\",\n" +
                "      \"imdbID\":\"tt0126029\",\n" +
                "      \"Type\":\"movie\",\n" +
                "      \"DVD\":\"25 Nov 2015\",\n" +
                "      \"BoxOffice\":\"\$267,665,011\",\n" +
                "      \"Production\":\"DreamWorks SKG, Pacific Data Images (PDI)\",\n" +
                "      \"Website\":\"N/A\",\n" +
                "      \"Response\":\"True\"\n" +
                "   }")
    }

    private fun matrix(): Movie {
        return objectMapper.readValue("{\n" +
                "      \"Title\":\"The Matrix\",\n" +
                "      \"Year\":\"1999\",\n" +
                "      \"Rated\":\"R\",\n" +
                "      \"Released\":\"31 Mar 1999\",\n" +
                "      \"Runtime\":\"136 min\",\n" +
                "      \"Genre\":\"Action, Sci-Fi\",\n" +
                "      \"Director\":\"Lana Wachowski, Lilly Wachowski\",\n" +
                "      \"Writer\":\"Lilly Wachowski, Lana Wachowski\",\n" +
                "      \"Actors\":\"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving\",\n" +
                "      \"Plot\":\"When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.\",\n" +
                "      \"Language\":\"English\",\n" +
                "      \"Country\":\"USA\",\n" +
                "      \"Awards\":\"Won 4 Oscars. Another 38 wins & 51 nominations.\",\n" +
                "      \"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\n" +
                "      \"Ratings\":[\n" +
                "         {\n" +
                "            \"Source\":\"Internet Movie Database\",\n" +
                "            \"Value\":\"8.7/10\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Rotten Tomatoes\",\n" +
                "            \"Value\":\"88%\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Metacritic\",\n" +
                "            \"Value\":\"73/100\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"Metascore\":\"73\",\n" +
                "      \"imdbRating\":\"8.7\",\n" +
                "      \"imdbVotes\":\"1,702,966\",\n" +
                "      \"imdbID\":\"tt0133093\",\n" +
                "      \"Type\":\"movie\",\n" +
                "      \"DVD\":\"01 Jan 2009\",\n" +
                "      \"BoxOffice\":\"\$171,479,930\",\n" +
                "      \"Production\":\"Village Roadshow Prod., Silver Pictures\",\n" +
                "      \"Website\":\"N/A\",\n" +
                "      \"Response\":\"True\"\n" +
                "   }")
    }

    private fun guardiansOfTheGalaxy(): Movie {
        return objectMapper.readValue("{\n" +
                "      \"Title\":\"Guardians of the Galaxy Vol. 2\",\n" +
                "      \"Year\":\"2017\",\n" +
                "      \"Rated\":\"PG-13\",\n" +
                "      \"Released\":\"05 May 2017\",\n" +
                "      \"Runtime\":\"136 min\",\n" +
                "      \"Genre\":\"Action, Adventure, Comedy, Sci-Fi\",\n" +
                "      \"Director\":\"James Gunn\",\n" +
                "      \"Writer\":\"James Gunn, Dan Abnett (based on the Marvel comics by), Andy Lanning (based on the Marvel comics by), Steve Englehart (Star-Lord created by), Steve Gan (Star-Lord created by), Jim Starlin (Gamora and Drax created by), Stan Lee (Groot created by), Larry Lieber (Groot created by), Jack Kirby (Groot created by), Bill Mantlo (Rocket Raccoon created by), Keith Giffen (Rocket Raccoon created by), Steve Gerber (Howard the Duck created by), Val Mayerik (Howard the Duck created by)\",\n" +
                "      \"Actors\":\"Chris Pratt, Zoe Saldana, Dave Bautista, Vin Diesel\",\n" +
                "      \"Plot\":\"The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego.\",\n" +
                "      \"Language\":\"English\",\n" +
                "      \"Country\":\"USA\",\n" +
                "      \"Awards\":\"Nominated for 1 Oscar. Another 15 wins & 57 nominations.\",\n" +
                "      \"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg\",\n" +
                "      \"Ratings\":[\n" +
                "         {\n" +
                "            \"Source\":\"Internet Movie Database\",\n" +
                "            \"Value\":\"7.6/10\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Rotten Tomatoes\",\n" +
                "            \"Value\":\"85%\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"Source\":\"Metacritic\",\n" +
                "            \"Value\":\"67/100\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"Metascore\":\"67\",\n" +
                "      \"imdbRating\":\"7.6\",\n" +
                "      \"imdbVotes\":\"583,183\",\n" +
                "      \"imdbID\":\"tt3896198\",\n" +
                "      \"Type\":\"movie\",\n" +
                "      \"DVD\":\"10 Jul 2017\",\n" +
                "      \"BoxOffice\":\"\$389,813,101\",\n" +
                "      \"Production\":\"Marvel Studios, Walt Disney Pictures\",\n" +
                "      \"Website\":\"N/A\",\n" +
                "      \"Response\":\"True\"\n" +
                "   }")
    }

    private fun addItem(item: Movie) {
        ITEMS.add(item)
        ITEM_MAP.put(item.imdbID.orEmpty(), item)
    }
}