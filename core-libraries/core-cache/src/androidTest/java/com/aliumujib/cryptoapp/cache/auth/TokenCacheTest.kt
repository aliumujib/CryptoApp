package com.aliumujib.cryptoapp.cache.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.aliumujib.cryptoapp.cache.auth.impl.TokenCacheImpl
import com.aliumujib.cryptoapp.cache.auth.impl.TokenCacheImpl.Companion.AUTH_TOKEN_PREF_KEY
import com.aliumujib.cryptoapp.cache.auth.impl.TokenCacheImpl.Companion.TOKEN_STORE_NAME
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.google.common.truth.Truth
import java.io.File
import konveyor.base.randomBuild
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TokenCacheTest : CoroutineTest() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = TOKEN_STORE_NAME, scope = testCoroutineScope)
    private val dataStore by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.dataStore
    }

    lateinit var tokenCache: TokenCacheImpl

    @Before
    fun setup() {
        tokenCache = TokenCacheImpl(dataStore)
    }

    @Test
    fun test_saveAuthToken_reads_from_and_writes_to_dataStore() = coroutineScopedTest {
        val authToken: String = randomBuild()
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_PREF_KEY] = authToken
        }
        Truth.assertThat(authToken).isEqualTo(tokenCache.getAuthToken())
    }

    @After
    fun removeDatastore() {
        File(
            ApplicationProvider.getApplicationContext<Context>().filesDir,
            "datastore"
        ).deleteRecursively()
        testCoroutineScope.cancel()
    }
}
