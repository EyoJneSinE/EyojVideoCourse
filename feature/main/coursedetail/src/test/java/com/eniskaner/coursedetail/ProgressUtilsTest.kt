package com.eniskaner.coursedetail

import com.eniskaner.coursedetail.ui.util.calculateProgressPercentage
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ProgressUtilsTest {

    @Test
    fun `calculateProgressPercentage returns correct percentage`() {
        val savedProgress = 30000L
        val duration = 60000L

        val result = savedProgress.calculateProgressPercentage(duration)

        assertThat(result).isEqualTo(50)
    }

    @Test
    fun `calculateProgressPercentage returns 0 when duration is zero`() {
        val savedProgress = 30000L
        val duration = 0L

        val result = savedProgress.calculateProgressPercentage(duration)

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `calculateProgressPercentage handles edge cases`() {
        val savedProgress = 0L
        val duration = 60000L

        val result = savedProgress.calculateProgressPercentage(duration)

        assertThat(result).isEqualTo(0)
    }
}