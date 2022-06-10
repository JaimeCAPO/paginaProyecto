@extends('layouts.layout')

@section('title')

<div class="p-3  text-white text-center" style="background: rgb(255, 136, 0)">
  <h1>PIZZAS JAIME</h1>
  <p>Proyecto pagina web de reparto de pizzas</p> 
</div>


@endsection




@section('content')
<div id="demo" class="carousel slide" data-bs-ride="carousel">

    <!-- Indicators/dots -->
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    </div>
    
    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
      <div class="carousel-item active">
        <div class="mt-4 p-5 bg-warning text-white text-center rounded">
            <h1>Jumbotron Example</h1>
            <p>Lorem ipsum...</p>
          </div>              </div>
      <div class="carousel-item">
        <div class="mt-4 p-5 bg-success text-white text-center rounded">
            <h1>Jumbotron Example</h1>
            <p>Lorem ipsum...</p>
          </div>              </div>
      <div class="carousel-item">
        <div class="mt-4 p-5 bg-danger text-white text-center rounded">
            <h1>Jumbotron Example</h1>
            <p>Lorem ipsum...</p>
          </div>              </div>
    </div>
    
    <!-- Left and right controls/icons -->
    <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
      <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
      <span class="carousel-control-next-icon"></span>
    </button>
  </div>
</div>

<div class="container " style="margin-top: 40px">
<div class="card" style="width:400px">
    <img class="card-img-top" src="../bootstrap4/img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body">
      <h4 class="card-title">John Doe</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>
</div>
<br>
@endsection
        
