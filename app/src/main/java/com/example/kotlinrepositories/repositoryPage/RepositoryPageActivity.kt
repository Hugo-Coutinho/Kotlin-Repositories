package com.example.kotlinrepositories.repositoryPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.webkit.WebView
import com.example.kotlinrepositories.R

class RepositoryPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupWebViewByUrl(intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)))
        this.preparingActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun preparingActionBar() {
        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.title_activity_repository_page)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupWebViewByUrl(url: String): WebView {
        val webView = WebView(applicationContext)
        webView.loadUrl(url)
        return webView
    }
}