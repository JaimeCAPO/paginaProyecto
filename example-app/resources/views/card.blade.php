@extends('layouts.layout')

@section('content')


  @for ($i = 1; $i < 8; $i++)

  <div class="offcanvas offcanvas-end"  id="demo">
    <div class="offcanvas-header">
      <h1 class="offcanvas-title">Heading</h1>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
      <p>Some text lorem ipsum.</p>
      <p>Some text lorem ipsum.</p>
      <p>Some text lorem ipsum.</p>
      <button class="btn btn-secondary" type="button">A Button</button>
    </div>
  </div>
  
  <div class="container pt-5" style="margin:auto">
    <div class="card" style="width:400px">
        <img class="card-img-top" src="../bootstrap4/img_avatar1.png" alt="Card image" style="width:100%">
        <div class="card-body">
          <h4 class="card-title">John Doe</h4>
          <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
          <button class="btn btn-warning" data-bs-target="#demo" data-bs-toggle="offcanvas">See pizza</button>
        </div>
      </div>
    </div>
<br>

@endfor




@endsection