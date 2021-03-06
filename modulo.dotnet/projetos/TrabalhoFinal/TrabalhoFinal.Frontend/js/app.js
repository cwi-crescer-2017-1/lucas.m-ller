angular.module('app', ['auth', 'ui.router', 'toastr'])
.run(function($rootScope, $transitions, toastr, $state) {
    $rootScope.siteName = "Locações Crescer";
    $rootScope.showSpinner = false;
    // $transitions.onError({}, function() {
    //     $rootScope.showSpinner = false;
    //     $state.go('adm.erro');
    //     // toastr.error('Verifique a API e sua conexão', 'Erro ao carregar página!');
    // });
    $transitions.onStart({}, function() {
        $rootScope.showSpinner = true;
    });
    $transitions.onFinish({}, function() {
        $rootScope.showSpinner = false;
    });
})
.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
    .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'LoginCtrl',
        resolve: {
            verificaLogin: function(authService, $state) {
                if(authService.isAutenticado())
                    $state.go('adm.index');
            }
        }
    })
    .state('logout', {
        url: '/logout',
        resolve: {
            efetuarLogout: function(authService, toastr) {
                authService.logout();
                toastr.info('Você foi deslogado.');
            }
        }
    })
    .state('adm', {
        abstract: true,
        templateUrl: 'templates/index.html',
        resolve: {
            verificaLogin: function(authService) {
                return authService.isAutenticadoPromise();
            }
        }
    })
    .state('adm.erro', {
        url: '/erro',
        templateUrl: 'templates/adm/erro.html'
    })
    .state('adm.index', {
        url: '/index',
        templateUrl: 'templates/adm/index.html',
        controller: 'IndexCtrl',
        resolve: {
            locacoesAtrasadas: function(ApiService, authService) {
                return ApiService.relatorios.atrasos();
            }
        }
    })
    .state('adm.novoCliente', {
        url: '/clientes/novo',
        templateUrl: 'templates/adm/novoCliente.html',
        controller: 'NovoClienteCtrl'
    })
    .state('adm.editarCliente', {
        url: '/clientes/:id',
        templateUrl: 'templates/adm/editarCliente.html',
        controller: 'EditarClienteCtrl',
        resolve: {
            cliente: function(ApiService, $stateParams) {
                return ApiService.clientes.obter($stateParams.id);
            },
            locacoes: function(ApiService, $stateParams) {
                return ApiService.clientes.locacoes($stateParams.id);
            }
        }
    })
    .state('adm.novaLocacao', {
        url: '/locacao/:id',
        templateUrl: 'templates/adm/novaLocacao.html',
        controller: 'NovaLocacaoCtrl',
        resolve: {
            cliente: function(ApiService, $stateParams) {
                return ApiService.clientes.obter($stateParams.id);
            },
            produtos: function(ApiService) {
                return ApiService.itens.obterProdutos();
            },
            pacotes: function(ApiService) {
                return ApiService.itens.obterPacotes();
            },
            opcionais: function(ApiService) {
                return ApiService.itens.obterOpcionais();
            }
        }
    });
    $urlRouterProvider.otherwise('/index');
})
.constant('authConfig', {
    urlUsuario: 'http://localhost:64885/api/acessos/usuario',
    urlLogin: 'login',
    urlPrivado: 'adm.index',
    urlLogout: 'login'
});