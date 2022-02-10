package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText:TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuther:TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())
    }
    fun setQuote(quotes: Quotes)
    {
        quoteText.text=quotes.text
        quoteAuther.text=quotes.author
    }

    fun onPrevious(view: android.view.View) {
        setQuote(mainViewModel.previousQuote())
    }
    fun onNext(view: android.view.View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: android.view.View) {
        val i=Intent(Intent.ACTION_SEND)
        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(i)
    }
}