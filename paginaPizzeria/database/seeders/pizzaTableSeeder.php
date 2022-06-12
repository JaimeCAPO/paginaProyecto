<?php

namespace Database\Seeders;

use App\Models\Pizza;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class pizzaTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $pizza=new Pizza();
        $pizza->pName ='Carbonara';
        $pizza->description='Champiñones, salsa carbonara, bacon, cebolla, ajo';
        $pizza->price=10.00;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Margarita';
        $pizza->description='Tomate, jamon, queso';
        $pizza->price=7.80;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Alemana';
        $pizza->description='Tomate, queso, bacon, huevo cocido y salchichas';
        $pizza->price=11.75;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Cuatro quesos';
        $pizza->description='Queso parmesano, mozzarella, provolone y roqueford';
        $pizza->price=10.10;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Hawaiana';
        $pizza->description='Tomate, jamon, queso y piña';
        $pizza->price=8.80;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Champiñones';
        $pizza->description='Tomate, champiñones, pimientos rojos y queso';
        $pizza->price=9.80;
        $pizza->save();

        $pizza=new Pizza();
        $pizza->pName ='Vegetal';
        $pizza->description='Queso, tomate, pimiento rojo, verde, amarillo, cebolla, calabacín y brócoli';
        $pizza->price=10.80;
        $pizza->save();

    }
}
