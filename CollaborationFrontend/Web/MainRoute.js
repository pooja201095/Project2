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
	
	.when("/AddBlog",{templateUrl:"Users/AddBlog.html",controller:"blogcontroller"})
	.when("/GetBlogs",{templateUrl:"Users/BlogList.html",controller:"blogcontroller"})
	.when("/getBlogById/:id",{templateUrl:"Users/BlogDetails.html",controller:"blogdetailcontroller"})
	.when("/GetApprovalForm/:id",{templateUrl:"Users/BlogApprovalForm.html",controller:"blogdetailcontroller"})
	
	.when("/AddJob",{templateUrl:"Users/JobForm.html",controller:"jobcontroller"})
	.when("/GetAllJobs",{templateUrl:"Users/JobList.html",controller:"jobcontroller"})
	
	.when("/uploadProfilePicture",{templateUrl:"Users/ProfilePicture.html"})
	.when("/getSuggestedUsers",{templateUrl:"Users/SuggestedUsers.html",controller:"friendcontroller"})
	.when("/pendingRequests",{templateUrl:"Users/PendingRequests.html",controller:"friendcontroller"})
	.when("/listOfFriends",{templateUrl:"Users/ListOfFriends.html",controller:"friendcontroller"})
	
	.otherwise({templateUrl:"Users/Home.html"})
	
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