FROM maven:v1.0
RUN git clone https://frostyaxe:Champion%201996.@github.com/frostyaxe/Frostyspark.git 
RUN ls -lrt
WORKDIR Frostyspark
RUN mvn clean test