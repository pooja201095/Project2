/**
 * 
 */
myApp.factory('BlogService',function($http){
	var blogService={}
	var BASE_URL="http://localhost:8081/CollaborationMiddleware"
		blogService.addBlog=function(blog){
		return $http.post(BASE_URL + "/AddBlog",blog)
	}
	blogService.blogsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getBlogs/"+0)
	}
	blogService.blogsApproved=function(){
		return $http.get(BASE_URL + "/getBlogs/"+1)
	}
	blogService.getBlogById=function(id){
		return $http.get(BASE_URL + "/getBlogById/"+id)
	}
	blogService.updateBlog=function(blog){
		return $http.put(BASE_URL + "/update",blog)
	}
	
	blogService.addComment=function(blogComment)
	{
		console.log(blogComment)
		return $http.post(BASE_URL + "/addComment",blogComment)
	}
	blogService.getBlogComments=function(id){
		return $http.get(BASE_URL + "/getComments/"+id)
	}
	return blogService;
})