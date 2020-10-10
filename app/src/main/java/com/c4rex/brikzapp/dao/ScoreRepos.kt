package com.c4rex.brikzapp.dao

import com.c4rex.brikzapp.player.PlayerModel
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
@IgnoreExtraProperties
data class User(
        var username: String? = "",
        var score: Int? = 0,
        var stage: String? = "0",
        var nivel: String? = "0"

)
class ScoreRepos {
    val database = Firebase.database



    fun updateScore(user:String,score:Int,nivel:String,stage:String)
    {


        val scoreTable = hashMapOf(
            score to user
        )
        val scorekey=nivel.plus(":").plus(stage);

        val scoreUser = hashMapOf(
                scorekey to score
        )
        database.getReference("User/${user}/levels").setValue(scoreUser);
        database.getReference("scores/${scorekey}").setValue(scoreTable);

    }



    fun getuserInfo(user:String, listener: (query:QueryDocumentSnapshot) -> Unit) {


      // db.collection("User").whereEqualTo("__name__",user)
      //      .get()
      //         .addOnSuccessListener { result ->

      //             var  userData= result.elementAt(0)
      //             listener(userData);

      //         }
      //         .addOnFailureListener { exception ->
      //             throw Exception("error Fetching")
      //         }

    }


    fun getOrderScore(nivel:String, stage:String, limit: Long, listener: (querySnapshot: QuerySnapshot?) -> Unit) {

//        val scorekey=nivel.plus(":").plus(stage);
//        db.collection("scores").document(scorekey).collection("scores")
//            .orderBy("__name__", Query.Direction.DESCENDING).limit(limit).get()
//
//            .addOnSuccessListener { result ->
//                listener(result);
//            }
//            .addOnFailureListener { exception ->
//                throw Exception("error Fetching")
//            }

    }



}