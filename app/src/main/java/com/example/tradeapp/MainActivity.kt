package com.example.tradeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeapp.data.Item
import com.example.tradeapp.ui.theme.TradeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradeAppTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun TradeTopBar() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun ItemUi(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(top = 8.dp, end = 16.dp, start = 16.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(end = 12.dp)) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(40.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(item.ticker, style = MaterialTheme.typography.h2)
                Text(item.area, style = MaterialTheme.typography.body1)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    "${item.currentPrice} $",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.End)
                )
                if (item.change > 0) {
                    Text(
                        text = "+${item.change}%",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Alignment.End),
                        color = MaterialTheme.colors.secondary
                    )
                } else if (item.change < 0) {
                    Text(
                        text = "${item.change}%",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Alignment.End),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: TradeViewModel = viewModel(factory = TradeViewModel.Factory)
) {
    val tradeUiState: TradeUiState = viewModel.tradeUiState
    when (tradeUiState) {
        is TradeUiState.Success -> StocksList(tradeUiState.stocks)
        is TradeUiState.Loading -> LoadingScreen()
        is TradeUiState.Error -> ErrorScreen()
    }

}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Failed to load")
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun StocksList(stocks: List<Item>) {
    Scaffold(topBar = { TradeTopBar() }) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(stocks) { stock ->
                ItemUi(stock)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TradeAppTheme {
        StocksList(
            listOf(
                Item("AAPL", 157.3, 2.69, "IT", R.drawable.apple),
                Item("AMZN", 99.2, -1.66, "IT", R.drawable.amazon)
            )
        )
    }
}