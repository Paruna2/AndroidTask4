import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private var databaseReference: DatabaseReference? = null
    private var adapter: MyAdapter? = null
    private var data: MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView =
            findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        data = ArrayList()
        adapter = MyAdapter(data)
        recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance()
            .getReference("your_node_name")

        databaseReference.addChildEventListener(object : ChildEventListener() {
            fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val item: String = dataSnapshot.getValue(String::class.java)
                data.add(item)
                adapter!!.notifyDataSetChanged()
            }

            fun onChildChanged(dataSnapshot: DataSnapshot?, previousChildName: String?) {
            }

            fun onChildRemoved(dataSnapshot: DataSnapshot?) {
            }

            fun onChildMoved(dataSnapshot: DataSnapshot?, previousChildName: String?) {
            }

            fun onCancelled(databaseError: DatabaseError?) {
            }
        })
    }
}