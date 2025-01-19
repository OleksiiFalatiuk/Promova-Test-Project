package com.example.common.filehelper

import java.io.File

object FileHelper {
    fun getFileByName(filesDir: File, directory: String, name: String): File? {
        return File(filesDir, directory).listFiles()?.find { it.nameWithoutExtension == name }
    }

    fun deleteFileByName(filesDir: File, directory: String, name: String): Boolean {
        return File(filesDir, directory).listFiles()?.find {
            it.nameWithoutExtension == name
        }?.delete() ?: false
    }
}