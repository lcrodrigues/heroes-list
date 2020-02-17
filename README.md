# heroes-list
Aplicativo que consome a API da Marvel e lista seus personagens.

Utilizada arquitetura MVVM e Databinding para exibição dos dados, Retrofit para requisição HTTP da API da Marvel e Gson para tratar os objetos jSON, Glide para carregar imagens e coroutines para realizar as requisições fora da UI Thread.

Cores escolhidas com base nas paletas:
https://www.schemecolor.com/avengers-age-of-ultron-theme-colors.php e https://www.schemecolor.com/thor-comic.php

Pendências:
- Tratamento para erro socket timeout;
- Tratamento para erro na API request;
- Testes unitários e de aceitação.
