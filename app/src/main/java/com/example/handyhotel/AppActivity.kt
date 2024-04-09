package com.example.handyhotel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.handyhotel.ui.account.AccountFragment
import com.example.handyhotel.ui.hotels.HotelsFragment
import com.example.handyhotel.ui.news.NewsFragment
import com.example.handyhotel.ui.services.ServicesFragment


class AppActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var buttonFilter: Button

    private lateinit var dataListHotels: List<String>
    private lateinit var dataListPrice: List<String>
    private lateinit var dataListPhotos: List<Int>

    private lateinit var dataListServices: List<String>
    private lateinit var dataListDescription: List<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        bottomNavigationView = findViewById(R.id.nav_view)
        val selectedTab = intent.getIntExtra("selectedTab", R.id.navigation_hotels)
        bottomNavigationView.selectedItemId = selectedTab

        dataListHotels = listOf(
            "Афина",
            "Эмпирия",
            "Искра",
            "Элеганс",
            "Лагуна",
            "Аврора",
            "Маркиз")
        dataListPrice = listOf(
            "4500 ₽ за ночь",
            "3200 ₽ за ночь",
            "3800 ₽ за ночь",
            "4200 ₽ за ночь",
            "5500 ₽ за ночь",
            "2700 ₽ за ночь",
            "2500 ₽ за ночь")
        dataListPhotos = listOf(
            R.drawable.room1,
            R.drawable.room2,
            R.drawable.room3,
            R.drawable.room4,
            R.drawable.room5,
            R.drawable.room6,
            R.drawable.room7)

        val hotelsFragment = HotelsFragment.newInstance(dataListHotels, dataListPrice, dataListPhotos)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, hotelsFragment)
            .commit()

        dataListServices = listOf(
            "Ресторан",
            "Пользование камерой хранения",
            "Вызов такси",
            "Прокат автомобилей",
            "Доставка цветов и подарков",
            "Парикмахерские услуги",
            "Бассейн и спа-центр")
        dataListDescription = listOf(
            "Насладитесь изысканными блюдами и непревзойденным обслуживанием в нашем ресторане. Мы предлагаем разнообразное меню и уютную атмосферу для приятного вечера",
            "Не хотите беспокоиться о своих ценностях? Мы предлагаем удобную камеру хранения, где вы можете безопасно оставить свои вещи на время пребывания",
            "Не беспокойтесь о транспорте! Мы поможем вам вызвать такси в любое время, чтобы вы могли безопасно и своевременно добраться до любой точки города",
            "Исследуйте окрестности удобным и комфортным способом, арендовав автомобиль прямо в отеле",
            "Сделайте вашего особого человека счастливым, заказав доставку красивых цветов или уникальных подарков прямо в номер",
            "Полноценный салон красоты прямо в отеле, где вы можете получить новую стрижку, стильную укладку и профессиональный уход за волосами",
            "Расслабьтесь и освежитесь в нашем современном бассейне, а затем побалуйте себя процедурами в нашем спа-центре для полного релакса")


        @Suppress("DEPRECATION")
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_hotels -> {
                    val hotelsFragment = HotelsFragment.newInstance(dataListHotels, dataListPrice, dataListPhotos)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, hotelsFragment)
                        .commit()
                    true
                }
                R.id.navigation_services -> {
                    val servicesFragment = ServicesFragment.newInstance(dataListServices, dataListDescription)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, servicesFragment)
                        .commit()
                    true
                }
                R.id.navigation_news -> {
                    val newsFragment =
                        NewsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, newsFragment)
                        .commit()
                    true
                }
                R.id.navigation_account -> {
                    val accountFragment =
                        AccountFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, accountFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }

    }

}