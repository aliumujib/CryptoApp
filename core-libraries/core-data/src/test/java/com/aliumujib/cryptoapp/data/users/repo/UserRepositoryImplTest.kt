package com.aliumujib.cryptoapp.data.users.repo

import com.aliumujib.cryptoapp.coremodels.UserModel
import com.aliumujib.cryptoapp.remote.datasource.FileDataSource
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryImplTest : CoroutineTest() {

    @MockK
    lateinit var userCache: UserCache

    @MockK
    lateinit var remoteDataSource: FileDataSource

    lateinit var sut: UserRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = UserRepositoryImpl(remoteDataSource, userCache)
    }

    @Test
    fun test_fetchUsers_does_not_fetch_if_cache_is_not_valid() = coroutineScopedTest {
        stubCacheValidity(isValid = false)
        sut.fetchUsers()
        coVerify(exactly = 0) {
            remoteDataSource.getUsers()
            userCache.saveUsers(any())
        }
    }

    @Test
    fun test_fetchUsers_fetches_if_cache_is_valid() = coroutineScopedTest {
        stubCacheValidity(isValid = true)
        stubUserRemoteData(randomBuild())
        sut.fetchUsers()
        coVerify(exactly = 1) {
            remoteDataSource.getUsers()
            userCache.saveUsers(any())
        }
    }

    @Test
    fun test_fetchUser_returns_correct_data_if_exists() = coroutineScopedTest {
        stubCacheValidity(isValid = false)
        val userModel: UserModel = randomBuild()
        stubUserCacheData(userModel)
        val result = sut.fetchUser(0)
        coVerify {
            userCache.fetchUserForId(any())
        }
        Truth.assertThat(result).isEqualTo(userModel)
    }

    private fun stubUserRemoteData(user: UserModel) {
        coEvery {
            remoteDataSource.getUsers()
        } returns listOf(user)
    }

    private fun stubUserCacheData(user: UserModel?) {
        coEvery {
            userCache.fetchUserForId(any())
        } returns user
    }

    private fun stubCacheValidity(isValid: Boolean) {
        coEvery {
            userCache.isValid()
        } returns isValid
    }
}
