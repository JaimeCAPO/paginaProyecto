<?php

namespace App\Http\Controllers;

use App\Models\Adds;
use App\Models\Contains;
use App\Models\Direction;
use App\Models\Orders;
use App\Models\Pizza;
use GuzzleHttp\Psr7\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class ControllerPizzas extends Controller
{
    public function index()
    {
        return view('welcome');
    }

    public function card()
    {

        $pizzas = DB::table('pizza')->get();

        return view('card', ['pizzas' => $pizzas]);
    }

    public function getOrder($id)
    {
        $order=DB::table('orders')->find($id);
        $user=DB::table('users')->find($order->users_id);
        $direction=DB::table('direction')->find($order->direction_id);
        return view('orderDesc',['order'=>$order,'user'=>$user,'direction'=>$direction]);
    }


    public function order()
    {

        $orders = DB::table('orders')->get()->where('users_id',Auth::user()->id);
        
        return view('order', ['orders' => $orders]);
    }

    public function createPizza()
    {
        
        return view('createPizza',);
    }

    public function createOrder()
    {
        $pizzas=DB::table('pizza')->get();
        $drinks=DB::table('drink')->get();

        return view('createOrder',['pizzas'=>$pizzas,'drinks'=>$drinks]);
    }

    public function storePizza()
    {
        $pizza=new Pizza();
        $pizza->pName =request("pName");
        $pizza->description=request("desc");
        $pizza->price=14.00;
        $pizza->save();

        $pizzas = DB::table('pizza')->get();

        return view("card", ['pizzas' => $pizzas]);
    }

    public function storeOrder()
    {
        $direction= new Direction();
        $direction->street=request('street');
        $direction->town=request('town');
        $direction->number=request('number');
        $direction->postalCode=request('postalCode');
        $direction->save();
        
        $order=new Orders(); 
        $order->users_id=Auth::user()->id;
        
        $direction=DB::table("direction")->get()->last();
        $order->direction_id=$direction->id;
        $order->coment=request('coment');
        $order->save();

        $contains=new Contains();
        $lastOrder=DB::table('orders')->get()->last();
        $contains->orders_id=$lastOrder->id;
        $contains->pName=request('pizzaL');
        $contains->save();


        $adds=new Adds();
        $adds->orders_id=$lastOrder->id;
        $adds->dName=trim(request("drinkL"));
        $adds->save();

        $orders = DB::table('orders')->get();

        return view('order', ['orders' => $orders]);
    }

    public function deleteOrder($id)
    {

        $order=new Orders();
        $delete=$order->obtenerOrdersForId($id);
        $delete->delete();
        $orders = DB::table('orders')->get();

        return view('order', ['orders' => $orders]);
    }
}
