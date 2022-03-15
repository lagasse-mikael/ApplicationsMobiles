package ca.qc.cstj.s05localdatasource.data.repositories

import androidx.room.*
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactRepository {

    @Query("SELECT * FROM contacts")
    fun retrieveAll(): Flow<List<Contact>>

    @Insert
    fun create(contacts: List<Contact>)

    @Insert
    suspend fun create(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

}