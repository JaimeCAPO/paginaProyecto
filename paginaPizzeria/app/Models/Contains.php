<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Contains extends Model
{
    use HasFactory;

    protected $table = "contains";

    protected $fillable = ['orders_id', 'pName'];
    protected $hidden = ['id'];

    public $timestamps = false;


    public function obtenerContains()
    {
        return contains::all();
    }

    public function obtenerContainsForId($id)
    {
        return contains::find($id);
    }
}
