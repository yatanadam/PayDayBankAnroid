package com.payday.kdogruer.data.remote.model.datamodels.requestmodels

class AuthRequestModel {
    var password: String? = null
    var email: String? = null

    constructor(password: String?, email: String?) {
        this.password = password
        this.email = email
    }
}
