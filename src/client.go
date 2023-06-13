package main

import (
	"fmt"
	"io"
	"net"
	"os"
)

const (
	CONN_HOST = "localhost"
	CONN_PORT = "8080"
	CONN_TYPE = "tcp"
)

func main() {
	conn, err := net.Dial(CONN_TYPE, CONN_HOST+":"+CONN_PORT)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	fmt.Println("Dialing on " + CONN_HOST + ":" + CONN_PORT)

	ReadNWrite(conn)
	conn.Close()
}

func ReadNWrite(conn net.Conn) {
	message := "Test Request\n"
	_, write_err := io.WriteString(conn, message)
	if write_err != nil {
		fmt.Println("failed:", write_err)
		return
	}
	conn.(*net.TCPConn).CloseWrite()

	buf, read_err := io.ReadAll(conn)
	if read_err != nil {
		fmt.Println("failed:", read_err)
		return
	}
	fmt.Println("Got: ", string(buf))
}
