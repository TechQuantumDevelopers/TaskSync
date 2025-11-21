package com.techquantum.tasksync.modules.splash.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.ui.theme.ThemeColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PulsingDots() {
    val animatable = remember { List(5) { Animatable(0.6f) } }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // loop forever with a staggered start for each dot
        while (true) {
            for (i in animatable.indices) {
                scope.launch {
                    animatable[i].animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                    )
                    animatable[i].animateTo(
                        targetValue = 0.6f,
                        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                    )
                }
                delay(120)
            }
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        animatable.forEachIndexed { index, anim ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(anim.value)
                    .clip(CircleShape)
                    .background(ThemeColors.Primary)
            )

            if (index != animatable.lastIndex) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}