/**
 * 
 */
myApp.controller('friendcontroller',function($scope,FriendService,$location){
	function listOfSuggestedUsers()
	{
		FriendService.listOfSuggestedUsers().then
		(function(response){
			$scope.suggestedUsers=response.data
		},
		function(response){
			if(response.status==401)
			{
			$location.path('/Login')
			}
		})
	}
	
	$scope.sendFriendRequest=function(toId){
		FriendService.sendFriendRequest(toId)
		.then(
				function(response){
					alert("Friend Request sent successfully...!")
					listOfSuggestedUsers()
					$location.path('/getSuggestedUsers')
				},
				function(response){
					if(response.status==401)
					{
					$location.path('/Login')
					}
				})
	}
	
	function pendingRequests(){
		FriendService.pendingRequests()
		.then(
				function(response){
					$scope.pendingRequests=response.data
				},
				function(response)
				{
					if(response.status==401)
					{
					$location.path('/Login')
					}
				})
	}
	
	$scope.updatePendingRequests=function(request,statusValue){
		//before assignment request.status is P
		console.log(request)//it will show all values
		console.log(request.status)//P
		request.status=statusValue
		console.log(request.status)//A or D
		console.log(request)//it will show all values but status value is changed
		FriendService.updatePendingRequests(request).
		then(
				function(response){
					pendingRequests()
					$location.path('/pendingRequests')
				},
				function(response){
					if(response.status==401)
					{
					$location.path('/Login')
					}
				})
		
	}
	
	
	function listOfFriends()
	{
		FriendService.listOfFriends().then
		(function(response){
			$scope.friends=response.data //list<String>
		},function(response){
			if(response.status==401)
					{
					$location.path('/Login')
					}
		})
	}
	
	//function call
	listOfSuggestedUsers()
	pendingRequests()
	listOfFriends()
})