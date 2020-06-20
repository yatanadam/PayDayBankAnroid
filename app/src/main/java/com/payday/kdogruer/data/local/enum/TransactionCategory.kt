package com.payday.kdogruer.data.local.enum

enum class TransactionCategory private constructor(val value: String,val colorHex: String) {
    SALARY("Salary","#fc5c65"),
    KIDS("Kids","#45aaf2"),
    GARDEN("Garden","#fd9643"),
    GROCERY("Grocery","#4a7bec"),
    TOYS("Toys","#fed330"),
    HEALTH("Health","#a65eea"),
    SHOES("Shoes","#d1d8e0"),
    MOVIES("Movies","#26de81"),
    BEAUTY("Beauty","#2acbba"),
    ELECTRONICS("Electronics","#fa8232"),
    GAMES("Games","#eb3b5a"),
    MUSIC("Music","#fc5c65"),//
    INDUSTRIAL("Industrial","#45aaf2"),
    TOOLS("Tools","#fd9643"),
    SPORTS("Sports","#4a7bec"),
    HOME("Home","#fed330"),
    AUTOMOTIVE("Automotive","#a65eea"),
    COMPUTERS("Computers","#d1d8e0"),
    CLOTHING("Clothing","#26de81");

    companion object {
        fun from(s: String): TransactionCategory? = values().find { it.value == s }
    }
}