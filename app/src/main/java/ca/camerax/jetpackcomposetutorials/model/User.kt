package ca.camerax.jetpackcomposetutorials.model

import ca.camerax.jetpackcomposetutorials.R

/**
 * Created by Royal Lachinov on 2021-08-22.
 */
data class User(
    var userProfileIcon:Int = R.drawable.img,
    var userFullName: String  = "",
    var transactionStatus: String = "",
    var transactionDate: String = "")