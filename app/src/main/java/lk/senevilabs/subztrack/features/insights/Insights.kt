package lk.senevilabs.subztrack.features.insights

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Design System Tokens (Kinetic Minimalist) ---
val SurfaceBase = Color(0xFFF5F6F7)
val PrimaryAccent = Color(0xFF004BE2)
val OnSurface = Color(0xFF2C2F30)
val SurfaceContainerLowest = Color(0xFFFFFFFF)
val SurfaceContainerLow = Color(0xFFEFF1F2)
val OnSurfaceVariant = Color(0xFF595C5D)
val PrimaryContainer = Color(0xFFD9E2FF) // Lighter blue for backgrounds
val ErrorContainer = Color(0xFFFFDAD6)
val ErrorColor = Color(0xFFBA1A1A)

// Custom chart colors
val ColorEntertainment = Color(0xFF3F51B5)
val ColorSoftware = Color(0xFF9C27B0)
val ColorUtilities = Color(0xFF00BCD4)
val ColorMisc = Color(0xFFE91E63)

@Composable
fun Insights() {
    Scaffold(
        containerColor = SurfaceBase
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SurfaceBase),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item { InsightsTopBar() }
            item { HeaderSection() }
            item { MonthlySpendHistoryCard() }
            item { OptimizationCard() }
            item { SummaryBanner() }
            item { SpendBreakdownCard() }
            item { RecentInsightsSection() }
        }
    }
}

@Composable
fun InsightsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = OnSurface
        )
        Text(
            text = "Insights",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurface
        )
        // Profile Placeholder
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(OnSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = SurfaceContainerLowest,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Spending Insights",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = OnSurface,
            letterSpacing = (-1).sp,
            lineHeight = 44.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Analyze your subscriptions and optimize your budget.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = OnSurfaceVariant
        )
    }
}

@Composable
fun MonthlySpendHistoryCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SurfaceContainerLowest)
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Monthly Spend\nHistory",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface,
                    lineHeight = 22.sp
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Last 6\nMonths",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp)) // Empty space for static chart representation

            // Tooltip over MAR
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(PrimaryAccent)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$428.50",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // X-Axis labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("OCT", "NOV", "DEC", "JAN", "FEB").forEach { month ->
                    Text(
                        text = month,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceVariant
                    )
                }
                Text(
                    text = "MAR",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    color = PrimaryAccent
                )
            }
        }
    }
}

@Composable
fun OptimizationCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SurfaceContainerLowest)
            .padding(24.dp)
    ) {
        Column {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info, // Using Info as a placeholder for a light bulb
                    contentDescription = "Optimization",
                    tint = PrimaryAccent
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Top Optimization Opportunity",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("Consider switching ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = OnSurface)) {
                        append("Adobe Creative Cloud")
                    }
                    append("\nto an Annual Plan to save ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = PrimaryAccent)) {
                        append("$120.00/year")
                    }
                    append(".")
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurfaceVariant,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Learn More",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryAccent
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Learn More",
                    tint = PrimaryAccent,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun SummaryBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(PrimaryContainer)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "TOTAL MONTHLY",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryAccent,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$428.50",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = OnSurface
                )
            }

            // Divider
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(PrimaryAccent.copy(alpha = 0.2f))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
            ) {
                Text(
                    text = "SUBSCRIPTIONS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryAccent,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "14",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = OnSurface
                )
            }
        }
    }
}

@Composable
fun SpendBreakdownCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SurfaceContainerLowest)
            .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Donut Chart Container
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Background shape matching mockup (Rounded square look)
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 24.dp.toPx()
                    val size = size.width - strokeWidth
                    
                    // Background track (optional, helps smooth things out)
                    drawArc(
                        color = SurfaceContainerLow,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Entertainment 45% (ColorEntertainment)
                    drawArc(
                        color = ColorEntertainment,
                        startAngle = -90f,
                        sweepAngle = 360f * 0.45f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                    
                    // Software 30% (ColorSoftware)
                    drawArc(
                        color = ColorSoftware,
                        startAngle = -90f + (360f * 0.45f),
                        sweepAngle = 360f * 0.30f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                    
                    // Utilities 15% (ColorUtilities)
                    drawArc(
                        color = ColorUtilities,
                        startAngle = -90f + (360f * 0.75f),
                        sweepAngle = 360f * 0.15f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                    
                    // Misc 10% (ColorMisc)
                    drawArc(
                        color = ColorMisc,
                        startAngle = -90f + (360f * 0.90f),
                        sweepAngle = 360f * 0.10f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }
                
                // Center Text
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "MARCH",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceVariant,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "$428",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = OnSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Spend Breakdown by Category",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Category List
            BreakdownItem("Entertainment", "45%", ColorEntertainment)
            Spacer(modifier = Modifier.height(8.dp))
            BreakdownItem("Software", "30%", ColorSoftware)
            Spacer(modifier = Modifier.height(8.dp))
            BreakdownItem("Utilities", "15%", ColorUtilities)
            Spacer(modifier = Modifier.height(8.dp))
            BreakdownItem("Misc", "10%", ColorMisc)
        }
    }
}

@Composable
fun BreakdownItem(name: String, percentage: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )
        }
        Text(
            text = percentage,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurface
        )
    }
}

@Composable
fun RecentInsightsSection() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Insights",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface
            )
            Text(
                text = "VIEW ALL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryAccent,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Insight 1
        InsightListItem(
            title = "Netflix Price Increase",
            description = "Your monthly plan increased by $2.00 starting Mar 15.",
            iconColor = ErrorColor,
            bgColor = ErrorContainer,
            icon = Icons.Default.Warning // Placeholder for trend arrow
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Insight 2
        InsightListItem(
            title = "Subscription Cancelled",
            description = "Hulu Basic was successfully removed.\nSavings: $7.99/mo.",
            iconColor = PrimaryAccent,
            bgColor = PrimaryContainer,
            icon = Icons.Default.CheckCircle // Placeholder for check
        )
    }
}

@Composable
fun InsightListItem(title: String, description: String, iconColor: Color, bgColor: Color, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLowest)
            .clickable { /* action */ }
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Icon Box
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(bgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Forward",
                tint = OnSurfaceVariant,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInsights() {
    Insights()
}