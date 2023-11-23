package uz.project.labsconnect.user.main.research.view_model

import kotlinx.coroutines.delay
import uz.project.labsconnect.user.main.types.MyResearchType
import uz.project.labsconnect.user.main.types.SimilarResearchType

class ResearchRepository {

    private var similarResearches = listOf(
        SimilarResearchType(
            1,
            "This hot air balloon pilot learned how to follow the wind from her dad ",
            "Kristin Shaw ",
            "Popular Science",
            "The flight process for soaring in a hot air balloon means operators don’t know precisely where they’re going to land. This article explains how it all works."
        ),
        SimilarResearchType(
            1,
            "The best guitar pedals of 2023",
            "Julian Vittorio",
            "Popular Science",
            "This article discusses how to transform your guitar’s tone and expand your creative potential."
        ),
        SimilarResearchType(
            1,
            "This off-roading, solar-powered vehicle just sped across the Sahara",
            "Andrew Paul",
            "Popular Science",
            "Designed by college students, the Stella Terra zipped through Morocco and portions of the desert as fast as 90 mph"
        ),
        SimilarResearchType(
            1,
            "12-million-year-old ape skull bares its fangs in virtual reconstruction",
            "Laura Baisas",
            "Popular Science",
            "Now extinct, Pierolapithecus catalaunicus could be one of the earliest known members of the great ape and human family"
        ),
        SimilarResearchType(
            1,
            "US will build seven regional ‘hydrogen hubs’ to spark clean energy transition",
            "Laura Baisas",
            "Popular Science",
            "The hubs will be spread across 16 states and aim to eliminate 25 million metric tons of carbon dioxide emissions."
        )
    )

    private var myResearch = MyResearchType(
        1,
        "Symptoms and Signs of Colorectal Cancer in Young Adults: A Systematic Review and Meta-analysis",
        "",
        "Social society",
        "Yeo SA, Chew MH, Chong JL, Ong WS, Loh M, Tang CL.",
        "This study provides insights into the clinicopathological and molecular characteristics of colorectal cancer in patie"
    )

//    private var myResearch = null

    suspend fun generateSimilarResearches(response: (List<SimilarResearchType>) -> Unit) {
        delay(2000)
        response(similarResearches)
    }

    suspend fun getMyResearchFromNetwork(response: (MyResearchType?) -> Unit) {
        delay(2000)
        response(myResearch)
    }

}