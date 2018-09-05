# 描述
函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
函数式接口可以被隐式转换为lambda表达式。
函数式接口可以现有的函数友好地支持 lambda。

Java8的新引入，包含函数式的设计，接口都有@FunctionalInterface的注解。就像这个注解的注释说明一样，它注解在接口层面，且注解的接口要有且仅有一个抽象方法。
具体就是说，注解在Inteface上，且interface里只能有一个抽象方法，可以有default方法。因为从语义上来讲，一个函数式接口需要通过一个***逻辑上的***方法表达
一个单一函数。那理解这个单一就很重要了，单一不是说限制你一个interface里只有一个抽象方法，单是多个方法的其他方法需要是继承自Object的public方法，
或者你要想绕过，就自己实现default。函数式接口自己本身一定是只有一个抽象方法。同时，如果是Object类的public方法，也是不允许的。

如：
@FunctionalInterface
public interface Runnable {
    void run();
}

# 核心函数式接口
Java8里关于函数式接口的包是java.util.function，里面全部是函数式接口。
主要包含几大类：Function、Predicate、Supplier、Consumer和*Operator（没有Operator接口，只有类似BinaryOperator这样的接口）。


# Function
@FunctionalInterface
public interface Function<T, T> {
    R apply(T t);
    ...
}
函数意为将参数T传递给一个函数，返回R。即$R=Function(T)$

其默认实现了3个default方法，分别是compose、andThen和identity，对应的函数表达为：compose对应$V=Function(ParamFunction(T))$，
体现嵌套关系；andThen对应$V=ParamFunction(Function(T))$，转换了嵌套的顺序；还有identity对应了一个传递自身的函数调用对应$Function(T)=T$。
从这里看出来，compose和andThen对于两个函数f和g来说，f.compose(g)等价于g.andThen(f)。


## Function接口相关的接口包括：
BiFunction ：R apply(T t, U u);接受两个参数，返回一个值，代表一个二元函数；
DoubleFunction ：R apply(double value);只处理double类型的一元函数；
IntFunction ：R apply(int value);只处理int参数的一元函数；
LongFunction ：R apply(long value);只处理long参数的一元函数；
ToDoubleFunction：double applyAsDouble(T value);返回double的一元函数；
ToDoubleBiFunction：double applyAsDouble(T t, U u);返回double的二元函数；
ToIntFunction：int applyAsInt(T value);返回int的一元函数；
ToIntBiFunction：int applyAsInt(T t, U u);返回int的二元函数；
ToLongFunction：long applyAsLong(T value);返回long的一元函数；
ToLongBiFunction：long applyAsLong(T t, U u);返回long的二元函数；
DoubleToIntFunction：int applyAsInt(double value);接受double返回int的一元函数；
DoubleToLongFunction：long applyAsLong(double value);接受double返回long的一元函数；
IntToDoubleFunction：double applyAsDouble(int value);接受int返回double的一元函数；
IntToLongFunction：long applyAsLong(int value);接受int返回long的一元函数；
LongToDoubleFunction：double applyAsDouble(long value);接受long返回double的一元函数；
LongToIntFunction：int applyAsInt(long value);接受long返回int的一元函数；


# Operator
Operator其实就是Function，函数有时候也叫作算子。算子在Java8中接口描述更像是函数的补充，和上面的很多类型映射型函数类似。
算子Operator包括：UnaryOperator和BinaryOperator。分别对应单元算子和二元算子。
算子的接口声明如下：
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}
二元算子的声明：
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }
    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}

## 其他的Operator接口：
LongUnaryOperator：long applyAsLong(long operand);
IntUnaryOperator：int applyAsInt(int operand);
DoubleUnaryOperator：double applyAsDouble(double operand);
DoubleBinaryOperator：double applyAsDouble(double left, double right);
IntBinaryOperator：int applyAsInt(int left, int right);
LongBinaryOperator：long applyAsLong(long left, long right);

# Predicate
predicate是一个谓词函数，主要作为一个谓词演算推导真假值存在，其意义在于帮助开发一些返回bool值的Function。
本质上也是一个单元函数接口，其抽象方法test接受一个泛型参数T，返回一个boolean值。等价于一个Function的boolean型返回值的子集。
接口声明如下：
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
    ...
}
Predicate在Stream中有应用，Stream的filter方法就是接受Predicate作为入参的。这个具体在后面使用Stream的时候再分析深入。

## 其他Predicate接口：
BiPredicate：boolean test(T t, U u);接受两个参数的二元谓词
DoublePredicate：boolean test(double value);入参为double的谓词函数
IntPredicate：boolean test(int value);入参为int的谓词函数
LongPredicate：boolean test(long value);入参为long的谓词函数

# Consumer
看名字就可以想到，这像谓词函数接口一样，也是一个Function接口的特殊表达——接受一个泛型参数，不需要返回值的函数接口。
接口声明如下：
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

## 其他Consumer接口：
BiConsumer：void accept(T t, U u);接受两个参数
DoubleConsumer：void accept(double value);接受一个double参数
IntConsumer：void accept(int value);接受一个int参数
LongConsumer：void accept(long value);接受一个long参数
ObjDoubleConsumer：void accept(T t, double value);接受一个泛型参数一个double参数
ObjIntConsumer：void accept(T t, int value);接受一个泛型参数一个int参数
ObjLongConsumer：void accept(T t, long value);接受一个泛型参数一个long参数

# Supplier
最后说的是一个叫做Supplier的函数接口，其声明如下：
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

其简洁的声明，会让人以为不是函数。这个抽象方法的声明，同Consumer相反，是一个只声明了返回值，不需要参数的函数（这还叫函数？）。
也就是说Supplier其实表达的不是从一个参数空间到结果空间的映射能力，而是表达一种生成能力，因为我们常见的场景中不止是要consume（Consumer）
或者是简单的map（Function），还包括了new这个动作。而Supplier就表达了这种能力。

## 其他Supplier接口：
BooleanSupplier：boolean getAsBoolean();返回boolean
DoubleSupplier：double getAsDouble();返回double
IntSupplier：int getAsInt();返回int
LongSupplier：long getAsLong();返回long

参考：
https://blog.csdn.net/lz710117239/article/details/76192629
