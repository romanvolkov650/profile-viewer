package com.romanvolkov.profileviewer.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romanvolkov.profileviewer.PaginationScrollListener
import com.romanvolkov.profileviewer.R
import com.romanvolkov.profileviewer.main.adapter.MainAdapter
import com.romanvolkov.profileviewer.main.viewmodel.IMainActivityViewModel
import com.romanvolkov.profileviewer.model.entity.Profile
import com.romanvolkov.profileviewer.profile.ProfileActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: IMainActivityViewModel

    private lateinit var recycler: RecyclerView
    private val mainAdapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.rv_profile)
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
            setHasFixedSize(true)
        }
        recycler.addOnScrollListener(object :
            PaginationScrollListener((recycler.layoutManager) as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.load()
            }
        })
        mainAdapter.onClickListener = {
            val intent = Intent(this, ProfileActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("profile", it)
            intent.putExtra("profileBundle", bundle)
            startActivity(intent)
        }
        viewModel.state.observe(this, Observer(::updateItems))
    }

    private fun updateItems(items: List<Profile>) {
        mainAdapter.updateItems(items)
    }
}