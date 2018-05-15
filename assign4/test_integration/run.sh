export uiclass=ui.ConverterUI
echo "compile and create jar:"
pushd .. > /dev/null
./gradlew jar
read -p "<enter>"
echo "run the program:"
java -classpath ./build/libs/assign4.jar $uiclass
read -p "<enter>"
popd > /dev/null
echo "compile the user defined converters:"
javac -d bin -classpath ../build/libs/assign4.jar thirdparty/*.java
jar cf userdefinedconverters.jar -C ./bin thirdparty
read -p "<enter>"
echo "run with one user defined converter:"
mkdir inputs
echo "blue." > inputs/text.txt
cp blocks1.txt inputs/blocks.txt
java -classpath ../build/libs/assign4.jar:userdefinedconverters.jar $uiclass
read -p "<enter>"
echo "run with the additional user defined converter:"
cp blocks2.txt inputs/blocks.txt
java -classpath ../build/libs/assign4.jar:userdefinedconverters.jar $uiclass
/bin/rm -r bin
/bin/rm userdefinedconverters.jar
/bin/rm -r inputs
