## Lessons

- Unit №4. Массивы, (third week, this branch)
- JAVA FROM EPAM, Задания к главе 3, (fourth week, lesson4 [branch](https://github.com/Miocasa/java_lessons/tree/lesson4))
- JAVA FROM EPAM, Задания к главе 4, (fourth week, lesson5 [branch](https://github.com/Miocasa/java_lessons/tree/lesson5)) 


```bash
# library manual install
mvn dependency:get -Dartifact=com.github.haifengl:smile-core:3.1.1
mvn dependency:get -Dartifact=com.github.haifengl:smile-plot:3.1.1
mvn dependency:get -Dartifact=org.apache.commons:commons-math3:3.6.1
mvn dependency:get -Dartifact=nz.ac.waikato.cms.weka:weka-stable:3.8.6 
```

```bash
mvn exec:java -Dexec.mainClass=GenerateData -Dexec.args="80 42" # gen csv, "count seed"
```