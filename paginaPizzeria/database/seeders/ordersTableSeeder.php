<?php

namespace Database\Seeders;

use App\Models\Orders;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class ordersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $order=new Orders();
        $order->users_id =1;
        $order->direction_id=1;
        $order->coment="Sin lactosa";
        $order->save();    

        $order=new Orders();
        $order->users_id =1;
        $order->direction_id=1;
        $order->coment="Con extra de queso";
        $order->save();   
    }
}
