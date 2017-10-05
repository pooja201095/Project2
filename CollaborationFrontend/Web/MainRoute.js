var myApp=angular.module("myApp",["ngRoute","ngCookies"])
myApp.config(function($routeProvider)
		{
	$routeProvider
	.when("/",{templateUrl:"Users/Home.html"})
	.when("/Login",{templateUrl:"Users/Login.html",controller:"usercontroller"})
	.when("/Register",{templateUrl:"Users/Register.html",controller:"usercontroller"})
	.when("/Home",{templateUrl:"Users/Home.html",controller:"usercontroller"})
	.when("/AboutUs",{templateUrl:"Users/Aboutus.html",controller:"usercontroller"})
	.when("/ContactUs",{templateUrl:"Users/ContactUs.html",controller:"usercontroller"})
	.when("/Editprofile",{templateUrl:"Users/Editprofile.html",controller:"usercontroller"})
	
		})
myApp.run(function($rootScope,$cookieStore,UserService,$location)
		{
		if($rootScope.currentUser==undefined)
			$rootScope.currentUser=$cookieStore.get('userDetails')
			
		$rootScope.logout=function()
		{
			UserService.logout().then(function(response){
				delete $rootScope.currentUser;
				$cookieStore.remove('userDetails')
				$location.path('/Login')
			},
			function(response){
				console.log(response.status)
				if(response.status==401)
					{
					delete $rootScope.currentUser;
					$cookieStore.remove('userDetails')
					$location.path('/Login')
					}
			})
		}
		})