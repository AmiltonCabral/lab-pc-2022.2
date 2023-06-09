package main

import (
	//"math/rand"
	"fmt"
	"math/rand"
)

func http2Server(outPutCh chan string, jCh chan int) {
	for x := range outPutCh {
		fmt.Printf(x + "\n")
	}

	close(jCh)
}

func http2Clients(inputCh chan string) {
	x := rand.Intn(40)
	for i := 0; i < x; i++ {
		inputCh <- "httpStatus.200"
	}
	close(inputCh)

}

func main() {
	ch := make(chan string, 20)
	joinCh := make(chan int)

	go http2Server(ch, joinCh)
	go http2Clients(ch)

	<-joinCh
}
