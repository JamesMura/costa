'use strict';

angular.module('costa')
.controller('MainCtrl', function ($scope,$http) {
  var update= function(){
    $http.get('/api/costs').
  success(function(data, status) {
    console.log('worked');
    $scope.costs = data;
  }).
error(function(data, status) {
  console.log(status);
});
};
update();

$scope.create = function(cost) {
  console.log(cost);
  $http.post('/api/costs', cost).
  success(function(data, status) {
    angular.copy({},cost);
    update();
  }).
error(function(data, status) {
  console.log(status);
  console.log(data);
});
};
});
