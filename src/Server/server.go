package main

import (
	"bufio"
	"fmt"
	"io"
	"log"
	"math/rand"
	"net"
	"time"
)

const (
	CONN_HOST = "localhost"
	CONN_PORT = "8080"
	CONN_TYPE = "tcp"
)

type Request struct {
	msg      string
	clientCh chan string
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

		go handleConn(conn, requests)
		fmt.Println("New client connected")
	}
}

func handleConn(conn net.Conn, requests chan<- Request) {
	clientCh := make(chan string, 10)
	go clientWriter(conn, clientCh)

	input := bufio.NewScanner(conn)
	for input.Scan() {
		requests <- Request{input.Text(), clientCh}
	}

	//checar se o canal do cliente está fechado antes de escrever nele
	close(clientCh)
	conn.Close()
	fmt.Printf("Client disconnected.\n")
}

func clientWriter(conn net.Conn, clientCh <-chan string) {
	for response := range clientCh {
		_, err := io.WriteString(conn, response)
		if err != nil {
			log.Print("Error writing: ", err.Error())
			continue
		}
	}
}

func generateResponses(requests <-chan Request) {
	for request := range requests {
		switch request.msg {
		case "text":
			go getText(request.clientCh)
		case "image":
			go getImage(request.clientCh)
		case "video":
			go getVideo(request.clientCh)
		}
	}
}

func getText(clientCh chan<- string) {
	simulateWork(1, 5)
	clientCh <- "this is a text\n"
}

func getImage(clientCh chan<- string) {
	simulateWork(10, 15)
	clientCh <- "this is an image\n"
}

func getVideo(clientCh chan<- string) {
	simulateWork(20, 30)
	clientCh <- "this is a video\n"
}

func simulateWork(min int, max int) {
	num := rand.Intn(max-min) + min
	time.Sleep(time.Duration(num) * time.Second)
}
