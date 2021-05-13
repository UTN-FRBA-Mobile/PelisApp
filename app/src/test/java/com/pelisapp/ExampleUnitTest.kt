package com.pelisapp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.pelisapp.core.Movie
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun json_mapper_test() {
        val json = "[\n" +
                "    \t{\"Title\":\"Guardians of the Galaxy Vol. 2\",\"Year\":\"2017\",\"Rated\":\"PG-13\",\"Released\":\"05 May 2017\",\"Runtime\":\"136 min\",\"Genre\":\"Action, Adventure, Comedy, Sci-Fi\",\"Director\":\"James Gunn\",\"Writer\":\"James Gunn, Dan Abnett (based on the Marvel comics by), Andy Lanning (based on the Marvel comics by), Steve Englehart (Star-Lord created by), Steve Gan (Star-Lord created by), Jim Starlin (Gamora and Drax created by), Stan Lee (Groot created by), Larry Lieber (Groot created by), Jack Kirby (Groot created by), Bill Mantlo (Rocket Raccoon created by), Keith Giffen (Rocket Raccoon created by), Steve Gerber (Howard the Duck created by), Val Mayerik (Howard the Duck created by)\",\"Actors\":\"Chris Pratt, Zoe Saldana, Dave Bautista, Vin Diesel\",\"Plot\":\"The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Nominated for 1 Oscar. Another 15 wins & 57 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.6/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"85%\"},{\"Source\":\"Metacritic\",\"Value\":\"67/100\"}],\"Metascore\":\"67\",\"imdbRating\":\"7.6\",\"imdbVotes\":\"583,183\",\"imdbID\":\"tt3896198\",\"Type\":\"movie\",\"DVD\":\"10 Jul 2017\",\"BoxOffice\":\"\$389,813,101\",\"Production\":\"Marvel Studios, Walt Disney Pictures\",\"Website\":\"N/A\",\"Response\":\"True\"},\n" +
                "    \t{\"Title\":\"The Matrix\",\"Year\":\"1999\",\"Rated\":\"R\",\"Released\":\"31 Mar 1999\",\"Runtime\":\"136 min\",\"Genre\":\"Action, Sci-Fi\",\"Director\":\"Lana Wachowski, Lilly Wachowski\",\"Writer\":\"Lilly Wachowski, Lana Wachowski\",\"Actors\":\"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving\",\"Plot\":\"When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Won 4 Oscars. Another 38 wins & 51 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.7/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"73/100\"}],\"Metascore\":\"73\",\"imdbRating\":\"8.7\",\"imdbVotes\":\"1,702,966\",\"imdbID\":\"tt0133093\",\"Type\":\"movie\",\"DVD\":\"01 Jan 2009\",\"BoxOffice\":\"\$171,479,930\",\"Production\":\"Village Roadshow Prod., Silver Pictures\",\"Website\":\"N/A\",\"Response\":\"True\"},\n" +
                "    \t{\"Title\":\"Shrek\",\"Year\":\"2001\",\"Rated\":\"PG\",\"Released\":\"18 May 2001\",\"Runtime\":\"90 min\",\"Genre\":\"Animation, Adventure, Comedy, Family, Fantasy\",\"Director\":\"Andrew Adamson, Vicky Jenson\",\"Writer\":\"William Steig (based upon the book by), Ted Elliott, Terry Rossio, Joe Stillman, Roger S.H. Schulman, Cody Cameron (additional dialogue), Chris Miller (additional dialogue), Conrad Vernon (additional dialogue)\",\"Actors\":\"Mike Myers, Eddie Murphy, Cameron Diaz, John Lithgow\",\"Plot\":\"A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.\",\"Language\":\"English\",\"Country\":\"USA, Japan\",\"Awards\":\"Won 1 Oscar. Another 39 wins & 60 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOGZhM2FhNTItODAzNi00YjA0LWEyN2UtNjJlYWQzYzU1MDg5L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"84/100\"}],\"Metascore\":\"84\",\"imdbRating\":\"7.8\",\"imdbVotes\":\"620,107\",\"imdbID\":\"tt0126029\",\"Type\":\"movie\",\"DVD\":\"25 Nov 2015\",\"BoxOffice\":\"\$267,665,011\",\"Production\":\"DreamWorks SKG, Pacific Data Images (PDI)\",\"Website\":\"N/A\",\"Response\":\"True\"}\t\n" +
                "    ]"
        print("json: $json")

        val mapper = jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)

        val movies = mapper.readValue<List<Movie>>(json)
        print("movies: $movies")
    }
}