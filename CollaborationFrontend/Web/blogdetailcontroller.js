/**
 * 
 */
myApp.controller('blogdetailcontroller',function($scope,BlogService,$location,$routeParams)
		{
	$scope.isRejected=false
	var id=$routeParams.id
	BlogService.getBlogById(id).then(function(response){
		console.log(response.data)
		$scope.blog=response.data
	},function(response){
		if(response.status==401)
			{
			$location.path('/Login')
			}
		
	})
	
	$scope.updateBlog=function(){
		BlogService.updateBlog($scope.blog).then(function(response){
			$location.path('/GetBlogs')
		},function(response){
			if(response.status==401)
			{
			$location.path('/Login')
			}
		})
	}
	
	$scope.setRejectionTxt=function(val){
		$scope.isRejected=val
	}
	
	$scope.updateLikes=function(){
		$scope.isLiked=!$scope.isLiked;
		if($scope.isLiked)
			{
			$scope.blog.likes=$scope.blog.likes+1
			}
		else{
			$scope.blog.likes=$scope.blog.likes-1
		}
		
			BlogService.updateBlog($scope.blog).then(function(response){
				console.log(reponse.data)
			},function(response){
				if(response.status==401)
				{
				$location.path('/Login')
				}
			})
		
	}
	
	
	
	//============insert into blogcomment==============//
	$scope.addComment=function()
	{
		console.log($scope.blogComment)
		$scope.blogComment.blog=$scope.blog
		console.log($scope.blogComment)
		BlogService.addComment($scope.blogComment)
		.then(function(response){
			$scope.blogComment.commentTxt=''
				getBlogComments()
		},function(response){
			console.log(response.status)
			if(response.status==401)
				{
				$location.path('/Login')
				}
			else{
				$location.path('/getBlogById/'+id)
			}
		})
		
	}
	
	function getBlogComments()
	{
		BlogService.getBlogComments(id)
		.then
		(function(response){
			$scope.blogComments=response.data
		},function(response){
			console.log(response.status)
			if(response.status==401)
				$location.path('/Login')
		})
	}
	getBlogComments()
		})