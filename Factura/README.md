# compilar 
mvn clean package 

# construir la imagen 

docker build . -t factura:1
docker run -d --name factura-1 factura:1
# RUN ( se deben poner las variables de entorno que tiene el micro conexion bd)
docker run --name factura-01 \
  -e DATABASE_URL=jdbc:postgresql://localhost:5432/demo \
  -e DATABASE_USERNAME=postgres \
  -e DATABASE_PASSWORD=admind \
  -e SERVER_PORT=8080 \º
  -network curso \
  -p 9090:8080 \
  -d factura:01
# run docker compose
docker compose  -f docker-compose-full.yaml up -d
# push registry
docker tag factura:1 dapanew/factura:1
# docker push para enviar al registry de dockerhub 
docker push  dapanew/factura:1
1


