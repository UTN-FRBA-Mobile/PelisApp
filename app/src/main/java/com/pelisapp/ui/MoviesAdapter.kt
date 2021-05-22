import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)
        context = holder.poster.context //FIXME: esto estara ok?
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.tvTitle) as TextView
        val rating = view.findViewById(R.id.tvIMDBRate) as TextView
        val poster = view.findViewById(R.id.ivPoster) as ImageView

        fun bind(movie:Movie, context: Context){
            title.text = movie.title
            rating.text = movie.rating
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    movie.title,
                    Toast.LENGTH_SHORT
                ).show()
            }
            poster.loadUrl(movie.poster)
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}