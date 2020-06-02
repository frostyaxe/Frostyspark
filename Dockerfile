FROM frostyaxe:1.0
RUN vncserver -depth 32 -geometry 1200x900 && websockify -D --web=/usr/share/novnc 8001 localhost:5901
RUN git clone https://frostyaxe:Champion%201996.@github.com/frostyaxe/Frostyspark.git 
RUN ls -lrt
WORKDIR Frostyspark
RUN mvn clean test