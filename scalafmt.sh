#! /bin/bash

for f in $(find . -name "*.scala")
do

###############################################################################################################################################################

  if [[ $f -ef ./mainExamples/src/main/scala/examples/main/active/writing/toConsoleWriting/reading/bigint/tailrecursive/FactorialMain.scala ]]
  # if [[ true ]]

  then

  if [[ $f -ef ./types/src/main/scala/pdbp/types/implicitUnit.scala ||
        $f -ef ./types/src/main/scala/pdbp/types/Thunk.scala ||
        $f -ef ./types/src/main/scala/pdbp/types/product/productType.scala ||
        $f -ef ./types/src/main/scala/pdbp/types/sum/sumType.scala ||
        
        $f -ef ./utils/src/main/scala/pdbp/utils/functionUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/productUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/sumUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/productAndSumUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/effectfulFunctionUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/failureUtils.scala ||
        $f -ef ./utils/src/main/scala/pdbp/utils/effectfulReadUtils.scala ||

        $f -ef ./naturalTransformation/src/main/scala/pdbp/naturalTransformation/binary/NaturalBinaryTypeConstructorTransformation.scala ||
        $f -ef ./naturalTransformation/src/main/scala/pdbp/naturalTransformation/unary/NaturalUnaryTypeConstructorTransformation.scala ||

        $f -ef ./liftingSyntax/src/main/scala/pdbp/lifting/ObjectLifting.scala ||
        $f -ef ./liftingSyntax/src/main/scala/pdbp/lifting/FunctionLifting.scala ||
        $f -ef ./liftingSyntax/src/main/scala/pdbp/lifting/OperatorLifting.scala ||
        $f -ef ./liftingSyntax/src/main/scala/pdbp/lifting/Lifting.scala ||

        $f -ef ./writableSyntax/src/main/scala/pdbp/writable/Startable.scala ||
        $f -ef ./writableSyntax/src/main/scala/pdbp/writable/Appendable.scala ||
        $f -ef ./writableSyntax/src/main/scala/pdbp/writable/Writable.scala ||

        $f -ef ./programSyntax/src/main/scala/pdbp/program/Function.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Composition.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Construction.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Condition.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Program.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Applying.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/reading/Reading.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/state/State.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/failure/Failure.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/control/types/Prompt.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/control/Control.scala ||

        $f -ef ./computationSyntax/src/main/scala/pdbp/computation/Computation.scala ||
        $f -ef ./computationSyntax/src/main/scala/pdbp/computation/Resulting.scala ||
        $f -ef ./computationSyntax/src/main/scala/pdbp/computation/Binding.scala ||
        $f -ef ./computationSyntax/src/main/scala/pdbp/program/ProgramWithLifting.scala ||
        $f -ef ./computationSyntax/src/main/scala/pdbp/program/ProgramWithApplying.scala ||

        $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/trying/types/tryType.scala ||
        $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/trying/utils/tryUtils.scala ||

        $f -ef ./writableSyntaxInstances/src/main/scala/pdbp/writable/instances/toConsoleWriting/types/ToConsoleWriting.scala ||
        $f -ef ./writableSyntaxInstances/src/main/scala/pdbp/writable/instances/toConsoleWriting/implicits.scala ||
        $f -ef ./writableSyntaxInstances/src/main/scala/pdbp/writable/instances/toConsoleWriting/utils/toConsoleWritingUtils.scala ||

        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/utils/active/functionUtils.scala || 
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/activeTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/free/freeActiveTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/reading/readingActiveTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/writing/writingActiveTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/writing/reading/readingWritingActiveTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/types/active/writing/reading/free/freeReadingWritingActiveTypes.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/reading/ReadingProgram.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/free/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/reading/bigint/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/toConsoleWriting/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/toConsoleWriting/reading/bigint/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/toConsoleWriting/reading/bigint/free/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/reading/twobigints/implicits.scala ||
        $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/toConsoleWriting/reading/twobigints/implicits.scala ||

        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/reading/ReadingMeaning.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/active/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/free/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/reading/bigint/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/writing/toConsoleWriting/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/writing/toConsoleWriting/reading/bigint/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/writing/toConsoleWriting/reading/bigint/free/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/reading/twobigints/implicits.scala ||
        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/writing/toConsoleWriting/reading/twobigints/implicits.scala ||

        $f -ef ./programRunners/src/main/scala/pdbp/program/runners/active/runner.scala ||
        $f -ef ./programRunners/src/main/scala/pdbp/program/runners/active/reading/runner.scala ||

        $f -ef ./programExampleUtils/src/main/scala/examples/types/info/Info.scala ||
        $f -ef ./programExampleUtils/src/main/scala/examples/utils/functionUtils.scala ||     

        $f -ef ./programExamples/src/main/scala/examples/programs/Factorial.scala ||

        $f -ef ./mainExampleUtils/src/main/scala/examples/utils/implicits.scala ||
        
        $f -ef ./mainExamples/src/main/scala/examples/main/active/writing/toConsoleWriting/reading/bigint/FactorialMain.scala ||
        $f -ef ./mainExamples/src/main/scala/examples/main/active/writing/toConsoleWriting/reading/bigint/tailrecursive/FactorialMain.scala ||
        $f -ef ./mainExamples/src/main/scala/examples/main/active/writing/toConsoleWriting/info/reading/argumentAndResultMultiplier/FactorialOfArgumentMultipliedByResultMultiplierMain.scala ||
        
        $f -ef ./effectfulMainProgramUtils/src/main/scala/pdbp/program/utils/EffectfulUtils.scala ||  
  
        $f -ef ./effectfulMainExampleUtils/src/main/scala/examples/utils/EffectfulUtils.scala ||

        $f -ef ./effectfulMainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/FactorialMain.scala ||       
        $f -ef ./effectfulMainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/SimpleFactorialMain.scala ||
        $f -ef ./effectfulMainExamples/src/main/scala/examples/main/active/tailrecursive/effectfulReadingAndWriting/FactorialMain.scala ]]

  then
    echo "scalafmt $f"
    scalafmt $f 

