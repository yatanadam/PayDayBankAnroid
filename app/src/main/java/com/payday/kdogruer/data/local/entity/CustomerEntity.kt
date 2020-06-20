package com.payday.kdogruer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

import com.payday.kdogruer.data.local.RoomConstants


@Entity(tableName = RoomConstants.CUSTOMER_TABLE)
data class CustomerEntity constructor(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = RoomConstants.CUSTOMER_ID)@SerializedName(RoomConstants.CUSTOMER_ID) var customerId: Int,
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_NAME)@SerializedName(RoomConstants.CUSTOMER_NAME) var customerName: String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_LAST_NAME)@SerializedName(RoomConstants.CUSTOMER_LAST_NAME) var customerLastName : String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_EMAIL)@SerializedName(RoomConstants.CUSTOMER_EMAIL) var customerEmail : String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_GENDER)@SerializedName(RoomConstants.CUSTOMER_GENDER) var customerGender : String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_PASS)@SerializedName(RoomConstants.CUSTOMER_PASS) var customerPass : String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_DOB)@SerializedName(RoomConstants.CUSTOMER_DOB) var customerDob : String? = "",
                                      @ColumnInfo(name = RoomConstants.CUSTOMER_PHONE)@SerializedName(RoomConstants.CUSTOMER_PHONE) var customerPhone : String? = ""
)

