package pierrick.merielbussy.prisonfantasy

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.FakerConfig
import io.github.serpro69.kfaker.create

var tempCode = 0
var code = 0

var tempUsername = ""
var username = ""

var tempPassword = ""
var password = ""

class Generate {
    fun genUsername() {
        val fakerConfig = FakerConfig.builder().create {
            locale = "fr"
        }
        val faker = Faker(fakerConfig)
        val usernameFirst = faker.name.firstName()
        val usernameLast = faker.name.lastName()
        val usernameMail = faker.internet.domain()
        tempUsername = "$usernameFirst.$usernameLast@$usernameMail"
    }
    
    fun genPassword() {
        val STRING_CHARACTERS = ('0'..'9')+('A'..'Z')+('a'..'z').toList().toTypedArray()
        tempPassword = (1..6).map {STRING_CHARACTERS.random()}.joinToString("")
    }

    fun genFourDigitsCode() {
        val STRING_CHARACTERS = ('0'..'9').toList().toTypedArray()
        tempCode = (1..4).map {STRING_CHARACTERS.random()}.joinToString("").toInt()
    }

    fun genFirstAndLastName() {
        val fakerConfig = FakerConfig.builder().create {
            locale = "fr"
        }
        val faker = Faker(fakerConfig)

        val usernameFirst = faker.name.firstName()
        val usernameLast = faker.name.lastName()
        var tempFirstAndLastName = "$usernameFirst $usernameLast"
    }

//    fun genFirstName() {
//        val fakerConfig = FakerConfig.builder().create {
//            locale = "fr"
//        }
//        val faker = Faker(fakerConfig)
//
//        tempFirstName = faker.name.firstName()
//    }
//
//    fun genLastName() {
//        val fakerConfig = FakerConfig.builder().create {
//            locale = "fr"
//        }
//        val faker = Faker(fakerConfig)
//
//        tempLastName = faker.name.lastName()
//    }

    fun genEndMail() {
        val fakerConfig = FakerConfig.builder().create {
            locale = "fr"
        }
        val faker = Faker(fakerConfig)

        var tempEndMail = faker.internet.domainSuffix()
    }
}