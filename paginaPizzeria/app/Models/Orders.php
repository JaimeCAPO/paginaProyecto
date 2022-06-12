<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Orders extends Model
{
    use HasFactory;

    protected $table = "orders";

    protected $fillable = ['users_id', 'direction_id', 'coment'];
    protected $hidden = ['id'];

    public $timestamps = false;


    public function obtenerOrders()
{
    return orders::all();
}

    public function obtenerOrdersForId($id)
{
    return orders::find($id);
}
}
