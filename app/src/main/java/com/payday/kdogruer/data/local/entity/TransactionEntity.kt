package com.payday.kdogruer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.google.gson.annotations.SerializedName
import com.payday.kdogruer.data.local.RoomConstants
import com.payday.kdogruer.data.local.converter.TimestampConverter
import java.io.Serializable
import java.util.*


@Entity(tableName = RoomConstants.TRANSACTIONS_TABLE)
data class TransactionEntity constructor(@PrimaryKey @ColumnInfo(name = RoomConstants.TRANSACTION_ID)@SerializedName(RoomConstants.TRANSACTION_ID) var transactionId: String = "",
                                         @ColumnInfo(name = RoomConstants.TRANSACTION_ACCOUNT_ID)@SerializedName(RoomConstants.TRANSACTION_ACCOUNT_ID) var transactionAccountId: String? = "",
                                         @ColumnInfo(name = RoomConstants.TRANSACTION_AMOUNT)@SerializedName(RoomConstants.TRANSACTION_AMOUNT) var transactionAmount : String? = "",
                                         @ColumnInfo(name = RoomConstants.TRANSACTION_VENDOR)@SerializedName(RoomConstants.TRANSACTION_VENDOR) var transactionVendor : String? = "",
                                         @ColumnInfo(name = RoomConstants.TRANSACTION_CATEGORY)@SerializedName(RoomConstants.TRANSACTION_CATEGORY) var transactionCategory : String? = "",
                                         @ColumnInfo(name = RoomConstants.TRANSACTION_DATE)@SerializedName(RoomConstants.TRANSACTION_DATE)@TypeConverters(TimestampConverter::class)var transactionDate : Date? = null
)


