<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pizzeria Jaime</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background-color: rgba(255, 244, 238, 0.918);font-family: 'Nunito', sans-serif">
    
    @yield('title')

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="padding:20px">
        <div class="container-fluid">
          <a class="navbar-brand text-white" href="/">PIZZERÍA JAIME</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse " id="collapsibleNavbar">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link " href="/card">Card</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="/orders">Orders</a>
              </li>  
            
            @if (Route::has('login'))
                    @auth
                      <li class="nav-item">
                        <a href="{{ url('/home') }}" class=" nav-link text-gray-700 dark:text-gray-50">ACOUNT</a>

                      </li>
                    @else
                    <li class="nav-item">
                        <a href="{{ route('login') }}" class="nav-link text-gray-700 dark:text-gray-500">LOG IN</a>
                    </li>
                        @if (Route::has('register'))
                        <li class="nav-item">
                            <a href="{{ route('register') }}" class="nav-link ml-4 text-gray-700 dark:text-gray-500 underline">REGISTER</a>
                        </li>
                        @endif
                    @endauth
            @endif
          </ul>
          </div>
        </div>
        <div class="container"></div>
      </nav>
      
      @yield('content')     

      <footer class="bg-dark text-center text-white" style="margin-top:10%">
        <!-- Grid container -->
        <div class="container p-4 pb-0">
          <!-- Section: Social media -->
          <section class="mb-3">

            <!-- Mestre -->
            <a class="btn btn-outline-light btn-floating m-1" href="https://mestre.iessanclemente.net" role="button">
                <i class="fab fa-twitter">Mestre</i>
            </a>
      
            <!-- Google -->
            <a class="btn btn-outline-light btn-floating m-1" href="https://www.google.com/" role="button">
                <i class="fab fa-google">Google</i>
            </a>
      
            <!-- Instagram -->
            <a class="btn btn-outline-light btn-floating m-1" href="https://www.instagram.com/jaimecabaleiro_/" role="button">
                <i class="fab fa-instagram">Insta</i>
            </a>

          </section>
          <!-- Section: Social media -->
        </div>
        <!-- Grid container -->
        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
          © 2022 Copyright:
          <a class="text-white" href="https://mestre.iessanclemente.net/user/view.php?id=1951&course=184">Pizzeria Jaime</a>
        </div>
        <!-- Copyright -->
      </footer>
</body>
</html>