@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Dashboard') }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    {{ __('You are logged in!') }}
                </div>
            </div>

            <div class="card mt-5 p-4">
                <div class="container p-3">
                    <h1 class="text-center">User Description</h1>
                    
                    <h4 class="mt-5 p-2">All the acount user information</h4>

                    <p class="p-2"><b>ID_User</b>: {{Auth::user()->id}}</p>
                    <p class="p-2"><b>Username</b>: {{Auth::user()->name}}</p>
                    <p class="p-2"><b>email</b>: {{Auth::user()->email}}</p>

                </div>
            </div>
        </div>
    </div>
</div>
@endsection
