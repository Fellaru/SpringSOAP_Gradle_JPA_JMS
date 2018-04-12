1. [Jaxb плагин для gradle](https://github.com/jacobono/gradle-jaxb-plugin)
1. [WSDL плагин для gradle](https://github.com/jacobono/gradle-wsdl-plugin)
1. [Основы конструкций Gradle](https://docs.gradle.org/4.6/dsl/)
1. [Java plugin](https://docs.gradle.org/4.6/userguide/java_plugin.html)


Работа с проектом:
Build всего проекта с выводом на консоль задач, что были выполнены:
``.\gradlew build --console verbose``
Генерация классов из wsdl:
``.\gradlew :daily-info-ws:wsimport``  или ``.\gradlew wsimport``
