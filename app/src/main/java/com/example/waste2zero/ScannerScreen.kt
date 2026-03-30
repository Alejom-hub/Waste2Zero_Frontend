package com.example.waste2zero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waste2zero.ui.theme.GreenWaste
import com.example.waste2zero.ui.theme.UltraLightGreenWaste
import com.example.waste2zero.ui.theme.DarkGreenWaste

@Composable
fun ScannerScreen(onLogout: () -> Unit = {}) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },
        floatingActionButton = {
            Box(contentAlignment = Alignment.Center) {
                FloatingActionButton(
                    onClick = { /* TODO */ },
                    containerColor = GreenWaste,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(64.dp)
                        .offset(y = 40.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(36.dp))
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onLogout,
                    modifier = Modifier
                        .size(40.dp)
                        .background(GreenWaste, CircleShape)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                }
                
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0C3B1))
                ) {
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Lee el código de barras del producto que quieras consultar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Scanner Area
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color(0xFFD9E2D5), RoundedCornerShape(32.dp))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SCAN",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGreenWaste,
                        letterSpacing = 6.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner, // O Icons.Default.PhotoCamera
                        contentDescription = "Camera",
                        modifier = Modifier.size(120.dp),
                        tint = DarkGreenWaste
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                contentDescription = "Barcode",
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "PRODUCTO: Tortillas-Bimbo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
        color = UltraLightGreenWaste
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavBarItem(icon = Icons.Default.Home, isSelected = false)
            NavBarItem(icon = Icons.Default.QrCodeScanner, isSelected = true)
            
            Spacer(modifier = Modifier.width(64.dp))
            
            NavBarItem(icon = Icons.Default.Notifications, isSelected = false)
            NavBarItem(icon = Icons.Default.Favorite, isSelected = false)
        }
    }
}

@Composable
fun NavBarItem(icon: ImageVector, isSelected: Boolean) {
    IconButton(onClick = { /* TODO */ }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) GreenWaste else DarkGreenWaste,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScannerScreenPreview() {
    ScannerScreen()
}
