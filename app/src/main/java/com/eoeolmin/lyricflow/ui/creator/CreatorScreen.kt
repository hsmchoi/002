package com.eoeolmin.lyricflow.ui.creator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eoeolmin.lyricflow.ui.theme.DarkBackground
import com.eoeolmin.lyricflow.ui.theme.PrimaryColor
import com.eoeolmin.lyricflow.ui.theme.SurfaceColor
import com.eoeolmin.lyricflow.ui.theme.TextColor
import com.eoeolmin.lyricflow.ui.theme.TextSecondaryColor

@Composable
fun CreatorScreen(
    // ViewModel을 Composable 함수 내에서 직접 가져옵니다.
    viewModel: CreatorViewModel = viewModel()
) {
    // UI가 ViewModel의 상태를 관찰하도록 나중에 확장할 수 있습니다.
    // var uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 임시로 UI 상태를 Composable 내에서 관리합니다.
    var lyrics by remember { mutableStateOf("") }
    val isReady = lyrics.isNotBlank()

    Scaffold(
        containerColor = DarkBackground,
        // TopAppBar 등 다른 UI 요소들은 나중에 추가할 수 있습니다.
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = lyrics,
                onValueChange = { lyrics = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = { Text("이곳에 가사를 작성하세요...") },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceColor,
                    unfocusedContainerColor = SurfaceColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = PrimaryColor,
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    focusedPlaceholderColor = TextSecondaryColor,
                    unfocusedPlaceholderColor = TextSecondaryColor,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // TODO: 다음 단계로 넘어가는 로직과 ViewModel 함수 호출
                    viewModel.startMasterTimer() // 예시: 다음 버튼 누르면 타이머 시작
                },
                enabled = isReady,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White,
                    disabledContainerColor = PrimaryColor.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next"
                )
            }
        }
    }
}