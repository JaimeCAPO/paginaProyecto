@extends("layouts/layout")

@section("content")

<div class="container mt-5">

    <div class="container">
        <div class="container">
            <img src="https://img.freepik.com/vector-gratis/entrega-servicios-envio-linea-seguimiento-pedidos-linea-entrega-hogar-oficina-mensajeria-camion-scooter-bicicleta-envio-paquetes-pines-ubicacion-telefono-movil-repartidor_1150-58774.jpg?t=st=1655027693~exp=1655028293~hmac=1fc0eb264e8a207e466adff2a362eb6c8032efc947364918e707eddf9fa2f378&w=1380" alt="imagen-repartidor" style="width: 50%;">

        </div>
        <form action="/order" class="m-5" method="POST">

            @csrf

            <div class="row">
                <div class="col-md-5 p-4 ">
                    <h2 class="pb-4">Direction</h2>
                    <label for="town" class="form-label"><b>Town:</b></label>
                    <input type="text" class="form-control" id="town" placeholder="Enter the direction's town" required name="town">
                    <label for="street" class="form-label"><b>Street:</b></label>
                    <input type="text" class="form-control" id="street" placeholder="Enter the street" name="street">
                    <label for="number" class="form-label"><b>Number:</b></label>
                    <input type="number" class="form-control" id="number" placeholder="Enter the direction number" required name="number">
                    <label for="postalCode" class="form-label"><b>Postal Code:</b></label>
                    <input type="number" class="form-control" id="postalCode" placeholder="Enter postal code" required name="postalCode">
                    
                </div>
                <div class="col-md-7 p-4">
                    <h2 class="pb-4">Order</h2>
                    
                    <label for="pizzaL" class="form-label"><b>Pizza List:</b></label>
                    <select class="form-select form-select-lg mb-3" name="pizzaL">
                        <option selected>~None~</option>
                        @foreach ($pizzas as $pizza)
                            <option>{{$pizza->pName}}</option>
                        @endforeach
                      </select>

                    <label for="drinkL" class="form-label"><b>Drink List:</b></label>
                    <select class="form-select form-select-lg mb-3" name="drinkL">
                        <option selected>~None~</option>
                        @foreach ($drinks as $drink)
                            <option>{{$drink->dName}}</option>
                        @endforeach
                      </select>

                    <label for="coment" class="form-label"><b>Coment:</b></label>
                    <input type="text" class="form-control" id="coment" placeholder="Enter a comentary if you wanna make some change" name="coment">

                    <button type="submit" class="btn btn-success mt-5">Create</button>
                    <button type="reset" class="btn btn-secondary mt-5">Reset</button>

                </div>
            </div>
            
        </form>
    </div>


</div>

@endsection