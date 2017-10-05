/**
 * 
 */
myApp.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8081/CollaborationMiddleware"
	userService.createUsers=function(users){
		alert("User Service "+users.userid)
		return $http.post(BASE_URL + "/createUsers",users)
	}
	userService.login=function(users){
		return $http.post(BASE_URL + "/login",users)
	}
	userService.logout=function(){
		return $http.put(BASE_URL + "/logout")
	}
	userService.getUser=function(){
		return $http.get(BASE_URL + "/getUser")
	}
	userService.updateUsers=function(users){
		return $http.put(BASE_URL + "/updateUsers",users)
	}
	return userService;
		
})