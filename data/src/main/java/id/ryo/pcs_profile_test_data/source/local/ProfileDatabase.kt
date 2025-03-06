package id.ryo.pcs_profile_test_data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import javax.inject.Inject

@Database(
    entities = [ProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProfileDatabase: RoomDatabase() {

    abstract val profileDao: ProfileDao

    companion object {
        const val DATABASE_NAME = "pcs_profile_test.db"
    }

}
class DbTransactionProvider @Inject constructor(
    private val profileDb: ProfileDatabase
) {
    suspend fun <R> runWithTransaction(block: suspend () -> R) =
        profileDb.withTransaction(block)
}