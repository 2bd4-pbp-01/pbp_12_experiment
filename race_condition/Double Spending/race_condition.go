package main

import (
	"fmt"
	"sync"
)

var barang = 5

func ambilBarang(wg *sync.WaitGroup, id int) {
	defer wg.Done()
	fmt.Printf(" Gorutine ke %d memeriksa jumlah barang...", id)
	if barang <= 0 {
		fmt.Printf("Barang habis\n")
	} else {
		fmt.Printf("Barang tersedia mengambil barang \n")
		barang = barang - 1
	}
}

func main() {
	var wg sync.WaitGroup

	for i := 1; i <= 10; i++ {
		wg.Add(1)
		go ambilBarang(&wg, i)

	}
	wg.Wait() // menunggu proses gorutine selsesai
	fmt.Printf("Jumlah barang akhir: %d\n", barang)
}
