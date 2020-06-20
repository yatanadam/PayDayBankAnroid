package com.payday.kdogruer.data.remote.model.datamodels.requestmodels

class DateRangeRequestModel {
    var date1: Long? = null
    var date2: Long? = null

    constructor(date1: Long?, date2: Long?) {
        this.date1 = date1
        this.date2 = date2
    }
}
