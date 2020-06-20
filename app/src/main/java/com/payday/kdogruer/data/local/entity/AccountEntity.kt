package com.payday.kdogruer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName
import com.payday.kdogruer.data.local.RoomConstants
import java.io.Serializable


@Entity(tableName = RoomConstants.ACCOUNT_TABLE)
data class AccountEntity constructor(@PrimaryKey @ColumnInfo(name = RoomConstants.ACCOUNT_ID)@SerializedName(RoomConstants.ACCOUNT_ID) var accountId: String = "",
                                      @ColumnInfo(name = RoomConstants.ACCOUNT_CUSTOMER_ID)@SerializedName(RoomConstants.ACCOUNT_CUSTOMER_ID) var accountCustomerId: String? = "",
                                      @ColumnInfo(name = RoomConstants.ACCOUNT_IBAN)@SerializedName(RoomConstants.ACCOUNT_IBAN) var accountIBAN : String? = "",
                                      @ColumnInfo(name = RoomConstants.ACCOUNT_TYPE)@SerializedName(RoomConstants.ACCOUNT_TYPE) var accountType : String? = "",
                                      @ColumnInfo(name = RoomConstants.ACCOUNT_DATE_CREATED)@SerializedName(RoomConstants.ACCOUNT_DATE_CREATED) var accountDateCreated : String? = "",
                                      @ColumnInfo(name = RoomConstants.ACCOUNT_ACTIVE)@SerializedName(RoomConstants.ACCOUNT_ACTIVE) var accountActive : String? = ""
)


