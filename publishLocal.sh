#! /bin/bash

# for d in \
#          types \
#          utils \
#          naturalTransformation \
#          programSyntax \
#          programSemantics \
#          programUtils \
#          programInstances \
#          programSemanticsInstances \
#          programRunners \
#          programExampleUtils \
#          programExamples \
#          mainExampleUtils \
#          mainExamples
for d in \
         types \
         utils \
         naturalTransformation \
         programSyntax \
         programInstances \        
do
 echo $d
 cd $d
 sbt publishLocal
 \rm -r _site
 cd ..
done
