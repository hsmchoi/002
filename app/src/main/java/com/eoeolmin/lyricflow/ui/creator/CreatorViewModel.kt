package com.eoeolmin.lyricflow.ui.creator

import androidx.lifecycle.ViewModel
import com.eoeolmin.lyricflow.data.model.TimedLine

class CreatorViewModel : ViewModel() {

    private var masterStartTime: Long = 0L
    private var firstLyricOffset: Long = -1L
    // (가사, 기록된 실제 시간) 쌍을 저장
    private val recordedLines = mutableListOf<Pair<String, Long>>()

    fun startMasterTimer() {
        masterStartTime = System.currentTimeMillis()
        firstLyricOffset = -1L
        recordedLines.clear()
        println("Master timer started.")
    }

    fun recordFirstLyricStartTime() {
        // 한 번만 기록되도록 방지
        if (firstLyricOffset == -1L && masterStartTime > 0L) {
            firstLyricOffset = System.currentTimeMillis() - masterStartTime
            println("First lyric start offset recorded: $firstLyricOffset ms")
        }
    }

    fun recordNextLine(lyric: String) {
        if (masterStartTime > 0L) {
            val timestamp = System.currentTimeMillis() - masterStartTime
            recordedLines.add(lyric to timestamp)
            println("Recorded line '$lyric' at $timestamp ms")
        }
    }

    fun getNormalizedLines(): List<TimedLine>? {
        if (firstLyricOffset == -1L) {
            println("Error: First lyric start time was not recorded.")
            return null // 첫 소절 시작이 기록되지 않으면 정규화 불가
        }

        val normalizedLines = recordedLines.map { (lyric, timestamp) ->
            val normalizedTime = timestamp - firstLyricOffset
            TimedLine(text = lyric, endTime = normalizedTime)
        }

        println("--- Normalization Complete ---")
        normalizedLines.forEach { println("Normalized: ${it.text} -> ${it.endTime} ms") }
        println("-----------------------------")

        // TODO: 여기서 Repository를 통해 Song 객체를 최종 저장해야 함
        return normalizedLines
    }
}