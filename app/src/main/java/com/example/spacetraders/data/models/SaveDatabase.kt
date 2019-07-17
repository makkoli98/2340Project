package com.example.spacetraders.data.models

import android.content.Context

import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.entity.Universe
import com.google.gson.Gson

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * The Save Database class is used to save the progress of any characters.
 * It should be instantiated when accessing a save's data or creating a new save.
 *
 * When creating a new save, the id must be saved in order to later save to the right saveFile.
 */
class SaveDatabase
/**
 *
 * @param applicationContext use getApplicationContext()
 */
(applicationContext: Context) {
    var numSaves: Int = 0
        private set
    private val gson: Gson
    private var path: File? = null

    init {
        path = applicationContext.filesDir

        val savesFolder = File(path, "saves")
        savesFolder.mkdir()

        path = savesFolder

        numSaves = savesFolder.listFiles().size

        gson = Gson()
    }

    fun createNewSave(): Int {
        val saveDir = File(path, "save$numSaves")
        if (saveDir.mkdir()) {
            val uni = File(path, "/save$numSaves/universe.json")
            val cha = File(path, "/save$numSaves/character.json")
            try {
                uni.createNewFile()
                cha.createNewFile()
            } catch (ioe: IOException) {
                System.err.println("Error creating universe or character json files")
            }

            numSaves++
            return numSaves - 1
        } else {
            System.err.println("Error creating specific save directory")
        }
        return -1
    }

    fun saveUniverse(universe: Universe, id: Int) {
        if (!validId(id)) {
            throw IllegalArgumentException("Attempted to save to a non-existent save file")
        }

        val contents = gson.toJson(universe)
        try {
            val uniJson = File(path, "/save$id/universe.json")
            val writer = FileOutputStream(uniJson)
            writer.write(contents.toByteArray())
            writer.close()
        } catch (e: Exception) {
            System.err.println(e)
        }

    }

    fun saveCharacter(character: Character, id: Int) {
        if (!validId(id)) {
            throw IllegalArgumentException("Attempted to save to a non-existent save file")
        }

        val contents = gson.toJson(character)
        try {
            val chaJson = File(path, "/save$id/character.json")
            chaJson.createNewFile()
            val writer = FileOutputStream(chaJson)
            writer.write(contents.toByteArray())
            writer.close()
        } catch (e: Exception) {
            System.err.println(e)
        }

    }

    fun getUniverse(id: Int): Universe? {
        if (!validId(id)) {
            throw IllegalArgumentException("Attempted to get a non-existent save file")
        }
        try {
            val uniJson = File(path, "/save$id/universe.json")
            val data = ByteArray(uniJson.length().toInt())
            val reader = FileInputStream(uniJson)
            reader.read(data)
            val contents = String(data)
            reader.close()
            if (contents != "") {
                return gson.fromJson<Universe>(contents, Universe::class.java!!)
            }
        } catch (e: Exception) {
            System.err.println(e)
        }

        return null
    }

    fun getCharacter(id: Int): Character? {
        if (!validId(id)) {
            throw IllegalArgumentException("Attempted to get a non-existent save file")
        }
        try {
            val uniJson = File(path, "/save$id/character.json")
            val data = ByteArray(uniJson.length().toInt())
            val reader = FileInputStream(uniJson)
            reader.read(data)
            val contents = String(data)
            reader.close()
            if (contents != "") {
                return gson.fromJson<Character>(contents, Character::class.java!!)
            }
        } catch (e: Exception) {
            System.err.println(e)
        }

        return null
    }

    fun validId(id: Int): Boolean {
        return -1 < id && id < numSaves
    }

    /**
     * DANGEROUS
     */
    fun deleteAllFiles() {
        try {
            deleteFile(path!!)
        } catch (e: Exception) {
            System.err.println(e)
            System.err.println("Error deleting file in delete all files")
        }

    }

    private fun deleteFile(file: File) {
        if (file.isDirectory) {
            for (f in file.listFiles()) {
                deleteFile(f)
            }
        }
        file.delete()
    }

}
