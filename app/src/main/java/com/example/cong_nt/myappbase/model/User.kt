package com.example.cong_nt.myappbase.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "users")
class User {
    private val defaultPassword = "8888888"

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var mId: Int = 0

    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @ColumnInfo(name = "age")
    var userAge: Int? = 0

    @ColumnInfo(name = "password")
    var password: String? = null

    constructor(userName: String?, userAge: Int?) {
        this.userName = userName
        this.userAge = userAge
    }
}