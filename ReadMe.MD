1. [Jaxb плагин для gradle](https://github.com/jacobono/gradle-jaxb-plugin)
1. [WSDL плагин для gradle](https://github.com/jacobono/gradle-wsdl-plugin)
1. [Основы конструкций Gradle](https://docs.gradle.org/4.6/dsl/)
1. [Java plugin](https://docs.gradle.org/4.6/userguide/java_plugin.html)
1. [Spring Web Services Documentation](https://docs.spring.io/spring-ws/docs/2.2.0.RELEASE/reference/htmlsingle/#tutorial) for contract-first SOAP service development
1. [Информация о веб сервисе для получения ежедневных данных ЦБР](http://www.cbr.ru/development/DWS/)


Что делает приложение:
1. Отправляет запрос на получение сегодняшнего курса валют от центро банка http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx

Работа с проектом:
Build всего проекта с выводом на консоль задач, что были выполнены:
``.\gradlew build --console verbose``
Генерация классов из wsdl:
``.\gradlew :daily-info-ws:wsimport``  или ``.\gradlew wsimport``
Генерация классов из xsd:
``.\gradlew xjc``
Запуск приложения:
``.\gradlew bootRun``


Проект с использование Spring Boot + Gradle
Создан клиент для SOAP ервиса http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL 
сгенерированы объекты из wsdl и xsd  помощью jacobono плагинов для gradle (модуль daily-info-ws)
Spring Web Service использован для написания клиента:
Наш класс DailyInfoClientImpl наследуется от WebServiceGatewaySupport. В классе ClientConfig настраиваем Маршалер для клиента
