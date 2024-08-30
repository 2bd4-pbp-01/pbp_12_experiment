package main

import (
	"fmt"
	"os"
	"sync"
)

func tulisKeFile(wg *sync.WaitGroup, id int, file *os.File) {
	defer wg.Done()

	for i := 0; i < 1000; i++ {
		_, err := file.WriteString(fmt.Sprintf("Goroutine %d menulis baris ke-%d\n", id, i))
		if err != nil {
			fmt.Printf("Goroutine %d mengalami kesalahan: %s\n", id, err)
			return
		}
	}
}

func main() {
	file, err := os.Create("output.txt")
	if err != nil {
		fmt.Println("Gagal membuat file:", err)
		return
	}
	defer file.Close()

	var wg sync.WaitGroup

	for i := 1; i <= 5; i++ {
		wg.Add(1)
		go tulisKeFile(&wg, i, file)
	}

	wg.Wait()
	fmt.Println("Penulisan selesai. Periksa output.txt untuk hasilnya.")
}
