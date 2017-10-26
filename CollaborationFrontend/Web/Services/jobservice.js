/**
 * 
 */
myApp.factory('JobService',function($http){
	var jobservice={} //create instance
	var BASE_URL="http://localhost:8081/CollaborationMiddleware"
		jobservice.addJob=function(job)
		{
		return $http.post(BASE_URL+"/addJob",job)
		}
	jobservice.getAllJobs=function()
	{
	return $http.get(BASE_URL+"/getAllJobs")
	}
	jobservice.getJobDetails=function(jobId){
		
		return $http.get(BASE_URL+"/getJob/"+jobId)
	}
	return jobservice;
})