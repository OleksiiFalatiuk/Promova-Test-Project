package com.example.common.filehelper

import java.io.File

object FileHelper {
    fun getFileByName(filesDir: File, directory: String, name: String): File? {
        val files = File(filesDir, directory).listFiles()
        if (files != null) {
            for (file in files) {
                if (file.nameWithoutExtension == name) {
                    return file
                }
            }
        }
        return null
    }

    fun deleteFileByName(filesDir: File, directory: String, name: String): Boolean {
        val files = File(filesDir, directory).listFiles()
        if (files != null) {
            for (file in files) {
                if (file.nameWithoutExtension == name) {
                    return file.delete()
                }
            }
        }
        return false
    }
}