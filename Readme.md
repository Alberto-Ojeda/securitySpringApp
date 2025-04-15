#  Aplicación que utiliza Spring Security para poder autenticar usuarios 

### En esta aplicación utilizaremos las siguientes tecnologias 

- Spring Security
- Mysql
- Keycloack 

So Spring security have a problem with java 17 or +
so add to entries in jvm next options for solution
--add-opens java.base/java.lang=ALL-UNNAMED,
--add-opens java.base/java.io=ALL-UNNAMED,
--add-opens java.base/sun.security.util=ALL-UNNAMED