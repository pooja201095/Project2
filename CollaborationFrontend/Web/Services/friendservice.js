/**
 * 
 */
myApp.factory('FriendService',function($http){
	var friendservice={}
	var BASE_URL="http://localhost:8081/CollaborationMiddleware"
		friendservice.listOfSuggestedUsers=function()
		{
			return $http.get(BASE_URL+"/getSuggestedUsers")
		}
	friendservice.sendFriendRequest=function(toId)
	{
		return $http.get(BASE_URL+"/friendRequest/"+toId)
	}
	friendservice.pendingRequests=function()
	{
		return $http.get(BASE_URL+"/pendingRequests")
	}
	friendservice.updatePendingRequests=function(request)	//request is friend object
	{
		return $http.put(BASE_URL+"/updatePendingRequests",request)
	}
	friendservice.listOfFriends=function()
	{
		return $http.get(BASE_URL+"/listOfFriends")
	}
	return friendservice;
})