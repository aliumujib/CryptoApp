package com.aliumujib.cryptoapp.cache.wallets.impl

import com.aliumujib.cryptoapp.cache.wallets.TestDummyData
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.cache.wallets.mappers.userModelToUserCacheModel
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserCacheImplTest : CoroutineTest() {

    @MockK
    lateinit var usersDao: ExchangeRatesDao

    @MockK
    lateinit var dataValidityStore: DataValidityStore

    private lateinit var sut: UserCacheImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sut = UserCacheImpl(usersDao, dataValidityStore)
    }

    @Test
    fun test_saveUsersCorrectlyCallsUsersDao() = coroutineScopedTest {
        val users = listOf(TestDummyData.userModel)
        sut.saveUsers(users)
        coVerify(exactly = 1) {
            usersDao.saveUsers(users.map {
                userModelToUserCacheModel(it)
            })
        }
    }

    @Test
    fun test_isValid_returns_true_when_cache_isNotEmpty_and_data_has_Not_expired() = coroutineScopedTest {
        stubUserCount(3)
        stubIsDataExpired(false)
        Truth.assertThat(true).isEqualTo(sut.isValid())
    }

    @Test
    fun test_isValid_returns_false_when_cache_isEmpty_or_data_has_expired() = coroutineScopedTest {
        stubUserCount(0)
        stubIsDataExpired(true)
        Truth.assertThat(false).isEqualTo(sut.isValid())

        stubUserCount(3)
        stubIsDataExpired(true)
        Truth.assertThat(false).isEqualTo(sut.isValid())

        stubUserCount(0)
        stubIsDataExpired(false)
        Truth.assertThat(false).isEqualTo(sut.isValid())
    }

    private fun stubUserCount(count: Int) {
        coEvery {
            usersDao.count()
        } returns count
    }

    private fun stubIsDataExpired(isExpired: Boolean) {
        coEvery {
            dataValidityStore.hasExpired()
        } returns isExpired
    }
}
