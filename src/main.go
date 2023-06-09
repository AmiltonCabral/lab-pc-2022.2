package main

import (
	"fmt"
	"math/rand"
	"time"
)

func http2Server(ch chan string, jCh chan int) {
	tick := time.Tick(1000 * time.Millisecond)
	for {
		select {
		case <-tick:
			fmt.Println("Se passaram 100 milisegundos")
		case x := <-ch:
			if x == "Me informe seu status" {
				fmt.Println("httpstatus.200")
			}
		default:
			close(jCh)
			return
		}
	}
}

func http2Clients(ch chan string) {
	x := rand.Intn(40)
	for i := 0; i < x; i++ {
		select {
		case ch <- "Me informe seu status":
		default:
			fmt.Println("Servidor lotado. Tente mais tarde")
		}

	}
	close(ch)
}

func main() {
	ch := make(chan string, 5)
	joinCh := make(chan int)

	go http2Server(ch, joinCh)
	go http2Clients(ch)

	<-joinCh
}
