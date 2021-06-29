What this project about: <br>
It is multimodule gradle project where I'm experimenting with techs

#webapp<br>
Spring Boot based application just for testing purpose <br>
<b>Includes:</b> <br>
Spring JPA for DB support <br>
Spring Thymeleaf for Web UI <br>
Also there is a RestClient <br>
and some other stuff <br>


#testframework<br>
it is classical TestNG extendable test framework for multipurpose usages such as Selenium UI tests and Rest API\DB tests
with java code tests also support springboot for DI, supports parallel execution

#cucumber_stf<br>
Is cucumber + jUnit framework with parallel execution also it is multimodule inside <br>
There is Core part and subproject part <br>
<b>Core</b> part basically stores all common logic<br><br>
<b>Subproject</b> part can override some steps and fields or extend it<br>
In case of submodules architecture it allows separating tests and run them independently with their own report
for example one submodule tests rest api other submodule can test UI or even different part of system\systems 

