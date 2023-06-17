package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
	"time"
)

const (
	CONN_HOST = "localhost"
	CONN_PORT = "8080"
	CONN_TYPE = "tcp"
)

func main() {
	conn, err := net.Dial(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		fmt.Println("Error dialing:", err.Error())
		os.Exit(1)
	}
	fmt.Println("Dialing on " + CONN_HOST + ":" + CONN_PORT)

	handleConn(conn)
	conn.Close()
}

func handleConn(conn net.Conn) {
	outPutCh := make(chan string) // outgoing client messages
	go reader(conn)
	go writer(conn, outPutCh)

	for {
		outPutCh <- "image"
		outPutCh <- "video"
		time.Sleep(20 * time.Second)
		outPutCh <- "image"
		outPutCh <- "text"
		time.Sleep(30 * time.Second)
	}
}

func reader(conn net.Conn) {
	input := bufio.NewScanner(conn)
	for input.Scan() {
		fmt.Println(input.Text())
	}
}

func writer(conn net.Conn, outPutCh chan string) {
	for msg := range outPutCh {
		fmt.Fprintln(conn, msg) // NOTE: ignoring network errors
	}
}
