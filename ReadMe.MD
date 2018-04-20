TODO 
- изучить iBatis
- ПРОЧЕСТЬ и законспектировать [Java Transaction API](https://docs.oracle.com/javaee/7/tutorial/transactions.htm#BNCIH)
- Конспект по документации только Hibernate

#Литература
1. [Jaxb плагин для gradle](https://github.com/jacobono/gradle-jaxb-plugin)
1. [WSDL плагин для gradle](https://github.com/jacobono/gradle-wsdl-plugin)
1. [Основы конструкций Gradle](https://docs.gradle.org/4.6/dsl/)
1. [Java plugin](https://docs.gradle.org/4.6/userguide/java_plugin.html)
1. [Spring Web Services Documentation](https://docs.spring.io/spring-ws/docs/2.2.0.RELEASE/reference/htmlsingle/#tutorial) for contract-first SOAP service development
1. [Информация о веб сервисе для получения ежедневных данных ЦБР](http://www.cbr.ru/development/DWS/)

------------- JPA -------------------------------
1. [JPA java ee 7 tutorial](https://docs.oracle.com/javaee/7/tutorial/partpersist.htm#BNBPY)
1. [Java Persistence 2.1 Specification](http://download.oracle.com/otndocs/jcp/persistence-2_1-fr-eval-spec/index.html)
1. [Статья с HabraHabr с основными моментами JPA, Hibernate](https://habrahabr.ru/post/265061/)
1. [Хорошие ответы на вопросы по Hibernate](http://javastudy.ru/interview/jee-hibernate-questions-answers/)
1. [Hibernate Cache](https://habrahabr.ru/post/135176/)
1. [The JPA 2.0 EntityManager vs. the Hibernate Session: Which one to use](https://www.theserverside.com/news/2240186700/The-JPA-20-EntityManager-vs-the-Hibernate-Session-Which-one-to-use)
1. [Hibernate основы работы с сессиями и транзакциями](https://habrahabr.ru/post/271115/)
1. [Container vs Application managed entitymanager](http://www.byteslounge.com/tutorials/container-vs-application-managed-entitymanager)
1. [Баг Spring Boot Postgresql](https://stackoverflow.com/questions/43905119/postgres-error-method-org-postgresql-jdbc-pgconnection-createclob-is-not-imple)

-------------------JTA---------------------------------
1. [Java Transaction API 1.3 Specification](https://jcp.org/en/jsr/detail?id=907)
1. [Transactional java ee 7 tutorial](https://docs.oracle.com/javaee/7/tutorial/transactions.htm#BNCIH)
1. [Отличная статья про модели транзакций с предостерижениями и наставлениями от опытного разработчика](https://www.ibm.com/developerworks/ru/library/j-ts2/index.html)
Темы с Habrahabr по JTA
1. [1](https://habrahabr.ru/post/62484/)
1. [1](https://habrahabr.ru/post/325470/)
1. [1](https://habrahabr.ru/post/165223/)
1. [1](https://habrahabr.ru/company/mosigra/blog/216129/)
1. [1](https://habrahabr.ru/post/317884/)
1. [1](https://habrahabr.ru/post/153321/)

-------------------Hibernate---------------------------------
1. [1](https://habrahabr.ru/post/268747/)


# О приложении
Что делает приложение:
1. Отправляет запрос на получение сегодняшнего курса валют от центро банка http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx

**Работа с проектом:**
 - Build всего проекта с выводом на консоль задач, что были выполнены:
``.\gradlew build --console verbose``
 - Генерация классов из wsdl:
``.\gradlew :daily-info-ws:wsimport``  или ``.\gradlew wsimport``
 - Генерация классов из xsd:
``.\gradlew xjc``
 - Запуск приложения:
``.\gradlew bootRun``




###Что тут сделанно?

Проект с использование Spring Boot + Gradle.

Создан клиент для SOAP ервиса http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL .

сгенерированы объекты из wsdl и xsd  помощью jacobono плагинов для gradle (модуль daily-info-ws).

Spring Web Service использован для написания клиента:
Наш класс DailyInfoClientImpl наследуется от WebServiceGatewaySupport. В классе ClientConfig настраиваем Маршалер для клиента.


#Теория
## S.O.L.I.D principles
 + Single Responsibility Principle
 + Open/Closed Principle
 + Liskov Substitution Principle
 + Interface Segregation Principle
 + Dependency Inversion Principle
 
 
### Hibernate vs Mybatis
Где хорошо работает Hibernate (Объект мапиться на Таблицу, реализует JPA)
- Общая функциональность C.R.U.D

Где хорошо работает Mybatis (Объект мапиться на sql запрос, не реализует JPA)
- специальные Select, поддержка очень специфических или сложных Selectов


### SessionFactory(Hibernate) vs. EntityManagerFactory(JPA)
[Хороший ответ на StackOverFlow](https://stackoverflow.com/questions/5640778/hibernate-sessionfactory-vs-entitymanagerfactory)
Предпочитайте  EntityManagerFactory. Вы всегда сможете взять session  из EntityManager, если вам понадобиться дополнительная функциональность

```
Session session = entityManager.unwrap(Session.class);
```
 
#JPA 

Читать вместе с [этой статьей](https://habrahabr.ru/post/265061/) 

 Уровни абстракции:
 + JDBC
 + ORM-framework (абстрагируется от JDBC, так как у каждой базы данных свой драйвер, синтаксис и пр.)
 + JPA (абстрагируется от определенного поставщика ORM-фреимворка)(Единый интерфейс для Java)
 
 
 Java Persistence API предоставляет разработчикам Java object/relational mapping решение для управления реляционными данными в Java-приложениях.
  Java Persistence состоит из четырех областей:
 + The Java Persistence API
 + The query language
 + The Java Persistence Criteria API
 + Object/relational mapping metadata
 
 **JPA (Java Persistence API)** это спецификация Java EE и Java SE, описывающая систему управления сохранением java объектов
  в таблицы реляционных баз данных в удобном виде. Сама Java не содержит реализации JPA, однако есть существует много 
  реализаций данной спецификации от разных компаний (открытых и нет). 
 
 **Entities**
 Entity это легковесный хранимый объект бизнес логики (persistent domain object). Как правило, объект представляет таблицу
 в реляционной базе данных, и каждый экземпляр объекта соответствует строке в этой таблице. 
 Вообще Первичная(основная) программная сущность это entity класс, который так же может использовать дополнительные классы,
 который могут использоваться как вспомогательные классы или для сохранения состояния еntity.
 
 
 **Требования к Entity классам**
 1. Entity класс должен быть аннотирован javax.persistence.Entity аннотацией.
 1. Класс должен иметь public or protected, конструктор без аргументов. Класс может иметь любые другие дополнительные конструкторы.
 1. Entity класс не может быть финальным классом(final class). Никакие методы или переменные экземпляра не должны быть объявлены final.
 1.  Если объект Entity класса будет передаваться по значению как отдельный объект (detached object), например через 
 удаленный интерфейс (through a remote interface), он так же должен реализовывать Serializable интерфейс, 
 1. Entities могут наследоваться от обоих entity and non-entity классов, и  non-entity классы могут наследоваться от entity классов.
 1. Поля Entity класс должны быть напрямую доступны только методам самого Entity класса(должны быть объявлены private, 
 protected, or package-private) и не должны быть напрямую доступны другим классам, использующим этот Entity. Такие классы
  должны обращаться только к методам (getter/setter методам или другим методам бизнес-логики в Entity классе).
 1.Enity класс должен содержать первичный ключ, то есть атрибут или группу атрибутов которые уникально определяют запись
  этого Enity класса в базе данных.(Чуть дальше в главе "Primary Keys and Entity Identity" написано Every entity must have a primary key.)
  
  
 Почему мы не должны делать Entity class как final?
 Хибернейт использует прокси классы для ленивой загрузки данных (т.е. по необходимости, а не сразу). Это достигается с
 помощью расширения entity bean и, следовательно, если бы он был final, то это было бы невозможно. Ленивая загрузка данных 
 во многих случаях повышает производительность, а следовательно важна.
  
  
**Что бы поле Entity объекта не было представлено в БД используейте аннотацию @Transient(annotation is used to indicate that a field is not to be persisted in the database)
или ключевое слово transient (is used to denote that a field is not to be serialized).**

JPA указывает что она может работать: 
 - с полями (field)  
 - с свойствами классов (property) - это JavaBeans                               
 Оба эти типа элементов Entity класса называются **атрибутами** Entity класса.
    
    
    

  **Допустимые типы атрибутов у Entity классов:**
   1. примитивные типы и их обертки Java,
   1. строки, 
   1. любые сериализуемые типы Java (реализующие Serializable интерфейс),
   1. enums; 
   1. entity types; 
   1. embeddable классы
   1. и коллекции типов 1-6
   
   
####**Использывание Сollections в полях и свойствах Entity классов**
 
Могут использоваться следующие интерфейсы коллекций:
1. java.autil.Collection
1. java.autil.Set
1. java.autil.List
1. java.autil.Map

Если свойство или поле состоит из коллекции используйте аннотацию: 
**javax.persistence.ElementCollection**

У анатации @ElementCollection есть два аттрибута:
 - targetClass (атрибут задает имя класса базового или внедряемого(Embedded) класса и является необязательным, если поле или свойство определено с помощью generic'ов)
 - fetch (используется, чтобы указать, следует ли подтягивать данные из БД лениво(LAZY) или нетерпеливо(EAGER), 
 используя константы javax.persistence.FetchType либо LAZY, либо EAGER соответственно. По умолчанию коллекция будет взята LAZY.)
 
 
**При использовании элементов Map или отношений применяются следующие правила:**
- Map ключ или значение могут быть basic Java programming language type(примитивные типы и их Java обертки, строки), embeddable кассы, или entity.
- Когда значение(value) в Map является embeddable классом или  базовым типом(basic type), используй the @ElementCollection аннотацию.
- Когда значение(value) в Map - entity, используй @OneToMany или @ManyToMany аннотиции.
- Используй Map только на одной стороне двунаправленного отношения. (Use the Map type on only one side of a bidirectional relationship.)


Если тип ключа(key) в Map является базовым типом языка программирования Java(примитивные типы и их Java обертки, строки),
 используйте аннотацию [**javax.persistence.MapKeyColumn**](https://docs.oracle.com/javaee/6/api/javax/persistence/MapKeyColumn.html),
чтобы установить сопоставление столбца для ключа. По умолчанию атрибут **name** анотации @MapKeyColumn имеет форму 
имяСвойства_KEY (к названию свойства добавляется _KEY)

Если тип ключа(key) в Map является entity используйте
 [**javax.persistence.MapKeyJoinColumn**](https://docs.oracle.com/javaee/6/api/javax/persistence/MapKeyJoinColumn.html) аннотацию.
  Если нет атрибута **name** в @MapKeyJoinColumn, имя столбца сопоставления по умолчанию устанавливается имяСвойства_KEY (к названию свойства добавляется _KEY)
  
  
Если Map значение(value) является entity и частью однонаправленного(unidirectional) отношения «многие ко многим» или
 «один ко многим», оно будет отображаться как таблица соединений в базовой базе данных.
 
 !!! Однонаправленное отношение «один ко многим», которое использует Map, также может быть отображено с помощью аннотации @JoinColumn.!!!

Если entity является частью двунаправленного(bidirectional) отношения «один ко многим / многие к одному», он будет 
отображаться в таблице entity, которая представляет значение(value) Map. Если generic'и не используются, атрибут targetEntity
 аннотации @OneToMany и @ManyToMany должен быть установлен в тип значения(value) Map.
 
 **Валидация (Проверка) полей и свойств Entity**
 - @Pattern
 - @Past (прошлая дата)
 - @NotNull
 
 
### **Первичные ключи (Primary Keys) в Entity**
- простые первичные ключи помечаем поле аннотацией **javax.persistence.Id**
-  entity composite keys - состовные первичные ключи (когда первичный ключ состоит из нескольких атрибутов).  

Композитные(составные) первичные ключи должны быть представленны в отдельном классе(primary key class). Для обозначения
 составных первичных ключей используются аннотации:
- [javax.persistence.EmbeddedId](http://www.thejavageek.com/2014/05/01/jpa-embeddedid-example/) (предпочтительней)
- [javax.persistence.IdClass](http://www.thejavageek.com/2014/05/01/jpa-idclass-example/)

Класс первичного ключа(primary key class) должен удовлетворять требованиям:
- модификатор доступа к классу должен быть public
- свойства класса первичного ключа должны быть public или protected, если используется property-based access
- класс должен иметь дефолтный public конструктор
- класс должен реалтзовывать hashCode() и equals(Object other) методы
- класс должен быть сериализуемым(serializable)
- составной первичный ключ должен быть представлен и сопоставлен с несколькими полями или свойствами Entity класса или
 должен быть представлен и отображен как встраиваемый(Embeded) класс.
- Если класс сопоставляется с несколькими полями или свойствами Entity класса, то имена и типы полей или свойств 
первичного ключа в классе первичного ключа(primary key class) должны соответствовать именам Entity класса.


### Отношение Entity
1. **One-to-one**  (*javax.persistence.OneToOne*)
1. **One-to-many** (*javax.persistence.OneToMany*)
1. **Many-to-one** (*javax.persistence.ManyToOne*)
1. **Many-to-many** (*javax.persistence.ManyToMany*)

**Направление в отношениях сущностей**
1. ***Bidirectional***  (A bidirectional relationship has both an owning side and an inverse side)
    * В двунаправленном отношении каждый объект имеет поле отношений или свойство, которое относится к другому объекту
    + Двунаправленные отношения должны следовать этим правилам:
         1. обратная(inverse side) сторона двунаправленной связи должна ссылаться на сторону владельца(owning side),
          используя атрибут **mappedBy** аннотации @OneToOne, @OneToMany или @ManyToMany. Элемент mappedBy обозначает 
          свойство или поле в Entity, который является владельцем отношения.
         1. сторона Many двунаправленных отношений «many-to-one» не должна определять атрибут mappedBy. 
         Entity со стороны Many всегда является владельцем отношения
         1. для  one-to-one bidirectional отношений сторона-владелец соответствует стороне, которая содержит 
         соответствующий внешний ключ(foreign key).
         1. для many-to-many bidirectional отношений, любая сторона может быть стороной, владельцем.
         
1. ***Unidirectional***  (A unidirectional relationship has only an owning side)
   + В Unidirectional(однонапрвленном) отношение, только одна Entity имеет поле отношений или свойство,
    которое относится к другому.


+ The owning side of a relationship determines how the Persistence runtime makes updates to the relationship in the database.
+ The direction of a relationship determines whether a query can navigate from one entity to another.

**Каскадные операции** Enum javax.persistence.CascadeType записывается в атрибуте **cascade** всех аннотаций определяющих тип отношения
**Orphan Removal** булевое значение(true/false) в атрибуте **orphanRemoval** аннотаций @OneToMany и @oneToOne,
обозначает что автоматически удаляются осиротевшие зависимые Entity
[В чем разница между Каскадным удалением и Orphan удалением ](http://qaru.site/questions/46487/what-is-the-difference-between-cascade-and-orphan-removal-from-db)

Пример:
```
   @OneToMany(cascade=REMOVE, mappedBy="customer")
   public Set<CustomerOrder> getOrders() { return orders; }
   
   @OneToMany(mappedBy="customer", orphanRemoval="true")
   public List<CustomerOrder> getOrders() { ... }   
   ```
   
### Embeddable классы в Entities
Теже правила что и для Entity(Кроме идентификатора), Embededdable классы не имеют id.

Сами Embeddable классы помечаются аннотацией **javax.persistence.Embeddable**, а вот Embedded поля или свойста в Entity
(поля представляющие Embeddable класс) помечаются аннотацией **javax.persistence.Embedded**. Но ставить @Embedded аннотацию не обязательно.

Embeddable классы сами могут использовать другие Embeddable классы для представления своего состояния.

Embeddable классы могут также содержать отношения к другим Entity или collections Entities. Если Embeddable(вложенный)
класс имеет такую ​​взаимосвязь, то отношение происходит от Entity объекта или коллекции Entities к Entity, которому принадлежит Embeddable класс.

#### [Наследование Entity](https://docs.oracle.com/javaee/7/tutorial/persistence-intro002.htm#BNBQN)
  
  - Наследование от Entity класса от другого класса являющегося абстрактным и Entity. 
  ``@Entity
    public abstract class BLAbla``
     - Абстрактные объекты могут быть запрошены(в Query) точно так же, как конкретные объекты.
      Если абстрактный объект является объектом запроса, запрос работает со всеми конкретными подклассами абстрактного Entity класса
  
  - Наследование от класса не являющемся Entity, но содержащему поля состояние и информацию о сопоставлении.
     - Такой класс должен быть помечен аннотацией **javax.persistence.MappedSuperclass**
     - MappedSuperclass суперклассы не могут быть запрошены(использоваться в Query) и не могут использоваться в транзакциях EntityManager или Query.
     
  - Наследование от класса не являющимся Entity
      -  The state of non-entity superclasses is nonpersistent, and any state inherited from the non-entity superclass by an entity class is nonpersistent.
      (Как я поняла поля суперкласса не записываются в БД)
      - Такие классы не могут быть запрошены(использоваться в Query) и не могут использоваться в транзакциях EntityManager или Query.
    
    
  **Entity Inheritance Mapping Strategies**
  Вы можете настроить, как JPA сопоставляет унаследованные объекты с базовым хранилищем данных(datastore), аннотируя
   корневой класс иерархии(суперкласс) аннотацией **javax.persistence.Inheritance**. 
   
   Для сопоставления данных Entity с базой данных используются следующие стратегии сопоставления:
   - SINGLE_TABLE (Единая таблица для иерархии классов)
   - JOINED (При стратегии «joined» поля или свойства, относящиеся к подклассу, отображаются в другую таблицу, чем поля или свойства, общие для родительского класса)
   - TABLE_PER_CLASS (Своя таблица для каждого конкретного класса Entity) (Некоторыми не поддерживается. И не рекомендуется ее использовать)
   
   По умолчанию используется InheritanceType.SINGLE_TABLE
   
# Managing Entities

Persistence Context и Entity Manager - два базовых концепта (определения) Java Persistence API (JPA).

- **Persistence Context** отвечает за управление JPA entity:

    Когда приложение загружает entity из базы данных, entity на самом деле хранится в Persistence Context, поэтому entity
    становится управляемым Persistence Context'ом. Все дальнейшие изменения производимые над этим entity будут отслеживаться
    Persistence Context'ом.

    Persistence Context также будет при необходимости отправлять измененые entities в базу данных, когда это необходимо.
    Когда транзакция завершается(commits), связанный Persistence Context также будет  отправлять любые возможные ожидающие
    изменения в базе данных. Это некоторые из операций, которые обрабатываются Persistence Context.


- **Entity Manager**
  интерфейс для взаимодействия приложения с Persistence Context.
  
** ** **  

 Существуют два типа Persistence Context:
+ Container Managed
+ Application Managed

** ** ** 

Из документации Java EE :

Управление Entity осуществляет entity manager, который представляет инстанс **javax.persistence.EntityManager** интерфйса.
Каждый объект EntityManager связан с контекстом персистентности(Persistence Context): набором managed entity, которые 
существуют в конкретном хранилище данных. Контекст персистентности определяет область, в которой создаются, сохраняются и 
удаляются экземпляры конкретных entity. Интерфейс EntityManager определяет методы, которые используются для взаимодействия
с контекстом персистентности.

###**Сontainer-managed entity manager**

**PersistenceContext is managed by the enterprise container.** При этом контейнер ответственен за создание PersistenceContext,
 его внедрение в компоненты и также несет ответственность за его удаление в конце текущей транзакции.

С EntityManager'ом управляемым контейнером(например Jboss) (container-managed entity manager), persistence context принадлежащий экземпляру EntityManager 
автоматически распространяется контейнером ко всем компонентам приложения, которые используют экземпляр EntityManager 
в рамках одной транзакции Java Transactional API (JTA).

Обычно транзакции JTA включают вызовы между компонентами приложения. Для завершения транзакции JTA эти компоненты обычно
нуждаются в доступе к одному PersistenceContext. Это происходит, когда EntityManager вводится в компоненты приложения
с помощью аннотации javax.persistence.PersistenceContext. Контекст сохранения автоматически распространяется с текущей
транзакцией JTA, а ссылки EntityManager, сопоставленные с одним и тем же модулем сохранения, обеспечивают доступ к 
контексту персистентности внутри этой транзакции. При автоматическом распространении контекста персистентности компонентам
приложения не нужно передавать ссылки на экземпляры EntityManager друг другу, чтобы вносить изменения в одну транзакцию.
Контейнер Java EE управляет жизненным циклом управляемым контейнером EntityManager'ом(container-managed entity manager).

** ** **

Используя аннотацию **javax.persistence.PersistenceContext** мы можем inject(внедрить) container-manage EntityManager в наше приложение.

```
@PersistenceContext
  EntityManager em;
```
 
###**Application-Managed Entity Managers**

С EntityManager'ом управляемым приложением (application-managed entity manager) контекст персистентности(PersistenceContext)
не распространяется на компоненты приложения, а жизненный цикл экземпляров EntityManager управляется приложением.
  
Application-managed entity manager используется в случаях, когда приложениям необходимо получить доступ к 
PersistenceContext, который не передается с транзакцией JTA через экземпляры EntityManager в определенном модуле сохранения.
( in a particular persistence unit) (Что за модуль сохранения??!!) 


!!**In this case, each EntityManager creates a new, isolated persistence context.** В этом случае каждый EntityManager
создает новый изолированный persistence context. EntityManager и связанный с ним persistence context создаются и уничтожаются
явным образом приложением. Они также используются, когда непосредственное впрыскивание экземпляров EntityManager невозможно,
поскольку **экземпляры EntityManager не являются потокобезопасными. Экземпляры EntityManagerFactory являются потокобезопасными.**

Приложение создает Entity Manager с помощью javax.persistence.EntityManagerFactory -> entityManagerFactory.createEntityManager()

Получить экземпляр EntityManagerFactory можно используя аннотацию **javax.persistence.PersistenceUnit**

```
@PersistenceUnit
EntityManagerFactory emf;
```

```
EntityManager em = emf.createEntityManager();
```

!Про транзакции
Application-managed entity managers don't automatically propagate the JTA transaction context.

Nакие приложения должны вручную получить доступ к диспетчеру транзакций JTA и добавить информацию о демаркации транзакции
при выполнении операций с объектами. Интерфейс javax.transaction.UserTransaction определяет методы для begin, commit,
and rollback  транзакций. Внесите экземпляр UserTransaction, создав переменную экземпляра, аннотированную с помощью @Resource:

```
@Resource
UserTransaction utx;
```

Полный пример:
```$xslt
@PersistenceUnit
EntityManagerFactory emf;
EntityManager em;
@Resource
UserTransaction utx;
...
em = emf.createEntityManager();
try {
    utx.begin();
    em.persist(SomeEntity);
    em.merge(AnotherEntity);
    em.remove(ThirdEntity);
    utx.commit();
} catch (Exception e) {
    utx.rollback();
}
```

#####Управление жизненным циклом экземпляра Entity
 Вы управляете жизненым циклом Entity , вызывая операции EntityManager'а. Entity могут находится в одном из 4-х состояний:
 - **new** Новые экземпляры Entity не имеют persistent identity(сгенерированных первичных ключей) и еще не связаны с persistence context.
 - **managed** Управляемые экземпляры Entity имеют persistent identity и связаны с persistence context.
 - **detached** Отдельные экземпляры Entity имеют persistent identity и в настоящее время не связаны с persistence context.
 - **removed** Удаленные экземпляры Entity имеют persistent identity, связанные с persistence context и планируются 
 для удаления из хранилища данных(БД).
 
 
 #####Цитата про  разлиция в new и detached
 Открою Вам тайну - у entity предусмотрено четыре состояния. Отцы-основатели HIBERNATE в своей книге называют следующие
  названия: transient, persistent, detached и removed. В спецификации JPA 2.1 их тоже четыре, но с немного другими 
  именами: new, managed, detached и removed. New и detached сходны между собой в том, что они не хранятся в кэш первого 
  уровня, а отличаются тем, что у new нет в БД образа (с тем же ID), а у detached - есть. JPA 2.1 предполагает разную 
  реакцию new и detached объектов на действия. Например, если применить метод persist к new, то он станет managed, а если
  применить метод persist к detached, то должна быть поднята ошибка.
  
  
 
#####Некоторые методы EntityManager'а: (EntityManager.метод)
1. **Для операций над Entity:**
    - **persist**
       - Новый экземпляр Entity стновится managed and persistent при вызове метода persist или посредством каскадной 
        операции persist, вызываемой из связанных объектов, которые имеют элементы cascade = PERSIST или cascade = ALL,
        установленные в аннотации отношений. Т.е данные объекта сохранятся в базе данных как только транзакция, связанная с
        persist завершиться. Если entity уже managed, тогда операция persist игнорируется, хотя операция persist будет 
        каскадироваться к связанным объектам, у которых каскадный элемент установлен в PERSIST или ALL в аннотации отношений.
        Если entity находится в состоянии detached, тогда операция persist будет throw IllegalArgumentException, или commit
        транзакции завершиться ошибкой. Операция persist распространяется на все entity, относящиеся к вызывающему entity,
        у которого в аннотации отношений cascade аттрибут установлен в ALL или PERSIST.
     
    - **remove**
        - Managed экземпляры entity удаляются путем вызова remove метода или посредством каскадной операции remove, вызываемой
         из связанных объектов, которые имеют элементы cascade = REMOVE  или cascade = ALL, становленные в аннотации отношений.
         Если метод удаления вызывается на новом объекте, операция удаления игнорируется, хотя remove будет каскадироваться
         к связанным объектам, у которых каскадный элемент установлен в REMOVE или ALL в аннотации отношений. Если remove 
         вызывается для detached entity, тогда операция remove будет вызывать исключение IllegalArgumentException, или commit
         транзакции завершиться ошибкой. Данные entity будут удалены из хранилища данных после завершения транзакции или в 
         результате операции flush(сброса).
      
    - **flush**
       - Состояние persistent entities синхронизируется с базой данных, когда совершается транзакция, с которой связана entity.
        Если управляемый entity находится в двунаправленной связи с другим управляемым entity, данные будут сохраняться,
        основываясь на стороне которой принадлежит отношение. Чтобы принудительно синхронизировать управляемый entity 
        с хранилищем данных, вызовите метод flush экземпляра EntityManager. Если объект связан с другим объектом, а аннотация
        отношения имеет аттрибут cascade, установленный в PERSIST или ALL, данные связанного объекта будут синхронизированы
        с хранилищем данных, когда вызывается flush.
        
    - **merge**
        - обновление
        - Если статус detached, то либо данные будет скопированы в существующей managed entity с тем же первичным ключом, либо создан новый managed в который скопируются данные,
        - Если статус Entity new, то будет создана новый managed entity, в который будут скопированы данные прошлого объекта,
        - Если статус managed, операция игнорируется, однако операция merge сработает на каскадно зависимые Entity, если их статус не managed,
        - Если статус removed, будет выкинут exception сразу или на этапе commit'а транзакции,
    
    - **refresh**
        - обновление данных.
        - Если статус Entity managed, то в результате операции будут востановлены все изменения из базы данных данного Entity, так же произойдет refresh всех каскадно зависимых объектов,
        - Если статус new, removed или detached, будет выкинут exception
        
    - **detach**
        - удаление из управление JPA
        - Если статус Entity managed или removed, то в результате операции статус Entity (и всех каскадно-зависимых объектов) станет detached.
        - Если статус new или detached, то операция игнорируется,
        
    - **lock**
        - блокирование Enity от изменений в других thread
        
1. Получение данных:
    - **find**
        - метод используется для поиска entity в БД по первичному ключу
        
    - **createQuery**, createNamedQuery, createNativeQuery, contains, createNamedStoredProcedureQuery, createStoredProcedureQuery
     
1. Получение других сущностей JPA: getTransaction, getEntityManagerFactory, getCriteriaBuilder, getMetamodel, getDelegate
1. Работа с EntityGraph: createEntityGraph, getEntityGraph
1. Общие операции над EntityManager или всеми Entities: close, isOpen, getProperties, setProperty, clear     
     
 ** ** **        
    
***Persistence Units***
Persistence Units является логической группировкой, которая содержит информацию, такую ​​как конфигурация EntityManagerFactory,
набор entity классов, метаданные отображения.

Persistence Units определяет набор всех классов entity, которые управляются экземплярами EntityManager в приложении.
Этот набор entity классов представляет данные, содержащиеся в одном хранилище данных. 


 ** ** **        
 
##JTA (Java Transaction API)

Транзакции контролируют одновременный доступ к данным несколькими программами. В случае сбоя системы, транзакции гарантируют,
 что после восстановления данные будут в согласованном состоянии
 
В приложении Java EE транзакция представляет собой серию действий, которые должны завершиться успешно, или все изменения
в каждом действии будут отменены. Транзакции заканчиваются commit'ом(совершить, фиксировать) или rollback'ом(откат).

Java Transaction API (JTA) позволяет приложениям получать доступ к транзакциям таким образом, который не зависит от
конкретных реализаций. JTA определяет стандартные интерфейсы Java для работы с транзакциями.

JTA определяет интерфейс **UserTransaction**, который приложения используют для запуска, фиксации или откат транзакций

Application components get a UserTransaction object through a JNDI lookup by using the name java:comp/UserTransaction or
 by requesting injection of a UserTransaction object. An application server uses a number of JTA-defined interfaces to 
 communicate with a transaction manager; a transaction manager uses JTA-defined interfaces to interact with a resource manager.



Чтобы изобразить бизнес-транзакции, программе может потребоваться выполнить несколько шагов. Например, финансовая программа
может переводить средства с расчетного счета на сберегательный счет, используя шаги, перечисленные в следующем псевдокоде:

```$xslt
begin transaction
    debit checking account
    credit savings account
    update history log
commit transaction
```

Либо все, либо ни один из трех шагов не должен завершаться. В противном случае целостность данных теряется. Поскольку этапы
внутри транзакции являются единым целым, транзакция часто определяется как неделимая единица работы.

Транзакция может завершиться двумя способами: с commit(фиксацией) или с rollback(откатом). Когда транзакция совершается(commit),
изменения данных, произошедшие внутри транзакции, сохраняются. Если внутри транзакции что-то идет с ошибкой, транзакция 
откатывается, отменяя все действие произошедшие внутри транзакции. Например, в псевдокоде, если во время шага кредита 
произошел сбой диска, транзакция будет откатываться и отменять изменения данных, сделанные оператором дебетования.
Несмотря на то, что транзакция завершилась неудачей, целостность данных будет неповрежденной, поскольку учетные записи
по-прежнему сохраняются.
 
 
**Транзакции:**
- **container-managed** transactions
- **bean-managed** transactions

** ** ** 

####**Container-Managed Transactions**
При таком типе Транзакцией управляет EJB container. Разработка с таким типом транзакцией является простой и удобной,
т.к. в Enterprise bean c container-managed transaction demarcation(разграничении), container EJB устанавливает границы транзакций.
Таким образом код не содержит операторов, которые начинают и заканчивают транзакцию.
 
По умолчанию, если не указан тип разграничения транзакции, Enterprise bean используют Container-Managed Transactions.

Как правило, контейнер начинает транзакцию непосредственно перед запуском метода enterprise bean и совершает транзакцию 
непосредственно перед выходом метода. Каждый метод может быть связан с одной транзакцией. Вложенные или множественные 
транзакции не допускаются в рамках метода.

Операции, управляемые контейнером, не требуют, чтобы все методы были связаны с транзакциями. При разработке компонента 
можно указать атрибуты транзакции, чтобы указать, какие из методов компонента связаны с транзакциями.

- Enterprise beans использующие container-managed transaction разграничения **не должны использовать какие-либо методы 
управления транзакциями, которые мешают границам container-managed transaction разграничениям.** Примерами таких методов
являются методы commit, setAutoCommit и rollback java.sql.Connection или методы commit и rollback javax.jms.Session.
Если вам требуется контроль над разграничением транзакций, вы должны использовать application-managed transaction demarcation(разграничение).
- Enterprise beans которые используют container-managed transaction также **не должны использовать javax.transaction.UserTransaction интерфейс.**

** ** ** 

**Атрибуты транзакции**

Атрибут транзакции контролируют границы транзакции(scope of a transaction).

Атрибут транзакции может иметь одно из следующих значений:

- **Required** 
    - Если к методу применяется атрибут Required и метод вызывается в области видимости ранее начатой транзакции, то именно
     она будет использоваться при выполнении метода. В противном случае метод начнет новую транзакцию. Этот атрибут является
     аттрибутом по умолчанию.
     
- **RequiresNew** 
    - Если RequiresNew применяется к методу, то новая транзакция начинается (и, соответственно, должна быть закончена в данном методе) всегда!
     вне зависимости от того, был ли вызван метод в контексте существующей транзакции или нет.
     
     - Если метод был вызван в контексте некой транзакции (назовем ее Transaction1) то:
        - Transaction1 будет приостановлена
        - будет начата новая транзакция (Transaction2)
        - Выполнение метода теперь делегируется новой транзакции (Transaction2).
         При завершении метода methodA() транзакция Transaction2 либо подтверждается, либо откатывается.
        - После чего возобновляется выполнение Transaction1(после окончания Transaction 1)
        
     - Если метод не был связан с транзакцией, контейнер запускает новую транзакцию перед запуском метода.
     
     - ВАЖНО: Такая схема работы очевидным образом нарушает принцип ACID (атомарность, согласованность, изолированность,
     стойкость), присущий транзакциям (особенно атомарность). Другими словами, операции изменения данных в БД более не
     содержатся внутри одной единицы работы. Если транзакцию Transaction1 придется откатить, то результаты Transaction2
     все равно останутся подтвержденными. Если так, то зачем же нужен этот атрибут? Как объяснялось в первой статье, 
     он должен использоваться только для операций, которые должны производиться вне зависимости от исхода первой 
     транзакции (Transaction1), например, для ведения аудита или журналирования.

- **Mandatory**
    - Если к методу применяется атрибут Mandatory и метод вызывается в области видимости ранее начатой транзакции,
     то она будет использоваться при выполнении метода. Однако если метод вызывается вне контекста транзакции,
     то будет выброшено исключение типа TransactionRequiredException, сигнализирующее о том, что транзакция должна быть
     начата до вызова метода.
     
- **NotSupported**
    - Атрибут NotSupported означает, что метод не должен выполняться внутри транзакции, ни новой, ни уже существующей.
     Если этот атрибут указан для метода, вызванного в контексте транзакции, то она будет приостановлена до момента 
     завершения метода. После выхода из метода выполнение транзакции будет возобновлено.
     
- **Supports** 
    - Если Supports применяется к методу, который вызывается в области видимости существующей транзакции, то метод
     будет выполнен внутри этой транзакции. Если же метод вызывается вне контекста транзакции, то транзакция не будет
     начата вовсе.
         
     - Этот атрибут, как правило, используется для операций чтения данных из базы. Однако почему бы в этом случае не 
     использовать атрибут NotSupported? Это будет означать, что метод будет выполняться вне контекста транзакции. 
     Ответ достаточно прост. Если выполнять запрос внутри транзакции, то данные будут читаться из лога транзакций базы 
     данных, т.е. будут видны только что сделанные изменения. Если же запрос выполняется вне транзакции, то ему будут 
     доступны только неизмененные данные. Допустим, что вы добавляете новый торговый приказ в таблицу TRADE и сразу за 
     этим, не заканчивая транзакцию, запрашиваете полный список всех приказов. В этом случае еще не подтвержденный приказ
     попадет в результаты запроса. Если бы использовался атрибут NotSupported, то в результаты попали бы только записи
     из таблицы, а не из лога транзакций, поэтому неподтвержденный заказ был бы не виден. Это далеко не всегда является
     нежелательным эффектом – все зависит от конкретной ситуации и бизнес-логики приложения.

- **Never**
    - Если Never применяется к методу, который вызывается в области видимости существующей транзакции, то контейнер выдает
     исключение RemoteException. Если метод вызывается  вне  транзакцией, контейнер не запускает новую транзакцию перед запуском метода.


Для более красткого описания смотри таблицу [Table 51-1 Transaction Attributes and Scope](https://docs.oracle.com/javaee/7/tutorial/transactions003.htm#BNCIT)

Для натсройки аттрибутов транзакции аннотируйте аннотацией **javax.ejb.TransactionAttribute**  класс или один метод,
установив аттрибут этой аннотаци в одну из констант **javax.ejb.TransactionAttributeType**.

Если вы поставили аннотацию @TransactionAttribute над классом, то вы определяете TransactionAttributeType для всех
методов этого класса. При аннотировании метода с помощью @TransactionAttribute применяется тип TransactionAttributeType
только для этого метода. Если аннотация @TransactionAttribute находится как над классом, так и над методом, то 
TransactionAttributeType переопределяет(сильнее чем) TransactionAttributeType класса.
 
Пример:
```
@TransactionAttribute(NOT_SUPPORTED)
@Stateful
public class TransactionBean implements Transaction {
...
    @TransactionAttribute(REQUIRES_NEW)
    public void firstMethod() {...}

    @TransactionAttribute(REQUIRED)
    public void secondMethod() {...}

    public void thirdMethod() {...}

    public void fourthMethod() {...}
}
```

** ** ** **

**Rolling Back a Container-Managed Transaction**