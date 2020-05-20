package com.ashiq.mvvm.viewmodel

import com.ashiq.mvvm.pojo.User
import com.ashiq.mvvm.repository.UserRepository
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
//https://dzone.com/refcardz/mockito?chapter=1
//https://developer.android.com/studio/build/dependencies

//vimp
//https://blog.mindorks.com/mockito-cannot-mock-in-kotlin



class UsersViewModelTest{

    @Test
    fun getUsers(){
        var mockedUserRepo = Mockito.mock(UserRepository::class.java)
        var usersViewModel = UsersViewModel()
        usersViewModel.userRepo = mockedUserRepo
        var users: MutableList<User> = mutableListOf()
        users.add(User("","","",""))
        users.add(User("","","",""))



        `when`(mockedUserRepo.getUsers(usersViewModel)).then { usersViewModel.onSuccess(users!!)}
        var countDownLatch= CountDownLatch(1)
        usersViewModel.getUsers()

        countDownLatch.await(10,TimeUnit.SECONDS)
      //  var tenp = usersViewModel.getUsers()
        println("Size is ${usersViewModel.users?.size}")
        Assert.assertNotNull(usersViewModel.users)


    }



}