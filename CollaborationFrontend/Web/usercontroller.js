/**
 * 
 */
myApp.controller('usercontroller',function($scope,UserService,$location,$rootScope,$cookieStore){
	$scope.createUsers=function()
	{
		console.log($scope.users.userid)
		console.log("User data is:"+$scope.users)
		UserService.createUsers($scope.users).then(function(response){
			console.log(response.data)
			console.log(response.status)
			$location.path('/Home')
		}
		,function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data
			$location.path('/Register')
		})
	}
	$scope.login=function()
	{
		console.log($scope.users)
		UserService.login($scope.users).then(function(response){
			$rootScope.currentUser=response.data	//response.data in user object
			$cookieStore.put('userDetails',response.data)
			$location.path('/Home')
		}
		,function(response){
			$scope.error=response.data.message
			$location.path('/Login')
		})
	}
	
	$scope.updateUsers=function(){
		UserService.updateUsers($scope.users).then(function(response){
			alert("Update user details successfull")
			$location.path("/Login")
		},function(response){
			if(response.status==401)
				{
				$location.path("/Login")
				}
			else{
				$scope.error=response.data
				$location.path("/Editprofile")
			}
		})
	}
	
	if($rootScope.currentUser!=undefined)
	{
		UserService.getUser().then(function(response){
			$scope.users=response.data//user object in json format, these user details get displayed in editprofile.html
		},
		function(response){
			console.log(response.status)
		})
	}
})