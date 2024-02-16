package com.example.composeusersapp.ui

import com.example.composeusersapp.data.models.Picture
import com.example.composeusersapp.data.models.Registered
import com.example.composeusersapp.data.models.User
import org.junit.*
import org.mockito.kotlin.*

class UsersViewModelTest {

    val viewModel = UsersViewModel(mock())

    val newUsers = listOf(
        getUserMock("repeated"),
        getUserMock("test"),
        getUserMock("test01")
    )

    val storedUsers = listOf(getUserUIMock("repeated"))

    private fun getUserMock(email: String) = User(
        mock(),
        email,
        "",
        Registered("", 0),
        "",
        Picture("", "", "")
    )

    private fun getUserUIMock(email: String) = UserUI(
        "",
        email,
        "",
        "",
        "",
        ""
    )

    @Test
    fun `New users are not repeated and mapped correctly`() {
        val result = viewModel.filterRepeatedAndMapToUserUI(newUsers, storedUsers)
        Assert.assertEquals(2, result.size)
        Assert.assertNotEquals(newUsers.first().javaClass.name, result.first().javaClass.name)
    }


}