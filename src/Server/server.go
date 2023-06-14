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

func main() {
	ln, err := net.Listen(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		log.Fatal("Error listening:", err.Error())
	}
	defer ln.Close()
	fmt.Println("Listening on " + CONN_HOST + ":" + CONN_PORT)

	for {
		conn, err := ln.Accept()
		if err != nil {
			log.Print("Error accepting: ", err.Error())
			continue
		}
		go handleConn(conn)
	}
}

func handleConn(conn net.Conn) {
	defer conn.Close()

	buf, read_err := io.ReadAll(conn)
	if read_err != nil {
		fmt.Println("Failed:", read_err)
		return
	}
	fmt.Println("Got: ", string(buf))

	_, write_err := io.WriteString(conn, "Message received.\n")
	if write_err != nil {
		fmt.Println("Failed:", write_err)
		return
	}
}
