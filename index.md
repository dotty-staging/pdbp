<style type="text/css"> body { margin: auto; max-width: 44em; font-family: Calibri, sans-serif; font-size: 18pt; } /* automatic heading numbering */ h1 { counter-reset: h2counter; } 
h2 { counter-reset: h3counter; } 
h3 { counter-reset: h4counter; } 
h4 { counter-reset: h5counter; } 
h5 { counter-reset: h6counter; } 
h6 { } 
h2:before { counter-increment: h2counter; content: counter(h2counter) ".\0000a0\0000a0"; } 
h3:before { counter-increment: h3counter; content: counter(h2counter) "." counter(h3counter) ".\0000a0\0000a0"; } 
h4:before { counter-increment: h4counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) ".\0000a0\0000a0"; } 
h5:before { counter-increment: h5counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) ".\0000a0\0000a0"; } 
h6:before { counter-increment: h6counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) "." counter(h6counter) ".\0000a0\0000a0"; } 
</style>

# **Program Description Based Programming**

```scala
//       _______         __    __        _______
//      / ___  /\       / /\  / /\      / ___  /\
//     / /__/ / / _____/ / / / /_/__   / /__/ / /
//    / _____/ / / ___  / / / ___  /\ /____  / /
//   / /\____\/ / /__/ / / / /__/ / / \___/ / /
//  /_/ /      /______/ / /______/ /     /_/ /
//  \_\/       \______\/  \______\/      \_\/
//                                           v1.0
//  Program Description Based Programming Library
//  author        Luc Duponcheel        2017-2018
```

