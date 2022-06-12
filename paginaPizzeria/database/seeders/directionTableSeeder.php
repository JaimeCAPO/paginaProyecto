<?php

namespace Database\Seeders;

use App\Models\Direction;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class directionTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $direction=new Direction();
        $direction->street="O Sineiro";
        $direction->town="O Grove";
        $direction->number=19;
        $direction->postalCode=36980;
        $direction->save();
    }
}
