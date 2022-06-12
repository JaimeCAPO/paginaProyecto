<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pizza extends Model
{

    use HasFactory;

    
    protected $table = "pizza";
    protected $fillable = ['pName', 'description', 'price'];

    public $timestamps = false;

    public function obtenerPizzas()
    {
        return pizza::all();
    }

    public function obtenerPizzaPName($pName)
    {
        return pizza::find($pName);
    }
}