This document describes a [program description based programming](https://github.com/PDBP/pdbp) library.

We refer to it as the `PDBP` library.

We hope you enjoy reading this document and using the `PDBP` library it describes.

All comments are welcome [ pdbp dot blog at gmail dot com ]. 

## **Warning**      

Both this document and the library it describes are
  - opiniated, and
  - work in progress.

## **History**

In 1977, [John Backus](https://en.wikipedia.org/wiki/John_Backus) was an [ACM](https://www.acm.org/) [A.M. Turing Award Winner](https://amturing.acm.org/award_winners/backus_0703524.cfm).
The title of his Turing Award winning lecture was 

Can programming be liberated from the von Neumann style?
A functional style and it's algebra of programs.

This document builds upon the ideas of this influential lecture.

## **Introduction**

When writing an introduction it is challenging to find the right balance between providing too many details or too few details. 

This introduction
  - provides many details,
  - omits some details,
  - provides details that are repeated in the main part of this document.

You have to use your knowlegde and imagination to make sense of it.

It is perfectly fine to first read it diagonally.

I recommend to reread it from time to time once you get more and more acqainted with the content of the rest of this document.

## **Introducing `FP`**

In his Turing Award winning lecture, John Backus describes the [`FP` programming language](https://en.wikipedia.org/wiki/FP_(programming_language)). 

The `FP` programming language consists, roughly speaking, of objects, programs, forms and definitions, where
 - a program transforms objects to an object,
 - a form transforms programs to a program,
 - a definition defines a program or a form in terms of programs and forms.

The `FP` forms are 

 - Function

and

 - Composition
 - Construction
 - Condition

Think of the latter forms as program templates, programs transformed by them as program fragments, or program components, and the resulting program as a composite program.

## **Introducing `Dotty`**

The `PDBP` library is written in the [`Dotty` programming language](http://dotty.epfl.ch/) which will, eventually, evolve to version `3.0` of the [`Scala` programming language](https://www.scala-lang.org/).

When dealing with `Dotty`, we use the words value and object interchangeably.

## **Introducing `trait Program`**

The main type class of the `PDBP` library is `trait Program`.

```scala
trait Program[>-->[- _, + _]]
    extends Function[>-->]

    with Composition[>-->]
    with Construction[>-->]
    with Condition[>-->]

    with Aggregation[>-->]
```

For every `FP` form there is a `trait` that is mixed-in by `trait Program`.

`FP` does not really have an Aggregation form. 
`FP` does have sequences of objects and it is possible to define `FP` programs that aggregate sequences of objects to an object.

Think of the members of `trait Composition`, `trait Construction` and `trait Condition` as program templates, programs transformed by them as program fragments, or program components, and the resulting program as a composite program.

`trait Program` closely resembles arrows.

In 1998, John Hughes described arrows and used arrows in `Haskell` in
[Generalizing monads to arrows](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.29.4575&rep=rep1&type=pdf).

His work was, among others, inspired by [Deterministic, Error-Correcting Combinator Parsers](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.80.9967&rep=rep1&type=pdf) described, in 1998, by Doaitse Swierstra and me.

`trait Program` exposes a pointfree programming API for application developers.

`trait Program` is about program descriptions.
Program descriptions are defined in terms of programming capabilities that are declared or default defined as members of `trait Program`.

By abuse of notation, we often simply refer to program descriptions as programs. 
We hope that this does not lead to any confusion.

Compare this with the famous painting [Ceci n'est pas une pipe](https://en.wikipedia.org/wiki/The_Treachery_of_Images) of [René Magritte](https://en.wikipedia.org/wiki/Ren%C3%A9_Magritte).
The painting is not a pipe, it is a pipe description.

In a way programs generalize functions. 

 - A function transforms function arguments to yield a function result. 
 - A program also, somehow, transforms a program argument to yield a program result. 

When there is no danger of confusion 

  - we simply write arguments, argument and result, not mentioning function or program.

We often simply write

  - a function transforms arguments to yield a result, or
  - a function transforms arguments to a result, 
  
and

  - a program transforms an argument to yield a result, or
  - a program transforms an argument to a result.

Note that we use both 
  - zero or more arguments for functions,
  - and one argument for programs.

The difference between both is somewhat superfluous since tuple arguments can represent zero, one or more arguments.

## **Introducing `trait Computation`**

Another important type class of the `PDBP` library is `trait Computation`.

```scala
private[pdbp] trait Computation[C[+ _]]
    extends Resulting[C]
    with Binding[C]

    with Lifting[C]

    with Sequencing[C]

    with Program[Kleisli[C]]
    with Applying[Kleisli[C]]
```

where

```scala
private[pdbp] object kleisliBinaryTypeConstructorType {

  private[pdbp] type Kleisli[C[+ _]] = [-Z, + Y] => Z => C[Y]

}
```

Note that `Kleisli[C]`, where `C` is a unary type constructor, is itself a binary type constructor.

`trait Computation` closely resembles monads.

In 1991, Eugenio Moggi described monads in
[Notions of computation and monads](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.79.733&rep=rep1&type=pdf).

In 1992, Philip Wadler used monads in `Haskell` in 
[The essence of functional programming](http://citeseerx.ist.psu.edu/viewdoc/download;jsessionid=E09A5FD9362F6780675ADF29471B7428?doi=10.1.1.38.9516&rep=rep1&type=pdf).

`trait Computation` is about computation descriptions. 
Computation descriptions are defined in terms of computational capabilities that are declared or default defined as members of `trait Computation`.

By abuse of notation, we often simply refer to computation descriptions as computations. 
We hope that this does not lead to any confusion.

`trait Computation` exposes a pointful computation API for library developers.
All it's capabilities are `private[pdbp]`.

A program of type `Kleisli[C]` is referred to as a kleisli program.
Note that we use a lower case k. 
A kleisli program is a function that transforms an argument to yield a computation result.

In a way computations generalize expressions. 

 - An expression evaluation yields an expression result. 
 - A computation execution also, somehow, yields a computation result.

When there is no danger of confusion 

  - we simply write result, not mentioning expression or computation, and
  - we do not mention evaluation or execution.

We often simply write

  - an expression evaluation yields a result, or
  - an expression yields a result, or
  - an expression evaluation has a result, or
  - an expression has a result, 

and

  - a computation execution yields a result, or
  - a computation yields a result, or
  - a computation execution has a result, or
  - a computation has a result.
  
Recall that, in a way
 - programs in general, and kleisli programs in particular, generalize functions, and 
 - computations generalize expressions.

Think of a function as
 - an expression template with, to be filled in, unknown parts (its parameters).
   - For example `val function: (Z, Y) => X = { (z, y) => ex(z, y) }`, where `ex(z, y)` is an expression that, somehow, depends on `z` and `y`.

Think of a kleisli program as
 - a computation template with a, to be filled in, unknown part (its parameter).
   - for example `val kleislProgram: Z => C[Y] = { z => cy(z) }`, where `cy(z)` is a computation that, somehow, depends on `z`.

## **Power of expression**

In 2008, Conor McBride and Ross Paterson described applicatives (a.k.a. idioms) and used applicatives in `Haskell` in 
[Applicative programming with effects](http://www.staff.city.ac.uk/~ross/papers/Applicative.pdf).

In 2008, Sam Lindley, Philip Wadler and Jeremy Yallop compared the power of expression of monads, arrows and idioms in 
[Idioms are oblivious, arrows are meticulous, monads are promiscuous](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.187.6750&rep=rep1&type=pdf). 

 - Monads (cfr. `Computation`) have most power of expression.
 - Arrows (cfr. `Program`) are in between.
 - Applicatives (cfr. `Lifting`) have least power of expression. 

Recall that both `Program[Kleisli[C]]` and `Lifting[C]` are mixed-in by `trait Computation[C[+ _]]`.

Consider

```scala
private[pdbp] trait ProgramWithLifting[>-->[- _, + _]]
    extends Program[>-->]
    with Lifting[Kleisli[>-->]]
```

where

```scala
private[pdbp] object kleisliUnaryTypeConstructorType {

  private[pdbp] type Kleisli[>-->[- _, + _]] = [+Y] => Unit >--> Y

}
```

Note that `Kleisli[>-->]`, where `>-->` is a binary type constructor, is itself a unary type constructor.

`Lifting[Kleisli[>-->]]` is mixed-in by `trait ProgramWithLifting[>-->[- _, + _]]`. 

`Lifting[Kleisli[>-->]]` can be mixed-in by `trait Program[>-->[- _, + _]]`. 
Doing this leads to conflicting `Lifting` base types `[+Y] => C[Y]` and `[+Y] => Unit => C[Y]` for `trait Computation[C[+ _]]`.

A computation of type `Kleisli[>-->]` is referred to as a kleisli computation. 
Note that, again, we use a lower case k. 
A kleisli computation is a program without arguments.

Recall that `Applying[Kleisli[C]]` is mixed-in by `trait Computation[C[+ _]]`.

A kleisli program can be applied to an argument.

One can think of, somehow, applying an argument to a program.

If we add that applying capability to programs then they have the same power of expression as computations

```scala
private[pdbp] trait ProgramWithApplying[>-->[- _, + _]]
    extends Program[>-->] with Applying[>-->]
    with Resulting[Kleisli[>-->]]
    with Binding[Kleisli[>-->]]
```

because `trait Resulting` and `trait Binding` are the basic `trait`'s, the ones with undefined declared members, mixed-in by `trait Computation`.

## **Elegance of use**

Programming is not only about power of expression. 
It is also, and probably even more, about elegance of use.

Of course, elegance of use is a highly subjective concept.

Traditionally, the pointfree programming style has been considered to be elegant by some programmers and somewhat abstruse by other programmers. 
Luckily, the `Dotty` programming language comes to the rescue for the latter ones. 

`Dotty` is a strongly typed, scalable programming language. 
It is possible to extend the language in a type safe way at the library level with internal domain specific languages. 

By using a domain specific language for the domain of programs, program description based programming using the `PDBP` library can be done in an elegant way.

We refer to `PDBP`'s domain specific language for the domain of programs as `PDBP`'s programming DSL.

`PDBP`'s programming DSL defines the syntax of a programming language at the library level.

## **Our choice** 

`PDBP` goes for kleisli programs, offering
 - a powerful, slightly less elegant, pointful, result and binding based, computation API for library developers, and
 - a slightly less powerful, elegant, pointfree, composition based programming DSL for application developers.

We promote that the interaction between pointfree application developers and pointful library developer should be as follows.

If an application developer thinks that the available pointfree programming capabilities are not sufficient for developing his application, then either, when they are sufficient, a library developer should convince the application developer that they are or, when they are not sufficient, a library developer should think of appropriate extra pointfree programming capabilities that are sufficient, implement them as a pointful computational capabilities and expose them as corresponding (kleisli) programming capabilities.

## **Foundations** 

So we go for pointfree programming using kleisli programs.

For foundations we refer to [*Kleisli categories*](https://en.wikipedia.org/wiki/Kleisli_category). 

Intrestingly, pointful programming using programs is also possible.

For foundations we refer to [*arrow calculus*](http://homepages.inf.ed.ac.uk/slindley/papers/arrow-calculus.pdf).

## **Introducing `factorial` program**

Below is a `factorial` program written using `PDBP`'s programming DSL (also read: `PDBP`'s programming language syntax).

```scala
  val factorial: BigInt >--> BigInt =
    `if`(isZero) {
      one
    } `else` {
      `let` {
        subtractOne >-->
          factorial
      } `in` {
        multiply
      }
    }
```

`val factorial` implicitly uses the programming capabilities of `trait Program`. 
The programming capabilities that `factorial` makes use are
  - ` ... >--> ... ` (of `trait Composition`), 
  - `` `let` { ... } `in` { ... } `` (of `trait Construction`),  and
  - `` `if`(...) { ... } `else` { ... } ``  (of `trait Condition`),

The atomic programs, `isZero`, `one`, `subtractOne` and `multiply` that `factorial` makes use of are defined in a much simpler way, and only use the programming capabilities of `trait Function`. 

## **Informal description of `factorial`**

Note, again, that `factorial` is a program description.
Think of `factorial` as uses syntax of the library level `PDBP` programming DSL.
There is no semantics associated with it at all.

Below is an informal description of the semantics of the program description fragments of the `factorial` program description above
 - `isZero` is a program description of type `BigInt >--> Boolean`
   - think of `isZero` as a predicate (`Boolean`-valued function) that transforms its argument to the result `true` if it is equal to `0` and to the result `false` otherwise,
 - `one` is a program description of type `Z >--> BigInt`
   - think of `one` as a constant function that transforms its argument to the result `1`,
 - `subtractOne` is a program description of type `BigInt >--> BigInt`
   - think of `subtractOne` as a function that transforms its argument to the result obtained by subtracting `1` from it,
 - `multiply` is a program description of type `(BigInt && BigInt) >--> BigInt`
   - think of `multiply` as a function that transforms its arguments to the result obtained by multiplying them.

Note that
  - `isZero`, `one` and `subtractOne` are programs with parameter type `BigInt`: one parameter of type `BigInt`,
  - `multiply` is a program with parameter type `BigInt && BigInt`: two parameters of type `BigInt`.

Also note that
  - `one` is a generic program description of type `Z >--> BigInt` for all `Z`.

Below is an informal description of the meaning of the program description templates of the `factorial` program description above
 - `first >--> second` is part of the `PDBP` programming DSL related to `Composition`
   - think of `first` as a first function that transforms an argument and `second` as a second function that transforms the result of the first function,
 - `` `let` { constructNewUsingCurrent } `in` { useBothNewAndCurrent } `` is part of the `PDBP` programming DSL related to `Construction`
   - note that `` `let` `` and `` `in` `` are between backticks,
   - think of `constructNewUsingCurrent` as a function that constructs a new value using the current argument,
   - think of `useBothNewAndCurrent` as a function that uses both the new value and the current argument, 
     - toghether, the new value and the current argument become the new current argument,
 - `` `if`(predicate) { trueCase } `else` { falseCase } `` is part of the `PDBP` programming DSL related to `Condition`
   - note that `` `if` `` and `` `else` `` are between backticks,
   - think of `predicate` as a predicate (`Boolean`-valued function) that tests the current argument,
     - if the result of the test is `true`, then function `trueCase` takes over control,
     - if the result of the test is `false`, then function `falseCase` takes over control.

Agreed, at first sight the pointfree `factorial` syntax above may seem a bit abstruse.

Agreed, we explained the semantics of the `factorial` syntax in a pointful way.

But, once you get used to program templates

 - ` ... >--> ... `, 
 - `` `let` { ... } `in` { ... } `` , 
 - `` `if`(...) { ... } `else` { ... } ``,  

and to program fragments like

 - `isZero`, 
 - `one`,
 - `subtractOne`,
 - `multiply`,

you will, hopefully, start appreciating the power of expression and elegance of use of pointfree code.

## **Introducing `factorial` kleisli program**

Below is a `factorial` kleisli program written using `PDBP`'s computation API.

```scala
  val factorial: BigInt `=>C` BigInt = { z =>
    isZero(z) bind { b =>
      if (b) {
        one(z)
      } else {
        subtractOne(z) bind { y =>
          factorial(y) bind { x =>
            multiply((y, x))
          }
        }
      }
    }
  }
```

The code above is provided for illustration purposes only.
It is not the intention to write pointful application code like this.

Besides, there is an error in the `factorial` definition above. 
Did you spot it? 

Here is a correct `factorial` definition 

```scala
  val factorial: BigInt `=>C` BigInt = { z =>
    isZero(z) bind { b =>
      if (b) {
        one(z)
      } else {
        subtractOne(z) bind { y =>
          factorial(y) bind { x =>
            multiply((z, x))
          }
        }
      }
    }
  }
```

Agreed, we might as well have written the correct `factorial` definition.
The point we want to make is that pointful programming, because it is more complex than pointfree programming, is inherently more difficult.
Human brains can only deal with a limited amount of complexity.

`val factorial` implicitly uses the computational capabilities of `trait Computation`. 
The computational capabilities that `factorial` makes use of are
  - `bind` (of `trait Binding`). 

The atomic kleisli programs, `isZero`, `one`, `subtractOne` and `multiply` that `factorial` makes use of are defined in a much simpler way, and only use the programming capabilities of `trait Resulting`. 

## **main programs**

Recall that programs have type `Z >--> Y` for types `Z` and `Y`.

For example: `factorial` has type `BigInt >--> BigInt`.

If
 - `producer` is a producer program of type `Unit >--> Z`,
 - `program` is a program of type `Z >--> Y`,
 - `consumer` is a consumer program of type `Y >--> Unit`,

then
 - `producer >--> program >--> consumer` is a main program of type `Unit >--> Unit`.

We also simply refer to 
  - a producer program as a producer,
  - a consumer program as a consumer.

Programs are design artifacts that, among others, are combined using composition
  - when given two programs, the result of the first one is the argument of the second one.

Main programs are architectural artifacts that are combined using I/O 
  - when given two main programs, the output produced by the first one is the input consumed by the second one.

We also refer to main programs as a services.

There is a tendency to keep both programs and main services relatively small.

Relatively small services are often referred to as microservices.

Keeping programs relatively small is often referred to as
  - good programmers write baby-code. 

This quote is introduced by [Erik Meijer](https://en.wikipedia.org/wiki/Erik_Meijer_(computer_scientist)).
Erik Meijer is so famous that he does not need an introduction. 
I was very lucky to be able to do research with him, on monads and related stuff, at the Univeristy of Utrecht back in the ninetees.

Keeping services relatively small is often referred to as
  - do one thing and do it well.

This quote is inspired by the [`Unix`](https://en.wikipedia.org/wiki/Unix) philosophy. 

## **`FP` versus `PDBP`**

There are important differences between `FP` and `PDBP`. 

Exploiting the flexibility that comes with those differences is the most important theme of the `PDBP` library.

## **Heteregeneous versus homogeneous**

 - `FP` is heterogeneous,
   -  programs are not objects.
 - `PDBP` is homogeneous,
   - programs are objects (values). 

Programs are defined as members of `class`es that are declared to implicitly have the programming capabilities available that are declared in `trait Program`.
Many programming capabilities come with an operator equivalent.
They are made available by `import`-ing them.

Programming with `PDBP` is a lot about passing around programming capabilities.

## **Syntax of programs**

 - `FP` programs are programming language level programs.
   - Think of `FP` programs as language level syntactic constructs.
 - `PDBP` programs are `Dotty` domain specific language level programs.
   - Think of `PDBP` programs as library level syntactic constructs.

## **Semantics of programs**

 - in `FP`,
   - programs have fixed semantics
   - the implementation of `FP` defines the semantics of programs.
 - in `PDBP,`
   - programs have flexible semantics using
     - `implicit object`'s that `extend trait Program`
       - both simple `implicit object`'s and complex ones obtained by naturally transforming simpler ones, 
     - program implementations that, indirectly, depend on those `implicit object`'s
       - program implementations are made are available as members of `object`'s that, directly depending on those `implicit object`'s, `extend` the `class`es in which programs are defined as members. 
     - `implicit object`'s that `extend trait ProgramMeaning`, that naturally transform those program implementations to meaning programs
       - both simple `implicit object`'s and complex ones obtained by natural transformation composition with simpler ones,
     - run utilities that run main meaning programs
       - both simple run utilities and complex ones.   

Agreed, the statements above, and the vocabulary they make use of, may seem daunting at first sight, but they become more familiar later in this document.

Let's rephrase the statements
  - we define a type class `trait Program`,
  - we define programs as members of `class`es that are declared to implicitly have the programming capabilities available that are declared in `trait Program`,
  - we define `object`'s that `extend` those `class`es using dependency injection by `import` of `implicit object`'s that `extend` `trait Program`, making program implementations available as members of those `object`'s,
  - we define meaning programs by naturally transforming those program implementations using dependency injection by `import` of `implicit object`'s that `extend trait ProgramMeaning`,
  - we run main meaning programs by making use of run utilities.

We also refer to transforming program implementations as as defining programming semantics.

Note that `factorial` has recursive program syntax (`factorial` uses `factorial`).
It can, for example, be given both a stack based semantics and a heap based semantics.

Strictly speaking, since `trait ProgramMeaning` is not a type class, the `object`'s that `extend` `trait ProgramMeaning` do not need to be `implicit object`'s, however, it is convenient that they are.

## **Extra programming capabilities**

 - in `FP`
   - the set of forms of the language is fixed.
 - in `PDBP`
   - the capabilities of the type class `trait Program` can be extended by mixing-in extra `traits`'s.

Extra programming capabilities can be added such as
 - state manipulation,
 - failure handling 
   - we do this by trying,
 - latency handling
   - we do this by going reactive,
 - advanced control handling 
   - we do this by using delimited continuations,
 - ... .

Note that `Program` already has basic control handling capabilities (the ones of `Condition`).

We also refer to adding programming capabilities as extending programming language syntax.

## **I/O**

 - in `FP`
   - input and output are effectful, they execute I/O effects in an impure way.
 - in `PDBP`
   - input and output are effectfree, they describe I/O effects in an pure way. 

Programming capabilities can be added related to
 - input reading, and
 - output writing
  
A program description involving I/O can, for example, be given 
 - different effectfree meanings for different testing purposes,
 - different effectful meanings for different production purposes. 

Reading and writing capabilities are, more or less, declared as

```scala
  trait Reading[R, >-->[- _, + _]] {
    def read: Unit >--> R
  }
```

and

```scala
  trait Writing[W: Writable, >-->[- _, + _]] {
    def write[Y]: implicit (Y => W) => Y >--> Unit
  }
```

We describe a correct definition of `Reading` and `Writing` is later.

Note that `read` is a producer program and `write[Y]`, once the implicit value of type `Z => W` is resolved, is a consumer program.
So, if `program` is a program of type `Z >--> Y`, then `read >--> program >--> write[Y]` is a main program.

Syntactically, `read` and `write` describe reading and writing effects in a pure way.
They come into play when defining main program descriptions.

Semantically, `read` and `write` may execute reading and writing effects in an impure way.
Eventually, they come into play when running main meaning programs.

## **Goal of the `PDBP` library**

The goal of the `PDBP` library is to illustrate that program description based programming using a pointfree style in `Dotty` is 
 - powerful
   -  as a library developer you can use the expressive power of computations,
   -  as an application developer you can use the expressive power of programs,
 - elegant
   - as an application developer you can use the elegance of the `PDBP` programming DSL,
   - as an library developer you can use the elegance the `PDBP` computation API,
 - flexible
   - as a library developer you can define many meanings,
   - as an application developer you can use many meanings,
 - extendible
   - as a library developer you can define extra capabilities,
   - as an application developer you can use extra capabilities,
 - pure
   - as a library developer you can define effects like state, failure, control and I/O in a pure way,
   - as a library tester you can use effects like state, failure, control and I/O in a pure way,
   - as an application developer you can use effects like state, failure, control and I/O in a impure way.

## **Summary**

For some of you this introduction may have touched upon a lot of, if not too many of, frightening stuff. 

Here is the good news.

For now, you only have to concentrate on the concepts below
  - power of expression, 
  - elegance of use.

The concepts below
  - flexible meanings,
  - extra capabilities,
  - pure I/0,
   
come in later.

You may ask yourself why we go for arrows (`trait Program`) instead of applicatives (`trait Lifing`).
After all, working with applicatives boils down to defining functions and lifting them to the computation (and corresponding kleisli program) world.

We may not really have a convincing answer but note that
  - Coding programs in a pointfree way is not really more challenging than coding functions in a pointfree way, and
    - Coding programs can only be done in a pointfree way using `PDBP`'s programming DSL (also read: `PDBP`'s programming language syntax).
    - Coding programs can both be done in a pointful way and a in pointfree way, and, as such, requires application developer discipline to be done in pointfree way.

To finish, we claim that

 - Pointfree program description based application programming naturally leads to deep insights into the nature of programs (remember: programs generalize functions). It requires you, as an application developer, to reason at an appropriate elegant (and reasonably powerful) level of abstraction. 
 - Pointful computation description based library programming naturally leads to deep insights into the nature of computations (remember: computations generalize expressions). It allows you, as a library developer, to reason at an appropriate, powerful (and reasonably elegant) level of abstraction.

Hopefully, the (some of the) statements above sound exiting to both programmers with and programmers without a background in computer science.

## **Lazyness**

`Dotty` has call-by-value and call-by-name parameters, but it does not have lazy parameters (yet, see[Add support for lazy (explicit|implicit) parameters #3005](https://github.com/lampepfl/dotty/issues/3005)).

The first thing we define is a low-down-dirty lazyness implementation to overcome this limitation.

The implementation makes use of implictt functions.
We make use of implictt functions for many other  implementations as well.

Groundbraking work by Martin Odersky, [Simplicity](https://infoscience.epfl.ch/record/229878/files/simplicitly_1.pdf), introduces implicit functions. 
Implicit functions replace boilerplate repetition of `implicit` parameters by an implicitly available global value, `implicitly`. 
You may argue that this is going back to the past since, for years, using globals has been considered to be harmful. 
In fact, instead, it is going back to the future since the global value `implicitly` is only available in a context where the type system can infer that it is available.

Implicit function types are types like other ones.
It is convenient to define a type alias for them as follows

```scala
package pdbp.types

object implicitFunctionType {

  type `I=>`[-Z, +Y] = implicit Z => Y

}
```

Consider

```scala
package pdbp.types

import implicitFunctionType._
import implicitUnit._

case class Thunk[+Z](`ui=>z`: Unit `I=>` Z) {

  lazy val eval: Z = `ui=>z`


```

where

```scala
package pdbp.types

object implicitUnit {

  implicit val implicitUnit: Unit = ()

}
```

A thunk is, in essence, an implicit function of type `` Unit `I=>` Z ``.

Wherever `implicit val implicitUnit` is available, we can 
 - make use of values of type `` Unit `I=>` Z `` as value of type `Z`.

For example, `eval` uses `` `ui=>z` ``, a value of type `` Unit `I=>` Z ``, as value of type `Z`.

If `thunk` is a thunk, then `thunk.eval` can be thought of as an evaluated version of `thunk` that can be used as a cache rather than using `thunk` itself.

Likewise, wherever `implicit val implicitUnit` is available, we can 
 - make use of values of type `Z` as value of type `` Unit `I=>` Z ``.

For example, the `REPL` session below illustrates the latter.
The session compares call-by-value parameters, call-by-name parameters and our implementation of lazy parameters.

```scala
sbt:types> console

scala> import pdbp.types.implicitFunctionType._

scala> import pdbp.types.implicitUnit._

scala> import pdbp.types.Thunk

scala> def cbvTwice(i: Int) = i + i
def cbvTwice(i: Int): Int

scala> def cbvZero(i: Int) = 0
def cbvZero(i: Int): Int

scala> def cbnTwice(i: => Int) = i + i
def cbnTwice(i: => Int): Int

scala> def cbnZero(i: => Int) = 0
def cbnZero(i: => Int): Int

scala> def lazyTwice(i: Thunk[Int]) = cbnTwice(i.eval)
def lazyTwice(i: pdbp.types.Thunk[Int]): Int

scala> def lazyZero(i: Thunk[Int]) = cbnZero(i.eval)
def lazyZero(i: pdbp.types.Thunk[Int]): Int

scala> cbvTwice({ println("evaluating") ; 1})
evaluating
val res0: Int = 2

scala> cbvZero({ println("evaluating") ; 1})
evaluating
val res1: Int = 0

scala> cbnTwice({ println("evaluating") ; 1})
evaluating
evaluating
val res2: Int = 2

scala> cbnZero({ println("evaluating") ; 1})
val res3: Int = 0

scala> lazyTwice(Thunk({ println("evaluating") ; 1}))
evaluating
val res4: Int = 2

scala> lazyZero(Thunk({ println("evaluating") ; 1}))
val res5: Int = 0
```
The last two expressions use `{ println("evaluating") ; 1}`, a value of type `Int` as value of type `` Unit `I=>` Int ``.

Now that we have defined our low-down-dirty lazyness implementation we can proceed with the core part of this document. 

## **`trait Program`**

Consider

```scala
package pdbp.program

trait Program[>-->[- _, + _]]
    extends Function[>-->]
    with Composition[>-->]
    with Construction[>-->]
    with Condition[>-->]
```
where

```scala
trait Function[>-->[- _, + _]]

trait Composition[>-->[- _, + _]]

trait Construction[>-->[- _, + _]]

trait Condition[>-->[- _, + _]]
```

belong to the same `package pdbp.program`.

We deal with the details of `trait Program`, `trait Function`, `trait Composition`, `trait Construction` and `trait Condition` later. 

`trait Program` is a type class.
`trait Program` declares or default defines the programming capabilities of program descriptions. 

We often write program instead of program description.

Note that we are a bit sloppy by not showing `[>-->[- _, + _]]`.

The programming capabilities of `Function`, `Composition` and `Construction` correspond to arrows. 

In 1998, John Hughes described arrows and used arrows in `Haskell` in
[Generalizing monads to arrows](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.29.4575&rep=rep1&type=pdf).

A program is an object of type `Z >--> Y`, where

 - `>-->` is a binary type constructor,
 - `Z` is the parameter (or argument) type of `>-->`,
 - `Y` is the return (or result) type of `>-->`.

We write

 - parameter and return if we want to be explicit about being at the delaration or definition site.

We write

 - argument and result if we want to be explicit about being at the usage site.

At the usage site the parameter is passed an argument and a result is returned.

We also write that a program transforms an argument to yield a result or that a program transforms an argument to a result.

Note that `>-->` is declared to be

 - contravariant in its parameter type,
 - covariant in its return type.

This variance property of `>-->` is related to two principles that are known as

 - the [Liskov Substitution Principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle) which, roughly speaking, states
   - consume less,
   - provide more, 
 - the [Internet Robustness Principle](https://en.wikipedia.org/wiki/Robustness_principle) which, roughly speaking, states 
   - be liberal in what you receive,
   - be generous in what you send.

## **`trait Function`**

Consider

```scala
package pdbp.program

trait Function[>-->[- _, + _]] {

  def function[Z, Y]: (Z => Y) => Z >--> Y

}
```

Think of `` function(`z=>y`) `` as a program, of type `Z >--> Y`, that is a pure function `` `z=>y` `` of type `Z => Y`. 
In what follows we also refer to programs `` function(`z=>y`) `` as atomic programs. 
It is up to you to define the granularity of atomic programs.

`` function(`z=>y`) `` is supposed to do nothing else than transforming an argument `z` of type `Z` to yield a result `` y == `z=>y`(z) `` of type `Y`.

For generic functions, we use mixed alphabetic and symbolic names within backticks, like `` `z=>y` `` to, hopefully, improve readability. 
Think of such names as typeful names.
We agree that this is a somewhat unusual naming convention. 
We know programers who love it, we know programmers who hate it. 

It may look as if programs can have only one argument resp. result.

  - Think of one argument of type `Unit` as zero arguments.

Consider

```scala
package pdbp.types.product

object productType {

  type &&[+Z, +Y] = Tuple2[Z, Y]

}
```

  - Think of one argument of type `Z && Y` as two arguments, one argument of type `Z` and one argument of type `Y`. 

The `PDBP` library deals with many arguments resp. results using nested tuples

  - `Z && Y` for two of them,
  - `Z && Y && X` for three of them,
  - `Z && Y && X && W` for four of them,
  - ...  

Note that `&&` associates to the left, so, for example, the triple type `Z && Y && X` is the same type as the nested tuple type `(Z && Y) && X`.
 
Let's motivate the reason of using typeful names with some examples that are special cases of [Theorems for free!](http://homepages.inf.ed.ac.uk/wadler/papers/free/free.dvi), as introduced by Philip Wadler.

 - There is really only one generic function of type `Z => Z` for all `Z` : identity. 
   - The name `` `z=>z` ``, hopefully, suggests this function.
 - There is really only one generic function of type `(Z && Y) => Z` for all `Z` and `Y` : left projection. 
   - The name `` `(z&&y)=>z` ``, hopefully, suggests this function.
 - There is really only one generic function of type `(Z && Y) => Y` for all `Z` and `Y` : right projection. 
   - The name `` `(z&&y)=>y` ``, hopefully, suggests this function.
 - There is really only one generic function of type `(Z && Y) => Y && Z` for all `Z` and `Y` : swap. 
   - The name `` `(z&&y)=>y&&z` ``, hopefully, suggests this function. 
 - There is really only one generic function of type `((Z => Y) && Z) => Y` for all `Z` and `Y` : function application (or, equivalently, argument binding). 
   - The name `` `((z=>y)&&z)=>y` ``, hopefully, suggests this function.

We use names like `` `y=>y` ``, `` `x=>x` ``, etc. by need, when types `Y`, `X`, etc. are involved.

We also use names like `` `z=>z`[Y] ``, `` `z=>z`[X] ``, etc. by need, when types `Y`, `X`, etc. are involved.

We could have used names `identity`, `leftProjection`, `rightProjection` `swap` and `functionApplication`. 
Sometimes we would simply run out of meaningful generic names.

For example, how would you name the unique generic function of type `(Z && Y && X & WW) => W && X` for all `Z`, `Y`, `X`, and `W`?

The main benefit of using typeful names comes when trying to understand the type of expressions.

 - `` `z=>y`(z) `` is a function application expression for which, hopefully, it should be clear that it has type `Y`. 

 and

 - `` `z=>y` apply z `` is an equivalent function application expression of type `Y`, where function application is explicit, using `apply`. 
 - `` z bind `z=>y` `` is an equivalent argument binding expression of type `Y`, where argument binding is explicit, using `bind`. 

Note that, in a way, the argument binding expression is the most elegant one since it can, conveniently, be read from left to right. 

By the way, argument binding, equivalent with function application, can be defined using an `implicit class` as follows

```scala
  object bindingOperator {

    implicit class BindingOperator[Z](z: Z) {

      def bind[Y](`z=>y`: Z => Y) = `z=>y` apply z

    }

  }
``` 

When dealing with more complex expressions, having nested sub-expressions, the usefulness of typeful names becomes even more apparent. 

Let's define some pure functions.

```scala
package pdbp.program

import pdbp.utils.functionUtils._

trait Function[>-->[- _, + _]] {

  def `z>-->z`[Z]: Z >--> Z =
    function(`z=>z`)   
  
} 
```

For programs, we use typeful names, like `` `z>-->y` ``, to, hopefully, improve readability. 

We define `` `z>-->z` `` in terms of `function` and `` `z=>z` `` where 

  - `` `z=>z` `` 

is the function you expect.

```scala
package pdbp.utils

object functionUtils {

  def `z=>z`[Z]: Z => Z = { z =>
    z
  }

} 
```

You may have doubts about the usefulness of a trivial program like`` `z>-->z` ``.  
It turns out that, when defining composite programs, obtained by substituting program arguments for program parameters of program templates, using `` `z>-->z` `` for one or more of those program arguments results in interesting composite programs of their own. 

## **`trait Composition`**

Consider

```scala
package pdbp.program

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

trait Composition[>-->[- _, + _]] {

  private[pdbp] def seqCompose[Z, Y, X]
    : (Z >--> Y && Thunk[Y >--> X]) => Z >--> X

}
```

`` seqCompose(`z>-->y`, `y>-->x`) `` is the sequential composition of `` `z>-->y` `` and `` `y>-->x` ``. 

Note that `` `y>-->x` `` is a lazy parameter.
If program `` `z>-->y` `` fails to yield a result, then program `` `y>-->x` `` is not used.

Think of `seqCompose` as a program template  and of `` `z>-->y` `` and `` `y>-->x` `` as program fragment parameters (a.k.a. program component parameters or, simply, program parameters).
Once we substitute program fragment arguments (a.k.a. program component arguments or, simply, program arguments) for those program parameters we obtain a composite program.

If the program `` `z>-->y` `` transforms an argument of type `Z` to yield a result of type `Y`, then that result serves as an argument for the subsequent program `` `y>-->x` `` which transforms it to yield a result of type `X`.

Note that `seqCompose` is `private[pdbp]`.

Consider

```scala
package pdbp.program

object compositionOperator {

  implicit class CompositionOperator[>-->[- _, + _]: Composition, -Z, +Y](
      `z>-->y`: Z >--> Y) {

    private val implicitComposition = implicitly[Composition[>-->]]

    import implicitComposition._

    def >-->[X](`y>-->x`: => Y >--> X) =
      seqCompose(`z>-->y`, Thunk(`y>-->x`))

  }

}
```

  - `seqCompose` comes with an operator equivalent `>-->`. 

Note that `>-->` is `public` (the default in `Dotty`).
Also note that `` `y>-->x` `` is a call-by-name parameter that is immediately transformed to a lazy parameter.

`` /* ... */ >--> /* ... */ `` is a first example where `Dotty` spices pointfree programming with some domain specific language flavor. 

It should not come as a surprise that 

  - `` `z>-->y` >--> `y>-->x` `` has type `Z >--> X`.

The binary type constructor `>-->` is declared to implicitly have the programming capability `seqCompose` that is declared in the type class `trait Composition`. 
The operator `>-->` is defined in terms of this programming capability. 

The definition of the `>-->` uses `val implicitComposition = implicitly[Composition[>-->]]`, that is available as an evidence having the `seqCompose` capability of `Composition`. 


## **`trait Construction`**

Consider

```scala
package pdbp.program

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

trait Construction[>-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] def product[Z, Y, X]
    : (Z >--> Y && Thunk[Z >--> X]) => Z >--> (Y && X)

  private[pdbp] def and[Z, X, Y, W]
    : (Z >--> X && Thunk[Y >--> W]) => (Z && Y) >--> (X && W) = {
    (`z>-->x`, `y>-->w`) =>
      product(seqCompose(`(z&&y)>-->z`, Thunk(`z>-->x`)),
              Thunk(seqCompose(`(z&&y)>-->y`, `y>-->w`)))
  }

  private[pdbp] def left[Z, X, Y](`z>-->x`: Z >--> X): (Z && Y) >--> (X && Y) =
    and(`z>-->x`, Thunk(`y>-->y`))

  private[pdbp] def right[Z, W, Y](`y>-->w`: Y >--> W): (Z && Y) >--> (Z && W) =
    and(`z>-->z`, Thunk(`y>-->w`))

  def `let`[Z, Y, X](`z>-->y`: Z >--> Y): In[Z, Y, X] =
    new In[Z, Y, X] {
      def `in`(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X =
        seqCompose(product(`z>-->z`, Thunk(`z>-->y`)), Thunk(`(z&&y)>-->x`))
    }

  trait In[Z, Y, X] {
    def `in`(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X
  }

}
```

where

 - `` `y>-->y` ``

and

 - `` `(z&&y)>-->z` ``, 
 - `` `(z&&y)>-->y` ``,

are the programs you expect.

```scala
package pdbp.program

import pdbp.types.product.productType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._

trait Function[>-->[- _, + _]] {

  def `y>-->y`[Y]: Y >--> Y =
    function(`y=>y`)

  def `(z&&y)>-->z`[Z, Y]: (Z && Y) >--> Z =
    function(`(z&&y)=>z`)

  def `(z&&y)>-->y`[Z, Y]: (Z && Y) >--> Y =
    function(`(z&&y)=>y`)

}
```

where

```scala
package pdbp.utils

object functionUtils {

  def `y=>y`[Y]: Y => Y = { y =>
    y
  } 
  
}  
```

and

```scala
package pdbp.utils

import pdbp.types.product.productType._

object productUtils {

  def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z = { (z, _) =>
    z
  }

  def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y = { (_, y) =>
    y
  }

}
```

`` product(`z>-->y`, `z>-->x`) `` yields a result constructed from the results yielded by `` `z>-->y` `` and `` `z>-->x` ``.

Note that `` `z>-->x` `` is a lazy parameter.
The definition of `product` is left biased. 

Think of `product` as a program template and of `` `z>-->y` `` and `` `z>-->x` `` as program parameters.
Once we substitute program arguments for those program parameters we obtain a composite program.

If the program `` `z>-->y` `` transforms an argument of type `Z` to yield a result of type `Y`, 
and the program `` `z>-->y` `` transforms that argument to yield a result of type `Y`,
then the program `` product(`z>-->y`, `z>-->x`) `` transforms that argument to yield a result of type `Y && X`.  

`trait Construction` has other members

 - `and[Z, Y, X, W]` is a more complex version of `product[Z, Y, X]`,
 - `left[Z, X, Y]` is a simpler version of ``and[Z, Y, X, W]`,
 - `right[Z, W, Y]` is a simpler version of ``and[Z, Y, X, W]`.

 Note that `product`, `and`, `left` and `right` are `private[pdbp]`.

 - `` `let`[Z, Y, X] `` has a parameter that is a program fragment that constructs a new result, and `` `in` `` has a parameter that is a program fragment that has that result available as an extra argument.

 Note that `` `let` `` and `` `in` `` are `public` (the default in `Dotty`).

 `` `let` { /* ... */ } `in` { /* ... */ } `` is a second example where `Dotty` spices pointfree programming with some domain specific language flavor.

The main difference between `` `let` { /* ... */ } `in` { /* ... */ } `` and `` /* ... */ >--> /* ... */ `` is that the former does not loose the original argument of type `Z` while the latter does. 

Note that

 - `and[Z, Y, X, W]` can be defined in terms of `product[Z, Y, X]`, `` `(z&&y)>-->z` ``, `` `(z&&y)>-->y` `` and `seqCompose`,
 - `` `let`[Z, Y, X] `` and `` `in` `` can be defined in terms of `product`, `` `z>-->z` `` and `seqCompose`.

Consider

```scala
object constructionOperators {

  implicit class ConstructionOperators[>-->[- _, + _]: Construction, -Z, +Y](
      `z>-->y`: Z >--> Y) {

    private val implicitConstruction = implicitly[Construction[>-->]]

    import implicitConstruction._

    def &[ZZ <: Z, X](`zz>-->x`: => ZZ >--> X) =
      product(`z>-->y`, Thunk(`zz>-->x`))

    def &&[X, W](`x>-->w`: => X >--> W) =
      and(`z>-->y`, Thunk(`x>-->w`))

  }

}
```

  - `product[Z, Y, X]` comes with an operator equivalent `&`,
  - `and[Z, Y, X, W]` comes with an operator equivalent `&&`.

Note that `&` and `&&` are `public` (the default in `Dotty`).
Also note that `` `zz>-->x` `` and `` `x>-->w` `` are call-by-name parameters that are immediately transformed to a lazy parameters.

`` /* ... */ & /* ... */ `` and `` /* ... */ && /* ... */ `` are a third and fourth example where `Dotty` spices pointfree programming with some domain specific language flavor. 

It should not come as a surprise that 

  - `` `z>-->y` & `z>-->x` `` has type `Z >--> (Y && X)`,
  - `` `z>-->y` && `x>-->w` `` has type `(Z && X) >--> (Y && W)`.

The type constructor `>-->` is declared to implicitly have the programming capabilities `product` and `and` that are declared or default defined in the type class `trait Construction`. 
The operators `&` and `&&` are defined in terms of those programming capabilities. 

The definitions use `val implicitConstruction = implicitly[Construction[>-->]]`, that is available as an evidence having the `product` and `and` capabilities of `Construction`.

## **`trait Condition`**

Consider 

```scala
package pdbp.program

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._

trait Condition[>-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] & Construction[>-->] =>

  private[pdbp] def sum[Z, Y, X]
    : (Thunk[Y >--> Z] && Thunk[X >--> Z]) => (Y || X) >--> Z

  private[pdbp] def or[Z, X, Y, W]
    : (Thunk[X >--> Z], Thunk[W >--> Y]) => (X || W) >--> (Z || Y) = {
    (`x>-->z`, `w>-->y`) =>
      sum(Thunk(seqCompose(`x>-->z`.eval, Thunk(`z>-->(z||y)`))),
          Thunk(seqCompose(`w>-->y`.eval, Thunk(`y>-->(z||y)`))))
  }

  def `if`[W, Z](`w>-->b`: W >--> Boolean): Apply[W, Z] =
    new Apply[W, Z] {
      override def apply(`w>-t->z`: => W >--> Z): Else[W, Z] =
        new Else[W, Z] {
          override def `else`(`w>-f->z`: => W >--> Z): W >--> Z =
            seqCompose(`let`(`w>-->b`) `in` `(w&&b)>-->(w||w)`,
                       Thunk(sum(Thunk(`w>-t->z`), Thunk(`w>-f->z`))))
        }
    }

  trait Apply[W, Z] {
    def apply(`w>-t->z`: => W >--> Z): Else[W, Z]
  }

  trait Else[W, Z] {
    def `else`(`w>-f->z`: => W >--> Z): W >--> Z
  }

}
```
where

```scala
package pdbp.types.sum

object sumType {

  case class Left[+Z](z: Z)

  case class Right[+Y](y: Y)

  type ||[+Z, +Y] = Left[Z] | Right[Y]

}
```

and where

 - `` `z>-->(z||y)` ``,
 - `` `y>-->(z||y)` ``,
 - `` `(w&&b)>-->(w||w)` ``, where `b` corresponds to the type `Boolean`

are the programs you expect. 

Agreed, `` `(w&&b)>-->(w||w)` `` is one of the two programs you expect. 
It is the one where `true` corresponds to `Left` and  `false` corresponds to `Right`.

```scala
package pdbp.program

import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._

trait Function[>-->[- _, + _]] {

  def `z>-->(z||y)`[Z, Y]: Z >--> (Z || Y) =
    function(`z=>(z||y)`)

  def `y>-->(z||y)`[Z, Y]: Y >--> (Z || Y) =
    function(`y=>(z||y)`)

  def `(w&&b)>-->(w||w)`[W]: (W && Boolean) >--> (W || W) =
    function(`(w&&b)=>(w||w)`)

}
```

where

```scala
package pdbp.utils

import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

object sumUtils {  

  def `z=>(z||y)`[Z, Y]: Z => (Z || Y) = { z =>
    Left(z)
  }

  def `y=>(z||y)`[Z, Y]: Y => (Z || Y) = { y =>
    Right(y)
  }

  def foldBoolean[Z]: (Thunk[Z], Thunk[Z]) => Boolean => Z = { (tz, fz) =>
    {
      case true  => tz.eval
      case false => fz.eval
    }
  }

  def `(w&&b)=>(w||w)`[W]: (W && Boolean) => (W || W) = { (w, b) =>
    foldBoolean[W || W](Thunk(Left(w)), Thunk(Right(w)))(b)
  }

}
```

`` sum(`y>-->z`, `x>-->z`) `` uses a "left or right" condition to let either `` `y>-->z` `` or `` `x>-->z` `` take over control.

Note that `` `y>-->z` `` and `` `x>-->z` `` are lazy parameters.

Think of `sum` as a program template and of `` `y>-->z` `` and `` `x>-->z` `` as program parameters.
Once we substitute program arguments for those program parameters we obtain a composite program.

`trait Condition` has other members

 - `or[Z, X, Y, W]` is a more complex version of `sum[Z, Y, X]`

Note that `sum` and `or` are `private[pdbp]`.

 - `` `if`[W, Z] `` has a parameter that is a program that has a result of type `Boolean` that is used to let either the program parameter of `apply` or the program parameter of `` `else` `` take over control.

Note that `` `if` `` and `` `else` `` are `public` (the default in `Dotty`).

`` `if`(/* ... */) { /* ... */ } `else` { /* ... */ } `` is a fifth example where `Dotty` spices pointfree programming with some domain specific language flavor.  

Note that

 - `and[Z, Y, X, W]` can be defined in terms of `product[Z, Y, X]`, `` `(z&&y)>-->z` ``, `` `(z&&y)>-->y` `` and `seqCompose`,
 - `` `if`[W, Z] `` and `` `else` `` can be defined in terms of `sum`, `` `let` `` and `` `in` ``.

## **The power of expression of `` `let` { } `in` { } ``**

`product[Z, Y, X]` can be defined in terms of `` `let` { /* ... */  } `in` { /* ... */ } ``.

```scala
package pdbp.examples

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.utils.productUtils._

import pdbp.program.Function
import pdbp.program.Composition
import pdbp.program.Construction

import pdbp.program.compositionOperator._

trait ProductInTermsOfLetAndIn[
    >-->[- _, + _]: Function: Composition: Construction] {

  val implicitFunction = implicitly[Function[>-->]]
  val implicitConstruction = implicitly[Construction[>-->]]

  import implicitFunction._
  import implicitConstruction._

  private[pdbp] def product[Z, Y, X]
    : (Z >--> Y && Thunk[Z >--> X]) => Z >--> (Y && X) = {
    (`z>-->y`, `z>-->x`) =>
      `let` {
        `z>-->y`
      } `in` {
        `let` {
          `(z&&y)>-->z` >--> `z>-->x`.eval
        } `in` {
          `(z&&y&&x)>-->(y&&x)`
        }
      }
  }

}
```
where

  - `` `(z&&y&&x)>-->(y&&x)` `` 
 
is the program you expect.

```scala
package pdbp.program

import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._
import pdbp.utils.productAndSumUtils._

trait Function[>-->[- _, + _]] {

  def `(z&&y&&x)>-->(y&&x)`[Z, Y, X]: (Z && Y && X) >--> (Y && X) =
    function(`(z&&y&&x)=>(y&&x)`)

}
```

where

```scala
package pdbp.utils

import pdbp.types.product.productType._

object productUtils {

  def `(z&&y&&x)=>(y&&x)`[Z, Y, X]: (Z && Y && X) => (Y && X) = {
    case ((_, y), x) => (y, x)
  }

}
```

The definition of `product` is an example of a recurring theme of the `PDBP` library.

Defining a program as an application developer, or programming capability as a library developer, often boils down to a "getting the types right" puzzle. 

Often there is only one meaningful way to get them right. 
Let's have a look at some of the details of the typing puzzle for this definition.

The outer `` `let` `` creates, using `` `z>-->y` ``, a new argument of type `Y` for the outer `` `in` `` which, as a consequence, has an argument of type `Z && Y` available, representing two arguments, one of type `Z` and one of type `Y`. 

The inner `` `let` `` of the outer `` `in` `` creates, using `` `(z&&y)>-->z` >--> `z>-->x`.eval ``, the composition of `` `(z&&y)>-->z` `` and `` `z>-->x`.eval ``, a new argument of type `X` for the inner `` `in` ``  of the outer `` `in` `` which, as a consequence, has an argument of type `Z && Y && X` available, representing three arguments, one of type `Z`, one of type `Y`, and one of type `X`. 

The inner `` `in` `` in the outer `` `in` `` simply gets rid of the original argument of type `Z` using `` `(z&&y&&x)>-->(y&&x)` ``.

Note that typeful names, hopefully, help to understand the typing puzzle. 

For example

  - in the composition `` `(z&&y)>-->z` >--> `z>-->x`.eval ``, the matching `z`'s reflect the type `Z` involved,
  - in the name `` `(z&&y&&x)>-->(y&&x)` ``, both `(z&&y&&x)` and `(y&&x)` reflect the types `(Z && Y && X)` and `(Y && X)` involved.

One challenge that comes with pointfree programming is getting the necessary arguments out of all available arguments. 
The program `` `(z&&y&&x)>-->(y&&x)` `` above gets a `y` and an `x` out of a `z`, a `y` and an `x`. 

One way to deal with this challenge is to keep programs, and therefore, the arguments and results that come with them, relatively small. 
After all, small program components can be combined to obtain larger composite programs by substituting them into program templates.

## **The power of expression of `` `if`() { } `else` { }``**

`sum[Z, Y, X]` can be defined in terms of `` `if`(/* ... */) { /* ... */ } `else` { /* ... */ }``.

```scala
package pdbp.examples

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.sumUtils._

import pdbp.program.Function
import pdbp.program.Composition
import pdbp.program.Condition

import pdbp.program.compositionOperator._

trait SumInTermsOfIfAndElse[>-->[- _, + _]: Function: Composition: Condition] {

  val implicitFunction = implicitly[Function[>-->]]
  val implicitCondition = implicitly[Condition[>-->]]

  import implicitFunction._
  import implicitCondition._

  private[pdbp] def sum[Z, Y, X]
    : (Thunk[Y >--> Z] && Thunk[X >--> Z]) => (Y || X) >--> Z = {
    (`y>-->z`, `x>-->z`) =>
      `if`(`(y||x)>-->b`) {
        `(y||x)>-->y` >--> `y>-->z`.eval
      } `else` {
        `(y||x)>-->x` >--> `x>-->z`.eval
      }
  }

}
```

where

  - `` `(y||x)>-->y` ``,
  - `` `(y||x)>-->x` ``,
  - `` `(y||x)>-->b` ``, where `b` corresponds to the type `Boolean`
 
are the programs you expect.

Agreed, `` `(y||x)>-->b` `` is one of the two programs you expect. 
It is the one where `true` corresponds to `Left` and  `false` corresponds to `Right`.

```scala
package pdbp.program

import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._
import pdbp.utils.productAndSumUtils._

trait Function[>-->[- _, + _]] {

  def `(y||x)>-->y`[Y, X]: (Y || X) >--> Y =
    function(`(y||x)=>y`)

  def `(y||x)>-->x`[Y, X]: (Y || X) >--> X =
    function(`(y||x)=>x`)    

  def `(y||x)>-->b`[Y, X]: (Y || X) >--> Boolean =
    function(`(y||x)=>b`)

}
```

where

```scala
package pdbp.utils

import pdbp.types.implicitUnit._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

object sumUtils {

  def sum[Z, Y, X]: (Thunk[Y => Z] && Thunk[X => Z]) => (Y || X) => Z = {
    (`y=>z`, `x=>z`) =>
      {
        case Left(y) =>
          `y=>z`.eval(y)
        case Right(x) =>
          `x=>z`.eval(x)
      }
  }

  def `(y||x)=>y`[Y, X]: (Y || X) => Y =
    sum[Y, Y, X](Thunk(y => y), Thunk(_ => ???))

  def `(y||x)=>x`[Y, X]: (Y || X) => X =
    sum[X, Y, X](Thunk(_ => ???), Thunk(x => x))

  def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
    sum[Boolean, Y, X](Thunk(_ => true), Thunk(_ => false))
    
}
```

Note that, again, typeful names, hopefully, help to understand the typing puzzle. 
For example

  - in the composition `` `(y||x)>-->y` >--> `y>-->z`.eval ``, the matching `y`'s reflect the type `Y` involved, 
  - In the composition `` `(y||x)>-->x` >--> `x>-->z`.eval ``, the matching `x`'s reflect the type `X` involved.

## **`factorial`**

Consider

```scala
import pdbp.program.Program

import pdbp.program.compositionOperator._

class Factorial[>-->[- _, + _]: Program]
    extends AtomicPrograms[>-->]()
    with HelperPrograms[>-->]() {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  val factorial: BigInt >--> BigInt =
    `if`(isZero) {
      one
    } `else` {
      `let` {
        subtractOne >-->
          factorial
      } `in` {
        multiply
      }
    }

}
```

where

```scala
package examples.programs

import pdbp.types.product.productType._

import pdbp.program.Program

trait AtomicPrograms[>-->[- _, + _]: Program] extends HelperPrograms[>-->] {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  val isZero: BigInt >--> Boolean =
    isZeroHelper

  def one[Z]: Z >--> BigInt =
    oneHelper

  val subtractOne: BigInt >--> BigInt =
    subtractOneHelper

  val multiply: (BigInt && BigInt) >--> BigInt =
    multiplyHelper

}
```

and


```scala
import pdbp.types.product.productType._

import pdbp.program.Program

import examples.utils.functionUtils._

trait HelperPrograms[>-->[- _, + _]: Program] {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  val isZeroHelper: BigInt >--> Boolean =
    function(isZeroFunction)

  def oneHelper[Z]: Z >--> BigInt =
    function(oneFunction)

  val subtractOneHelper: BigInt >--> BigInt =
    function(subtractOneFunction)

  val multiplyHelper: (BigInt && BigInt) >--> BigInt =
    function(multiplyFunction)

}
```

and

```scala
package examples.utils

import scala.language.implicitConversions

import pdbp.types.product.productType._

object functionUtils {

  val isZeroFunction: BigInt => Boolean = { i =>
    i == 0
  }

  def oneFunction[Z]: Z => BigInt = { z =>
    1
  }

  val subtractOneFunction: BigInt => BigInt = { i =>
    i - 1
  }

  val multiplyFunction: (BigInt && BigInt) => BigInt = { (i, j) =>
    i * j
  }

}
```

`factorial` is a first, `PDBP` program DSL flavored, pointfree functional program. 

The atomic programs, `isZero`, `one`, `subtractOne` and `multiply` used by `factorial` are maximally fine-grained.

Note that, for `factorial`, the extra helper programs layer is not necessary (it is needed later). 

## **main programs**

Recall that programs have type `Z >--> Y` for types `Z` and `Y`.

For example: `factorial` has type `BigInt >--> BigInt`.

If

 - `producer` is a producer program of type `Unit >--> Z`,
 - `program` is a program of type `Z >--> Y`,
 - `consumer` is a consumer program of type `Y >--> Unit`,

then

 - `producer >--> program >--> consumer` is a main program of type `Unit >--> Unit`.


We also simply refer to 

  - a producer program as a producer,
  - a consumer program as a consumer.
  
We also refer to
  - a main program as a service.

Programs are design artifacts that, among others, are combined using composition
  - when given two programs, the result of the first one is the argument of the second one.

Main programs are architectural artifacts that are combined using I/O 
  - when given two main programs, the output produced by the first one is the input consumed by the second one.

There is a tendency to keep both programs and main programs relatively small.

Relatively small services are often referred to as microservices.

Keeping programs relatively small is often referred to as
  - good programmers write baby-code. 

This quote is introduced by [*Erik Meijer*](https://en.wikipedia.org/wiki/Erik_Meijer_(computer_scientist)).
Erik Meijer is so famous that he does not need an introduction. 
I was very lucky to be able to do research with him, on monads and related stuff, at the Univeristy of Utrecht back in the ninetees.

Keeping services relatively small is often referred to as
  - Do one thing and do it well.

This quote is inspired by the [`Unix`](https://en.wikipedia.org/wiki/Unix) philosophy.

## **`mainFactorial`**

Consider

```scala
package examples.mainPrograms

import pdbp.program.Program

import pdbp.program.compositionOperator._

import examples.programs.Factorial

trait MainFactorial[>-->[- _, + _]: Program] {

  private object factorialObject extends Factorial[>-->]

  import factorialObject.factorial

  val producer: Unit >--> BigInt

  val consumer: BigInt >--> Unit

  lazy val mainFactorial: Unit >--> Unit =
    producer >-->
      factorial >-->
      consumer

}
```

`trait MainFactorial` defines `lazy val mainFactorial` using abstract members `producer` and `consumer`.
`mainFactorial` is a main program of type `Unit >--> Unit`.

Note that `mainFactorial` is a main program description.
It is a `PDBP` library level syntactic construct.

## **`trait Computation`**

Consider

```scala
package pdbp.computation

import pdbp.types.kleisli.kleisliBinaryTypeConstructorType.Kleisli

import pdbp.program.Program

import pdbp.program.Applying

private[pdbp] trait Computation[C[+ _]]
    extends Resulting[C]
    with Binding[C]
    with Lifting[C]
}
```

where

```scala
private[pdbp] trait Resulting[C]

private[pdbp] trait Binding[C]

private[pdbp] trait Lifting[C]
```

belong to the same `package pdbp.computation`.

`trait Computation` declares or default defines the computational capabilities of computation descriptions. 

We often write computation instead of computation description.

Note that we are a bit sloppy by not showing `[C[+ _]]`.

`trait Computation`, `trait Resulting`, `trait Binding` and `trait Lifting` are dealt with later. 

Note that, again, we are a bit sloppy by not showing `[C]`.

The computational capabilities of `Resulting` and `Binding` correspond to monads. 

In 1991, Eugenio Moggi described monads in
[*Notions of computation and monads*](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.79.733&rep=rep1&type=pdf).

In 1992, Philip Wadler used monads in `Haskell` in 
[*The essence of functional programming*](http://citeseerx.ist.psu.edu/viewdoc/download;jsessionid=E09A5FD9362F6780675ADF29471B7428?doi=10.1.1.38.9516&rep=rep1&type=pdf).

A computation is an `object` of type `C[Z]`, where

 - `C` is a unary type constructor,
 - `Z` is the return (or result) type of `C`.

We write

 - return if we want to be explicit about being at the delaration or definition site.

We write

 - result if we want to be explicit about being at the usage site.

We also write that a computation yields a result or that a computation has a result.

Note that all computational capabilities are `private [pdbp]`. 

We do not want to expose the pointful computational capabilies to the users of the `PDBP` library. 
We only expose pointfree programming capabilities to the users of the `PDBP` library. 
It is convenient to have pointful computational capabilies available for the developers of the `PDBP` library. 
It is also simpler (not necessarily easier, though) to define `object`'s that `extend Computation`, since `Computation` has less declared capabilities to define than `Program`.

 - We offer a DSL flavored pointfree functional programming API for he users of the `PDBP` library.
 - We offer a pointful functional programming API for he developers of the `PDBP` library.

## **Variance**

Note that `C` is

 - *covariant* in its result type.

This *variance* property of `C` is related to two principles that are known as

 - the [*Liskov Substitution Principle*](https://en.wikipedia.org/wiki/Liskov_substitution_principle) which, roughly speaking, states, among others
   - *provide more*, 
 - the [*Internet Robustness Principle*](https://en.wikipedia.org/wiki/Robustness_principle) which, roughly speaking, states, among others 
   - *be generous in what you send*.

## **`trait Resulting`**

Consider

```scala
package pdbp.computation

private[pdbp] trait Resulting[C[+ _]] {

  private[pdbp] def result[Z]: Z => C[Z]

}
```

Think of `result(ez)` as a computation that is a pure expression `ez`. 
Executing `result(ez)` it is supposed to do nothing else than evaluating `ez`.

In what follows we also refer to computations `result(ez)`, that, essentially, are pure expression, as atomic computations. 
It is up to you to define the granularity of atomic computations.

## **`trait Binding`**

Consider

```scala
package pdbp.computation

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

private[pdbp] trait Binding[C[+ _]] {

  private[pdbp] def bind[Z, Y]: (C[Z] && Thunk[Z => C[Y]]) => C[Y]

}
```

Think of a thunk `` `z=>cy` `` as a computation execution continuation, or, simply, computation continuation, and of `cz` as a computation fragment or computation component, or, simply, sub-computation).

If we start executing the sub-computation `cz`, then, once its result has been bound to the computation continuation `z=>cy`, we can continue executing the composite computation `` bind(cz, `z=>cy`) ``.

If the sub-computation `cz` yields a result of type `Z`, then that result serves as an argument for the computation continuation `z=>cy.eval`, which transforms it to a computation that yields a result of type `Y`.

Consider

```scala
package pdbp.computation

private[pdbp] object bindingOperator {

  implicit class bindingOperator[C[+ _]: Binding, -Z, ZZ <: Z](czz: C[ZZ]) {

    private val implicitBinding = implicitly[Binding[C]]

    private[pdbp] def bind[Y](`zz=>cy`: ZZ => C[Y]): C[Y] =
      implicitBinding.bind(czz, Thunk(`zz=>cy`))
  }

}
```

  - `bind[Z, Y]` comes with an operator equivalent `bind`,


## **`trait Lifting`**

Consider

```scala
package pdbp.computation

private[pdbp] trait Lifting[C[+ _]]
    extends ObjectLifting[C]
    with FunctionLifting[C]
    with OperatorLifting[C]
```

where

```scala
private[pdbp] trait ObjectLifting[C]

private[pdbp] trait FunctionLifting[C]

private[pdbp] trait OperatorLifting[C]
```

belong to the same `package pdbp.computation`.

`trait Lifting` is a type class. 
`trait Lifting` declares the lifting capabilities of computations. 

Note that we are a bit sloppy by not showing `[C[+ _]]`.

The lifting capabilities of `Lifting` correspond to applicatives (a.k.a. idioms).

In 2008, Conor McBride and Ross Paterson described applicatives and used applicatives in `Haskell` in 
[*Applicative programming with effects*](http://www.staff.city.ac.uk/~ross/papers/Applicative.pdf).

`trait Lifting`, `trait ObjectLifting`, `trait FunctionLifting` and `trait OperatorLifting` are dealt with later in this section. 

Note that, again, we are a bit sloppy by not showing `[C]`.

## **`trait ObjectLifting`**

Consider

```scala
package pdbp.computation

private[pdbp] trait ObjectLifting[C[+ _]] {

  private[pdbp] def liftObject[Z](z: Z): C[Z] =
    lift0(z)

  private[pdbp] def lift0[Z]: Z => C[Z] =
    liftObject

}
```

`liftObject` and it's alias `lift0` are members that lift an object `z` of type `Z` to a computation of type `C[Z]` with result `z`.
The `0` in `lift0` stands for lifting zero parameters (resp. arguments).

## **Describing `trait FunctionLifting`**

Consider

```scala
package pdbp.computation

private[pdbp] trait FunctionLifting[C[+ _]] {

  private[pdbp] def liftFunction[Z, Y](`z=>y`: Z => Y): C[Z] => C[Y] =
    lift1(`z=>y`)

  private[pdbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] =
    liftFunction

}
```

`liftFunction` and it's alias `lift1` are members that lift an object level function to a computation level function.
The `1` in `lift1` stands for lifting one parameter (resp. argument).

The computational capabilities of `FunctionLiftinh` correspond to functors. 

## **`trait OperatorLifting`**

Consider

```scala
package pdbp.computation

import pdbp.types.product.productType._

private[pdbp] trait OperatorLifting[C[+ _]] {

  private[pdbp] def liftOperator[Z, Y, X](
      `(z&&y)=>x`: (Z && Y) => X): (C[Z] && C[Y]) => C[X] =
    lift2(`(z&&y)=>x`)

  private[pdbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] =
    liftOperator

}
```

`liftOperator` and it's alias `lift2` are members that lift an object level operator to a computation-level operator.
The `2` in `lift2` stands for lifting two parameters (resp. arguments).

## **`trait Lifting` revisited**

Consider

```scala
package pdbp.computation

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._

private[pdbp] trait Lifting[C[+ _]]
    extends ObjectLifting[C]
    with FunctionLifting[C]
    with OperatorLifting[C] {

  // lifting apply

  private[pdbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y] =
    liftOperator(`((z=>y)&&z)=>y`)

  // lifting and

  private[pdbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    liftOperator(`(z&&y)=>(z&&y)`)

  private[pdbp] def liftAnd[Z, Y, X, W]
    : ((Z => C[X]) && Thunk[Y => C[W]]) => ((Z && Y) => C[X && W]) = {
    (`z=>cx`, `y=>cw`) =>
      and(`z=>cx`, `y=>cw`) andThen liftProduct
  }

  // lifting or

  private[pdbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y] =
    sum(Thunk(lift1[Z, Z || Y](`z=>(z||y)`)),
        Thunk(lift1[Y, Z || Y](`y=>(z||y)`)))

  private[pdbp] def liftOr[Z, Y, X, W]
    : (Thunk[Z => C[X]] && Thunk[Y => C[W]]) => ((Z || Y) => C[X || W]) = {
    (`z=>cx`, `y=>cw`) =>
      or(`z=>cx`, `y=>cw`) andThen liftSum
  }

  // lift1 in terms of lift0 and lift2

  private[pdbp] override def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] = {
    `z=>y` => cz =>
      lift2(`((z=>y)&&z)=>y`)(lift0(`z=>y`), cz)

  }

  // keep on lifting

  private[pdbp] def lift3[Z, Y, X, W]
    : ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] = {
    `((z&&y)&&x)=>w` =>
      `(z=>y)=>((z&&x)=>(y&&x)))`(liftProduct) andThen lift2(`((z&&y)&&x)=>w`)
  }

  // and so on ...

}
```

`Lifting` comes with other interesting computational capabilities.

  - `liftApply`, defined in terms of `liftOperator`,
  - `liftProduct`, defined in terms of `liftOperator`,
  - `liftSum`, defined in terms of `sum`

where

 - `` `((z=>y)&&z)=>y` ``
 - `` `(z&&y)=>(z&&y)` ``

are the functions you expect.  

`Lifting` comes with two other other interesting computational capabilities.

  - `liftAnd`, defined in terms of `liftProduct` and `and`
  - `liftOr`, defined in terms of `liftSum` and `or` 

where

 - `and`
 - `or`

are the functions you expect. 

`lift1` can be defined in terms of `lift0` and `lift2`.

Lifting does not stop with objects (`lift0`), (unary) functions (`lift1`) and operators (binary functions) (`lift2`).
It is possible to define `lift3`, for lifting ternary opertors and so on ... .

  - `lift3` is defined in terms of `lift2`

where

 - `` `(z=>y)=>((z&&x)=>(y&&z)))` ``

is the functions you expect (actually, it is `` `(z=>y)=>((z&&x)=>(y&&x)))`[C[Z] && C[Y], C[Z && Y], C[X]] ``.

Below are the `productUtils` details

```scala
package pdbp.utils.product

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

object productUtils {

  def `((z=>y)&&z)=>y`[Z, Y]: ((Z => Y) && Z) => Y = { (`z=>y`, z) =>
    `z=>y`(z)
  }

  def `(z&&y)=>(z&&y)`[Z, Y]: (Z && Y) => (Z && Y) = { `z&&y` =>
    `z&&y`
  }

  def `(z=>y)=>((z&&x)=>(y&&x)))`[Z, Y, X]
    : (Z => Y) => ((Z && X) => (Y && X)) = { `z=>y` => (z, x) =>
    (`z=>y`(z), x)
  }  

  def and[Z, X, Y, W]: ((Z => X) && Thunk[Y => W]) => ((Z && Y) => (X && W)) = {
    (z2x, y2w) => (z, y) =>
      (z2x(z), y2w.eval(y))
  }  

}
```

Below are the `sumUtils` details


```scala
package pdbp.utils.product

import pdbp.types.implicitUnit._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

object sumUtils {

  def or[Z, Y, X, W]: (Thunk[X => Z], Thunk[W => Y]) => (X || W) => (Z || Y) = {
    (`x=>z`, `w=>y`) =>
      {
        case Left(x) =>
          Left(`x=>z`.eval(x))
        case Right(w) =>
          Right(`w=>y`.eval(w))
      }
  }

}
```

## **`Kleisli` types**

Consider

```scala
package pdbp.types.kleisli.binary

private[pdbp] object kleisliBinaryTypeConstructorType {

  private[pdbp] type Kleisli[C[+ _]] = [-Z, + Y] => Z => C[Y]

}
```

Note that `Kleisli[C]`, where `C` is a unary type constructor, is itself a binary type constructor.

A program of type `Kleisli[C]` is referred to as a kleisli program.
Note that we use a lower case k. 
A kleisli program is a function that transforms an argument to yield a computation result.

Consider

```scala
package pdbp.types.kleisli.unary

private[pdbp] object kleisliUnaryTypeConstructorType {

  private[pdbp] type Kleisli[>-->[- _, + _]] = [+Y] => Unit >--> Y

}
```
 
Note that `Kleisli[>-->]`, where `>-->` is a binary type constructor, is itself a unary type constructor.

A computation of type `Kleisli[>-->]` is referred to as a kleisli computation. 
Note that, again, we use a lower case k. 
A kleisli computation is a program without arguments.

## **`ProgramWithLifting`**

Consider

```scala
package pdbp.program

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.kleisli.unary.kleisliUnaryTypeConstructorType._

import pdbp.computation.Lifting

private[pdbp] trait ProgramWithLifting[>-->[- _, + _]]
    extends Program[>-->]
    with Lifting[Kleisli[>-->]] {

  private type C = Kleisli[>-->]

  private[pdbp] override def lift0[Z]: Z => C[Z] = 
    `z=>(u>-->z)`


  private[pdbp] override def lift2[Z, Y, X]
    : ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] = { `(z&&y)=>x` => (cy, cz) =>
    seqCompose(product(cy, Thunk(cz)), Thunk(function(`(z&&y)=>x`)))
  }

}
```
where

 - `z=>(u>-->z)`

 is the function you expect

 ```scala
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._
import pdbp.utils.productAndSumUtils._

trait Function[>-->[- _, + _]] {

  def `z=>(y>-->z)`[Z, Y]: Z => (Y >--> Z) = { z =>
    function(`z=>(y=>z)`(z))
  }

  def `z=>(u>-->z)`[Z]: Z => (Unit >--> Z) =
    `z=>(y>-->z)`[Z, Unit] 

}
```

where

```scala
package pdbp.utils

object functionUtils {

  def `z=>(y=>z)`[Z, Y]: Z => Y => Z = { z => y =>
    z
  }

}  
```

Lifting capabilities can be defined in terms of programming capabilities.

## **`trait Computation` revisited**

Consider

```scala
package pdbp.computation

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.utils.sumUtils._

import pdbp.program.Program
import pdbp.program.Applying

private[pdbp] trait Computation[C[+ _]]
    extends Resulting[C]
    with Binding[C]
    with Lifting[C]
    with Program[Kleisli[C]]
    with Applying[Kleisli[C]] {

  override private[pdbp] def lift0[Z]: Z => C[Z] =
    result

  override private[pdbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] = `z=>y` => {
    case cz =>
      bind(cz, Thunk(z => result(`z=>y`(z))))
  }

  override private[pdbp] def lift2[Z, Y, X]
    : ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] = `(z&&y)=>x` => {
    case (cz, cy) =>
      bind(cz, Thunk(z => bind(cy, Thunk(y => result(`(z&&y)=>x`(z, y))))))
  }

  override private[pdbp] def lift3[Z, Y, X, W]
    : ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] =
    `(z&&y&&x)=>w` => {
      case ((cz, cy), cx) =>
        bind(
          cz,
          Thunk(
            z =>
              bind(cy,
                   Thunk(y =>
                     bind(cx, Thunk(x => result(`(z&&y&&x)=>w`((z, y), x))))))))
    }

  // and so on ...

  private type `=>C` = Kleisli[C]

  override def function[Z, Y]: (Z => Y) => Z `=>C` Y = { `z=>y` => z =>
    result(`z=>y`(z))
  }

  override def seqCompose[Z, Y, X]
    : (Z `=>C` Y && Thunk[Y `=>C` X]) => Z `=>C` X = {
    (`z=>cy`, `y=>cx`) => z =>
      bind(`z=>cy`(z), `y=>cx`)
  }

  override def product[Z, Y, X]
    : (Z `=>C` Y && Thunk[Z `=>C` X]) => Z `=>C` (Y && X) = {
    (`z=>cy`, `z=>cx`) => z =>
      bind(`z=>cy`(z),
           Thunk(y => bind(`z=>cx`.eval(z), Thunk(x => result(y, x)))))
  }

  override def sum[Z, Y, X]
    : (Thunk[Y `=>C` Z] && Thunk[X `=>C` Z]) => (Y || X) `=>C` Z = {
    (`y=>cz`, `x=>cz`) =>
      pdbp.utils.sumUtils.sum(`y=>cz`, `x=>cz`)
  }

  override private[pdbp] def apply[Z, Y]: (Z && (Z `=>C` Y)) `=>C` Y = {
    (z, `z=>cy`) =>
      `z=>cy`(z)
  }

  // useful

  private[pdbp] def flatten[Z]: C[C[Z]] => C[Z] = { ccz =>
    bind(ccz, Thunk(identity[C[Z]]))
  }

  private[pdbp] def flatSeqCompose[Z, Y, X](
      `z=>cy`: Z `=>C` Y,
      `y=>ccx`: Thunk[Y `=>C` C[X]]): Z `=>C` X =
    seqCompose(
      seqCompose(
        `z=>cy`,
        `y=>ccx`
      ),
      Thunk(identity[C[X]])
    )

}
```

where

```scala
package pdbp.program

import pdbp.types.product.productType._

private[pdbp] trait Applying[>-->[- _, + _]] {

  private[pdbp] def apply[Z, Y]: (Z && (Z >--> Y)) >--> Y

}
```

The lifting, programming and applying capabilities can be defined in terms of the resulting and binding computational capabilities.

Note that `result` and `bind` really provide a lot of, agreed, pointful, power of expression for library developers.
Note that the definitions of `lift0`, `lift1`, `lift2`, `lift3`, ... , `function`, `seqCompose` and `product` naturally read from left to right.

Note that it suffices to define the `lift0` and `lift2` in terms of `result` and `bind` but we define `lift1` and `lift3` anyway.

If we add the applying capability to the programming capababilities, then the computational resulting and binding capabilities can be defined in terms of them.

```scala
package pdbp.program

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.kleisli.unary.kleisliUnaryTypeConstructorType._

import pdbp.program.Program
import pdbp.program.Applying

import pdbp.computation.Resulting
import pdbp.computation.Binding

private[pdbp] trait ProgramWithApplying[>-->[- _, + _]]
    extends Program[>-->]
    with Applying[>-->]
    with Resulting[Kleisli[>-->]]
    with Binding[Kleisli[>-->]] {

  private type C = Kleisli[>-->]

  override private[pdbp] def result[Z]: Z => C[Z] =
    `z=>(u>-->z)`

  override private[pdbp] def bind[Z, Y]: (C[Z] && Thunk[Z => C[Y]]) => C[Y] = {
    (cz, `z=>cy`) =>
      seqCompose(cz,
                 Thunk(
                   seqCompose(product(`z>-->u`, Thunk(function(`z=>cy`.eval))),
                              Thunk(apply))))
  }

}
```

 where

 - `z>-->u`

 is the program you expect

```scala
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._
import pdbp.utils.productAndSumUtils._

trait Function[>-->[- _, + _]] {

  def `z>-->u`[Z]: Z >--> Unit =
    function(`z=>u`)

}
```

where

```scala
package pdbp.utils

object functionUtils {

  def `z=>u`[Z]: Z => Unit = { z =>
    ()
  }

}  
```

In 2008, Sam Lindley, Philip Wadler and Jeremy Yallop compared the *power of expression* of monads, arrows and idioms in 
[*Idioms are oblivious, arrows are meticulous, monads are promiscuous*](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.187.6750&rep=rep1&type=pdf). 

 - Monads have most power of expression (cfr. `trait Computation[C]` which mixes-in `trait Program[Kleisli[C]]`).

 - Arrows are in between (cfr. `trait ProgramWithLifting[>-->]` which mixes-in `trait Lifting[Kleisli[>-->]]`) .
 
 - Applicatives have least power of expression. 

We do not let `trait Program[>-->]` extends `trait Lifting[Kleisli[>-->]]` because that leads to type inferencing errors 
(incompatible unary type constructors `C` (`[+ Y] => C[Y]`) and `Kleisli[Kleisli[C]]` (`[+Y] => Unit => C[Y]`)).


 - Monads (`Computation`) naturally promote pointful, result and binding based programming.
 - Arrows (`Program`) naturally promote pointfree, composition based programming.
 - Functions also naturally promote pointfree, composition based programming, and applicatives (`Lifting`) are lifted functions.

You may argue why we go for arrows (cfr. `Program`) instead of applicatives (cfr. `Lifting`) for application developers.

First, programming programs in a pointfree style is not really more difficult than programming functions in a pointfree style.

Second, by flavoring the pointfree programming API with a programming DSL, programming programs using a pointfree style is not really less elegant than programming functions using a pointfree style.

Moreover, programs can only be programmed using a pointfree style.
Functions can be programmed both using a pointful style and using a pointfree style, programming functions using a pointfree style requires programmers to have the discipline to do so.

Finally, as stated above, applicatives provide less power of expression.

We go for monads for library developers.
Monads, being the most concrete of the three models it is also the least applicable one.
We can live with this limitation.

Programs are defined as members of `class`es that are declared to implicitly have the declared or default defined programming capabilities of `trait Program[>-->[- _, + _]]`.

Think of programs as programming domain specific, library language level, syntactic constructs.

We also refer to programs as `PDBP` programming DSL syntactic constructs.

We also refer to programs as syntactic program descriptions.

There is no semantics associated with them.

Below are the first steps towards associating semantics with syntactic program descriptions.

 - Define the programming capabilities of type class `trait Program[>-->[+ _, - _]]` in `implicit object`'s that `extend trait Program[>-->[+ _, - _]]`. 
 - 
 - Implement programs by defining `object`'s, depending on those `implicit object`'s, that extend the `class`es the programs are defined in as members. 

We refer to an `implicit object` that is defined as above as an implicit program object.

We refer to a program that is implemented as above as a program implementation.

We refer to a main program that is implemented as above as a main program implementation.

Note that defining the computational capabilities of type class `trait Computation[C[+ _]]` in `implicit object`'s that `extends trait trait Computation[C[+ _]]` also defines the programming capabilities of `trait Program[Kleisli[C]]`.

We also refer to an `implicit object` that is defined as above as an implicit computation object or implicit kleisli program object.

We also refer to a kleisli program that is implemented as above as a kleisli program implementation.

Program implementations depend on implicit program objects use what we refer to as implicit dependency injection by `import`.

Implicit dependency injection by `import` is a very common design pattern for type clases.

## **`active` implicit `program`**

The first computation (and corresponding kleisli program) `implicit object` one can probably think of is the `active` `program` one defined below

```scala
package pdbp.program.instances.active

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.utils.active.functionUtils._

object implicits {

  implicit object program extends Computation[Active] with Program[`=>A`] {

    override private[pdbp] def result[Z]: Z => Active[Z] = `z=>az`

    override private[pdbp] def bind[Z, Y]
      : (Active[Z] && Thunk[Z => Active[Y]]) => Active[Y] = { (az, `z=>ay`) =>
      `z=>ay`.eval(az)
    }

  }

}
```

where the types `Active` and `` `=>A` `` are defined as follows

```scala
package pdbp.program.instances.types.active

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

object activeTypes {

  type Active[+Z] = Z

  type `=>A` = Kleisli[Active]

}
```

and where

  - `` `z=>az` ``

is the program you expect

```scala
package pdbp.program.instances.utils.active

import pdbp.program.instances.types.active.activeTypes._

object functionUtils {

  def `z=>az`[Z]: Z => Active[Z] = { z =>
    z
  }

}
```

Note that we use active as opposed to reactive.
We deal with reactive later.

Note that the `package` name refers how (active) and the `implicit object` name refers to what (programming capabilities).

For this simple `program` implicit program object we can already go ahead and run programs.

For other implicit program objects we need more machinery.


```scala
package examples.main.active.effectfulReadingAndWriting

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.active.implicits.program

import examples.mainPrograms.MainFactorial

import examples.utils.EffectfulUtils

object SimpleFactorialMain
    extends EffectfulUtils[`=>A`]()
    with MainFactorial[`=>A`]() {

  override val producer = effectfulReadBigIntFromConsole

  override val consumer = effectfulWriteFactorialOfBigIntToConsole

  def main(args: Array[String]): Unit = {

    import pdbp.program.runners.active.runner.run

    run(mainFactorial)

  }

}
```

where

```scala
package pdbp.program.runners.active

object runner {

  val run: (Unit => Unit) => Unit = { `u=>u` =>
    `u=>u`(())
  }

}
```

and where

```scala
package examples.utils

import pdbp.program.Program

class EffectfulUtils[>-->[- _, + _]: Program]
    extends pdbp.program.utils.EffectfulUtils[>-->] {

  lazy val effectfulReadBigIntFromConsole: Unit >--> BigInt =
    effectfulReadBigIntFromConsoleWithMessage("please type an integer")

  lazy val effectfulWriteFactorialOfBigIntToConsole: BigInt >--> Unit =
    effectfulWriteToConsoleWithMessageLine(
      "the factorial value of the integer is")

}
```

where

```scala
package pdbp.program.utils

import pdbp.program.Program

import pdbp.utils.effectfulFunctionUtils._

class EffectfulUtils[>-->[- _, + _]: Program] {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  def effectfulReadBigIntFromConsoleWithMessage(
      message: String): Unit >--> BigInt =
    function(effectfulReadBigIntFromConsoleWithMessageFunction(message))

  def effectfulWriteToConsoleWithMessageLine[Y](message: String): Y >--> Unit =
    function(effectfulWriteToConsoleWithMessageLineFunction(message))

}
```

where

```scala
package pdbp.utils

import scala.io.StdIn.readInt

object effectfulFunctionUtils {

  def effectfulReadBigIntFromConsoleWithMessageFunction(
      message: String): Unit => BigInt = { _ =>
    println(message)
    BigInt(readInt())
  }

  def effectfulWriteToConsoleWithMessageLineFunction[Y](
      message: String): Y => Unit = { y =>
    println(s"$message\n$y")
  }

}
```

Here are some details of `SimpleFactorialMain`

  - `object SimpleFactorialMain` extends `` trait `MainFactorial[`=>A`] ``.

  - `SimpleFactorialMain` uses implicit dependency injection by `import` of `program` that is a program implementation of type `` Program[`=>A`]  ``.
  
  - `` trait `MainFactorial[`=>A`]` `` has a member `object factorialObject` that extends `` `Factorial[`=>A`] ``.

  - `factorialObject` has a member `val factorial` that is a program implementation of type `` BigInt `=>A` BigInt ``.
 
  - `` trait `MainFactorial[`=>A`]` `` has a member  `lazy val mainFactorial` that is a main program implementation of type `` Unit `=>A` Unit ``.
 
  - `mainFactorial` uses members `effectfulReadIntFromConsole` resp. `effectfulWriteFactorialOfIntToConsole` from `class EffectUtils` to define `producer` resp. `consumer` of `trait `MainFactorial[>-->]`.

  - `mainFactorial` has type `` Unit `=>A` Unit ``, which is `Unit => Active[Unit]`, which is `Unit => Unit`

  - `main` uses `run` of type `(Unit => Unit) => Unit`

Note that there is a lot of `import` flexibility involved.

  - `import pdbp.program.active.implicits.program` defines the program implementation we use, and, as a consequence, which `factorial` program implementation and `mainFactorial` main program implementation we use.

  - `import pdbp.program.runners.active.runner.run` defines how to run the `mainFactorial` main program implementation.

Agreed, for `run` there is not really a lot of flexibility since the main program implementation meaning defines it's type.  

Let's try `factorial` with `10`.

```scala
[info] Running examples.main.active.effectfulReadingAndWriting.SimpleFactorialMain
please type an integer
10
the factorial value of the integer is
3628800
```

Let's try `factorial` with `100`.

```scala
[info] Running examples.main.active.effectfulReadingAndWriting.SimpleFactorialMain
please type an integer
100
the factorial value of the integer is
93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000
```

Let's try `factorial` with `1000` (using `sbt -J-Xss1m`).

```scala
[info] Running examples.main.active.effectfulReadingAndWriting.SimpleFactorialMain 
please type an integer
1000
[error] (run-main-2) java.lang.StackOverflowError
```

We have a problem. 

We have stack which overflow for the argument `1000` (ok, we can increase the stack size, but that's another story).  
 
The good news is that `mainFactorial` is a just a main program description and it is possible to arrange things such that running it uses the heap instead of the stack.

But we also have another problem.

I/O using `effectfulReadBigIntFromConsole` resp. `effectfulWriteFactorialOfBigIntToConsole` uses functions that execute effects in an effectful (a.k.a. as impure) way.

The good news is that it is possible to arrange things such that I/O uses programs that describe effects in an effectfree (a.k.a. as pure) way.

## **Natural transformations**

Note that `>-->[- _, + _]` is a binary type constructor.

Such type constructors can be transformed using natural transformations. 

Consider

```scala
package pdbp.naturalTransformation.binary

trait `~B~>`[`>-F->`[- _, + _], `>-T->`[- _, + _]] {

  def apply[Z, Y]: Z `>-F->` Y => Z `>-T->` Y

}
```

`` trait `~B~>` `` defines natural binary type constructor transformations.
`F` stands for from, `T` stands for to, and `B` stands for binary.

Note that C[+ _]` is a unnary type constructor.

Such type constructors can be transformed using natural transformations. 

Consider

```scala
package pdbp.naturalTransformation.unary

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.binary.`~B~>`

private[pdbp] trait `~U~>`[F[+ _], T[+ _]]
    extends `~B~>`[Kleisli[F], Kleisli[T]] {
  `f~u~t1` =>

  private[pdbp] def apply[Z](fz: F[Z]): T[Z]

  type T1 = T

  private[pdbp] def andThen[T2[+ _]](`t1~u~t2`: T1 `~U~>` T2): F `~U~>` T2 =
    new {
      override private[pdbp] def apply[Z](fz: F[Z]): T2[Z] =
        `t1~u~t2`(`f~u~t1`(fz))
    }

  private type `=>F` = Kleisli[F]

  private type `=>T` = Kleisli[T]

  override def apply[Z, Y]: Z `=>F` Y => Z `=>T` Y = { `z=>fy` =>
    `z=>fy` andThen apply
  }

}

```

`` trait `~U~>` `` defines natural unary type constructor transformations.
`F` stands for from, `T` stands for to, and `U` stands for unary.

Natural unary type constructor transformations are similar to functions 
 - they have an `apply` member, so that they can be applied, and
 - they have an `andThen` member, so that they can be composed.

The difference with functions is that they work at the unary type constructor level instead of at the type level.

For kleisli binary type constructor types, natural binary type constructor transformations can be defined in terms of natural unary type constructor transformations.

## **`ComputationTransformation`**

In this section we transform programs using program transformations.
More precisely we transform computations, and corresponding kleisli programs, using computation transformations.
Such transformations happen at the syntactic level.

Computation transformations are defined using natural unary type constructor transformations as follows

```scala
package pdbp.computation.transformation

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`
import pdbp.naturalTransformation.binary.`~B~>`

import pdbp.program.Program

import pdbp.computation.Computation

private[pdbp] trait ComputationTransformation[C[+ _]: Computation, T[+ _]]
    extends Computation[T]
    with Program[Kleisli[T]] {

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC}

  private[pdbp] val transform: C `~U~>` T

  override private[pdbp] def result[Z]: Z => T[Z] = { z =>
    transform(resultC(z))
  }

}

```

`trait ComputationTransformation` declares, using `C[+ _]: Computation`, to implicitly have the computational capabilites of the to be transformed computation `C`.
`implicitly[Computation[F]]`, is an evidence of those capabilities.
The computational capabilites of `C` that we need are made available using `import implicitComputation.{result => resultC}`.

Note that `trait ComputationTransformation` comes with a default definition for `result` in terms of `transform` and `resultC`.

We can use `implicitly.result`, an abbreviation of `implicitComputation.result` (`resultC`), as well.
For the type system it does not matter.

Computation transformations closely resemble monad transformers.

Monad transformers were introduced in [Monad Transformers and Modular Interpreters](http://haskell.cs.yale.edu/wp-content/uploads/2011/02/POPL96-Modular-interpreters.pdf). 

I have contributed to monad transformers myself by combining them with catamorpisms in [Using Catamorphisms, Subtypes and Monad Transformers for Writing Modular Functional Interpreters](http://citeseerx.ist.psu.edu/viewdoc/download;jsessionid=97555A49D9F56885C9EA225088EA73BA?doi=10.1.1.11.7093&rep=rep1&type=pdf).

## **`ProgramMeaning`**

In the previous sections we transformed programs using program transformations.
More precisely we transform computations, and corresponding kleisli programs, using computation transformations.
Such transformations happen at the syntactic level.

In this section we transform program implementations using program meanings.
More precisely we transform computation implementations, and corresponding kleisli program implemetantations, using computation meanings.
Such transformations happen at the semantic level.

For changing program semantics (e.g. using the heap instead of the stack for recursive programs) or extend programming syntax (e.g. for describing I/O instead of executing it), we us both transformations at the syntactic level and transformations at the semantic level.

Program meanings are defined using natural unary type constructor transformations as follows

Consider

```scala
package pdbp.program.meaning

import pdbp.program.Program

import pdbp.naturalTransformation.binary.`~B~>`

private[pdbp] trait ProgramMeaning[
    `>-P->`[- _, + _]: Program, `>-M->`[- _, + _]: Program] {

  private[pdbp] lazy val binaryTransformation: `>-P->` `~B~>` `>-M->`

  lazy val meaning: `>-P->` `~B~>` `>-M->` = binaryTransformation

}
```

`trait ProgramMeaning` has a `public` member, `meaning` that is a natural binary type constructor transformation that is an alias for a `private[pdbp]` member, `binaryTransformation`.

We refer to the `meaning` member of `trait ProgramMeaning` as a program meaning.

We refer to a program that is a meaning `meaning(program)` of a program implementation `program` as a meaning program.


## **`ComputationMeaning`**

Consider

```scala
package pdbp.computation.meaning

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.binary.`~B~>`
import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.program.meaning.ProgramMeaning

private[pdbp] trait ComputationMeaning[C[+ _]: Computation, M[+ _]: Computation]
    extends ProgramMeaning[Kleisli[C], Kleisli[M]] {

  private[pdbp] lazy val unaryTransformation: C `~U~>` M

  private type `=>C` = Kleisli[C]

  private type `=>M` = Kleisli[M]

  private[pdbp] override lazy val binaryTransformation: `=>C` `~B~>` `=>M` =
    unaryTransformation

}
```
`trait ComputationMeaning` has a `private[pdbp]` member, `unaryTransformation`. that is a natural unary type constructor transformation.

We refer to the `unaryTransformation` member of `trait ComputationMeaning` as a computation meaning.

We refer to a computation that is a computation meaning `unaryTransformation(computation)` of a computation `computation` as a meaning computation.

For kleisli programs, program meanings can, using `binaryTransformation`, be defined in terms of computation meanings.

We refer to program meanings of kleisli programs as kleisli program meanings.

## **`IdentityMeaning`**

The simplest computation meaning is `IdentityMeaning` below

```scala
package pdbp.computation.meaning

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

private[pdbp] trait IdentityComputationMeaning[C[+ _]: Computation]
    extends ComputationMeaning[C, C] {

  override private[pdbp] lazy val unaryTransformation: C `~U~>` C = new {
    override private[pdbp] def apply[Z](cz: C[Z]): C[Z] = {
      cz
    }
  }

}
```

## **`ImplicitComputationMeaningTransformation`**

Recall that `trait Computation[C[+ _]]` is a type class.
There is one unary type constructor, `C`, involved.
It is possible, using a combination of `[C: Computation]` and `implicitly[Computation[C]]`, to make use of the implicitly available computational capabilities of `C`.

`trait ComputationMeaning[C[+ _]: Computation, M[+ _]: Computation]` is not a type class.
There two unary type constructors, `C` and `M`, involved.
It is possible, using implicit functions, to make use of the implicitly available computation meaning of `C`.

Consider

```scala
package pdbp.computation.meaning.transformation

import pdbp.types.implicitFunctionType.`I=>`

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.meaning.ComputationMeaning

private[pdbp] trait ImplicitComputationMeaningTransformation[
    C[+ _]: Computation,
    M[+ _]: Computation,
    TC[+ _]: Computation,
    TM[+ _]: Computation]
    extends (ComputationMeaning[C, M] `I=>` ComputationMeaning[TC, TM])
    with ComputationMeaning[TC, TM] {

  // todo: is lazy maybe needed here?
  private[pdbp] implicit val implicitComputationMeaning: ComputationMeaning[C, M]

  override private[pdbp] lazy val unaryTransformation: TC `~U~>` TM =
    this(implicitComputationMeaning).unaryTransformation

}
```

`trait ImplicitComputationMeaningTransformation` declares a `implicit lazy val` of type `ComputationMeaning[C, M]` that allows us to define `unaryTransformation` of type `` TC `~U~>` TM `` in terms of `unaryTransformation` of type `` C `~U~>` M ``.

We can use `implicitly`, an abbreviation of `implicitly[ComputationMeaning[C, M]]` as well. 
For the type system it does not matter.

Below are the next steps towards associating semantics with syntactic program descriptions.

 - Define the `meaning` member of `trait ProgramMeaning` in `object`'s that `extend trait ProgramMeaning`.  
 - Use program meanings to naturally transform program implementations.

We refer to a program implementation that is transformed using a program meaning as a program implementation meaning. 

We refer to a main program implementation that is transformed using a program meaning as a main program implementation meaning. 

We refer to a meaning program of a program implementation as a meaning program implementation.

We refer to a meaning program of a main program implementation as a main meaning program implementation.

Although. since `trait ProgramMeaning` is not a type class, it is not necessary to define `implicit object`'s it is convenient to do so. 

## **`active.of.active.identity`**

The first computation meaning `implicit object` (and corresponding kleisli program meaning `implicit object`) is the identity meaning of active one defined below

```scala
package pdbp.program.meaning.active.of.active

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.IdentityComputationMeaning

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object identity
      extends IdentityComputationMeaning[Active]()
      with ComputationMeaning[Active, Active]()
      with ProgramMeaning[`=>A`, `=>A`]()

}}
```

Note that the `package` name reflects the usage of active rather than the `object` name. 

## **Running `mainFactorial` using `active.implicits.program`, `active.of.active.implicits.identity.meaning`, and `active.runner.run`, and using `effectfulReadBigIntFromConsole` and `effectfulWriteFactorialOfBigIntToConsole`**

Consider

```scala
package examples.main.active.effectfulReadingAndWriting

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.active.implicits.program

import examples.mainPrograms.MainFactorial

import examples.utils.EffectfulUtils

object FactorialMain
    extends EffectfulUtils[`=>A`]()
    with MainFactorial[`=>A`]() {

  override val producer = effectfulReadBigIntFromConsole

  override val consumer = effectfulWriteFactorialOfBigIntToConsole

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.active.implicits.identity.meaning

    import pdbp.program.runners.active.runner.run

    run(meaning(mainFactorial))

  }

}
```

Here are some extra details of `FactorialMain`

  - `main` uses implicit dependency injection by `import` of `identity.meaning` that transforms the program implementation `program` of type `` Program[`=>A`]  ``to a meaning program implementation of type `` Program[`=>A`]  ``.
 
  - `meanng(mainFactorial)` has type `` Unit `=>A` Unit ``, which is `Unit => Active[Unit]`, which is `Unit => Unit`

Note that there is, again a lot of `import` flexibility involved.

  - `import pdbp.program.active.implicits.program` defines the program implementation we use, and, as a consequence, which `factorial` program implementation and `mainFactorial` main program implementation we use.

  - `import pdbp.program.meaning.active.ofActive.implicits.identity.meaning` defines which program meaning we use, and, as a consequence, which main meaning program implementation `meaning(mainFactorial)` we use.
  
  - `import pdbp.program.runners.active.runner.run` defines how to run the `meaning(mainFactorial)` main meaning program implementation.


Let's try `factorial` with `10`.

```scala
[info] Running examples.main.active.effectfulReadingAndWriting.FactorialMain
please type an integer
10
the factorial value of the integer is
3628800
```

## **`FreeTransformation`**

The first computation transformation that we describe is `trait FreeTransformation`.
We use it to change the semantics of `factorial` from one using the stack to one using the heap.

```scala
package pdbp.computation.transformation.free

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object FreeTransformation {

  sealed trait Free[C[+ _], +Z]

  final case class Transform[C[+ _], +Z](cz: C[Z]) extends Free[C, Z]
  final case class Result[C[+ _], +Z](z: Z) extends Free[C, Z]
  final case class Bind[C[+ _], -Z, ZZ <: Z, +Y](
      fczz: Free[C, ZZ],
      `z=>fcy`: Thunk[Z => FreeTransformed[C][Y]])
      extends Free[C, Y]

  private[pdbp] type FreeTransformed[C[+ _]] = [+Z] => Free[C, Z]

}

import FreeTransformation._

private[pdbp] trait FreeTransformation[C[+ _]: Computation]
    extends ComputationTransformation[C, FreeTransformed[C]] {

  private type FTC = FreeTransformed[C]

  override private[pdbp] val transform: C `~U~>` FTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): FTC[Z] =
      Transform(cz)
  }

  override private[pdbp] def result[Z]: Z => FTC[Z] = { z =>
    Result(z)
  }

  override private[pdbp] def bind[Z, Y]
    : (FTC[Z] && Thunk[Z => FTC[Y]]) => FTC[Y] = { (ftcz, `z=>ftcy`) =>
    Bind(ftcz, `z=>ftcy`)
  }

}
```

`trait Free` is an abstract data type that 

either is a

  - `case class Result`, or a
  - `case class Bind` 

corresponding to the members 

  - `result` and 
  - `bind` 
  
of `trait Computation`.

or is a

  - `case class Transform`

corresponding to the member 

  - `transform` 
 
of `trait ComputationTransformation`,

`trait FreeTransformation` transforms 

  - a computation `C` to a computation `FreeTransformed[C]`,
  - the corresponding kleisli program `Kleisli[C]` to a kleisli program `Kleisli[FreeTransformed[C]]`. 

The definitions of `transform`, `result` and `bind` are trivial.
They construct a data structure on the heap.

 - `transform` constructs a `Transform`,
 - `result` constructs a `Result`,
 - `bind` constructs a `Bind`.

Think of `Free[C, Z]` as a free data type wrapped around `C` as described in [Data types a la carte](http://www.cs.ru.nl/~W.Swierstra/Publications/DataTypesALaCarte.pdf).

The data structure `Free[C, Z]` is used to define, using `Result` and `Bind`, the computational capabilities of `trait Computation[FreeTransformed[C]]`.
Note that `result` and `bind` do not use the computational capabilities of `C` at all

The data structure `Free[C, Z]` is also used to wrap, using `Transform`, the computational capabilities of `C` to suspended computational capabilities of type `Free[C, Z]`.
 
In a way `Free[C, Z]` is the most free implementation one can think of because there are no constraints involved.

Anyway, `Free[C, Z]` is an implementation, it is not a description. 
Some implementation choice has been taken, abeit the one to build a data structure without taking into account any constraints.

## **`active` implicit `freeProgram`**

The next computation `implicit object` (and corresponding kleisli program `implicit object`) is the `active` `freeProgram` one defined below

```scala
package pdbp.program.instances.active.free

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object freeProgram
      extends Computation[FreeActive]
      with Program[`=>FA`]
      with ComputationTransformation[Active, FreeActive]()
      with FreeTransformation[Active]()

}
```

where the types `FreeActive` and `` `=>FA` `` are defined as follows

```scala
package pdbp.program.instances.types.active.free

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.program.instances.types.active.activeTypes._

object freeActiveTypes {

  type FreeActive = FreeTransformed[Active]

  type `=>FA` = Kleisli[FreeActive]

}
```

## **`meaning.of.free.tailrecFolding.ComputationMeaningTransformation`**

Consider the tail recursive folding computation meaning transformation below

```scala
package pdbp.computation.meaning.of.free.tailrecFolding

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

private[pdbp] trait ComputationMeaningTransformation[
    C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[C,
                                                     M,
                                                     FreeTransformed[C],
                                                     M] {

  private type FTC = FreeTransformed[C]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[FTC, M] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    val implicitComputation = implicitly[Computation[M]]

    import implicitComputation.{result => resultM, bind => bindM}

    implicit object freeComputation
        extends FreeTransformation[C]()
        with ComputationTransformation[C, FTC]()

    object tailrecFoldingComputationMeaning
        extends ComputationMeaning[FTC, M]()
        with ProgramMeaning[Kleisli[FTC], Kleisli[M]]() {

      override private[pdbp] lazy val unaryTransformation: FTC `~U~>` M =
        new {
          override private[pdbp] def apply[Z](ftcz: FTC[Z]): M[Z] = ftcz match {
            case Transform(cz) =>
              `c~u~>m`(cz)
            case Result(z) =>
              resultM(z)
            case Bind(Transform(cy), y2ftcz) =>
              bindM(`c~u~>m`(cy), Thunk({ y =>
                apply(y2ftcz.eval(y))
              }))
            case Bind(Result(y), y2ftcz) =>
              apply(y2ftcz.eval(y))
            case Bind(Bind(ftcx, x2ftcy), y2ftcz) =>
              apply(Bind(ftcx, Thunk({ x =>
                Bind(x2ftcy.eval(x), y2ftcz)
              })))
            case any =>
              sys.error(
                s"Impossible, since, 'apply' eliminates the case for $any")
          }
        }

    }

    tailrecFoldingComputationMeaning

  }

}
```

Note that, for pattern matching, we use names like `y2ftcz` instead of `` `y=>ftcz` ``.

Also note that the `package` name refers to what (`of.free`) and how (`tailrecFolding`).

The method `apply` that defines `unaryTransformation` of `tailrecFoldingComputationMeaning` is a tail recursive folding of a computation of type `FTC[Z]`, a free data structure wrapping a computation of type `C[Z]`, to a meaning computation of type `M[Z]`. 

Note that the last `case` for `Bind` uses an associativity law of `bind`.
The left associated `Bind`'s are folded to right associated `Bind`'s. 

Note that we do not annotate `apply` with `@annotation.tailrec`. 
The compiler would complain because the call to `apply` in the second `case` is not in tail position.
Luckily, the compiler optimizes all other calls because they are all in tail position.

## **`active.of.free.tailrecFolding`**

The next computation meaning `implicit object` (and corresponding kleisli program meaning `implicit object`) is the tail recursive folding of free active one defined below

```scala
package pdbp.program.meaning.active.of.free

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation
import pdbp.computation.meaning.of.free.tailrecFolding.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.free.implicits.freeProgram

import pdbp.program.meaning.active.of.active.implicits.identity

object implicits {

  implicit object tailrecFolding
      extends ComputationMeaningTransformation[Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    FreeActive,
                                                    Active]()
      with ComputationMeaning[FreeActive, Active]()
      with ProgramMeaning[`=>FA`, `=>A`]() {

    override private[pdbp] implicit val implicitComputationMeaning
      : ComputationMeaning[Active, Active] =
      identity

  }

}
```

Note that the `package` name refers to what (`active.of.free`) and the `object` name refers to how (`tailrecFolding`).

## **Running `mainFactorial` using `active.free.implicits.freeProgram`, `active.of.free.implicits.tailrecFolding.meaning`, and `active.runner.run`, and using `effectfulReadBigIntFromConsole` and `effectfulWriteFactorialOfBigIntToConsole`**

Consider

```scala
package examples.main.active.tailrecursive.effectfulReadingAndWriting

import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.free.implicits.freeProgram

import examples.utils.EffectfulUtils

import examples.mainPrograms.MainFactorial

object FactorialMain
    extends EffectfulUtils[`=>FA`]
    with MainFactorial[`=>FA`]() {

  override val producer = effectfulReadBigIntFromConsole

  override val consumer = effectfulWriteFactorialOfBigIntToConsole

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.free.implicits.tailrecFolding.meaning

    import pdbp.program.runners.active.runner.run

    run(meaning(mainFactorial))

  }

}
```

Here are some details of `FactorialMain` using the heap that differ from the ones using the stack.

  - `FactorialMain` uses implicit dependency injection by `import` of `freeProgram` that is a program implementation of type `` Program[`=>FA`]  ``.

  - `main` uses implicit dependency injection by `import` of `tailrecFolding.meaning` that transforms the program implementation `freeProgram` of type `` Program[`=>FA`]  ``to a meaning program implementation of type `` Program[`=>A`]  ``.

  - `meanng(mainFactorial)` has type `` Unit `=>A` Unit ``, which is `Unit => Active[Unit]`, which is `Unit => Unit`

Note that there is, again a lot of `import` flexibility involved.

  - `import pdbp.program.instances.active.free.implicits.freeProgram` defines the program implementation we use, and, as a consequence, which `factorial` program implementation and `mainFactorial` main program implementation we use.

  - `import pdbp.program.meaning.active.of.free.implicits.tailrecFolding.meaning` defines which program meaning we use, and, as a consequence, which main meaning program implementation `meaning(mainFactorial)` we use.
  
  - `import pdbp.program.runners.active.runner.run` defines how to run the `meaning(mainFactorial)` main meaning program implementation.

Let's try `factorial` with `1000` (using `sbt -J-Xss1m`).

```scala
[info] Running examples.main.active.tailrecursive.effectfulReadingAndWriting.FactorialMain 
please type an integer
1000
the factorial value of the integer is
402387260077093773543702433923003985719374864210714632543799910429938512398629020592044208486969404800479988610197196058631666872994808558901323829669944590997424504087073759918823627727188732519779505950995276120874975462497043601418278094646496291056393887437886487337119181045825783647849977012476632889835955735432513185323958463075557409114262417474349347553428646576611667797396668820291207379143853719588249808126867838374559731746136085379534524221586593201928090878297308431392844403281231558611036976801357304216168747609675871348312025478589320767169132448426236131412508780208000261683151027341827977704784635868170164365024153691398281264810213092761244896359928705114964975419909342221566832572080821333186116811553615836546984046708975602900950537616475847728421889679646244945160765353408198901385442487984959953319101723355556602139450399736280750137837615307127761926849034352625200015888535147331611702103968175921510907788019393178114194545257223865541461062892187960223838971476088506276862967146674697562911234082439208160153780889893964518263243671616762179168909779911903754031274622289988005195444414282012187361745992642956581746628302955570299024324153181617210465832036786906117260158783520751516284225540265170483304226143974286933061690897968482590125458327168226458066526769958652682272807075781391858178889652208164348344825993266043367660176999612831860788386150279465955131156552036093988180612138558600301435694527224206344631797460594682573103790084024432438465657245014402821885252470935190620929023136493273497565513958720559654228749774011413346962715422845862377387538230483865688976461927383814900140767310446640259899490222221765904339901886018566526485061799702356193897017860040811889729918311021171229845901641921068884387121855646124960798722908519296819372388642614839657382291123125024186649353143970137428531926649875337218940694281434118520158014123344828015051399694290153483077644569099073152433278288269864602789864321139083506217095002597389863554277196742822248757586765752344220207573630569498825087968928162753848863396909959826280956121450994871701244516461260379029309120889086942028510640182154399457156805941872748998094254742173582401063677404595741785160829230135358081840096996372524230560855903700624271243416909004153690105933983835777939410970027753472000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
```

We do not have stack which overflow for the argument `1000` any more since we use the heap now together with tail recursion (ok, we can have an out of memory exception, but that's another story). 

## **`Reading` and `Writing`**

`trait Program` describes the basic programming capabilities. 

`trait Reading` and `trait Writing` describe I/O programming capabilities.

Consider

```scala
package pdbp.program.reading

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk

import pdbp.program.Function
import pdbp.program.Composition

trait Reading[R, >-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] val `u>-->r`: Unit >--> R

  def read[Z]: Z >--> R =
    seqCompose(`z>-->u`, Thunk(`u>-->r`))

}
``` 

`` `u>-->r` `` is a program without arguments that yields result of type `R`.

We also write that `` `u>-->r` `` is a producer of a result of type `R`. 
We also write that `` `u>-->r` `` produces a value of type `R`. 
We also write that `` `u>-->r` `` reads a value of type `R`. 

Note that `` `u>-->r` `` is `private[pdbp]`.

`read` as a program that transforms any argument to a result of type `R`.

We also write that `read`  produces a result of type `R`. 
We also write that `read` reads a value of type `R`. 

Note that `read` is `public` (the default in `Dotty`), `read` is part of the `PDBP` programming DSL.

Consider

```scala
package pdbp.program.writing

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.writable.Writable

import pdbp.program.Function
import pdbp.program.Composition
import pdbp.program.Construction

trait Writing[W: Writable, >-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] & Construction[>-->] =>

  private[pdbp] val `w>-->u`: W >--> Unit

  def write[Z]: (Z => W) `I=>` Z >--> Unit =
    seqCompose(function(implicitly), Thunk(`w>-->u`))

  def writing[Z, Y, X]
    : ((Z && Y) => X) => (Z >--> Y) => ((X => W) `I=>` Z >--> Y) = {
    `(z&&y)=>x` => `z>-->y` =>
      val `(z&&y)>-->x` = function(`(z&&y)=>x`)
      val `z>-->(x&&y)` =
        `let` {
          `z>-->y`
        } `in` {
          `let` {
            `(z&&y)>-->x`
          } `in` {
            `(z&&y&&x)>-->(x&&y)`
          }
        }
      seqCompose(seqCompose(`z>-->(x&&y)`, Thunk(left(write))),
                 Thunk(`(u&&y)>-->y`))
  }

}
```

 where

 - `(z&&y&&x)>-->(x&&y)`
 - `(u&&y)>-->y`

 are the programs you expect

```scala
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.functionUtils._
import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._
import pdbp.utils.productAndSumUtils._

trait Function[>-->[- _, + _]] {

  def `(z&&y&&x)>-->(x&&y)`[Z, Y, X]: (Z && Y && X) >--> (X && Y) =
    function(`(z&&y&&x)=>(x&&y)`)

  def `(u&&y)>-->y`[Y]: (Unit && Y) >--> Y =
    `(z&&y)>-->y`[Unit, Y]

}
```

where

```scala
package pdbp.utils

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

object productUtils {

  def `(z&&y&&x)=>(x&&y)`[Z, Y, X]: (Z && Y && X) => (X && Y) = {
    case ((_, y), x) => (x, y)
  }
}  
```

`Writable` is described later.

`` `w>-->u` `` as a program with a writable argument that yields no result.

We also write that `` `w>-->u` `` is a consumer of a writable argument of type `W`. 
We also write that `` `w>-->u` `` consumes a writable argument of type `W`. 
We also write that `` `w>-->u` `` writes a value of type `W`. 

Note that `` `w>-->u` `` is `private[pdbp]`.

`write` as a program with an implicitly writable argument that yields no result.

We also write that `write` consumes an implicitly writable argument
We also write that `write` writes an implicitly writable value.

Note that `write` is `public` (the default in `Dotty`), `write` is part of the `PDBP` programming DSL.

`writing` wraps a program of type `Z >--> Y` into a program of type `Z >--> Y` that also writes an implicitly writable value of type `X` that is the result of a function of type `(Z && Y) => X`.

Note that the definition of `write` uses `implicitly[Z => W]` (we can also use an abbreviation `implicitly`) an evidence that the argument of `write` of type `Z` can implicitly be converted to a writable value of type `W`.

## **`Writable`**

Consider

```scala
package pdbp.writable

import pdbp.types.const.constType._

import pdbp.computation.Lifting

trait Writable[W] extends Startable[W] with Appendable[W] with Lifting[Const[W]]
```

where

```scala
package pdbp.writable

import pdbp.types.const.constType._

import pdbp.computation.ObjectLifting

private[pdbp] trait Startable[W] extends ObjectLifting[Const[W]] {

  private[pdbp] val start: W

  override private[pdbp] def lift0[Z]: Z => W = { _ =>
    start
  }

}
```

and

```scala
package pdbp.writable

import pdbp.types.product.productType._
import pdbp.types.const.constType._

import pdbp.computation.OperatorLifting

private[pdbp] trait Appendable[W] extends OperatorLifting[Const[W]] {

  private[pdbp] val append: W && W => W

  override private[pdbp] def lift2[Z, Y, X]
    : ((Z && Y) => X) => ((W && W) => W) = { _ =>
    append
  }

}
```

The relationship with `Lifting` is defined in terms of the type `Const` below

```scala
package pdbp.types.const

object constType {

  type Const[X] = [+Z] => X

}
```

`trait Writable` corresponds to monoids. 

In 2008, Conor Mc Bride and Ross Paterson used idioms in `Haskell` and describe how monoids can be seen as as phantom idioms in 
[idioms](http://strictlypositive.org/Idiom.pdf), 

## **`ReadingTransformation` and `WritingTransformation`**

The next computation transformations that we describe are `trait ReadingTransformation` and `trait WritingTransformation`.

Consider

```scala
package pdbp.computation.transformation.reading

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object ReadingTransformation {

  private[pdbp] type ReadingTransformed[R, C[+ _]] = [+Z] => R `I=>` C[Z]

}

import ReadingTransformation._

private[pdbp] trait ReadingTransformation[R, C[+ _]: Computation]
    extends ComputationTransformation[C, ReadingTransformed[R, C]]
    with Reading[R, Kleisli[ReadingTransformed[R, C]]] {

  private type RTC = ReadingTransformed[R, C]
  private type `=>RTC` = Kleisli[RTC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC, bind => bindC}

  override private[pdbp] val transform: C `~U~>` RTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): RTC[Z] = {
      cz
    }
  }

  override private[pdbp] def bind[Z, Y]
    : (RTC[Z] && Thunk[Z => RTC[Y]]) => RTC[Y] = { (rtcz, `z>=rtcy`) =>
    bindC(rtcz, Thunk({ z =>
      `z>=rtcy`.eval(z)
    }))
  }

  override private[pdbp] val `u>-->r`: Unit `=>RTC` R = { _ =>
    resultC(implicitly[R])
  }

}
```

The types `RTC` and `` `=>RTC` ``, defined using `` `I=>` ``, indicate that the an implicitly available global value `implicitly[R]` is available. 
In fact, in `` `u>-->r` `` we use it as `implicitly[R]` (we can also use it as the abbreviation `implicitly`).

Note that there are two implicit evidences involved:`implicitly[R]` and `implicitly[Computation[C]]`. 

You may wonder how on earth it is possible that the definitions above are so simple. 
The magic of implicit function types is that the compiler can turn value types into implicit function types whenever it expects them to be, based upon available type information.

By the way, the code above is a typical example where we push the limits of the `Dotty` type system.
At a certain moment in time the `Dotty` type inferencer crashed for `transform`.
I created an issue, [#5212](https://github.com/lampepfl/dotty/issues/5212), for it, and [Guillaume Martres](https://github.com/smarter) fixed it.
I am proud that, since then, `PDBP` has been added to the `Dotty` [community build](https://github.com/lampepfl/dotty-community-build/pull/36).

Consider

```scala
package pdbp.computation.transformation.writing

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.writable.Writable

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.Program
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object WritingTransformation {

  private[pdbp] type WritingTransformed[W, C[+ _]] = [+Z] => C[W && Z]

}

import WritingTransformation._

private[pdbp] trait WritingTransformation[W: Writable, C[+ _]: Computation]
    extends ComputationTransformation[C, WritingTransformed[W, C]]
    with Writing[W, Kleisli[WritingTransformed[W, C]]] {

  private type WTC = WritingTransformed[W, C]
  private type `=>WTC` = Kleisli[WTC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{bind => bindC, result => resultC}

  private val implicitWritable = implicitly[Writable[W]]

  import implicitWritable._

  override private[pdbp] val transform: C `~U~>` WTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): WTC[Z] =
      bindC(cz, Thunk({ z =>
        resultC((start, z))
      }))
  }

  override private[pdbp] def bind[Z, Y]
    : (WTC[Z] && Thunk[Z => WTC[Y]]) => WTC[Y] = { (wtcz, `z=>wtcy`) =>
    bindC(wtcz, Thunk({ (leftW, z) =>
      bindC(`z=>wtcy`.eval(z), Thunk({ (rightW, y) =>
        resultC(append(leftW, rightW), y)
      }))
    }))
  }

  override private[pdbp] val `w>-->u`: W `=>WTC` Unit = { w =>
    resultC((w, ()))
  }

}
```

Note that `transform` resp. `bind` make use of `start` resp. `append`.

Note that, again, there are two implicit evidences involved: `implicitly[Writable[W]]` and `implicitly[Computation[C]]`.

This is a recurring theme.
We avoid using the abbreviation `implicitly` (also when using it does not lead to ambiguity) and work with `private val`'s instead.
 
## **`ComputationReadingTransformation` and `ComputationWritingTransformation`**

When using computation transformers to enhance computations (and corresponding kleisli programs) with new computational capabilities (and corresponding programming capabilities) we need to take care that the already existing computational capabilities (and corresponding programming capabilities) are transformed to computational capabilities (and corresponding programming capabilities) of the transformed computations (and corresponding kleisli programs).

For some programming capabilities this can be done in a way that only depends on `transform`.
Reading, using `` `u>-->r` ``, and writing, using `` `w>-->u` ``, are two of them, as shown below.

```scala
package pdbp.computation.transformation.reading

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationReadingTransformation[
    R, 
    C[+ _]: Computation
          : [C[+ _]] => Reading[R, Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with Reading[R, Kleisli[T]] {

  private val implicitReading: Reading[R, Kleisli[C]] =
    implicitly[Reading[R, Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] val `u>-->r`: Unit `=>T` R = 
    transform(implicitReading.`u>-->r`)

}
```

and

```scala
package pdbp.computation.transformation.writing

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationWritingTransformation[
    W, 
    C[+ _]: Computation
          : [C[+ _]] => Writing[W, Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with Writing[W, Kleisli[T]] {

  private val implicitWriting: Writing[W, Kleisli[C]] =
    implicitly[Writing[W, Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] val `w>-->u`: W `=>T` Unit = 
    transform(implicitWriting.`w>-->u`)

}
```

## **`ReadingWritingTransformation`**

Computation transformations can be composed to enhance computations with the new programming capabilities of both of them.

Consider

```scala
package pdbp.computation.transformation.writing.reading

import pdbp.types.implicitFunctionType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.writable.Writable

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.computation.transformation.writing.ComputationWritingTransformation

private[pdbp] trait ReadingWritingTransformation[
    R, 
    W: Writable, 
    C[+ _]: Computation
          : [C[+ _]] => Writing[W, Kleisli[C]]]
    extends ReadingTransformation[R, C]
    with ComputationWritingTransformation[W, C, ReadingTransformed[R, C]]
```

For `trait ReadingWritingTransformation` we do not have to write any code since `trait ComputationWritingTransformation` does all the heavy lifting.

You may ask yourself why we go for `ReadingWritingTransformation` while we could go for `WritingReadingTransformation` instead.

It is a choice.

Note that computation transformation composition is not commutative.
You can argue that it a disadvantage that you do not have the liberty to compose computation transformations in any possible order you want.
You can argue that [every disadvantage has it's advantage](https://en.wikiquote.org/wiki/Johan_Cruyff) because [Constraints Liberate, Liberties Constrain](https://www.youtube.com/watch?v=GqmsQeSzMdw).

Up to you to decide.

## **`active` `ReadingProgram` and `WritingProgram`**

The next computation `trait`'s (and corresponding kleisli programs `trait`'s) are the `active` `ReadingProgram` and `WritingProgram` ones defined below

```scala
package  pdbp.program.instances.active.reading

import pdbp.program.Program
import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

private[pdbp] trait ReadingProgram[R]
    extends Computation[ReadingActive[R]]
    with Program[`=>RA`[R]]
    with Reading[R, `=>RA`[R]]
    with ComputationTransformation[Active, ReadingActive[R]]
    with ReadingTransformation[R, Active]
```

and

```scala
package pdbp.program.instances.active.writing

import pdbp.writable.Writable

import pdbp.program.Program
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.WritingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._

private[pdbp] trait WritingProgram[W: Writable]
    extends Computation[WritingActive[W]]
    with Program[`=>WA`[W]]
    with Writing[W, `=>WA`[W]]
    with ComputationTransformation[Active, WritingActive[W]]
    with WritingTransformation[W, Active]
```

where the types `ReadingActive` and `` `=>RA` `` are defined as follows

```scala
package pdbp.program.instances.types.active.reading

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.program.instances.types.active.activeTypes._

object readingActiveTypes {

  type ReadingActive[R] = ReadingTransformed[R, Active]

  type `=>RA`[R] = Kleisli[ReadingActive[R]]

}
```

and where the types `WritingActive` and `` `=>WA` `` are defined as follows

```scala
package pdbp.program.instances.types.active.writing

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.computation.transformation.writing.WritingTransformation._

import pdbp.program.instances.types.active.activeTypes._

object writingActiveTypes {

  type WritingActive[W] = WritingTransformed[W, Active]

  type `=>WA`[W] = Kleisli[WritingActive[W]]

}
```

Note that we did not define `implicit object`'s because 
  - reading using `trait Reading[R, >-->[- _, + _]]`, and 
  - writing using `trait Writing[W: Writable, >-->[- _, + _]]`
are generic programming capabilities.

## **`active` implicit `BigInt` `readingProgram`**

The next computation `implicit object` (and corresponding kleisli program `implicit object`) is the `active` `BigInt` `readingProgram` one defined below

```scala
package pdbp.program.instances.active.reading.bigint

import pdbp.program.Program
import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.instances.active.reading.ReadingProgram

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object readingProgram
      extends ReadingProgram[BigInt]()
      with Computation[ReadingActive[BigInt]]()
      with Program[`=>RA`[BigInt]]()
      with Reading[BigInt, `=>RA`[BigInt]]()
      with ComputationTransformation[Active, ReadingActive[BigInt]]()
      with ReadingTransformation[BigInt, Active]()

}
```

Now that we substitute `BigInt` for `R`, we can define an `implicit object`.

## **`active` implicit `ToConsoleWriting` `writingProgram`**

The next computation `implicit object` (and corresponding kleisli program `implicit object`) is the `active` `ToConsoleWriting` `writingProgram` one defined below

```scala
package pdbp.program.instances.active.writing.toConsoleWriting

import pdbp.program.Program
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.WritingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._

import pdbp.program.instances.active.writing.WritingProgram

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object writingProgram
      extends WritingProgram[ToConsoleWriting]()
      with Computation[WritingActive[ToConsoleWriting]]()
      with Program[`=>WA`[ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>WA`[ToConsoleWriting]]()
      with ComputationTransformation[Active, WritingActive[ToConsoleWriting]]()
      with WritingTransformation[ToConsoleWriting, Active]()

}
```

where

```scala
package pdbp.writable.instances.toConsoleWriting.types

case class ToConsoleWriting(effect: Unit => Unit)
```

and where

```scala
package pdbp.writable.instances.toConsoleWriting

import pdbp.types.product.productType._

import pdbp.writable.Writable

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

object implicits {

  implicit object toConsoleWritingWritable extends Writable[ToConsoleWriting] {

    override private[pdbp] val start: ToConsoleWriting =
      ToConsoleWriting { _ =>
        ()
      }

    override private[pdbp] val append
      : (ToConsoleWriting && ToConsoleWriting) => ToConsoleWriting = {
      (first, second) =>
        ToConsoleWriting { _ =>
          first.effect(())
          second.effect(())
        }
    }

  }

}
```

`ToConsoleWriting` wraps a to console writing `effect` of type `Unit => Unit` in a `case class` data structure.


`toConsoleWritingWritable` is a first writable `implicit object`. 
It's `start` wraps no effects at all and it's `append` wraps sequentially executed unwrapped effects.

Note that we describe the effects rather than executing them.

Also note that `ToConsoleWriting` is not very typeful. 
We define other, more typeful writable `implicit object`'s later.

## **`active` `ReadingWritingProgram`**

The next computation `trait` (and corresponding kleisli programs `trait`) is the `active` `ReadingWritingProgram` one defined below

```scala
package pdbp.program.active.writing.reading

import pdbp.writable.Writable

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.reading.ReadingWritingTransformation

import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

private[pdbp] trait ReadingWritingProgram[R, W: Writable]
    extends Computation[ReadingWritingActive[R, W]]
    with Program[`=>RWA`[R, W]]
    with Reading[R, `=>RWA`[R, W]]
    with Writing[W, `=>RWA`[R, W]]
    with ComputationTransformation[WritingActive[W], ReadingWritingActive[R, W]]
    with ReadingWritingTransformation[R, W, WritingActive[W]]
```

where the types `ReadingWritingActive` and `` `=>RWA` `` are defined as follows

```scala
package pdbp.program.instances.types.active.writing.reading

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.program.instances.types.active.writing.writingActiveTypes._

object readingWritingActiveTypes {

  type ReadingWritingActive[R, W] = ReadingTransformed[R, WritingActive[W]]

  type `=>RWA`[R, W] = Kleisli[ReadingWritingActive[R, W]]

}
```

## **`active` implicit `ToConsoleWriting` `BigInt` `readingWritingProgram`**

The next computation `implicit object` (and corresponding kleisli program `implicit object`) is the `active` `ToConsoleWriting` `BigInt` `readingWritingProgram` one defined below

```scala
package pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation
import pdbp.computation.transformation.writing.reading.ReadingWritingTransformation

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.writing.reading.ReadingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram

object implicits {

  implicit object readingWritingProgram
      extends ReadingWritingProgram[BigInt, ToConsoleWriting]()
      with Computation[ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with Program[`=>RWA`[BigInt, ToConsoleWriting]]()
      with Reading[BigInt, `=>RWA`[BigInt, ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>RWA`[BigInt, ToConsoleWriting]]()
      with ComputationTransformation[
        WritingActive[ToConsoleWriting],
        ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ReadingTransformation[BigInt, WritingActive[ToConsoleWriting]]()
      with ComputationWritingTransformation[
        ToConsoleWriting,
        WritingActive[ToConsoleWriting],
        ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ReadingWritingTransformation[BigInt,
                                        ToConsoleWriting,
                                        WritingActive[ToConsoleWriting]]()
}
```

## **`meaning.of.reading.readingImplicit.ComputationMeaningTransformation`**

Consider the implicit value reading computation meaning transformation below

```scala
package pdbp.computation.meaning.of.reading.reading

import pdbp.types.implicitFunctionType.`I=>`
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

private[pdbp] trait ComputationMeaningTransformation[
    R, C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[C,
                                                     M,
                                                     ReadingTransformed[R, C],
                                                     ReadingTransformed[R, M]] {

  private type RTC = ReadingTransformed[R, C]
  private type RTM = ReadingTransformed[R, M]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[RTC, RTM] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    implicit object readingComputation
        extends ReadingTransformation[R, C]()
        with ComputationTransformation[C, RTC]()

    implicit object readingComputationMeaning
        extends ReadingTransformation[R, M]()
        with ComputationTransformation[M, RTM]()

    object implicitValueReadingComputationMeaning
        extends ComputationMeaning[RTC, RTM]()
        with ProgramMeaning[Kleisli[RTC], Kleisli[RTM]]() {

      override private[pdbp] lazy val unaryTransformation: RTC `~U~>` RTM =
        new {
          override private[pdbp] def apply[Z](rtcz: RTC[Z]): RTM[Z] =
            `c~u~>m`(rtcz(implicitly[R]))
        }

    }

    implicitValueReadingComputationMeaning

  }

}
```

The method `apply` that defines `unaryTransformation` of `implicitValueReadingComputationMeaning` uses `rtcz(implicitly[R])` to read the implicit value. 

## **`meaning.of.writing.toConsoleWriting.effectExecuting`**

Consider the to console writing effect executing computation meaning transformation below

```scala
package pdbp.computation.meaning.of.writing.toConsoleWriting.effectExecuting

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.WritingTransformation
import pdbp.computation.transformation.writing.WritingTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

private[pdbp] trait ComputationMeaningTransformation[
    C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[
      C,
      M,
      WritingTransformed[ToConsoleWriting, C],
      M] {

  private type WTC = WritingTransformed[ToConsoleWriting, C]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[WTC, M] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    val implicitComputation = implicitly[Computation[C]]

    import implicitComputation.{result => resultC, bind => bindC}

    implicit object writingComputation
        extends WritingTransformation[ToConsoleWriting, C]()
        with ComputationTransformation[C, WTC]()
        with Writing[ToConsoleWriting, Kleisli[WTC]]()

    object toConsoleWritingEffectExecutingComputationMeaning
        extends ComputationMeaning[WTC, M]()
        with ProgramMeaning[Kleisli[WTC], Kleisli[M]]() {

      override private[pdbp] lazy val unaryTransformation: WTC `~U~>` M =
        new {
          override private[pdbp] def apply[Z](wtcz: WTC[Z]): M[Z] =
            `c~u~>m`(bindC(wtcz, Thunk({
              case (ToConsoleWriting(effect), z) =>
                effect(())
                resultC(z)
            })))
        }

    }

    toConsoleWritingEffectExecutingComputationMeaning

  }

}
```

The method `apply` that defines `unaryTransformation` of `toConsoleWritingEffectExecutingComputationMeaning` uses `effect(())` to execute the to console writing effect. 

Note that, in contrast with the general implicit value reading computation meaning transformation, the console writing effect executing computation meaning transformation is specific for `ToConsoleWriting`.

## **`active.of.reading.bigint.reading`**

The next computation meaning `implicit object` (and corresponding kleisli program meaning `implicit object`) is the reading of big int reading active one defined below

```scala
package pdbp.program.meaning.active.of.reading.bigint

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation
import pdbp.computation.meaning.of.reading.readingImplicit.ComputationMeaningTransformation

import  pdbp.program.instances.types.active.activeTypes._
import  pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.reading.bigint.implicits.readingProgram

import pdbp.program.meaning.active.of.active.implicits.identity
import pdbp.program.meaning.active.of.reading.ReadingMeaning

object implicits {

  implicit object readingImplicit
      extends ReadingMeaning[BigInt] 
      with ComputationMeaningTransformation[BigInt, Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    ReadingActive[BigInt],
                                                    ReadingActive[BigInt]]()
      with ComputationMeaning[ReadingActive[BigInt], ReadingActive[BigInt]]()
      with ProgramMeaning[`=>RA`[BigInt], `=>RA`[BigInt]]()

}
```

where

```scala
package pdbp.program.meaning.active.of.reading

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.of.reading.readingImplicit.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.meaning.active.of.active.implicits.identity

trait ReadingMeaning[R]
    extends ComputationMeaningTransformation[R, Active, Active]
    with ComputationMeaning[ReadingActive[R], ReadingActive[R]] {

  override private[pdbp] implicit val implicitComputationMeaning
    : ComputationMeaning[Active, Active] =
    identity

}
```

## **`active.of.writing.toConsoleWriting.effectExecuting`**

The next computation meaning `implicit object` (and corresponding kleisli program meaning `implicit object`) is the effect executing of to console writing active one defined below

```scala
package pdbp.program.meaning.active.of.writing.toConsoleWriting

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation
import pdbp.computation.meaning.of.writing.toConsoleWriting.effectExecuting.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram

import pdbp.program.meaning.active.of.active.implicits.identity

object implicits {

  implicit object effectExecuting
      extends ComputationMeaningTransformation[Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    WritingActive[
                                                      ToConsoleWriting],
                                                    Active]()
      with ComputationMeaning[WritingActive[ToConsoleWriting], Active]()
      with ProgramMeaning[`=>WA`[ToConsoleWriting], `=>A`]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      identity

  }

}
```

## **`active.of.writing.toConsoleWriting.reading.bigint.readingImplicitEffectExecuting`**

The next computation meaning `implicit object` (and corresponding kleisli program meaning `implicit object`) is the reading, effect executing of to console writing big int reading active one defined below

```scala
package pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.computation.meaning.of.reading.readingImplicit.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

import pdbp.program.instances.active.reading.bigint.implicits.readingProgram

import pdbp.program.meaning.active.of.active.implicits.identity

import pdbp.program.meaning.active.of.writing.toConsoleWriting.implicits.effectExecuting

object implicits {

  implicit object readingImplicitEffectExecuting
      extends ComputationMeaningTransformation[BigInt,
                                               WritingActive[ToConsoleWriting],
                                               Active]()
      with ImplicitComputationMeaningTransformation[
        WritingActive[ToConsoleWriting],
        Active,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ComputationMeaning[ReadingWritingActive[BigInt, ToConsoleWriting],
                              ReadingActive[BigInt]]()
      with ProgramMeaning[`=>RWA`[BigInt, ToConsoleWriting], `=>RA`[BigInt]]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      effectExecuting

  }

}
```

## **Running `mainFactorial` using `active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram`, `active.of.writing.toConsoleWriting.reading.bigint.readingImplicitEffectExecuting.meaning`, and `active.reading.runner.run`, and using `readingWritingProgram.read` and `readingWritingProgram.write`**


Consider

```scala
package examples.main.active.writing.toConsoleWriting.reading.bigint

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

import examples.mainPrograms.MainFactorial

object FactorialMain
    extends MainFactorial[`=>RWA`[BigInt, ToConsoleWriting]]() {

  override val producer = readingWritingProgram.read

  import examples.utils.implicits.convertFactorialOfBigIntReadToToConsoleWriting

  override val consumer = readingWritingProgram.write

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.implicits.readingImplicitEffectExecuting.meaning

    import pdbp.program.runners.active.reading.runner.run

    import examples.utils.implicits.bigIntEffectfullyReadFromConsole

    run(meaning(mainFactorial))

  }

}
```

where, for reading and writing we use

```scala
package examples.utils

import pdbp.utils.effectfulReadUtils._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.utils.toConsoleWritingUtils._

object implicits {

  implicit val bigIntEffectfullyReadFromConsole: BigInt =
    bigIntEffectfullyReadFromConsoleWithMessage(
      "please type an integer to read")

  implicit val convertFactorialOfBigIntReadToToConsoleWriting
    : BigInt => ToConsoleWriting =
    toToConsoleWritingWithMessageLine(
      "the factorial value of the integer read is"
    )

}
```

where

```scala
package pdbp.utils

import pdbp.utils.effectfulFunctionUtils._

object effectfulReadUtils {

  def bigIntEffectfullyReadFromConsoleWithMessage(message: String): BigInt =
    effectfulReadBigIntFromConsoleWithMessageFunction(message)(())

}
```

and

```scala
package pdbp.writable.instances.toConsoleWriting.utils

import pdbp.utils.effectfulFunctionUtils._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

object toConsoleWritingUtils {

  def toToConsoleWritingWithMessageLine[Z](
      message: String): Z => ToConsoleWriting = { z =>
    ToConsoleWriting({ _ =>
      effectfulWriteToConsoleWithMessageLineFunction(message)(z)
    })
  }

}

```

and where, for running we use

```scala
package pdbp.program.runners.active.reading

import pdbp.types.implicitFunctionType._

object runner {

  def run[R]: (Unit => (R `I=>` Unit)) => R `I=>` Unit = { `u=>(ri=>u)` =>
    `u=>(ri=>u)`(())
  }

}
```


Here are some details of `FactorialMain` using effectfree I/O that differ from the ones using effectful I/O.

  - `FactorialMain` uses implicit dependency injection by `import` of `readingWritingProgram` that is a program implementation of type `` Program[`=>RWA`]  ``.

  - `FactorialMain` uses `readingWritingProgram.read` to describe the effect of producing a `BigInt` rather than executing it.

  - `FactorialMain` uses `readingWritingProgram.write` to describe the effect of consuming the `factorial` of the `BigInt` read rather than executing it.

  - `FactorialMain` uses implicit dependency injection by `import` of `convertFactorialOfBigIntReadToToConsoleWriting` convert the `factorial` of the `BigInt` read to a to console writing effect description.

  - `main` uses implicit dependency injection by `import` of `readingImplicitEffectExecuting.meaning` that transforms the program implementation `readingWritingProgram` of type `` Program[`=>RWA`]  ``to a meaning program implementation of type `` Program[`=>RA`]  ``.

  - `meanng(mainFactorial)` has `` Unit `=>RA` Unit ``, which is `Unit => ReadingActive[Unit]`, which is `` Unit => (BigInt `I=>` Unit) ``

  - `main` uses `run` of type `` (Unit => (R `I=>` Unit)) => R `I=>` Unit ``

  - `run(meaning(mainFactorial))` has type `` BigInt `I=>` Unit ``

  - `main` uses implicit dependency injection by `import` of `bigIntEffectfullyReadFromConsole` to provide an implicit `BigInt` for `run(meaning(mainFactorial))`


Note that there is, again a lot of `import` flexibility involved.

  - `pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram` defines the program implementation we use, and, as a consequence, which `factorial` program implementation and `mainFactorial` main program implementation we use.

  - `import examples.utils.implicits.bigIntEffectfullyReadFromConsole` defines the way we read a `BigInt`.

  - `import examples.utils.implicits.convertFactorialOfBigIntReadToToConsoleWriting` defines the way we write the `factorial` of the `BigInt` read.

  - `import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.implicits.readingImplicitEffectExecuting.meaning` defines which program meaning we use, and, as a consequence, which main meaning program implementation `meaning(mainFactorial)` we use.
  
  - `import pdbp.program.runners.active.reading.runner.run` defines how to run the `meaning(mainFactorial)` main meaning program implementation.


Note that, of course, eventually we do effectful stuff.
Otherwise our program wouls not be useful in the first place.

The point we want to make is that we pushed I/O to the boundaries of the code.

 - For reading, we postpone defining the implicit `BigInt` of `read` of `readingWritingProgram` to the line before using it and we can do this by `import`ing `intEffectfullyReadFromConsole` that executes the reading from console effect.
 - For writing, we postpone defining the implicit `BigInt => ToConsoleWriting` of `write` of `readingWritingProgram` to the line before using it and we can do this by `import`ing `convertFactorialOfBigIntReadToToConsoleWriting` to let the meaning of the implementation of `write` execute the to console writing effect.


Let's try `factorial` with `100`


```scala
[info] Running examples.main.active.writing.toConsoleWriting.reading.bigint.FactorialMain 
please type an integer to read
10
the factorial value of the integer read is
3628800
```

## **Combining tail recursion with reading and writing**

We have dealt two problems separately

 - stack overflow
   - using `FreeTransformation` and `tailrecFolding.ComputationMeaningTransformation`
 - effectful reading
   - using `ReadingTransformation` and `readingImplicit.ComputationMeaningTransformation`
 - effectful writing
   - using `WritingTransformation` and `toConsoleWriting.effectExecuting.ComputationMeaningTransformation`

We combined the latter two techniques to deal with effectful I/0.

note that we dealt with reading in a generic way and with writing in a specific way.

What about combining the three techniques?

Consider

```scala
package pdbp.computation.transformation.writing.reading.free

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.writable.Writable

import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.free.FreeTransformation._
import pdbp.computation.transformation.reading.ComputationReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation

private[pdbp] trait FreeReadingWritingTransformation[
    R, W: Writable, 
    C[+ _]: Computation
          : [C[+ _]] => Reading[R, Kleisli[C]]
          : [C[+ _]] => Writing[W, Kleisli[C]]]
    extends FreeTransformation[C]
    with ComputationReadingTransformation[R, C, FreeTransformed[C]]
    with ComputationWritingTransformation[W, C, FreeTransformed[C]]
```


For `trait FreeReadingWritingTransformation` we do not have to write any code since `trait ComputationReadingTransformation`and `trait ComputationWritingTransformation` do all the heavy lifting.

At the syntactic level we can now define

```scala
package pdbp.program.instances.types.active.writing.reading.free

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

object freeReadingWritingActiveTypes {

  type FreeReadingWritingActive[R, W] =
    FreeTransformed[ReadingWritingActive[R, W]]

  type `=>FRWA`[R, W] = Kleisli[FreeReadingWritingActive[R, W]]

}
```

and


```scala
package pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.writing.reading.free.FreeReadingWritingTransformation
import pdbp.computation.transformation.reading.ComputationReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting
import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

import pdbp.program.instances.active.writing.reading.free.FreeReadingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

object implicits {

  implicit object freeReadingWritingProgram
      extends FreeReadingWritingProgram[BigInt, ToConsoleWriting]()
      with Computation[FreeReadingWritingActive[BigInt, ToConsoleWriting]]()
      with Program[`=>FRWA`[BigInt, ToConsoleWriting]]()
      with Reading[BigInt, `=>FRWA`[BigInt, ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>FRWA`[BigInt, ToConsoleWriting]]()
      with ComputationTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]]()
      with FreeTransformation[ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ComputationReadingTransformation[
        BigInt,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]
      ]()
      with ComputationWritingTransformation[
        ToConsoleWriting,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]
      ]()
      with FreeReadingWritingTransformation[
        BigInt,
        ToConsoleWriting,
        ReadingWritingActive[BigInt, ToConsoleWriting]]()

}
```

At the semantic level we can now define

```scala
package pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.free

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.computation.meaning.of.free.tailrecFolding.ComputationMeaningTransformation

import pdbp.program.instances.types.active.reading.readingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

import pdbp.program.instances.active.reading.bigint.implicits.readingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free.implicits.freeReadingWritingProgram

import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.implicits.readingImplicitEffectExecuting

object implicits {

  implicit object tailrecFoldingReadingEffectExecuting
      extends ComputationMeaningTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ImplicitComputationMeaningTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt],
        FreeReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ComputationMeaning[FreeReadingWritingActive[BigInt, ToConsoleWriting],
                              ReadingActive[BigInt]]()
      with ProgramMeaning[`=>FRWA`[BigInt, ToConsoleWriting], `=>RA`[BigInt]]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      readingImplicitEffectExecuting

  }

}
```

## **Running `mainFactorial` using `active.writing.toConsoleWriting.reading.bigint.free.implicits.freeReadingWritingProgram`, `active.of.writing.toConsoleWriting.reading.bigint.free.implicits.tailrecFoldingReadingEffectExecuting.meaning`, and `active.reading.runner.run`, and using `readingWritingProgram.read` and `readingWritingProgram.write`**

Consider

```scala
package examples.main.active.writing.toConsole.reading.bigint.tailrecursive

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free.implicits.freeReadingWritingProgram

import examples.mainPrograms.MainFactorial

object FactorialMain
    extends MainFactorial[`=>FRWA`[BigInt, ToConsoleWriting]]() {

  override val producer = freeReadingWritingProgram.read

  import examples.utils.implicits.convertFactorialOfBigIntReadToToConsoleWriting

  override val consumer = freeReadingWritingProgram.write

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.free.implicits.tailrecFoldingReadingEffectExecuting.meaning

    import pdbp.program.runners.active.reading.runner.run

    import examples.utils.implicits.bigIntEffectfullyReadFromConsole

    run(meaning(mainFactorial))

  }

}
```

Here are some details of `FactorialMain` using effectfree I/0 and the heap that differ from the ones using effectfree I/0 and the stack.

  - `FactorialMain` uses implicit dependency injection by `import` of `freeReadingWritingProgram` that is a program implementation of type `` Program[`=>FRWA`]  ``.   

  - `main` uses implicit dependency injection by `import` of `tailrecFoldingReadingEffectExecuting.meaning` that transforms the program implementation `freeReadingWritingProgram` of type `` Program[`=>FRWA`]  ``to a meaning program implementation of type `` Program[`=>RA`]  ``.

Note that there is, again a lot of `import` flexibility involved.

  - `import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free.implicits.freeReadingWritingProgram` defines the program implementation we use, and, as a consequence, which `factorial` program implementation and `mainFactorial` main program implementation we use.

  - `import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.free.implicits.tailrecFoldingReadingEffectExecuting.meaning` defines which program meaning we use, and, as a consequence, which main meaning program implementation `meaning(mainFactorial)` we use.
  
  - `import pdbp.program.runners.active.runner.run` defines how to run the `meaning(mainFactorial)` main meaning program implementation.

Let's try `factorial` with `1000` (using `sbt -J-Xss1m`).

```scala
[info] Running examples.main.active.writing.toConsoleWriting.reading.bigint.tailrecursive.FactorialMain 
please type an integer to read
1000
the factorial value of the integer read is
402387260077093773543702433923003985719374864210714632543799910429938512398629020592044208486969404800479988610197196058631666872994808558901323829669944590997424504087073759918823627727188732519779505950995276120874975462497043601418278094646496291056393887437886487337119181045825783647849977012476632889835955735432513185323958463075557409114262417474349347553428646576611667797396668820291207379143853719588249808126867838374559731746136085379534524221586593201928090878297308431392844403281231558611036976801357304216168747609675871348312025478589320767169132448426236131412508780208000261683151027341827977704784635868170164365024153691398281264810213092761244896359928705114964975419909342221566832572080821333186116811553615836546984046708975602900950537616475847728421889679646244945160765353408198901385442487984959953319101723355556602139450399736280750137837615307127761926849034352625200015888535147331611702103968175921510907788019393178114194545257223865541461062892187960223838971476088506276862967146674697562911234082439208160153780889893964518263243671616762179168909779911903754031274622289988005195444414282012187361745992642956581746628302955570299024324153181617210465832036786906117260158783520751516284225540265170483304226143974286933061690897968482590125458327168226458066526769958652682272807075781391858178889652208164348344825993266043367660176999612831860788386150279465955131156552036093988180612138558600301435694527224206344631797460594682573103790084024432438465657245014402821885252470935190620929023136493273497565513958720559654228749774011413346962715422845862377387538230483865688976461927383814900140767310446640259899490222221765904339901886018566526485061799702356193897017860040811889729918311021171229845901641921068884387121855646124960798722908519296819372388642614839657382291123125024186649353143970137428531926649875337218940694281434118520158014123344828015051399694290153483077644569099073152433278288269864602789864321139083506217095002597389863554277196742822248757586765752344220207573630569498825087968928162753848863396909959826280956121450994871701244516461260379029309120889086942028510640182154399457156805941872748998094254742173582401063677404595741785160829230135358081840096996372524230560855903700624271243416909004153690105933983835777939410970027753472000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
[success] Total time: 16 s, completed Nov 16, 2018 10:11:07 PM

```

























