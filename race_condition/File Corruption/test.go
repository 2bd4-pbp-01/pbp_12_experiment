package main

import (
	"fmt"
	"os"
	"path/filepath"
	"sync"
	"time"
)

func tulisKeFile(wg *sync.WaitGroup, id int, file *os.File) {
	defer wg.Done()

	// Simulasi penulisan yang memakan waktu
	time.Sleep(time.Millisecond * 10)

	_, err := file.WriteString(fmt.Sprintf("Goroutine %d menulis ke file.\n", id))
	if err != nil {
		fmt.Printf("Goroutine %d mengalami kesalahan: %s\n", id, err)
	}
}

func main() {
	// Nama folder tujuan
	folderPath := "output/golang_race2_fileCorrupt"

	// Membuat folder jika belum ada
	if err := os.MkdirAll(folderPath, os.ModePerm); err != nil {
		fmt.Println("Gagal membuat folder:", err)
		return
	}

	// Mengatur jumlah file yang ingin dibuat
	numFiles := 1000

	for fileNum := 1; fileNum <= numFiles; fileNum++ {
		fileName := fmt.Sprintf("output%d.txt", fileNum)
		filePath := filepath.Join(folderPath, fileName)

		file, err := os.Create(filePath)
		if err != nil {
			fmt.Println("Gagal membuat file:", err)
			return
		}
		defer file.Close()

		var wg sync.WaitGroup

		for i := 1; i <= 1000; i++ {
			wg.Add(1)
			go tulisKeFile(&wg, i, file)
		}

		wg.Wait()
		fmt.Printf("Penulisan selesai untuk %s. Periksa file tersebut untuk hasilnya.\n", filePath)
	}
}
