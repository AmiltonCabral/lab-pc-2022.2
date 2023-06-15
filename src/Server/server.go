package main

import (
	"fmt"
	"io"
	"log"
	"net"
)

const (
	CONN_HOST = "localhost"
	CONN_PORT = "8080"
	CONN_TYPE = "tcp"
)

type Request struct {
	msg  string
	conn net.Conn
}

func main() {
	requests := make(chan Request)
	go generateResponses(requests)

	listener, err := net.Listen(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		log.Fatal("Error listening:", err.Error())
	}

	defer listener.Close()
	fmt.Println("Listening on " + CONN_HOST + ":" + CONN_PORT)

	for {
		conn, err := listener.Accept()
		if err != nil {
			log.Print("Error accepting: ", err.Error())
			continue
		}

		go reader(conn, requests)
		fmt.Println("cliente conectado")

	}
}

func reader(conn net.Conn, requests chan Request) {
	//input := bufio.NewScanner(conn)
	for {
		//fmt.Printf(input.Text())
		// msg, err := reader.ReadString('\n')
		// if err != nil {
		// 	)
		// 	break
		// }
		requests <- Request{"sadasdsa", conn}
	}
	//fmt.Printf("Client disconnected.\n")
}

func generateResponses(requests chan Request) {
	for {
		request := <-requests

		_, err := io.WriteString(request.conn, "request.msg")
		if err != nil {
			continue
		}
	}
}
