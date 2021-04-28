package pierrick.merielbussy.prisonfantasy

class ConversionIntToClassName {
    fun convertInt(activityNumber: Int) {
        when (activityNumber) {
            0 -> activityName = MainActivity::class.java
            1 -> activityName = CreateIdentityActivity::class.java
            2 -> activityName = ConfirmIdentityActivity::class.java
            3 -> activityName = StoryStartActivity::class.java
            4 -> activityName = FirstCallActivity::class.java
            5 -> activityName = PostmanActivity::class.java
        }
    }
}