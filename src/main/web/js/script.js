var app = angular.module("app", []);

app.controller("booksController", function ($scope, $http) {
    $http({
        url: '/Servlet',
        method: 'GET'
    }).success(function (data) {
        $scope.books = data.books;

    });

});