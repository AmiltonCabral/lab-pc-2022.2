# projeto-pc-2022.2
Projeto Programação Concorrente, implementando um protocolo HTTP/2 em golang.

## Promessas
- [x] Promessa 1 - 09/jun: Os clientes fazem requisições HTTP/2 ao servidor, e o servidor retorna um status.
- [ ] Promessa 2 - 16/jun: O cliente pode solicitar e ter a resposta de vários recursos de forma paralela através de apenas uma conexão (multiplexação).
- [ ] Promessa 3 - 23/jun: O servidor atende o cliente com priorização de requisições.

## comandos
`go run src/Server/server.go`
`printf “GET / HTTP/1.0\r\n\r\n” | nc localhost 8080`