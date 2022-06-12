<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Drink extends Model
{
    use HasFactory;

    protected $table = "drink";
    protected $fillable = ['dName', 'price'];

    public $timestamps = false;
    
    public function obtenerDrinks()
    {
        return drink::all();
    }

    public function obtenerDrinkDName($dName)
    {
        return drink::find($dName);
    }
}
