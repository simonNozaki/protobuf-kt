import com.google.gson.Gson
import java.util.*

/**
 * Gsonシングルトン
 */
object GsonSingleton {

    private val _g = Gson()

    fun <T> toJson(src: T): String {
        return _g.toJson(src)
    }
}

fun main(args: Array<String>) {
    // facebook, FY2021Q2のスナップショットから. 数値はアジアのみ。
    val facebook = Base.SnsSnapshotAchievement.newBuilder()
        .setName("Twitter")
        .setCountryOfOrigin("US")
        .setMonthlyActiveUser(1260000000)
        .setArpu(413)
        .setLaunchedAt("2004")
        .setCreatedAt(Date().toString())
        .setUpdatedAt(Date().toString())

    val serialized = GsonSingleton.toJson(facebook)

    println("ネイティブ: $facebook")

    println("シリアライズ後: $serialized")
}
