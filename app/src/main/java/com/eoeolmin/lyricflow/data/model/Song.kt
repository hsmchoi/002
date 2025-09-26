package com.eoeolmin.lyricflow.data.model

data class TimedLine(
    val text: String,
    val endTime: Long // 해당 라인이 끝나는 시간 (정규화된 시간)
)

data class Song(
    val id: Long,
    val title: String,
    val firstLyricStartTime: Long, // '시작 오프셋' 시간 (ms)
    val rawTimedLines: List<TimedLine> // 정규화된 최종 데이터
)