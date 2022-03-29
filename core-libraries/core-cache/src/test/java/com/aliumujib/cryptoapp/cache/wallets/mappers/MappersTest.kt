package com.aliumujib.cryptoapp.cache.wallets.mappers

import com.aliumujib.cryptoapp.cache.wallets.TestDummyData
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
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MappersTest {

    @Test
    fun test_userModelToUserCacheModel_DoesCorrectMapping() = runBlockingTest {
        val model: UserModel = TestDummyData.userModel
        val expectedCacheModel: UserCacheModel = TestDummyData.userCacheModel
        val actual = userModelToUserCacheModel(model)
        assertThat(actual).isEqualTo(expectedCacheModel)
    }

    @Test
    fun test_addressModelToAddressCacheModel_DoesCorrectMapping() = runBlockingTest {
        val model: AddressModel = TestDummyData.addressModel
        val expectedCacheModel: AddressCacheModel = TestDummyData.addressCacheModel
        val actual = addressModelToAddressCacheModel(model)
        assertThat(actual).isEqualTo(expectedCacheModel)
    }

    @Test
    fun test_avatarModelToAvatarCacheModel_DoesCorrectMapping() = runBlockingTest {
        val model: AvatarModel = TestDummyData.avatarModel
        val expectedCacheModel: AvatarCacheModel = TestDummyData.avatarCacheModel
        val actual = avatarModelToAvatarCacheModel(model)
        assertThat(actual).isEqualTo(expectedCacheModel)
    }

    @Test
    fun test_companyModelToCompanyCacheModel_DoesCorrectMapping() = runBlockingTest {
        val model: CompanyModel = TestDummyData.companyModel
        val expectedCacheModel: CompanyCacheModel = TestDummyData.companyCacheModel
        val actual = companyModelToCompanyCacheModel(model)
        assertThat(actual).isEqualTo(expectedCacheModel)
    }

    @Test
    fun test_geoModelToGeoCacheModel_DoesCorrectMapping() = runBlockingTest {
        val model: GeoModel = TestDummyData.geoModel
        val expectedCacheModel: GeoCacheModel = TestDummyData.geoCacheModel
        val actual = geoModelToGeoCacheModel(model)
        assertThat(actual).isEqualTo(expectedCacheModel)
    }

    @Test
    fun test_userCacheModelToUserModel_DoesCorrectMapping() = runBlockingTest {
        val cacheModel: UserCacheModel = TestDummyData.userCacheModel
        val expectedModel: UserModel = TestDummyData.userModel
        val actual = userCacheModelToUserModel(cacheModel)
        assertThat(actual).isEqualTo(expectedModel)
    }

    @Test
    fun test_addressCacheModelToAddressModel_DoesCorrectMapping() = runBlockingTest {
        val cacheModel: AddressCacheModel = TestDummyData.addressCacheModel
        val expectedModel: AddressModel = TestDummyData.addressModel
        val actual = addressCacheModelToAddressModel(cacheModel)
        assertThat(actual).isEqualTo(expectedModel)
    }

    @Test
    fun test_avatarCacheModelToAvatarModel_DoesCorrectMapping() = runBlockingTest {
        val cacheModel: AvatarCacheModel = TestDummyData.avatarCacheModel
        val expectedModel: AvatarModel = TestDummyData.avatarModel
        val actual = avatarCacheModelToAvatarModel(cacheModel)
        assertThat(actual).isEqualTo(expectedModel)
    }

    @Test
    fun test_companyCacheModelToCompanyModel_DoesCorrectMapping() = runBlockingTest {
        val cacheModel: CompanyCacheModel = TestDummyData.companyCacheModel
        val expectedModel: CompanyModel = TestDummyData.companyModel
        val actual = companyCacheModelToCompanyModel(cacheModel)
        assertThat(actual).isEqualTo(expectedModel)
    }

    @Test
    fun test_geoCacheModelToGeoModel_DoesCorrectMapping() = runBlockingTest {
        val cacheModel: GeoCacheModel = TestDummyData.geoCacheModel
        val expectedModel: GeoModel = TestDummyData.geoModel
        val actual = geoCacheModelToGeoModel(cacheModel)
        assertThat(actual).isEqualTo(expectedModel)
    }
}
