package ca.qc.cstj.s05localdatasource.domain.models

data class User(var firstName:String,var lastName:String,var isOnline:Boolean) {
    fun fullName() : String {
        return "${this.firstName} ${this.lastName}"
    }
}