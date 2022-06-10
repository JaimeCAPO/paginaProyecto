<?php

use App\Http\Controllers\ControllerPizzas;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', [ControllerPizzas::class,'index']);
Route::get('/card',[ControllerPizzas::class,'card']);
