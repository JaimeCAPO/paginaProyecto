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
        <div class="carousel-item  p-5 bg-warning text-center text-white rounded active">
          <h1>CARD</h1>
          <p>In this web page you can see all the pizzas we have and create your own pizzas</p>
        </div>
        <div class="carousel-item">
          <div class="p-5 bg-success text-white text-center rounded">
              <h1>MANAGE ORDERS</h1>
              <p>You can see your user orders list and delete the ones you want</p>
          </div>              
        </div>
        <div class="carousel-item">
          <div class="p-5 bg-danger text-white text-center rounded">
              <h1>CREATE ORDERS</h1>
              <p>Create your own orders</p>
            </div>              
          </div>
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

<div class="container">

  <!-- Portfolio Item Heading -->
  <h1 class="my-4">CREATING PIZZA FROM 2022
    <small> Description</small>
  </h1>

  <!-- Portfolio Item Row -->
  <div class="row">

    <div class="col-md-8">
      <img class="img-fluid" src="https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_960_720.jpg" alt="">
    </div>

    <div class="col-md-4">
      <h3 class="my-3">Project Description</h3>
      <p>For this part of the proyect I used the next Programs and tools:</p>
      <ul>
        <li><b>Laravel</b> As the Develop Framework.</li>
        <li><b>PHP</b> As the Programing code.</li>
        <li><b>SQLite</b> For the databases.</li>
        <li><b>Bootstrap and HTML</b> As markup languajes.</li>
        <li><b>Vue</b> For the login autoimplemented in laravel.</li>

      </ul>
      <h3 class="my-3">Project Details</h3>
      <ul>
        <li>Lorem Ipsum</li>
        <li>Dolor Sit Amet</li>
        <li>Consectetur</li>
        <li>Adipiscing Elit</li>
      </ul>
    </div>

  </div>

</div>
<br>
@endsection
        
