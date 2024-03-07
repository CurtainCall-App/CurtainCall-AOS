package com.cmc.curtaincall.common.designsystem.component.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Composable
fun CurtainCallSelectDialog(
    title: String,
    description: String? = null,
    cancelButtonText: String,
    actionButtonText: String,
    onAction: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        BaseDialog(
            type = DialogType.Select(
                title = title,
                description = description,
                cancelButtonText = cancelButtonText,
                actionButtonText = actionButtonText,
                action = onAction,
                cancel = onCancel
            )
        )
    }
}

@Composable
fun CurtainCallConfirmDialog(
    title: String,
    description: String? = null,
    actionText: String,
    onAction: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        BaseDialog(
            type = DialogType.Confirm(
                title = title,
                description = description,
                actionText = actionText,
                action = onAction
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectDialogPreview() {
    CurtainCallTheme {
        CurtainCallSelectDialog(
            title = "'커튼콜'이 사용자의 사진에\n접근하려고 해요",
            description = "앱에서 사진을 업로드하려면\n사진 라이브러리에 접근할 수 있어야 해요:)",
            cancelButtonText = "거부",
            actionButtonText = "허용"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmDialogPreview() {
    CurtainCallTheme {
        CurtainCallConfirmDialog(
            title = "이미 공연 리뷰를 등록했어요!",
            actionText = "확인"
        )
    }
}