###############################################################################################################################################################

  elif [[ $f -ef ./programSyntax/src/main/scala/pdbp/examples/ProductInTermsOfLetAndIn.scala ||
          $f -ef ./programSyntax/src/main/scala/pdbp/examples/SumInTermsOfIfAndElse.scala ||
          $f -ef ./programSyntax/src/main/scala/pdbp/program/writing/Writing.scala ||

          $f -ef ./programExamples/src/main/scala/examples/programs/HelperPrograms.scala ||
          $f -ef ./programExamples/src/main/scala/examples/programs/AtomicPrograms.scala ||
          $f -ef ./programExamples/src/main/scala/examples/mainPrograms/MainFactorial.scala ||
          $f -ef ./programExamples/src/main/scala/examples/programs/writing/info/AtomicPrograms.scala ]]

  then
    echo "scalafmt $f has problems with 'trait '"
    sed -i "s/trait /class /g" $f
    scalafmt $f
    sed -i "s/class /trait /g" $f

###############################################################################################################################################################

  elif [[ $f -ef ./types/src/main/scala/pdbp/types/implicitFunctionType.scala ||
          $f -ef ./types/src/main/scala/pdbp/types/const/constType.scala ]]
  
  then
    echo "scalafmt $f has problems with 'type '"
    sed -i "s/type /\/\/type /g" $f
    scalafmt $f
    sed -i "s/\/\/type /type /g" $f    

###############################################################################################################################################################

  elif [[ $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/ComputationTransformation.scala ||
         
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/IdentityComputationMeaning.scala ||
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/of/free/tailrecFolding/ComputationMeaningTransformation.scala ||
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/of/reading/readingImplicit/ComputationMeaningTransformation.scala ||
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/of/writing/toConsoleWriting/effectExecuting/ComputationMeaningTransformation.scala ||
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/of/writing/toConsoleWriting/effectExecuting/ComputationMeaningTransformation.scala ||
  
          $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/WritingProgram.scala ||
          $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/reading/ReadingWritingProgram.scala ||
          $f -ef ./programSyntaxInstances/src/main/scala/pdbp/program/instances/active/writing/reading/free/FreeReadingWritingProgram.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    scalafmt $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f  

###############################################################################################################################################################

  elif [[ $f -ef ./kleisli/src/main/scala/pdbp/types/kleisli/binary/kleisliBinaryTypeConstructorType.scala ||
          $f -ef ./kleisli/src/main/scala/pdbp/types/kleisli/unary/kleisliUnaryTypeConstructorType.scala ]]
  
  then
    echo "scalafmt $f has problems with 'private[pdbp] type '"
    sed -i "s/private\[pdbp\] type /\/\/private\[pdbp\] type /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] type /private\[pdbp\] type /g" $f          

###############################################################################################################################################################

  elif [[ $f -ef ./programSemantics/src/main/scala/pdbp/program/meaning/ProgramMeaning.scala ||

          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/ComputationMeaning.scala ||
          $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/IdentityComputationMeaning.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] lazy val  '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] lazy /\/\/private\[pdbp\] lazy /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] lazy /private\[pdbp\] lazy /g" $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f    

###############################################################################################################################################################

  elif [[ $f -ef ./computationSemantics/src/main/scala/pdbp/computation/meaning/transformation/ImplicitComputationMeaningTransformation.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] implicit val  '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] implicit /\/\/private\[pdbp\] implicit /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] implicit /private\[pdbp\] implicit /g" $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f 

###############################################################################################################################################################

  elif [[ $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/free/FreeTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/reading/ReadingTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/writing/WritingTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/state/StateTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/trying/FailureTransformation.scala ]]
  
  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] type '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] type /\/\/private\[pdbp\] type /g" $f
    scalafmt $f

    sed -i "s/\/\/private\[pdbp\] type /private\[pdbp\] type /g" $f 
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f    

###############################################################################################################################################################

  elif [[ $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/reading/ComputationReadingTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/writing/ComputationWritingTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/writing/reading/ReadingWritingTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/state/ComputationStateTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/trying/ComputationFailureTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/delimiting/ControlTransformation.scala ||
          $f -ef ./computationTransformations/src/main/scala/pdbp/computation/transformation/writing/reading/free/FreeReadingWritingTransformation.scala ||

          $f -ef ./programExamples/src/main/scala/examples/mainPrograms/writing/info/reading/argumentAndResultMultiplier/MainFactorialOfArgumentMultipliedByResultMultiplier.scala ||
          $f -ef ./programExamples/src/main/scala/examples/programs/writing/info/reading/argumentAndResultMultiplier/FactorialOfArgumentMultipliedByResultMultiplier.scala ]]
  
  then
    echo "WARNING: $f not formatted"

###############################################################################################################################################################

  else
    echo "ERROR: $f not formatted"

  fi 

fi  

done





