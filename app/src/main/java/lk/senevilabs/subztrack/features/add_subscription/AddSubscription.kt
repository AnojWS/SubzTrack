package lk.senevilabs.subztrack.features.add_subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Design System Tokens (Kinetic Minimalist) ---
val SurfaceBase = Color(0xFFF5F6F7)
val PrimaryAccent = Color(0xFF004BE2)
val OnSurface = Color(0xFF2C2F30)
val SurfaceContainerLowest = Color(0xFFFFFFFF)
val SurfaceContainerLow = Color(0xFFEFF1F2)
val SurfaceContainerHigh = Color(0xFFE2E4E5)
val OnSurfaceVariant = Color(0xFF595C5D)
val PrimaryContainer = Color(0xFF809BFF)

@Composable
fun AddSubscription() {
    var enableReminders by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = SurfaceBase,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SurfaceBase),
            contentPadding = PaddingValues(bottom = 120.dp) // Extra padding for the bottom button
        ) {
            item {
                AddSubscriptionTopBar()
            }
            item {
                HeaderSection()
            }
            item {
                ServiceProviderCard()
            }
            item {
                AmountCard()
            }
            item {
                BillingCycleCard()
            }
            item {
                FirstBillingDateCard()
            }
            item {
                RemindersToggle(
                    checked = enableReminders,
                    onCheckedChange = { enableReminders = it }
                )
            }
            item {
                AddSubscriptionButton()
            }
        }
    }
}

@Composable
fun AddSubscriptionTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
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
            text = "CURATE YOUR PLAN",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Add New Service",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = OnSurface,
            letterSpacing = (-1).sp,
            lineHeight = 44.sp
        )
    }
}

@Composable
fun ServiceProviderCard() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedProvider by remember { mutableStateOf("Netflix") }
    val providers = listOf("Netflix", "Spotify", "Adobe CC")

    CardContainer {
        Text(
            text = "SERVICE PROVIDER",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Search Input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceContainerLow)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = PrimaryAccent
            )
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = OnSurface,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search Netflix, Spotify, Adobe.",
                            color = OnSurfaceVariant,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(providers) { provider ->
                val isSelected = provider == selectedProvider
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) PrimaryAccent else SurfaceContainerHigh)
                        .clickable { selectedProvider = provider }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = provider,
                        color = if (isSelected) Color.White else OnSurface,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun AmountCard() {
    var amount by remember { mutableStateOf("0.00") }

    CardContainer {
        Text(
            text = "AMOUNT",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Currency Selector
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainerLow)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "USD",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Select Currency",
                    tint = OnSurfaceVariant
                )
            }

            // Amount Input
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainerLow)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = OnSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BillingCycleCard() {
    var isMonthly by remember { mutableStateOf(true) }

    CardContainer {
        Text(
            text = "BILLING CYCLE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Segmented Control
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceContainerLow)
                .padding(4.dp)
        ) {
            // Monthly Segment
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isMonthly) SurfaceContainerLowest else Color.Transparent)
                    .clickable { isMonthly = true }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Monthly",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isMonthly) PrimaryAccent else OnSurfaceVariant
                )
            }

            // Annually Segment
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (!isMonthly) SurfaceContainerLowest else Color.Transparent)
                    .clickable { isMonthly = false }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Annually",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (!isMonthly) PrimaryAccent else OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FirstBillingDateCard() {
    CardContainer {
        Text(
            text = "FIRST BILLING DATE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceVariant,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Date Input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceContainerLow)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = OnSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "11/24/2023",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurface
                )
            }
            Icon(
                imageVector = Icons.Default.DateRange, // Using DateRange as placeholder for right icon
                contentDescription = "Pick Date",
                tint = OnSurface
            )
        }
    }
}

@Composable
fun RemindersToggle(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Enable Reminders",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurface
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryAccent,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = SurfaceContainerHigh,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun AddSubscriptionButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(PrimaryAccent, PrimaryContainer)
                )
            )
            .clickable { /* TODO: Submit */ }
            .padding(vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Add Subscription",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CardContainer(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLowest)
            .padding(20.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddSubscription() {
    AddSubscription()
}