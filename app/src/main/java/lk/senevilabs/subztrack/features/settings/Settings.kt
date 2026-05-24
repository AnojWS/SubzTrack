package lk.senevilabs.subztrack.features.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
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
val SurfaceContainerHigh = Color(0xFFE2E4E5)
val OnSurfaceVariant = Color(0xFF595C5D)
val OutlineVariant = Color(0xFFABADAE)
val GhostBorderColor = OutlineVariant.copy(alpha = 0.15f)

@Composable
fun Settings() {
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
            item { SettingsTopBar() }
            item { HeaderSection() }
            
            item { SectionLabel("ACCOUNT") }
            item { AccountCard() }
            
            item { SectionLabel("PREFERENCES") }
            item { PreferencesCard() }
            
            item { SectionLabel("APP") }
            item { AppCard() }
            
            item { LogOutButton() }
            item { FooterSection() }
        }
    }
}

@Composable
fun SettingsTopBar() {
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
        // Consistent with other pages: OnSurface color
        Text(
            text = "Settings",
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
            text = "Settings",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = OnSurface,
            letterSpacing = (-1).sp,
            lineHeight = 44.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Manage your account and preferences.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = OnSurfaceVariant
        )
    }
}

@Composable
fun SectionLabel(label: String) {
    Text(
        text = label,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = OnSurfaceVariant,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
    )
}

@Composable
fun AccountCard() {
    CardContainer {
        // Profile Info Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(OnSurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = SurfaceContainerLowest,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Alex Sterling",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "alex.sterling@example.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurfaceVariant
                )
            }
        }
        
        // Faint Divider (Ghost Border Fallback rule)
        Divider(color = GhostBorderColor, thickness = 1.dp)
        
        // Change Password Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle click */ }
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change Password",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Forward",
                tint = OnSurfaceVariant
            )
        }
    }
}

@Composable
fun PreferencesCard() {
    var notificationReminders by remember { mutableStateOf(true) }

    CardContainer {
        // Notification Reminders
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Notification Reminders",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Alerts before subscription renewals",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurfaceVariant
                )
            }
            
            Switch(
                checked = notificationReminders,
                onCheckedChange = { notificationReminders = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = PrimaryAccent,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = SurfaceContainerHigh,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
        
        Divider(color = GhostBorderColor, thickness = 1.dp)
        
        // Default Currency
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle click */ }
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Default Currency",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "USD ($)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = OnSurfaceVariant
                )
            }
            
            // Custom up/down arrows icon using text placeholder for simplicity
            Text(
                text = "↕",
                fontSize = 18.sp,
                color = OnSurfaceVariant
            )
        }
    }
}

@Composable
fun AppCard() {
    var selectedTheme by remember { mutableStateOf("System Default") }
    val themes = listOf("System Default", "Light", "Dark")

    CardContainer {
        // Theme
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = "Theme",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                themes.forEach { theme ->
                    val isSelected = theme == selectedTheme
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLowest) // all white background
                            // The mockup shows ghost borders for all, and primary border for selected
                            .clickable { selectedTheme = theme }
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isSelected) PrimaryAccent else GhostBorderColor
                            ),
                            color = Color.Transparent // transparent to let box background show
                        ) {
                            Text(
                                text = theme,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) PrimaryAccent else OnSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
                                // Handling multi-line for "System Default"
                                minLines = if (theme == "System Default") 2 else 1,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Divider(color = GhostBorderColor, thickness = 1.dp)

        // Version Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Version Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )
            Text(
                text = "v1.0.4",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurfaceVariant,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun LogOutButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .clickable { /* TODO: Log out */ }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, PrimaryAccent), // Ghost border with primary text
            color = Color.Transparent
        ) {
            Text(
                text = "Log Out",
                color = PrimaryAccent,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 18.dp)
            )
        }
    }
}

@Composable
fun FooterSection() {
    Text(
        text = "PROUDLY CURATED FOR DIGITAL EFFICIENCY",
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = OnSurfaceVariant,
        letterSpacing = 1.5.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    )
}

@Composable
fun CardContainer(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLowest)
            .padding(20.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettings() {
    Settings()
}
