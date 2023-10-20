package listeners

interface OnItemClick {
    fun onClick(position:Int)
    fun onDeleteClick(position:Int)
}