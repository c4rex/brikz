package com.c4rex.brikzapp.player

import com.google.gson.Gson

object PlayerMockSource {
    fun getArtistMock():PlayerModel {
        val gson = Gson()
        val mockJson:String = getMockJson()
        return gson.fromJson(mockJson, PlayerModel::class.java)
    }

    private fun getMockJson():String {
        return "{ \"id\": 10, \"name\": \"testUser\", \"email\": \"test@mail.com\", \"gender\": \"male\", \"image\": 0, \"levelAdvance\": [ { \"id\": 1, \"stageAdvance\": [ { \"id\": 101, \"completed\": true }, { \"id\": 102, \"completed\": false }, { \"id\": 103, \"completed\": false }, { \"id\": 104, \"completed\": false } ] }, { \"id\": 2, \"stageAdvance\": [ { \"id\": 105, \"completed\": true }, { \"id\": 106, \"completed\": false }, { \"id\": 107, \"completed\": false }, { \"id\": 108, \"completed\": false } ] } ] }"
    }
}