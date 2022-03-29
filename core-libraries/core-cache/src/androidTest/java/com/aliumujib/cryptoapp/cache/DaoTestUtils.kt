package com.aliumujib.cryptoapp.cache

import com.aliumujib.cryptoapp.cache.wallets.models.UserCacheModel
import konveyor.base.randomBuild

object DaoTestUtils {

    val userCacheModel1: UserCacheModel = randomBuild()

    private val userCacheModel2: UserCacheModel = randomBuild()

    private val userCacheModel3: UserCacheModel = randomBuild()

    private val userCacheModel4: UserCacheModel = randomBuild()

    val dummyList = listOf(userCacheModel1, userCacheModel2, userCacheModel3, userCacheModel4)
}
