# SpringBoot入门与进阶



## 一、入门

### startup

@SpringBootApplication 注解：指定当前类为SpringBoot项目启动类。

启动SpringBoot项目的三种方式：

- IDEA启动
- 命令行：打开项目所在目录，使用 mvn spring-boot:run 指令启动。
- 打包方式启动：打开项目所在目录，使用 mvn clean package 指令打包，打包后在target目录会生成一个jar文件，使用 java -jar xxx.jar 即可启动。

### 配置相关

配置文件的两种方式：

- .properties：全名配置。
- .yml：层次分明，格式简洁。（推荐）

属性配置：

- 使用@Value("${xxx}")注解引入在配置文件中编写的单个属性。
- 使用@ConfigurationProperties(prefix="aaa") + @Component 注解引入aaa下的所有属性。例：

```properties
limit: 
	minMoney: 1
	maxMoney: 999
	description: 最少${minMoney},最多${maxMoney}
```

```java
@Component
@ConfigurationProperties(prefix="limit")
public class LimitConfig(){
    private BigDecimal minMoney;
    private BigDecimal maxMoney;
    private String description;
}
```

多环境配置：

- 开发配置：-dev
- 生产配置：-prod
- 在启动项目时 java -jar -Dspring.profiles.active= dev xxx.jar指定环境配置。

### Controller

- @Controller：处理http请求。与@ResponseBody配合使用。
- @RestController：Spring4以后添加的注解，效果等于@Controller+@ResponseBody。
- @RequestMapping：配置url映射。可以指定为具体的@GetMapping ，@PostMapping等。
- @PathVariable：从url中获取到的参数。
- @RequestParam：从请求中获取的参数。

### dao

- 集成ORM框架。常用MyBatis、Spring Data JPA。
- 引入mysql-connector-java依赖，配置数据库连接参数。

### Service

- @Service注解。
- @Transactional注解提供事务支持。



## 二、进阶

### 表单验证

- 在对象内部添加约束，使用javax.validation.constraints包下的注解。
- 给需要验证的对象添加 @Valid 注解。

### 使用AOP处理请求

- 添加aop依赖。
- 定义一个切面类，添加@Aspect注解。添加@Component注解将其注入spring容器。
- 添加@Pointcut注解设置切面，使用@Before，@After，@AfterReturning 等注解实现对切面的控制。

例：

```java
@Aspect
@Component
public class HttpAspect {

    private static final Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.dmarco.luckymoney.controller.LuckymoneyController.*(..))")
    public void log(){
    }
    
    @Before("log()")
    public void doBefore(){
        logger.info("Before Aspect");
    }
    
    @After("log()")
    public void doAfter(){
        logger.info("After Aspect");
    }
}
```

### 统一返回结果与异常处理

为了前端便于处理返回结果，需要对返回结果的形式进行统一。

- 封装CommonResult类用来抽象返回结果。一般包含code、msg和data三个字段。
- 封装ResultUtil用来处理请求成功与失败的结果。

统一异常处理

- 封装异常处理类。使用@ControllerAdvice（类）+ @ExceptionHandler(value=Exception.class)@ResponseBody（方法）
- 自定义异常类，继承RuntimeException。一般需要注意：Spring只会在出现RuntimeExeption时才会进行事务回滚。
- 统一管理错误码与错误信息。封装一个枚举类用来创建返回结果。



### 单元测试

- 选择一个方法，右键go to --> test，IDEA会自动在test目录下生成相应的测试类。

- service类的测试：添加@RunWith(SpringRunner.class)与@SpringBootTest注解到类，方法上会自动生成一个@Test注解，使用Assert断言测试业务逻辑的结果是否正确。

- controller类的测试：测试期望是在对应的url下获取到正确地信息，或者返回200状态码。例：

  ```java
  @RunWith(SpringRunner.class)
  @SpringBootTest
  @AutoConfigureMockMvc
  class LuckymoneyControllerTest {
  
      @Autowired
      private MockMvc mvc;
  
      @Test
      void listLuckymoney() throws Exception {   mvc.perform(MockMvcRequestBuilders.get("/list")).andExpect(MockMvcResultMatchers.status().isOk());
      }
  }
  ```
  






