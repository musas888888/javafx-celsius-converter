FROM openjdk:17-slim
WORKDIR /app

# JavaFX runtime deps for Linux
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    && rm -rf /var/lib/apt/lists/*

# Download JavaFX SDK (Linux) and keep only /lib
ENV JFX_VER=21.0.2
RUN mkdir -p /javafx-sdk \
 && wget -O /tmp/javafx.zip https://download2.gluonhq.com/openjfx/${JFX_VER}/openjfx-${JFX_VER}_linux-x64_bin-sdk.zip \
 && unzip /tmp/javafx.zip -d /javafx-sdk \
 && mv /javafx-sdk/javafx-sdk-${JFX_VER}/lib /javafx-sdk/lib \
 && rm -rf /javafx-sdk/javafx-sdk-${JFX_VER} /tmp/javafx.zip

# Copy your fat JAR from Maven build
COPY target/tempconv.jar /app/app.jar

# X display on Windows (Xming)
ENV DISPLAY=host.docker.internal:0.0

CMD ["java","--module-path","/javafx-sdk/lib","--add-modules","javafx.controls,javafx.fxml","-jar","/app/app.jar"]
