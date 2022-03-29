package com.aliumujib.cryptoapp.cache.wallets

import com.aliumujib.cryptoapp.cache.wallets.models.AddressCacheModel
import com.aliumujib.cryptoapp.cache.wallets.models.AvatarCacheModel
import com.aliumujib.cryptoapp.cache.wallets.models.CompanyCacheModel
import com.aliumujib.cryptoapp.cache.wallets.models.GeoCacheModel
import com.aliumujib.cryptoapp.cache.wallets.models.UserCacheModel
import com.aliumujib.cryptoapp.coremodels.AddressModel
import com.aliumujib.cryptoapp.coremodels.AvatarModel
import com.aliumujib.cryptoapp.coremodels.CompanyModel
import com.aliumujib.cryptoapp.coremodels.GeoModel
import com.aliumujib.cryptoapp.coremodels.UserModel

object TestDummyData {
    // cache models
    val geoCacheModel = GeoCacheModel("123", "123")

    val addressCacheModel = AddressCacheModel(
        "Lagos", geoCacheModel, "street",
        "1234", "1234"
    )

    val avatarCacheModel = AvatarCacheModel(
        "http://unsplash.com1", "http://unsplash.com2", "http://unsplash.com3"
    )

    val companyCacheModel = CompanyCacheModel("bs", "we win!", "ABC company")

    val userCacheModel = UserCacheModel(
        addressCacheModel, avatarCacheModel, companyCacheModel, "mujeeb@mujeeb.com",
        2, "Mujeeb Aliu", "08192397009", "aliumujib", "https://aliumujib.com"
    )

    // models
    val geoModel = GeoModel("123", "123")

    val addressModel = AddressModel(
        "Lagos", geoModel, "street",
        "1234", "1234"
    )

    val avatarModel = AvatarModel("http://unsplash.com1", "http://unsplash.com2", "http://unsplash.com3"
    )

    val companyModel = CompanyModel("bs", "we win!", "ABC company")

    val userModel = UserModel(
        addressModel, avatarModel, companyModel, "mujeeb@mujeeb.com",
        2, "Mujeeb Aliu", "08192397009", "aliumujib", "https://aliumujib.com"
    )
}
