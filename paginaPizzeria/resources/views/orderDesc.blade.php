@extends('layouts.layout')

<?php

//saber si el usuario tiene pedidos;
$contadorPedidos = 0;
$listPizzas = '';
$listDrinks = '';
$price = 0.0;

?>

@section('content')

    @if ($order)
        <div class="row m-5">
            <div class="col-md-7">
                <img style="width:90%"
                    src="https://img.freepik.com/psd-gratis/ilustracion-3d-entrega-repartidor-bebida-hamburguesa_23-2149442165.jpg?t=st=1655061079~exp=1655061679~hmac=92e52d5a123cb394aabda93ae96adb26c8689b60521c4d821c95e2a9b6d9053a&w=1380"
                    alt="">
            </div>

            <div class="col-md-5">
                <h1>Order Nº{{ $order->id }}</h1>

                <?php
                $containsAr = DB::table('contains')
                    ->where('orders_id', $order->id)
                    ->get();
                $addsAr = DB::table('adds')
                    ->where('orders_id', $order->id)
                    ->get();
                
                $pizzasAr = DB::table('pizza')->get();
                $drinksAr = DB::table('drink')->get();
                ?>

                <h2>User Description</h2>
                <p>Username: {{ $user->name}}</p>
                <p>Email {{ $user->email }}</p>
                <h2>Direction</h2>
                    <p>Street: {{ $direction->street }}</p>
                    <p>Town: {{ $direction->town }}</p>
                    <p>Number: {{ $direction->number }}</p>
                    <p>PostalCode: {{ $direction->postalCode }}</p>

                <h2>Ordered Pizzas</h2>
                <?php
                if ($containsAr->count()) {
                    foreach ($containsAr as $contains) {
                        $listPizzas = $listPizzas . $contains->pName . ', ';
                        foreach ($pizzasAr as $pizza) {
                            if (trim($pizza->pName) == trim($contains->pName)) {
                                $price = $price + $pizza->price;
                            }
                        }
                    }
                } else {
                    $listPizzas = 'The order dont have pizza';
                }
                
                ?>
                <p>{{ $listPizzas }}</p>
                <h2>Ordered Drinks</h2>
                <?php
                if ($addsAr->count()) {
                    foreach ($addsAr as $adds) {
                        $listDrinks = $listDrinks . $adds->dName . ', ';
                        foreach ($drinksAr as $drink) {
                            if (trim($drink->dName) == trim($adds->dName)) {
                                $price = $price + $drink->price;
                            }
                        }
                    }
                } else {
                    $listDrinks = 'The order dont have drinks';
                }
                ?>
                <p>{{ $listDrinks }}</p>
                <h2>Coment</h2>
                @if (empty($order->coment))
                    <p>That order don't have coments</p>
                @else
                    <p>{{ $order->coment }}</p>
                @endif

                <h2>Total Price</h2>


                <p>{{ $price }}€</p>

                <form method="post" action="/order/{{ $order->id }}">
                    @csrf
                    <button type="submit" class="btn btn-secondary">Delete</button>
                </form>
            </div>
        </div>
    @else
        <div class="container-fluid bg-danger">
            <div class="text-white mt-5" style="padding: 10%">
                <h1 class="text-center">404</h1>
                <h2 class="text-center">Not Found</h2>
            </div>
        </div>
    @endif
@endsection
