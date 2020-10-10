package com.c4rex.brikzapp.model

class RankItem {
      var avatarId: Int

      var name: String

      var score: Int


    constructor(avatarId: Int, name: String, score: Int) {

        this.avatarId = avatarId
        this.name = name;
        this.score = score;
    }

}