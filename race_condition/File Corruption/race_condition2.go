package main

import (
	"fmt"
	"sync"
)

var counter int // Variabel global yang akan dimodifikasi secara bersamaan

// Fungsi untuk meningkatkan nilai counter
func increaseCounter(wg *sync.WaitGroup, id int) {
	defer wg.Done()

	for i := 0; i < 5; i++ {
		// Memodifikasi counter secara bersamaan tanpa kunci
		value := counter
		value++
		counter = value
		fmt.Printf("Goroutine %d meningkatkan counter menjadi %d\n", id, counter)
	}
}

func main() {
	var wg sync.WaitGroup

	for i := 1; i <= 5; i++ {
		wg.Add(1)
		go increaseCounter(&wg, i)
	}

	wg.Wait()
	fmt.Println("Nilai akhir counter:", counter)
}
