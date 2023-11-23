package uz.project.labsconnect.user.main.search.view_model

import com.example.myapplication.types.list.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.project.labsconnect.user.main.home.view_model.HomeRepository
import uz.project.labsconnect.user.main.types.LabsType
import uz.project.labsconnect.user.main.types.TopicType

class SearchRepository {
    val mainRepository = HomeRepository()

    var topic = listOf(
        TopicType(1, "Microscope", 15),
        TopicType(1, "Test tubes", 15),
        TopicType(1, "Super computer", 15),
        TopicType(1, "Medical lab", 15),
        TopicType(1, "Wet combination", 15),
        TopicType(1, "Regenerator", 15)
    )

    var recommended = listOf(
        LabsType(
            1,
            "Samarqand Davlat Veterinariya Meditsinasi, Chorvachilik Va Biotexnologiyalar Universiteti",
            12,
            10,
            "Free",
            "Universitetda amaliy va innovatsion xarakterdagi ilmiy-tadqiqotlarni, yangi texnika va texnologiyalar yaratishni qo'llab-quvatlash. Ilmiy-tadqiqot, tajriba-konstruktorlik ishlari olib borish, yangi texnologiya, ilmiy ishlanmalarni yaratish va ularni chorvachilik hamda veterinariya sohalariga joriy etish. Tadqiqotlar natijalari asosida yaratilgan ilmiy-texnik mahsulotlarni buyurtmachilarga (iste'molchilarga) sotish orqali innovatsion jamg'arma hisob raqamiga pul tushirish. Universitet professor-o'qituvchi, doktorantlari va mustaqil izlanuvchilarini turli ilmiy tanlovlarga qatnashishi uchun uslubiy yordam berish, ilmiy-texnik axborotga, patent-litsenzion xizmatlari ko'rsatilishini ta'minlash. Universitet innovatsion o'quv-diagnostika va davolash veterinariya markazi, kafedralararo va immunobiotexnologiya ilmiy-tadqiqot laboratoriyalarini o`quv- ilmiy asboblar, jihozlar va boshqa moddiy-ashyolar bilan ta'minlashni tashkillashtirish. Ilmiy seminar va konferensiyalar tashkil etish, ilg'or xorijiy o'quv yurtlari olimlari bilan axborot almashish, ilmiy hamkorlik qilishi.",
            listOf("", ""),
            "Yunusov Xudaynazar Beknazarovich",
            "samvmi@edu.uz",
            "662347686",
            "Samarqand shahar, Mirzo Ulug‘bek ko‘chasi 77-uy",
            "http://samvmi@edu.uz",
            true,
            "https://slab.gov.uz/media/organisation_logo/29X4BOv6_400x400.jpg",
            "",
            listOf(),
            listOf()
        ),

        LabsType(
            2,
            "Yadro Fizikasi Instituti",
            12,
            10,
            "Free",
            "Relyativistik yadrolar va zarralarni birlamchi energiyalarni yuqori diapazonidagi yadrolar bilan oʼzaro toʼqnashuvini tadqiq etish; adronlarni koʼp sonli generatsiyasini oʼrganish borasida tajribalar oʼtkazish, koʼp barionli rezonanslar va izobarlarni yadrolar bilan oʼzaro toʼqnashuvida aniqlash, qutblashtirilgan va qutblashtirilmagan adron nur tolalarini qoʼllash orqali adronli toʼqnashuvlarni oʼzaro taʼsirlarini toʼliq kesimini oʼlchash boʼyicha ishlarni olib borish; Yadroviy reaktsiyalar, atom yadrolarini xususiyatlari hamda elementar zarralarni nazariy va tajribaviy jihatdan tadqiq etish, yadroviy reaktsiyalarni koʼp nuklonli oʼtish jarayonlari bilan birgalikda tadqiq etish, yadrolarni yuqori konstantlarini aniqlash, elementar zarralarni yangi turlarini yaratish, astrofizikani bir qator jumboqlarini hal etishda kam quvvatli energiyalar yadro fizikasini natijalarini qoʼllash. Kam quvvatli energiyalarda yadrolarni yuqori uygʼotilgan holatlarini hamda yadrolarni bir-biri bilan oʼzaro taʼsirlarini tadqiq etish, atom yadrolari modellarini ogʼir neytrino-nazariy tarzda tadqiq etish turlarini aniqlashda tajribalar oʼtkazish. Ilmiy tadqiqot reaktorlarida, tezlatgichlarda hamda yadro texnika sohasida qoʼllaniladigan turli xildagi materiallarga radiatsiyaviy nurlanishni oʼzaro taʼsirini ilmiy tadqiq etish, yangi materiallarni yaratish.",
            listOf("", ""),
            "I.I. Sodiqov",
            "croownguuard@gmail.com",
            "71 289-31-60",
            "Ulug‘bek shaharchasi, U,G‘ulomov ko‘chasi,1",
            "http://inp.uz",
            true,
            "https://slab.gov.uz/media/organisation_logo/download.png",
            "",
            listOf(),
            listOf()
        ),
        LabsType(
            3,
            "Toshkent Axborot Texnologiyalari Universiteti",
            12,
            10,
            "1 000 000",
            "Muhammad al-Xorazmiy nomidagi Toshkent axborot texnologiyalari universiteti (sobiq Toshkent elektrotexnika aloqa instituti) 1955 yilda tashkil topgan. 2002 yil 30 may kuni O‘zbekiston Respublikasi Prezidenti PF-3080 Farmoniga binoan Toshkent axborot texnologiyalari universitetiga aylantirildi. 2005 yil 2 iyun kuni O‘zbekiston Respublikasi Prezidenti PQ-91 sonli Qaroriga TATUning Qarshi, Samarqand, Urganch va Farg‘ona shaharlarida mintaqaviy filiallar tashkil etilgan. O‘zbekiston Respublikasi Prezidentining 2013 yil 26 martdagi \"Axborot-kommunikatsiya texnologiyalari sohasida kadrlar tayyorlash tizimini takomillashtirish to‘g‘risida”gi PQ-1942 sonli qaroriga muvofiq so‘ngi yillar ichida xalqaro standartlari darajasida AKT sohasida yuqori malakali mutaxassislar tayyorlash tizimini mutlaqo yaxshilash bo‘yicha bir qator tadbirlar o‘tkazildi. Universitet va uning mintaqaviy filiallari tarkibida 13ta fakultet, 62 ta kafedra mavjud bo‘lib, ta’limning 23 yo‘nalishi bo‘yicha, jumladan bakalavriatning 8 yangi yo‘nalishi va magistraturaning 9 yangi mutaxassisligi bo‘yicha kadrlarni tayyorlanadi. Xalqaro standartlari darajasida AKT sohasida yuqori malakali mutaxassislar tayyorlash.",
            listOf("", ""),
            "Мaxkamov Baxtiyor Shuxratovich",
            "info@tuit.uz",
            "712386415",
            "Amir Temur shox ko‘chasi 108 uy",
            "http://info@tuit.uz",
            false,
            "https://slab.gov.uz/media/organisation_logo/download.jpg",
            "",
            listOf(),
            listOf()
        )
    )

//    suspend fun getRecommendation(response: (list: List<LabsType>) -> Unit) {
//
//        mainRepository.getLabsFlow()
//
//        mainRepository.getLabs {
//            response(it.take(6))
//        }
//    }

    suspend fun getRecommendation(): Flow<List<LabsType>> {
        return mainRepository.getLabsFlow()
    }

    suspend fun getCategory() = flow { emit(mainRepository.category.take(6)) }

    suspend fun convertResultToLab(list: List<Result>): List<LabsType> {
        return list.map { mainRepository.convertInstitutionShortToLabsType(it) }
    }

//    fun getCategory(response: (list: List<CategoryType>) -> Unit) {
//        response(mainRepository.category.take(4))
//    }
}