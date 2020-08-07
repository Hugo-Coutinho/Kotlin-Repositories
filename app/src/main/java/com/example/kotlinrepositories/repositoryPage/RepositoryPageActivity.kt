package com.example.kotlinrepositories.repositoryPage

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.constant.Constant.Companion.ACTION_WEB_VIEW
import kotlinx.android.synthetic.main.webview_repository_page.*

class RepositoryPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_repository_page)
        this.setupWebViewByUrl(intent.getStringExtra(ACTION_WEB_VIEW))
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
    }

    private fun setupWebViewByUrl(url: String) {
        wb_repository_page.loadUrl(url)
        wb_repository_page.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                wb_repository_page_progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                wb_repository_page_progress.visibility = View.INVISIBLE
            }
        }
    }
}