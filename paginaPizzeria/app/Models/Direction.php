<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Direction extends Model
{
    use HasFactory;

    protected $table = "direction";

    protected $fillable = ['street', 'town', 'number','postalCode'];
    protected $hidden = ['id'];

    public $timestamps = false;


    public function obtenerDirections()
{
    return direction::all();
}

    public function obtenerDirectionForId($id)
{
    return direction::find($id);
}}
