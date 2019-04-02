angular.module('app',['ngRoute']).config(function ($routeProvider, $locationProvider){
  $locationProvider.html5Mode(true);

  $routeProvider.when('/',{
    templateUrl: 'public/html/home.html',
    controller: 'appController'
  });
});
