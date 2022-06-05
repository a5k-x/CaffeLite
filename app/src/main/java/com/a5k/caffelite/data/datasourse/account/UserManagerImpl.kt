package com.a5k.caffelite.data.datasourse.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.util.Log
import com.a5k.caffelite.data.network.ApiCaffe
import com.a5k.caffelite.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManagerImpl @Inject constructor(
    private val context: Context,
    private val apiCaffe: ApiCaffe
) : UserManager {

    companion object {

        private const val TYPE_ACCOUNT = "com.coffelite.acc"
    }

    private val accountManager = AccountManager.get(context)
    private var account: Account? = null

    override suspend fun createAccount(user: User): Boolean {
        val name = user.login
        val password = user.password
        val token = apiCaffe.registration(name, password)
        if (token.token.isNotEmpty()){
            account = Account(user.login, TYPE_ACCOUNT)
            accountManager.setAuthToken(account, TYPE_ACCOUNT,  token.token)
        }
        return accountManager.addAccountExplicitly(account, user.password, null)
    }

    override suspend fun logout(user: User): Boolean {
        return try {
            val token = apiCaffe.authorization(user.login, user.password)
            val accountSystem = getSystemAccount(user)
            val password = accountManager.getPassword(accountSystem)
            if (password == user.password) {
                account = accountSystem
                accountManager.setAuthToken(account, TYPE_ACCOUNT,  token.token)
                val getTestToken = getAuthToken(account)
                Log.i("AAA", "get Test Token $getTestToken")
                true
            } else {
                account = null
                false
            }
        } catch (e: Throwable) {
            Log.e("userManager", e.message.toString())
            false
        }
    }

    override fun unLogin() {
        account = null
    }

    private fun getAuthToken(account: Account?): String? {
       if (account != null){
           val bundle = accountManager.getAuthToken(account, TYPE_ACCOUNT, null, null, null, null)
           return bundle.result.get(AccountManager.KEY_AUTHTOKEN).toString()
       } else return null
    }

    override fun updatePassword(user: User): Boolean {
        return try {
            val accountSystem = getSystemAccount(user)
            accountManager.clearPassword(accountSystem)
            accountManager.setPassword(accountSystem, user.password)
            true
        } catch (e: Throwable) {
            Log.e("userManager", e.message.toString())
            false
        }
    }

    private fun getSystemAccount(user: User): Account =
        accountManager.accounts.first { it.name == user.login && it.type == TYPE_ACCOUNT }
}