#!/usr/bin/env bash

echo ""
echo "Setting variables..."
workspaceFolder=$(pwd)
workspaceFolderBasename=autotrader

echo ""
echo "Removing package folder..."
rm -R ${workspaceFolder}/package

echo ""
echo "Creating package folder..."
mkdir -p ${workspaceFolder}/package/lib

echo ""
echo "Copying libs to package..."
cp -r ${workspaceFolder}/lib ${workspaceFolder}/package

echo ""
echo "Putting java class names into 'classes.log'..."
find -name '*.java' > classes.log

echo ""
echo "Compiling classes..."
javac -cp ${workspaceFolder}/lib/commons-jexl3-3.2.1.jar:${workspaceFolder}/lib/commons-logging-1.2.jar:${workspaceFolder}/lib/lombok-1.18.26.jar:${workspaceFolder}/lib/jackson-annotations-2.15.0-rc2.jar:${workspaceFolder}/lib/jackson-core-2.15.0-rc2.jar:${workspaceFolder}/lib/jackson-databind-2.15.0-rc2.jar -d ${workspaceFolder}/target @classes.log

echo ""
echo "Creating jar file"
jar cfm ${workspaceFolder}/package/${workspaceFolderBasename}.jar ${workspaceFolderBasename}.mf -C target ./com

echo ""
echo "Creating package..."
tar -czvf ${workspaceFolderBasename}.tar.gz -C package ${workspaceFolderBasename}.jar lib

echo ""
echo "Moving package to project root folder..."
mv ${workspaceFolder}/${workspaceFolderBasename}.tar.gz ${workspaceFolder}/package
