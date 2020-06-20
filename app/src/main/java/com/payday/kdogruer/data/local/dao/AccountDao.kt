package com.payday.kdogruer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.payday.kdogruer.data.local.entity.AccountEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity

@Dao
interface AccountDao {

    @get:Query("SELECT * FROM account ORDER BY id DESC")
    val accountList: LiveData<List<AccountEntity>>
    
    @Query("DELETE FROM account")
     fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(accountList: List<AccountEntity>)

    @Query("SELECT * FROM account WHERE id=:accountId")
    fun getAccountById(accountId: Int): LiveData<AccountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccounts(accountEntities: List<AccountEntity>)

    @Delete
    fun deleteAccounts(accountEntities : List<AccountEntity>)

    @Update
    fun updateAccount(accountEntity: AccountEntity)

    @Delete
    fun deleteAccount(accountEntity: AccountEntity)

}
