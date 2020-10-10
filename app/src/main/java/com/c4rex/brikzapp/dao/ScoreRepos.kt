package com.c4rex.brikzapp.dao

import com.c4rex.brikzapp.player.PlayerModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class ScoreRepos {

    private val db = FirebaseFirestore.getInstance()

    /**
     * @exception
     */

    fun updateScore(user:String,score:Int,nivel:String,stage:String){

        val scoreUser = hashMapOf(
            "score" to score
        )

        val scoreTable = hashMapOf(
            score to user
        )
        val scorekey=nivel.plus(":").plus(stage);

      db.collection("scores").document(scorekey).set(scoreTable);
        db.collection("User").document(user).collection("progress").document(scorekey).set(scoreUser)
    }



    fun getuserInfo(user:String,listener: (user: PlayerModel) -> Unit) {


        db.collection("User").whereEqualTo("__name__",user)
             .get()
                .addOnSuccessListener { result ->
                    for (resu in result){


                    }
                    listener(result);
                }
                .addOnFailureListener { exception ->
                    throw Exception("error Fetching")
                }

    }


    fun getOrderScore(nivel:String, stage:String, limit: Long, listener: (querySnapshot: QuerySnapshot?) -> Unit) {

        val scorekey=nivel.plus(":").plus(stage);
        db.collection("scores").document(scorekey).collection("scores")
            .orderBy("__name__", Query.Direction.DESCENDING).limit(limit).get()

            .addOnSuccessListener { result ->
                listener(result);
            }
            .addOnFailureListener { exception ->
                throw Exception("error Fetching")
            }

    }



}