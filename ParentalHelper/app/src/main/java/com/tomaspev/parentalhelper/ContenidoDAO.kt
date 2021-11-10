package com.tomaspev.parentalhelper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContenidoDAO {
    @Query("SELECT * FROM contenido")
    fun getAll(): LiveData<List<Contenido>>
    @Query("SELECT * FROM contenido WHERE idcontenido = :id")
    fun get(id:Int) : LiveData<Contenido>
    @Insert
    fun insertAll(vararg contenido: Contenido)
    @Update
    fun update(contenido:Contenido)
    @Delete
    fun delete(contenido:Contenido)
}