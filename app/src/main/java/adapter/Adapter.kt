package adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertyapp.databinding.ItemsLayoutBinding
import listeners.OnItemClick
import models.PropertyAddress

class Adapter(private val list: List<PropertyAddress>, private var onClick:OnItemClick, private var onDelete: OnItemClick) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: PropertyAddress) {
            binding.tvPropertyName.text=item.propertyName
            binding.tvAddress.text=item.propertyAdd
            binding.tvSize.text= item.size.toString()
            binding.tvRooms.text= "${item.rooms.toString()}rooms"
            binding.tvKitchen.text= "${item.kitchen.toInt().toString()}kitchen"
            binding.tvWashRooms.text="${item.washrooms.toString()} washRoom"
            binding.tvFloors.text= "${item.floors.toString()} floors"
            binding.tvFurnished.text=item.furnished
            binding.tvSale.text=item.sale
            binding.tvPrice.text="${item.price.toString()} Rs"
            binding.btnEdit.setOnClickListener {
                onClick.onClick(layoutPosition)
            }
            binding.btnDelete.setOnClickListener {
                onClick.onDeleteClick(layoutPosition)
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        /*holder.itemView.setOnClickListener {
//        val intent:Intent= Intent(Intent.ACTION_DIAL)
//            intent.data= Uri.parse(list.get(position).phoneNumber)
            *//*onClick.onClick(position)*//*

        }*/
    }
    override fun getItemCount(): Int {
        return list.size
    }
}
/*
class Adapter(private var list: List <Person>):RecyclerView.Adapter<ViewHolder>() {
    private lateinit var binding:ItemsLayoutBinding
    inner class ViewHolder(private val view:ItemsLayoutBinding):RecyclerView.ViewHolder(view.root) {
        fun bind(item: Person) {
            binding.tvUserName.text=item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        binding= ItemsLayoutBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}*/
