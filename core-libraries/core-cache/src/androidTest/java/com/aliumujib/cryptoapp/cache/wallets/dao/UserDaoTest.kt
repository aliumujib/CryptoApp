package com.aliumujib.cryptoapp.cache.wallets.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aliumujib.cryptoapp.cache.DaoTestUtils
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.cache.room.CryptoDatabase
import com.aliumujib.cryptoapp.cache.wallets.models.UserCacheModel
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDaoTest : CoroutineTest() {
    private lateinit var usersDao: ExchangeRatesDao
    private lateinit var db: CryptoDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
        usersDao = db.usersDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun writeUserAndReadUser() = coroutineScopedTest {
        val user: UserCacheModel = DaoTestUtils.userCacheModel1
        usersDao.saveUsers(listOf(user))
        val userDbResponse: UserCacheModel? = usersDao.getUserWithId(user.id)
        Truth.assertThat(userDbResponse).isEqualTo(user)
    }
}
