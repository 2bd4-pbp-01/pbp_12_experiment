package main

import (
	"fmt"
	"math/rand"
	"os"
	"sync"
	"time"
)

func tulisKeFile(wg *sync.WaitGroup, id int) {
	defer wg.Done()

	// Membuka file untuk dibaca dan ditulis
	file, err := os.OpenFile("output.txt", os.O_RDWR, 0666)
	if err != nil {
		fmt.Printf("Goroutine %d gagal membuka file: %s\n", id, err)
		return
	}
	defer file.Close()

	for i := 0; i < 100; i++ {
		// Posisi penulisan acak dalam file
		offset := rand.Int63n(100)
		file.Seek(offset, 0)

		// Menulis byte acak ke dalam file
		randomByte := byte(rand.Intn(256))
		_, err := file.Write([]byte{randomByte})
		if err != nil {
			fmt.Printf("Goroutine %d mengalami kesalahan saat menulis: %s\n", id, err)
		}

		// Simulasi penulisan yang memakan waktu
		time.Sleep(time.Millisecond * 1)
	}
}

func main() {
	// Mulai dengan membuat file kosong dan mengisi dengan data awal
	file, err := os.Create("output.txt")
	if err != nil {
		fmt.Println("Gagal membuat file:", err)
		return
	}
	file.WriteString("Ini adalah beberapa data awal untuk memulai.\n")
	file.Close()

	var wg sync.WaitGroup

	for i := 1; i <= 5; i++ {
		wg.Add(1)
		go tulisKeFile(&wg, i)
	}

	wg.Wait()
	fmt.Println("Penulisan selesai. Periksa output.txt untuk hasilnya.")
}
