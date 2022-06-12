<?php

namespace Database\Seeders;

use App\Models\Contains;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class containsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $contain=new Contains();
        $contain->orders_id=1;
        $contain->pName="Carbonara";
        $contain->save();    
    
        $contain=new Contains();
        $contain->orders_id=2;
        $contain->pName="Alemana";
        $contain->save();    
    }
}
