package com.ashsample.androidconcepts.mvvm.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//@Query("DELETE FROM users WHERE user_id = :userId")
//https://developer.android.com/reference/android/arch/persistence/room/Query
//https://developer.android.com/reference/android/arch/persistence/room/Insert
@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(NoteEntity noteEntity);


    @Delete
    public void delete(NoteEntity noteEntity);

    @Query("Delete From note_table where description = :des")
    public void deleteByDescription(String des);

    @Update
    public void update(NoteEntity noteEntity);



    @Query("Delete From note_table ")
    public void deleteALL();

    @Query("SELECT * from note_table ORDER BY priority ASC")
    public List<NoteEntity> getAllNotes();

}
