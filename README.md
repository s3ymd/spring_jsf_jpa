# 概要

- Spring Boot 1.3.3
- JSF 2.2 (Mojarra, 2.2.13)
- JPA (Spring Data, Hibernate, H2 Database)

# Mavenによるプロジェクト作成

- プロジェクトフォルダの作成

```
$ mvn archetype:generate \
-DgroupId=jp.co._3sss \
-DartifactId=shop \
-DarchetypeArtifactId=maven-archetype-webapp \
-DinteractiveMode=false

$ cd shop

$ mvn eclipse:eclipse
```

- Eclipseにインポート
- プロジェクト右クリック、convert to maven project

# web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
</web-app>
```

# pom.xml

- <dependencies>, <build>を削除
- 下記を追加
```
<properties>
    <java.version>1.8</java.version>
</properties>
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.3.RELEASE</version>
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>

```

- プロジェクト右クリック＞プロパティ＞Project Facets
- Dynamic Web ModuleとJavaのチェックを外す
- プロジェクトクリック、Option + F5(更新)

- src/main/javaフォルダを作成
- jp.co._3sssパッケージを作成
- Application.javaを作成
```
package jp.co._3sss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

- src/main/resources/application.properties

```
spring.main.banner_mode=off
```


# JSPを動作させるための指定(JSF使用時も必要)

- pom.xmlに下記を追加

```

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		
```

- src/main/webapp/index.jsp

```
<html>
<body>
<h2>Hello World!</h2>
<%= new java.util.Date() %>
</body>
</html>

```

- アプリを再起動して、localhost:8080/index.jspにアクセスして動作を確認

# Controller

- パッケージjp.co._3sss.web
- RootController.java
```
package jp.co._3sss.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co._3sss.domain.Item;
import jp.co._3sss.domain.ItemRepository;

@Controller
public class RootController {
  
  @Autowired
  ItemRepository itemRepository;
  
  @RequestMapping("/")
  public String index() {

    
    itemRepository.save(new Item());
    return "index.jsp";
  }
}

```

# H2 Databaseの導入

- pom.xml
```
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```

- application.properties

```
spring.datasource.url=jdbc:h2:~/test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
```

- コンソールは下記URLにてアクセス可能
- http://localhost:8080/h2-console/


# JPAを使用するための設定

- パッケージjp.co._3sss.domainを作成
- Item.java

```
package jp.co._3sss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name="items")
public class Item {
  
  @Id
  @GeneratedValue
  private Long id;
  
  @Column
  private String name;
  
  private int price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
  
}

```

- ItemRepository.java
```
package jp.co._3sss.domain;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepositories extends CrudRepository<Item, Long>{

}

```


# semantic ui導入

- src/main/webapp/resourcesフォルダを作成
- (src/main/resourcesではないので注意)
- bower
```
$ cd src/main/webapp/resources
$ bower install semantic-ui
```

- htmlのh:head内に下記を追加
```
        <h:outputStylesheet name="bower_components/semantic/dist/semantic.min.css"/>
```


# JSF導入

- 必要なライブラリ（Mojarra, JSF実装の1つ）を導入
```
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.13</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.13</version>
			<scope>compile</scope>
		</dependency>

```

- Spring連携
- AppConfig.java
```
package jp.co._3sss;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.faces.config.ConfigureListener;

@Configuration
public class AppConfig {


  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    FacesServlet servlet = new FacesServlet();
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
    return servletRegistrationBean;
  }
  
  @Bean
  public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
      return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
  }
}
```

- web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	version="3.1">

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<servlet>
		<servlet-name>Faces Servlet</servlet-name>
		<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>Faces Servlet</servlet-name>
		<url-pattern>*.xhtml</url-pattern>
	</servlet-mapping>

</web-app>
```

- WEB-INF/faces-config.xml
```
<?xml version="1.0" encoding="UTF-8"?>

<faces-config version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd">

	<application>
		<el-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</el-resolver>
	</application>

</faces-config>
```

# validation

src/main/java直下にValidationMessages.propertiesを作成
```
javax.validation.constraints.AssertFalse.message = must be false
javax.validation.constraints.AssertTrue.message  = must be true
javax.validation.constraints.DecimalMax.message  = must be less than ${inclusive == true ? 'or equal to ' : ''}{value}
javax.validation.constraints.DecimalMin.message  = must be greater than ${inclusive == true ? 'or equal to ' : ''}{value}
javax.validation.constraints.Digits.message      = numeric value out of bounds (<{integer} digits>.<{fraction} digits> expected)
javax.validation.constraints.Future.message      = must be in the future
javax.validation.constraints.Max.message         = xx must be less than or equal to {value}
javax.validation.constraints.Min.message         = {value}\u4EE5\u4E0A\u306E\u5024\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044
javax.validation.constraints.NotNull.message     = may not be null
javax.validation.constraints.Null.message        = must be null
javax.validation.constraints.Past.message        = must be in the past
javax.validation.constraints.Pattern.message     = must match "{regexp}"
javax.validation.constraints.Size.message        = size must be between {min} and {max}

org.hibernate.validator.constraints.CreditCardNumber.message = invalid credit card number
org.hibernate.validator.constraints.Email.message            = not a well-formed email address
org.hibernate.validator.constraints.Length.message           = length must be between {min} and {max}
org.hibernate.validator.constraints.NotBlank.message         = may not be empty
org.hibernate.validator.constraints.NotEmpty.message         = may not be empty
org.hibernate.validator.constraints.Range.message            = must be between {min} and {max}
org.hibernate.validator.constraints.SafeHtml.message         = may have unsafe html content
org.hibernate.validator.constraints.ScriptAssert.message     = script expression "{script}" didn't evaluate to true
org.hibernate.validator.constraints.URL.message              = must be a valid URL
org.hibernate.validator.constraints.br.CNPJ.message          = invalid Brazilian corporate taxpayer registry number (CNPJ)
org.hibernate.validator.constraints.br.CPF.message           = invalid Brazilian individual taxpayer registry number (CPF)
org.hibernate.validator.constraints.br.TituloEleitor.message = invalid Brazilian Voter ID card number
```

- jp.co._3sss以下にmessages_ja.propertiesを作成
- jsf-api-2.2.13.jarのjavax.faces以下のMessages_ja.properteisの内容を貼り付け


