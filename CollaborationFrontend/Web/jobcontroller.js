/**
 * 
 */
myApp.controller('jobcontroller',function($scope,JobService,$location)
		{
	$scope.addJob=function() //this function is called automatically when admin clicks on post job button
	{
		JobService.addJob($scope.job)//here call the service
		.then
		(function(response){
			console.log(response.data)
			console.log(response.status)
			$location.path('/GetAllJobs')
		},function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.errorMsg=response.data.message
			if(response.status==401){
				$scope.error=response.data.message
				$location.path('/Login')
			}
			else{
				$scope.error=response.data.message
				$location.path('/AddJob')
			}
		})
	}
	
	function getAllJobs()
	{
		JobService.getAllJobs().then(
				function(response){
					$scope.jobs=response.data //List<Job>
				},function(response){
					//console.log(response.data)
					if(response.status==401){
						$location.path('/Login')
					}
				})
	}
	getAllJobs() //this statement gets executed automatically whenever the controller gets instantiated
	
	
	$scope.getJobDetails=function(jobId)
	{
		$scope.showJobDetails=true;
		JobService.getJobDetails(jobId)
		.then(
				function(response){
					$scope.job=response.data
				},
				function(response){
					console.log(response.data)
					if(response.status==401){
						$location.path('/Login')
					}
				})
	}
		})