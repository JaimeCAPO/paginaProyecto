<?php

namespace Database\Seeders;

use App\Models\Drink;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class drinkTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $drink=new Drink();
        $drink->dName ='Nestea';
        $drink->price=2.30;
        $drink->save();   
        
        $drink=new Drink();
        $drink->dName ='Kas';
        $drink->price=2.30;
        $drink->save();  

        $drink=new Drink();
        $drink->dName ='Coca Cola';
        $drink->price=2.30;
        $drink->save();  

        $drink=new Drink();
        $drink->dName ='Aquarius';
        $drink->price=2.30;
        $drink->save();  

        $drink=new Drink();
        $drink->dName ='Red Bull';
        $drink->price=2.30;
        $drink->save();  

        $drink=new Drink();
        $drink->dName ='1906';
        $drink->price=2.50;
        $drink->save();  

        $drink=new Drink();
        $drink->dName ='~None~';
        $drink->price=0.00;
        $drink->save(); 
    }
}
