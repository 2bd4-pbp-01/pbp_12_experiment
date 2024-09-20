package main

import (
	"fmt"
	"os"
	"sync"
)

func tulisKeFile(wg *sync.WaitGroup, id int, file *os.File) {
	defer wg.Done()

	for i := 0; i < 5; i++ {
		// Simulasi jeda kecil untuk meningkatkan kemungkinan race condition
		fmt.Printf("Goroutine %d akan menulis baris ke-%d\n", id, i)
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

	// Memulai beberapa goroutine yang menulis ke file secara bersamaan
	for i := 1; i <= 5; i++ {
		wg.Add(1)
		go tulisKeFile(&wg, i, file)
	}

	wg.Wait()
	fmt.Println("Penulisan selesai. Periksa output.txt untuk hasilnya.")
}
