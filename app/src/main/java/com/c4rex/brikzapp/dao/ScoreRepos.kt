package com.c4rex.brikz.dao

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class ScoreRepos {

    private val db = FirebaseFirestore.getInstance()

    /**
     * @exception
     */

    fun updateScore(score:Int,nivel:Int,stage:Int){



    }



    fun getuserInfo(user:String,listener: (querySnapshot: QuerySnapshot?) -> Unit) {


        db.collection("User").whereEqualTo("__name__",user)
             .get()

                .addOnSuccessListener { result ->
                    listener(result);
                }
                .addOnFailureListener { exception ->
                    throw Exception("error Fetching")
                }

    }


    fun getOrderScore(limit: Long,listener: (querySnapshot: QuerySnapshot?) -> Unit) {


        db.collection("scores")
            .orderBy("__name__", Query.Direction.DESCENDING).limit(limit).get()

            .addOnSuccessListener { result ->
                listener(result);
            }
            .addOnFailureListener { exception ->
                throw Exception("error Fetching")
            }

    }



}