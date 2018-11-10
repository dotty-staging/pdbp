#! /bin/bash

for f in $(find . -name "*.scala")
do

if [[ $f -ef ./programSyntax/src/main/scala/pdbp/computation/transformation/free/FreeTransformation.scala ]]
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

        $f -ef ./naturalTransformation/src/main/scala/pdbp/naturalTransformation/binary/NaturalBinaryTypeConstructorTransformation.scala ||
        $f -ef ./naturalTransformation/src/main/scala/pdbp/naturalTransformation/unary/NaturalUnaryTypeConstructorTransformation.scala ||

        $f -ef ./programSyntax/src/main/scala/pdbp/program/Function.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Composition.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Construction.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Condition.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Program.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/Computation.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/Resulting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/Binding.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/ObjectLifting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/FunctionLifting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/OperatorLifting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/computation/Lifting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/ProgramWithLifting.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/Applying.scala ||
        $f -ef ./programSyntax/src/main/scala/pdbp/program/ProgramWithApplying.scala ||

        $f -ef ./programUtils/src/main/scala/pdbp/program/utils/EffectfulUtils.scala ||

        $f -ef ./programInstances/src/main/scala/pdbp/program/instances/types/active/activeTypes.scala ||
        $f -ef ./programInstances/src/main/scala/pdbp/program/instances/utils/active/functionUtils.scala || 
        $f -ef ./programInstances/src/main/scala/pdbp/program/instances/active/implicits.scala ||

        $f -ef ./programSemanticsInstances/src/main/scala/pdbp/program/meaning/active/of/active/implicits.scala ||

        $f -ef ./programRunners/src/main/scala/pdbp/program/runners/active/runner.scala ||

        $f -ef ./programExampleUtils/src/main/scala/examples/utils/functionUtils.scala ||     
        $f -ef ./programExamples/src/main/scala/examples/programs/Factorial.scala ||

        $f -ef ./mainExampleUtils/src/main/scala/examples/utils/EffectfulUtils.scala ||
        
        $f -ef ./mainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/FactorialMain.scala ||       
        $f -ef ./mainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/SimpleFactorialMain.scala ]]       
  
  then
    echo "scalafmt $f"
    scalafmt $f 

  elif [[ $f -ef ./programSyntax/src/main/scala/pdbp/examples/ProductInTermsOfLetAndIn.scala ||
          $f -ef ./programSyntax/src/main/scala/pdbp/examples/SumInTermsOfIfAndElse.scala ||
          
          $f -ef ./programExamples/src/main/scala/examples/programs/HelperPrograms.scala ||
          $f -ef ./programExamples/src/main/scala/examples/programs/AtomicPrograms.scala ||
          $f -ef ./programExamples/src/main/scala/examples/mainPrograms/MainFactorial.scala ]]

  then
    echo "scalafmt $f has problems with 'trait '"
    sed -i "s/trait /class /g" $f
    scalafmt $f
    sed -i "s/class /trait /g" $f 

  elif [[ $f -ef ./programSyntax/src/main/scala/pdbp/computation/transformation/ComputationTransformation.scala ||
  
          
          $f -ef ./programSemantics/src/main/scala/pdbp/computation/meaning/IdentityComputationMeaning.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    scalafmt $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f       

  elif [[ $f -ef ./programSemantics/src/main/scala/pdbp/program/meaning/ProgramMeaning.scala ||
          $f -ef ./programSemantics/src/main/scala/pdbp/computation/meaning/ComputationMeaning.scala ||
          $f -ef ./programSemantics/src/main/scala/pdbp/computation/meaning/IdentityComputationMeaning.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] lazy val  '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] lazy /\/\/private\[pdbp\] lazy /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] lazy /private\[pdbp\] lazy /g" $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f    

  elif [[ $f -ef ./programSemantics/src/main/scala/pdbp/computation/meaning/transformation/ImplicitComputationMeaningTransformation.scala ]]

  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] implicit val  '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] implicit /\/\/private\[pdbp\] implicit /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] implicit /private\[pdbp\] implicit /g" $f
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f       

  elif [[ $f -ef ./types/src/main/scala/pdbp/types/implicitFunctionType.scala ]]
  
  then
    echo "scalafmt $f has problems with 'type '"
    sed -i "s/type /\/\/type /g" $f
    scalafmt $f
    sed -i "s/\/\/type /type /g" $f

  elif [[ $f -ef ./types/src/main/scala/pdbp/types/kleisli/binary/kleisliBinaryTypeConstructorType.scala ||
          $f -ef ./types/src/main/scala/pdbp/types/kleisli/unary/kleisliUnaryTypeConstructorType.scala ]]
  
  then
    echo "scalafmt $f has problems with 'private[pdbp] type '"
    sed -i "s/private\[pdbp\] type /\/\/private\[pdbp\] type /g" $f
    scalafmt $f
    sed -i "s/\/\/private\[pdbp\] type /private\[pdbp\] type /g" $f  

  elif [[ $f -ef ./programSyntax/src/main/scala/pdbp/computation/transformation/free/FreeTransformation.scala ]]
  
  then
    echo "scalafmt $f has problems with 'private[pdbp] trait '"
    echo "scalafmt $f has problems with 'private[pdbp] type '"
    sed -i "s/private\[pdbp\] trait /private\[pdbp\] class /g" $f
    sed -i "s/private\[pdbp\] type /\/\/private\[pdbp\] type /g" $f
    scalafmt $f

    sed -i "s/\/\/private\[pdbp\] type /private\[pdbp\] type /g" $f 
    sed -i "s/private\[pdbp\] class /private\[pdbp\] trait /g" $f    

  else
    echo "ERROR: $f not formatted"

  fi 

fi  

done



