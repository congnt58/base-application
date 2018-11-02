package com.example.cong_nt.myappbase.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.cong_nt.myappbase.model.User
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    abstract fun getUserByUserId(userId: Int): Flowable<User>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flowable<List<User>>

    @Insert
    abstract fun insertUser(vararg users: User)

}