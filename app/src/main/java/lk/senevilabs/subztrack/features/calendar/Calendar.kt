package lk.senevilabs.subztrack.features.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
val OutlineVariant = Color(0xFFABADAE).copy(alpha = 0.5f)
val AmbientShadow = Color(0xFF2C2F30).copy(alpha = 0.06f)

// Calendar Data Models
data class CalendarDay(
    val dayOfMonth: Int,
    val isCurrentMonth: Boolean,
    val hasRenewal: Boolean = false,
    val isSelected: Boolean = false
)

// Static representation of October 2023 for UI review
val october2023Days = listOf(
    // Week 1
    CalendarDay(25, false), CalendarDay(26, false), CalendarDay(27, false), CalendarDay(28, false), CalendarDay(29, false), CalendarDay(30, false), CalendarDay(1, true),
    // Week 2
    CalendarDay(2, true), CalendarDay(3, true), CalendarDay(4, true), CalendarDay(5, true, hasRenewal = true), CalendarDay(6, true), CalendarDay(7, true), CalendarDay(8, true),
    // Week 3
    CalendarDay(9, true), CalendarDay(10, true), CalendarDay(11, true), CalendarDay(12, true, isSelected = true), CalendarDay(13, true), CalendarDay(14, true), CalendarDay(15, true),
    // Week 4
    CalendarDay(16, true), CalendarDay(17, true), CalendarDay(18, true), CalendarDay(19, true), CalendarDay(20, true), CalendarDay(21, true), CalendarDay(22, true),
    // Week 5
    CalendarDay(23, true, hasRenewal = true), CalendarDay(24, true), CalendarDay(25, true), CalendarDay(26, true), CalendarDay(27, true), CalendarDay(28, true), CalendarDay(29, true),
    // Week 6
    CalendarDay(30, true), CalendarDay(31, true), CalendarDay(1, false), CalendarDay(2, false), CalendarDay(3, false), CalendarDay(4, false), CalendarDay(5, false)
)

data class ScheduledSub(
    val name: String,
    val plan: String,
    val price: Double,
    val status: String,
    val color: Color,
    val isConfirmed: Boolean = false
)

@Composable
fun Calendar() {
    Scaffold(
        containerColor = SurfaceBase
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SurfaceBase),
            contentPadding = PaddingValues(bottom = 80.dp) // Extra padding to avoid bottom nav bar cutoffs
        ) {
            item { CalendarTopBar() }
            item { HeaderSection() }
            item { CalendarGridView() }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item { ScheduleSection() }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item { UpcomingSection() }
        }
    }
}

@Composable
fun CalendarTopBar() {
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
            text = "Subscriptions",
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
            text = "October 2023",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = OnSurface,
            letterSpacing = (-1).sp,
            lineHeight = 44.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "3 renewals scheduled this month",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = OnSurfaceVariant
        )
    }
}

@Composable
fun CalendarGridView() {
    val daysOfWeek = listOf("MO", "TU", "WE", "TH", "FR", "SA", "SU")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SurfaceContainerLowest)
            .padding(24.dp)
    ) {
        Column {
            // Days of the week header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                daysOfWeek.forEach { day ->
                    Text(
                        text = day,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceVariant,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Calendar Grid
            october2023Days.chunked(7).forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    week.forEach { day ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f) // Ensure square cells for easy centering
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (day.isCurrentMonth || day.dayOfMonth in 25..31) {
                                // Background & Shadow for selected day
                                val modifier = if (day.isSelected) {
                                    Modifier
                                        .fillMaxSize()
                                        .shadow(
                                            elevation = 16.dp,
                                            shape = CircleShape,
                                            spotColor = PrimaryAccent.copy(alpha = 0.5f),
                                            ambientColor = PrimaryAccent.copy(alpha = 0.5f)
                                        )
                                        .clip(CircleShape)
                                        .background(PrimaryAccent)
                                        .clickable { /* Handle day click */ }
                                } else {
                                    Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .clickable { /* Handle day click */ }
                                }

                                Box(
                                    modifier = modifier,
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = day.dayOfMonth.toString(),
                                            fontSize = 14.sp,
                                            fontWeight = if (day.isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = when {
                                                day.isSelected -> Color.White
                                                !day.isCurrentMonth -> OutlineVariant
                                                else -> OnSurface
                                            }
                                        )
                                        
                                        // The renewal indicator dot
                                        if (day.hasRenewal && !day.isSelected) {
                                            Box(
                                                modifier = Modifier
                                                    .padding(top = 2.dp)
                                                    .size(4.dp)
                                                    .clip(CircleShape)
                                                    .background(PrimaryAccent)
                                            )
                                        } else {
                                            // Invisible placeholder to keep text vertically stable
                                            Box(modifier = Modifier.padding(top = 2.dp).size(4.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleSection() {
    Column {
        // Schedule Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "Today's Schedule",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Text(
                    text = "Thursday, October 12",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurfaceVariant
                )
            }
            Text(
                text = "$34.98",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = PrimaryAccent
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Schedule Items
        ScheduleItemCard(
            ScheduledSub(
                name = "Netflix",
                plan = "Standard Plan • Monthly",
                price = 19.99,
                status = "DUE TODAY",
                color = Color.Black
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        ScheduleItemCard(
            ScheduledSub(
                name = "Spotify",
                plan = "Family Account • Monthly",
                price = 14.99,
                status = "CONFIRMED",
                color = Color(0xFF1DB954),
                isConfirmed = true
            )
        )
    }
}

@Composable
fun ScheduleItemCard(sub: ScheduledSub) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceContainerLowest)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Logo Placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(sub.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = sub.name.first().toString(),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = sub.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = sub.plan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurfaceVariant
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${sub.price}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = sub.status,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryAccent,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
fun UpcomingSection() {
    Column {
        Text(
            text = "COMING UP TOMORROW",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Muted Card for Upcoming
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainerLow) // Using low surface to distinguish from active white cards
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Logo Placeholder
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLowest),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "A",
                            color = OnSurfaceVariant,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Adobe Creative Cloud",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurfaceVariant
                    )
                }

                Text(
                    text = "$54.99",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendar() {
    Calendar()
}