package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.android.example.pelisApp.SimpleItemRecyclerViewAdapter
import com.pelisapp.core.DummyContent

private val ItemListActivity.recyclerView1: RecyclerView?
    get() = findViewById(R.id.item_list)

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : SearchView.OnQueryTextListener, AppCompatActivity() {

    private lateinit var adapter: SimpleItemRecyclerViewAdapter
    private lateinit var busqueda: SearchView

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        /*findViewById<FloatingActionButton>(R.id.fab_favoriteada).setOnClickListener { view ->
            Snackbar.make(view, "Todavia no king", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        busqueda = findViewById(R.id.busqueda)

        busqueda.setOnQueryTextListener(this)

        recyclerView = findViewById(R.id.item_list)

        setupRecyclerView(recyclerView)
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