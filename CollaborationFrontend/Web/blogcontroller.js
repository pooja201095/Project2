/**
 * 
 */
myApp.controller('blogcontroller',function($scope,BlogService,$location)
		{
	$scope.addBlog=function(){
		BlogService.addBlog($scope.blog).then(function(response){
			alert('Blog Added successfully and waiting for approval!')
			$location.path('/GetBlogs')
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				{
				$location.path('/Login')
				}
			else{
				$location.path('/AddBlog')
			}
			
		})
	}
	function blogsApproved(){
		BlogService.blogsApproved().then(function(response){
			$scope.listOfBlogsApproved=response.data
		},function(response){
			if(response.status==401)
			{
			$location.path('/Login')
			}
		})
	}
	function blogsWaitingForApproval(){
		BlogService.blogsWaitingForApproval().then(function(response){
			$scope.listOfBlogsWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
			{
			$location.path('/Login')
			}
		})
	}
	blogsApproved()
	blogsWaitingForApproval()
	
		})