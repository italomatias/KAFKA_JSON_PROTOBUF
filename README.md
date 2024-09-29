# Kafka With Json and Protobufer

This project was build for praticing kafka conection with objects in formats JSON and Protobuf , where you will find a producer and consumer utilizing the message formats described above.

## Installation

This project uses Docker to build the kafka server and his dependencies, to compile-it first start the docker-compose file in the resources folder in this project with this command :

```bash
docker-compose up -d
```

## Endpoints
Use this endpoint to send mensagens in JSON format :

```r
curl --location 'localhost:8000/send/json' \
--header 'Content-Type: application/json' \
--data '{
  "bordero": "TESTE BORDERO ITALO",
  "valor": 353.99,
  "dataOperacao": "2024-07-21",
  "itens":[
      {
          "parcela":"1",
          "valorparcela":55.87,
          "vencimento":"2024-07-30"
      },
      {
          "parcela":"2",
          "valorparcela":66.99,
          "vencimento":"2024-07-30"
      },
      {
          "parcela":"3",
          "valorparcela":231.13,
          "vencimento":"2024-08-17"
      }
  ]
  
}'
```

And this to send ProtoBufer mensagens :

```r
curl --location 'localhost:8000/send/protobuf' \
--header 'Content-Type: application/json' \
--data '{
  "bordero": "PARCELAS TOTAL 30",
  "valor": 11.11,
  "dataOperacao": "2024-09-22",
  "itens":[
      {
          "parcela":"1",
          "valorparcela":55.87,
          "vencimento":"2024-07-30"
      },
      {
          "parcela":"2",
          "valorparcela":66.99,
          "vencimento":"2024-07-30"
      },
      {
          "parcela":"3",
          "valorparcela":231.13,
          "vencimento":"2024-08-17"
      }
  ]
  
}'
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.
