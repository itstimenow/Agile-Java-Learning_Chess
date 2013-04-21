#! /bin/sh

javac -cp "/home/roger/Projects/Agile-Java/Lesson-02_Java-Basics/Exercise:/home/roger/lib/junit-4.11.jar" pieces/*.java chess/*.java

if [ $? -eq 0 ]; then
    java -cp "/home/roger/Projects/Agile-Java/Lesson-02_Java-Basics/Exercise:/home/roger/lib/junit-4.11.jar" junit.textui.TestRunner chess.AllTests
fi
