@extends('layouts.layout')

@section('content')
    <?php
    

    
    ?>
    
    <div class="container mt-5">
        <h1 class="text-center">Pizzas List</h1>

        @forelse  ($pizzas as $pizza)
            <div class="offcanvas offcanvas-end" id="demo{{$pizza->pName}}">
                <div class="offcanvas-header">
                    <h1 class="offcanvas-title">{{ $pizza->pName }}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
                </div>
                <div class="offcanvas-body">
                    <h2>Description</h2>
                    <p>{{ $pizza->description }}</p>
                    <h2>Price</h2>
                    <p>Pizza's price: {{ $pizza->price }}</p>
                    <button class="btn btn-secondary" data-bs-dismiss="offcanvas" type="button">Close</button>
                </div>
            </div>

            <div class="container pt-5 " style="margin:auto">
                <div class="card" style="width:50%">
                    <div class="row row justify-content-center">
                        <div class="col-md-4 ">
                            <img class="card-img-top m-3"
                                src="https://st2.depositphotos.com/1724125/5686/v/450/depositphotos_56862433-stock-illustration-salami-pizza-slice.jpg"
                                alt="Card image" style="width:70%">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h4 class="card-title">{{ $pizza->pName }}</h4>
                                <p class="card-text">Pizzas Price: {{ $pizza->price }}€</p>
                                <button class="btn btn-warning" data-bs-target="#demo{{$pizza->pName}}" data-bs-toggle="offcanvas">See pizza</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <br>

        @empty

            <div class="alert alert-danger alert-dismissible mt-5 mb-5">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong>Uips!!!</strong> There aren't available pizzas.
            </div>
        @endforelse

    </div>

    <h1 class="text-center mt-2">Crete Your Own Pizza</h1>
    <a href="/card/create" style="text-decoration: none">
        <div class="mt-4  p-5 bg-success text-center text-white rounded active">
            <h1>CUSTOMICE</h1>
            <p>In this web page Create your own pizza</p>
        </div>
    </a>
@endsection
