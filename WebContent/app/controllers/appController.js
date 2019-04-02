angular.module('app').controller('appController', function($scope, $location, $http) {

  $scope.res = '';

  $scope.envia = () => {
    $http.post('http://localhost:3002/potencia', {
        valor: $scope.numero
      })
      .then(function(data) {
        console.log('deu bom');
        console.log(data);
        $scope.res = data.data.result;
      })
      .catch(function(data) {
        console.log(data);
      });
  }

$scope.bruno = () =>{
  $http({
    method: 'GET',
      url: 'http://www.ages.pucrs.br/modeloapi'
      // url: 'http://localhost:3002'
  }).then(function successCallback(response) {
    console.log('deu bom');
    console.log(response);
  }, function errorCallback(response) {
    console.log('deu ruim');
    console.log(response);
  });
}
});
