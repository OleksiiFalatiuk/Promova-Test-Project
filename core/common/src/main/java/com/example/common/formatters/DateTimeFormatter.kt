package com.example.common.formatters

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object DateTimeFormatter {
    fun requireStringToLocalDateTime(value: String, zone: ZoneId = ZoneId.systemDefault()): LocalDateTime {
        val instant = Instant.parse(value)
        return instant.atZone(zone).toLocalDateTime()
    }
}