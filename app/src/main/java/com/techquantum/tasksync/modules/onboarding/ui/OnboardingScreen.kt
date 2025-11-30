package com.techquantum.tasksync.modules.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.preferences.UserPreferencesManager
import com.techquantum.tasksync.modules.dashboard.navigation.DashboardRoute
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: Int,
    val description: Int,
    val image: Int
)

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current
    val preferencesManager = UserPreferencesManager(context)
    
    val pages = listOf(
        OnboardingPage(
            title = R.string.onboarding_title_one,
            description = R.string.onboarding_message_one,
            image = R.drawable.ic_onboarding_one
        ),
        OnboardingPage(
            title = R.string.onboarding_title_two,
            description = R.string.onboarding_message_two,
            image = R.drawable.ic_onboarding_two
        ),
        OnboardingPage(
            title = R.string.onboarding_title_three,
            description = R.string.onboarding_message_three,
            image = R.drawable.ic_onboarding_third
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Skip button in top-right (only on first two pages)
            if (pagerState.currentPage < pages.size - 1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimension.Padding.LG),
                    contentAlignment = Alignment.TopEnd
                ) {
                    TextButton(onClick = {
                        preferencesManager.setOnboardingCompleted()
                        navController.navigate(DashboardRoute.Dashboard.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }) {
                        Text(
                            text = stringResource(R.string.onboarding_skip),
                            color = ThemeColors.Primary,
                            fontSize = AppDimension.FontSize.LG,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            } else {
                // Empty spacer to maintain layout consistency on last page
                Spacer(modifier = Modifier.height(AppDimension.IconSize.BUTTON_HEIGHT + AppDimension.Padding.LG))
            }

            // Horizontal Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageContent(
                    page = pages[page]
                )
            }

            // Page indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppDimension.Padding.XL),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    val isActive = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = AppDimension.Padding.XS)
                            .size(
                                width = if (isActive) 24.dp else 8.dp,
                                height = 8.dp
                            )
                            .clip(CircleShape)
                            .background(
                                if (isActive) ThemeColors.Primary else ThemeColors.TextDisabled
                            )
                    )
                }
            }

            // Next/Get Started button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppDimension.Padding.XL,
                        end = AppDimension.Padding.XL,
                        bottom = AppDimension.Padding.XXL
                    )
            ) {
                Button(
                    onClick = {
                        if (pagerState.currentPage == pages.size - 1) {
                            // Last page - navigate to dashboard
                            preferencesManager.setOnboardingCompleted()
                            navController.navigate(DashboardRoute.Dashboard.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        } else {
                            // Go to next page
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppDimension.IconSize.BUTTON_HEIGHT),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ThemeColors.Primary
                    ),
                    shape = RoundedCornerShape(AppDimension.Radius.MD)
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.size - 1) {
                            stringResource(R.string.onboarding_get_started)
                        } else {
                            stringResource(R.string.onboarding_next)
                        },
                        fontSize = AppDimension.FontSize.LG,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppDimension.Padding.XL),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(AppDimension.Radius.LG)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(AppDimension.Padding.XXL))

        // Title
        Text(
            text = stringResource(id = page.title),
            fontSize = AppDimension.FontSize.XXL,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppDimension.Padding.LG))

        // Description
        Text(
            text = stringResource(id = page.description),
            fontSize = AppDimension.FontSize.LG,
            color = ThemeColors.TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = AppDimension.FontSize.XL
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPageContentPreview() {
    OnboardingPageContent(
        page = OnboardingPage(
            title = R.string.onboarding_title_one,
            description = R.string.onboarding_message_one,
            image = R.drawable.ic_onboarding_one
        )
    )
}