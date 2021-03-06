# 描述
Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。  
Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。  
使用 Lambda 表达式可以使代码变的更加简洁紧凑。

# 语法
包含三部分：
1、一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数  
2、一个箭头符号：->  
3、方法体，可以是表达式和代码块。  
lambda 表达式的语法格式如下：    
(parameters) -> expression 或 (parameters) ->{ statements; }  

# Lambda表达式的重要特征
可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。  
可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。  
可选的大括号：如果主体包含了一个语句，就不需要使用大括号。  
可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。  


# 使用 Lambda 表达式需要注意以下两点：
Lambda 表达式主要用来定义行内执行的方法类型接口，例如，一个简单方法接口。在上面例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义了sayMessage的执行。
Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。


