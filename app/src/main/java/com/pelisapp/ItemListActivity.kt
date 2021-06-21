package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.android.example.pelisApp.SimpleItemRecyclerViewAdapter
import com.pelisapp.core.DummyContent

class ItemListActivity : SearchView.OnQueryTextListener, AppCompatActivity() {

    private lateinit var adapter: SimpleItemRecyclerViewAdapter
    private lateinit var busqueda: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        setUpBotonBusqueda()

        setupRecyclerView(findViewById(R.id.item_list))
    }

    private fun setUpBotonBusqueda() {
        busqueda = findViewById(R.id.busqueda)
        busqueda.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter(newText.orEmpty())
        return false
    }

}