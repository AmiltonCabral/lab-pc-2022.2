package main

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"time"
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
	}
}

func reader(conn net.Conn, requests chan Request) {
	reader := bufio.NewReader(conn)
	for {
		msg, err := reader.ReadString('\n')
		if err != nil {
			fmt.Printf("Client disconnected.\n")
			break
		}
		requests <- Request{msg, conn}
	}
}

func generateResponses(requests chan Request) {
	for {
		request := <-requests

		time.Sleep(3 * time.Second)

		request.conn.Write([]byte("Server got: " + request.msg))
	}
}
