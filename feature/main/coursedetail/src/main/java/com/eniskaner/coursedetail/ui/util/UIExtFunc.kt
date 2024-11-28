package com.eniskaner.coursedetail.ui.util

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

fun ConstraintLayout.updateProgressOverlay(progressOverlayId: Int, progressPercentage: Int) {
    val constraintSet = ConstraintSet()
    constraintSet.clone(this)

    constraintSet.constrainPercentWidth(
        progressOverlayId,
        progressPercentage / 100f
    )

    constraintSet.applyTo(this)
}

fun Long.formatDuration(): String {
    val minutes = (this / 1000) / 60
    val seconds = (this / 1000) % 60
    return String.format(buildString {
        append("%02d:%02d")
    }, minutes, seconds)
}

fun Long.calculateProgressPercentage(duration: Long): Int {
    return if (duration > 0) {
        (this * 100 / duration).toInt()
    } else {
        0
    }
}