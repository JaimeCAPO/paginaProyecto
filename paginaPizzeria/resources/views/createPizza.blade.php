@extends('layouts.layout')


@section('content')
    <div class="container mt-5">

        <div class="row">
            <div class="col-md-6">

                <img class="img-fluid" src="https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_960_720.jpg"
                    alt="">

            </div>
            <div class="col-md-6">
                <div class="m-3">
                    <h1>Pizza Creation </h1>
                    <p>The Customized pizza price will have a fixed price of <b>14€</b>.</p>
                </div>
                <div class="m-3">

                    <form action="/card" method="POST">

                        @csrf

                        <div class="mb-3 mt-5">
                            <label for="pName" class="form-label"><b>Pizza's Name:</b></label>
                            <input type="text" class="form-control" id="pName" placeholder="Enter Name" name="pName" required maxlength="50">
                        </div>
                        <div class="mb-3">
                            <label for="desc" class="form-label"><b>Description:</b></label>
                            <p>Ingredients and instructions from the pizza</p>
                            <input type="text" class="form-control" id="desc" placeholder="Enter Descrition" name="desc" required>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Create</button>
                        <button type="reset" class="btn btn-secondary mt-3">Reset</button>
                    </form>
                </div>
            </div>
        </div>


    </div>
@endsection
