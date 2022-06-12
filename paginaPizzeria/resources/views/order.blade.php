@extends('layouts.layout')
<?php

//saber si el usuario tiene pedidos;
$contadorPedidos = 0;
$listPizzas = '';
$listDrinks = '';
$price = 0.0;

?>
@section('content')
    <div class="container mt-5">
        <h1 class="text-center">Orders List</h1>
        @forelse  ($orders as $order)
            @if ($order->id == Auth::user()->id)
                <div class="offcanvas offcanvas-end" id="demo{{$order->id}}">
                    <div class="offcanvas-header">
                        <h1 class="offcanvas-title">Order Nº{{ $order->id }}</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
                    </div>

                    <?php
                    $containsAr = DB::table('contains')
                        ->where('orders_id', $order->id)
                        ->get();
                    $addsAr = DB::table('adds')
                        ->where('orders_id', $order->id)
                        ->get();
                    $directionAr = DB::table('direction')
                        ->where('id', $order->id)
                        ->get();
                    $pizzasAr = DB::table('pizza')->get();
                    $drinksAr = DB::table('drink')->get();
                    ?>

                    <div class="offcanvas-body">
                        <h2>User Description</h2>
                        <p>Username: {{ Auth::user()->name }}</p>
                        <p>Email {{ Auth::user()->email }}</p>
                        <h2>Direction</h2>
                        @foreach ($directionAr as $direction)
                            <p>Street: {{ $direction->street }}</p>
                            <p>Town: {{ $direction->town }}</p>
                            <p>Number: {{ $direction->number }}</p>
                            <p>PostalCode: {{ $direction->postalCode }}</p>
                        @endforeach

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

                        <form method="post" action="/order/{{$order->id}}">
                            @csrf
                            <button type="submit" class="btn btn-secondary">Delete</button>
                          </form>
                    </div>
                </div>

                <div class="container pt-5" style="margin:auto">
                    <div class="card" style="width:50%">
                        <div class="row">
                            <div class="col-md-4">
                                <img class="card-img-top m-3"
                                    src="https://img.freepik.com/vector-gratis/repartidor_1284-12633.jpg" alt="Card image"
                                    style="width:70%">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h4 class="card-title">Order Nº{{ $order->id }}</h4>
                                    <p class="card-text">Order Price: orderprice€</p>
                                    <button class="btn btn-warning" data-bs-target="#demo{{$order->id}}" data-bs-toggle="offcanvas">See order</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <br>
                <?php
                $contadorPedidos = $contadorPedidos + 1;
                ?>
            @endif
        @empty
            <div class="alert alert-danger alert-dismissible mt-5 mb-5">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong>Uips!!!</strong> We don't have any order.
            </div>
        @endforelse

        <?php
            
            if($contadorPedidos==0){
                ?>
        <div class="alert alert-danger alert-dismissible mt-5 mb-5">
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            <strong>Uips!!!</strong> We don't have any order for this user.
        </div>
        <?php
            }
            ?>
    </div>

    </div>

    <h1 class="text-center" style="margin-top:5%">Create Order</h1>
    <a href="/order/create" style="text-decoration: none">
        <div class="mt-4  p-5 bg-success text-center text-white rounded active">
            <h1>Order</h1>
            <p>Create a order with your favourite pizzas</p>
        </div>
    </a>
@endsection
