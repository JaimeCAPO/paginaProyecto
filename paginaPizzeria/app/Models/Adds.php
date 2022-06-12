<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Adds extends Model
    {
        use HasFactory;
    
        protected $table = "adds";
    
        protected $fillable = ['orders_id', 'dName'];
        protected $hidden = ['id'];
    
        public $timestamps = false;
    
    
        public function obtenerAdds()
    {
        return adds::all();
    }
    
        public function obtenerAddsForId($id)
    {
        return adds::find($id);
    }
}
