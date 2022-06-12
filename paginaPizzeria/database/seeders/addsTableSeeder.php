<?php

namespace Database\Seeders;

use App\Models\Adds;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class addsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $adds = new Adds();
        $adds->orders_id = 1;
        $adds->dName = "Nestea";
        $adds->save();

        $adds = new Adds();
        $adds->orders_id = 1;
        $adds->dName = "Kas";
        $adds->save();

        $adds = new Adds();
        $adds->orders_id = 2;
        $adds->dName = "1906";
        $adds->save();
    }
}
