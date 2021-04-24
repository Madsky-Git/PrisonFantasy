package pierrick.merielbussy.prisonfantasy

class ConversionIntToClassName {
    fun convertInt(activityNumber: Int) {
        when (activityNumber) {
            0 -> activityName = MainActivity::class.java
            1 -> activityName = CreateIdentityActivity::class.java
            2 -> activityName = ConfirmIdentityActivity::class.java
        }
    }
}