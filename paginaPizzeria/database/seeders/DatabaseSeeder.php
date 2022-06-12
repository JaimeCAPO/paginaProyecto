<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        $this->call([
            pizzaTableSeeder::class,
            drinkTableSeeder::class,
            directionTableSeeder::class,
            usersTableSeeder::class,
            ordersTableSeeder::class,
            containsTableSeeder::class, 
            addsTableSeeder::class
        ]);
    }
}
