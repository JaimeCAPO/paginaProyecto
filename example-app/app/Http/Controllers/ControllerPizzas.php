<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ControllerPizzas extends Controller
{
    public function index(){
        return view('welcome');
    }

    public function card(){
        return view('card');
    }
}
