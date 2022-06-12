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

Route::get('/', [ControllerPizzas::class, 'index']);
Route::get('/card', [ControllerPizzas::class, 'card']);
Route::get('/order', [ControllerPizzas::class, 'order'])->middleware("auth");
Route::get('/card/create', [ControllerPizzas::class, 'createPizza'])->middleware("auth");
Route::get('/order/create', [ControllerPizzas::class, 'createOrder'])->middleware("auth");
Route::post('/card', [ControllerPizzas::class, 'storePizza'])->middleware("auth");
Route::post('/order', [ControllerPizzas::class, 'storeOrder'])->middleware("auth");
Route::post('/order/{id}', [ControllerPizzas::class, 'deleteOrder'])->middleware("auth");
Route::get('/order/{id}', [ControllerPizzas::class, 'getOrder'])->middleware("auth");




Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');
