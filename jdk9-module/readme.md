# Java 9 模块化


# 背景

# 全新的模块系统

# 什么是模块化

# 聚合模块

# 声明模块

```
[open] module <moduleName> {

　　<module-statement>;

　　......

}
```
open修饰符是可选的，它声明一个开放的模块，一个开放的模块导出所有的包，以便其他模块使用反射访问。<moduleName>是要定义的模块的名称，<module-statement>是一个模块语句。模块声明中可以包含零个或多个模块语句：

- 导出语句（exports），导出模块，其他模块访问。
- 开放语句（opens），开放当前模块，其他模块可以访问，包括反射调用等。
- 需要语句（requires），声明模块对另一个模块的依赖关系。
- 使用语句（uses），表达服务消费。
- 提供语句（provides），表达服务提供。

## 模块命名
### 模块命名关键字
模块名称可以是Java限定标识符，与包命名约定类型，使用反向域名模式为模块提供唯一的名称。
JDK9中，open,module,requires,transitive,exports,opens,to,uses,provices,with是受限关键字，只有在具体位置出现在模块声明中时，它们才具有特殊意义。可以将它们用作程序中其他地方的标识符。

### 模块命名约束
（1）将包拆分成多个模块是不允许的，也就是说，同一个包不能在多个模块中定义；

（2）不能同时访问多个模块中的相同软件包；

（3）模块图不能包含循环依赖，也就是说两个模块不能彼此读取，如果需要，他们应该是一个模块，而不是两个；

（4）模块声明不支持模块版本，需要使用jar工具或其他一些工具（javac）将模块的版本添加为类文件属性；

（4）模块系统没有子模块的概念，com.jdk9.address和com.jdk9.address.child是两个单独的模块。

## 模块的访问控制
### exports
导出语句将模块的指定包导出到所有模块或编译时和运行时的命令模块列表。形式如下：

exports <package>;

假设需要开发多个模块组成的库或框架，其中有一个模块中的包含API，仅供某些模块内部使用。也就是说，该模块中的包不需要导出到所有模块，而是其可访问性必须限于几个命名的模块，可以使用模块声明中的限定的export to语句来实现：

exports <package> to <module1>, <module2>;

package：当前模块要导出的包的名称；

module1,module2……：可以读取当前模块的模块的名称。

实例，包含非限定导出和限定导出：
```
module com.jdk9.module {

　　exports com.jdk9.module.core;

　　exports com.jdk9.module.util to com.jdk9.module.internal, com.jdk9.module.server;

}
```
### opens
Java允许使用反射机制访问所有成员，包括私有，公共，包和受保护的类型。需要在成员对象上调用setAccessible(true)方法。

模块系统提供如下规则：

exports的包将允许在编译和运行时访问public类型及其public成员，如果不exports包，则该包中的所有类型都不可访问其他模块；
可以打开一个模块，以便在运行时对该模块中的所有包中的所有类型进行深层反射，这样的模块称为开放模块；
开放语句允许对所有模块的反射访问指定的包或运行时指定的模块列表。其他模块可以反射访问指定包中的所有类型以及这些类型的所有成员（私有和公共），开放语句采用如下形式：
```
opens <package>;
opens <package> to <module1>, <module2>;
```
### 开放模块
```
open com.jdk9.address {

　　exports xxx；

　　requires xxx；

　　uses xxx；

　　provides xxx；

　　// 不允许opens
}
```
定义com.jdk9.address模块是一个开放模块，其他模块可以在本模块中的所有软件包上对所有类型使用深层反射。
可以在开放模块中声明exports，requires，uses和provides语句，但不能再opens的模块中再声明opens语句。
opens语句用于打开特定的包以进行深层反射，因为开放模块打开所有的软件包进行深层反射，所以在开放模块中不允许再使用open语句。

### 打开包
打开一个包意味着其他模块对该包中的类型使用深层反射，可以打开一个包指定给所有其他模块或特定的模块列表，打开一个包到所有其他模块的打开语句的语法如下：

opens <package>;

opens <package> to <module1>, <module2>……

<package>仅用于深层反射到<module1>，<module2>等。

### 访问类型
在JDK9之前，有4中访问类型：

public
protected
<package>
private
在JDK8中，public类型意味着程序的所有部分都可以访问它，在JDK9中，public类型可能不是对每个类都公开的，模块中定义的public类型可能分为3类：

仅在定义模块内公开：如果一个类型在模块中被定义为public，但是该模块不导出包含该类型的包，则该类型仅在该模块中是公开的，没有其他模块而已访问类型；
只针对特定模块公开：如果一个类型在一个模块中被定义为public，但是该模块使用一个限定的export来导出包含该类型的包，该类型将只能在有限导出的子句中指定的模块中访问；
指定所有类公开：如果一个类型在模块中被定义为public，但该模块使用包含该类型的非限定的导出语句导出该包，该类型将公开给读取第一个模块的每个模块。

### 声明依赖关系
模块系统在编译时以及运行时验证模块的依赖关系，有事希望在编译时模块依赖性是必需的，但在运行时是可选的。 

需要（require）语句声明当前模块对另一个模块的依赖关系，

requires <module>;  
requires transitive <module>;  
requires static <module>;  
requires transitive static <module>;  

static标示在编译时的依赖是强制的，但在运行时是可选的：
requires static N意味着模块M需要模块N，模块N必须在编译时出现才能编译模块M，而在运行时存在模块N是可选的。
transitive当前模块依赖其他模块具有隐式依赖性，假设有三个模块P,Q和R，假设模块Q包含requires transitive R语句，
如果模块P包含requires Q，这意味着模块P隐含依赖模块R。

### 配置服务
Java允许使用服务提供者和服务使用者分离的服务提供者机制。JDK9运行使用语句uses和provides实现其服务。

use语句可以指定服务接口的名字，当前模块就会发现它，使用java.util.ServiceLoader类进行加载：

uses <service-interface>

实例：
```
module M {

　　uses com.jdk9.prime.PrimeChecker;

}
```
com.jdk9.prime.PrimeChecker是一个服务接口，其实现类将由其他模块提供，模块M将使用java.util.ServiceLoader类来发现和加载此接口的实现。

provide语句指定服务接口的一个或多个服务程序实现类：

provide <service-interface> with <service-impl-class1>, <service-impl-class2>;

实例：
```
module P {

　　uses com.jdk9.CsvParser;

　　provides com.jdk9.CsvParser with com.jdk9.CsvParserImpl;

　　provides com.jdk9.prime.PrimeChecker with com.jdk9.prime.PrimeCheckerImpl;

}
```















参考：  
- https://www.cnblogs.com/lujiango/p/7852120.html  







