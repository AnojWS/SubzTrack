package lk.senevilabs.subztrack.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Design System Tokens (Kinetic Minimalist) ---
val SurfaceBase = Color(0xFFF5F6F7)
val PrimaryAccent = Color(0xFF004BE2)
val ErrorColor = Color(0xFFB41340)
val OnSurface = Color(0xFF2C2F30)
val SurfaceContainerLowest = Color(0xFFFFFFFF)
val SurfaceContainerLow = Color(0xFFEFF1F2)
val SurfaceContainerHigh = Color(0xFFE2E4E5) // Slightly darker than Low
val SurfaceContainerHighest = Color(0xFFDFE1E2) // Even darker
val OnSurfaceVariant = Color(0xFF595C5D)
val AmbientShadow = Color(0xFF2C2F30).copy(alpha = 0.06f)

// Dummy data for subscriptions
data class SubItem(
    val id: Int,
    val name: String,
    val plan: String,
    val price: Double,
    val date: String,
    val color: Color
)

val sampleSubscriptions = listOf(
    SubItem(1, "Netflix", "Premium 4K Plan", 19.99, "OCT 12", Color.Black),
    SubItem(2, "Spotify", "Family Plan", 15.99, "OCT 15", Color(0xFF1DB954)),
    SubItem(3, "Adobe Creative Cloud", "All Apps Plan", 79.99, "OCT 18", Color(0xFF231F20)),
    SubItem(4, "Disney+", "Monthly Standard", 13.99, "OCT 25", Color(0xFF113CCF))
)

@Composable
fun Dashboard() {
    Scaffold(
        containerColor = SurfaceBase,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Implement Add Navigation */ },
                containerColor = PrimaryAccent,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .padding(16.dp)
                    // The ambient shadow logic is somewhat complex in compose, using a basic shadow
                    .shadow(
                        elevation = 20.dp,
                        shape = CircleShape,
                        spotColor = AmbientShadow,
                        ambientColor = AmbientShadow
                    )
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Subscription")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SurfaceBase),
            contentPadding = PaddingValues(bottom = 80.dp) // extra padding for FAB to not overlap last item
        ) {
            item {
                DashboardTopBar()
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                TotalSpendCard()
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
                ActiveSubscriptionsSection()
            }
            items(sampleSubscriptions) { sub ->
                Spacer(modifier = Modifier.height(16.dp)) // Spacing-3 equivalent between items (no lines)
                SubscriptionCard(sub)
            }
        }
    }
}

@Composable
fun DashboardTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp), // spacing-12 top padding leaning into "Digital Curator"
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
            fontSize = 24.sp, // headline-sm
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
fun TotalSpendCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(SurfaceContainerLow)
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = "TOTAL MONTHLY SPEND",
                fontSize = 12.sp, // label-md
                fontWeight = FontWeight.SemiBold,
                color = OnSurfaceVariant,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$124.50",
                fontSize = 56.sp, // display-lg
                fontWeight = FontWeight.Black,
                color = OnSurface,
                letterSpacing = (-1.12).sp // -0.02em of 56
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "↗ 4% from last month",
                    fontSize = 16.sp, // body-md
                    fontWeight = FontWeight.Medium,
                    color = PrimaryAccent
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Spend Meter Component
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceContainerLowest)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "MONTHLY BUDGET",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceVariant
                        )
                        Text(
                            text = "$150.00",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryAccent
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // The Meter Track and Progress Line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHighest) // Track
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.83f) // $124.5 / $150 width proportion
                                .fillMaxHeight()
                                .clip(CircleShape)
                                .background(PrimaryAccent) // Progress
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActiveSubscriptionsSection() {
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Streaming", "Work")

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Active\nSubscriptions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                lineHeight = 28.sp
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    val isSelected = filter == selectedFilter
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryAccent else SurfaceContainerHigh)
                            .clickable { selectedFilter = filter }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = filter,
                            color = if (isSelected) Color.White else OnSurfaceVariant,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubscriptionCard(sub: SubItem) {
    // Handling the "Active State" scaling as per the "Kinetic Minimalist" guidelines
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.98f else 1f
    val bgColor = if (isPressed) SurfaceContainerHigh else SurfaceContainerLowest

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .scale(scale)
            .clip(RoundedCornerShape(24.dp))
            .background(bgColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null // Manually handled by scaling and color shift above
            ) { /* Navigate to Subscription Details */ }
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Logo Placeholder (Colored box with initial)
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(sub.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = sub.name.first().toString(),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = sub.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sub.plan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = OnSurfaceVariant
                    )
                }
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${sub.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Next Billing Date Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "\uD83D\uDCC5 ${sub.date}", // Calendar emoji + date
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboard() {
    Dashboard()
}