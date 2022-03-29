package com.aliumujib.cryptoapp.cache.wallets.impl

//class DataValidityStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DataValidityStore {
//
//    override suspend fun saveLastRefresh(lastRefresh: Long) {
//        dataStore.edit {
//            it[LAST_REFRESH_PREF_KEY] = lastRefresh
//        }
//    }
//
//    override suspend fun hasExpired(): Boolean {
//        val lastRefresh = dataStore.data.map {
//            it[LAST_REFRESH_PREF_KEY]
//        }.firstOrNull()
//
//        return if (lastRefresh == null) {
//            true
//        } else {
//            val currentTime = System.currentTimeMillis()
//            (currentTime - lastRefresh) > 10800000 // if hasn't refreshed in 3 hrs
//        }
//    }
//
//    companion object {
//        private const val LAST_REFRESH_KEY = "LAST_REFRESH_KEY"
//        val LAST_REFRESH_PREF_KEY = longPreferencesKey(LAST_REFRESH_KEY)
//    }
//}
