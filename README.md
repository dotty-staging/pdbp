# Program Description Based Programming

Refactoring [pdbp.github.io](https://github.com/PDBP/pdbp.github.io).

## Issues

## Remarks

Difference between nightly builds

```scala
val dottyVersion = "0.11.0-bin-20181111-180c8df-NIGHTLY"
val dottyVersion = "0.11.0-bin-20181114-7a86d5b-NIGHTLY"
```

With `"0.11.0-bin-20181114-7a86d5b-NIGHTLY"`

No compiler error.

With `"0.11.0-bin-20181114-7a86d5b-NIGHTLY"`

A compiler error.

```scala
[error] -- Error: /opt/home/git/blog/pdbp/computationSemantics/src/main/scala/pdbp/computation/meaning/of/free/tailrecFolding/ComputationMeaningTransformation.scala:69:21 
[error] 69 |                apply(y2ftcz.eval(y))
[error]    |                ^^^^^^^^^^^^^^^^^^^^^
[error]    |               Cannot rewrite recursive call: it is not in tail position
[error] one error found
```

In fact, this error makes sense, and the `case` can be commented out.

But

What worries me a bit is that, if I combine tail recurion with reading and writing using `"0.11.0-bin-20181114-7a86d5b-NIGHTLY"` the `case` is needed and tail recursion optimization does work (I could run `factorial` with `1000` using `sbt -J-Xss1m`). So how could this have been optimized to make use of the heap instead of the stack?

