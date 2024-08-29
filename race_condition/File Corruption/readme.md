# Race Condition File Corrupt

## Pendahuluan

Race condition terjadi ketika hasil dari suatu eksekusi program bergantung pada urutan atau waktu kejadian-kejadian yang bersamaan. Dalam konteks file, race condition dapat menyebabkan **korupsi file**, di mana data dalam file menjadi tidak konsisten atau rusak karena beberapa proses mencoba mengakses dan memodifikasinya secara bersamaan.

## Contoh Kasus: Korupsi File Akibat Race Condition

Contoh ini menunjukkan bagaimana race condition dapat menyebabkan korupsi file dengan menggunakan beberapa goroutine yang mencoba menulis ke file secara bersamaan tanpa mekanisme penguncian.

### Kode Go untuk Mensimulasikan Korupsi File

```go
package main

import (
	"fmt"
	"os"
	"sync"
)

func tulisKeFile(wg *sync.WaitGroup, id int, file *os.File) {
	defer wg.Done()

	for i := 0; i < 1000; i++ { // Menulis banyak data dengan cepat
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

```

### Output 
* Output yang diharapkan 
![1724957985361](/output/golang_race2_fileCorrupt/image.png)


* Pada file corrupt
![1724958037433](/output/golang_race2_fileCorrupt/outputerr.png)

Setelah melakukan berkali-kali lipat percobaan ditemukan satu file yang rusak. Tanpa mekanisme penguncian, beberapa goroutine dapat menulis ke file pada saat yang bersamaan, menyebabkan korupsi file. Data yang ditulis mungkin tumpang tindih atau rusak, sehingga file output.txt  berisi byte acak yang tidak dapat diprediksi.

## Penanganan Race Condition: Menggunakan Lock
Untuk mencegah race condition dan korupsi file, kita perlu memastikan bahwa hanya satu goroutine yang dapat menulis ke file pada satu waktu. Ini dapat dicapai dengan menggunakan lock, pada golang telah disediakan tools untuk melakukan lock dengan mutex pada putsaka sync.


```go
package main

import (
	"fmt"
	"os"
	"sync"
)

// Mendeklarasikan mutex
var mutex sync.Mutex

func tulisKeFile(wg *sync.WaitGroup, id int, file *os.File) {
	defer wg.Done()

	for i := 0; i < 1000; i++ {
		// Mengunci sebelum menulis ke file
		mutex.Lock()
		_, err := file.WriteString(fmt.Sprintf("Goroutine %d menulis baris ke-%d\n", id, i))
		// Membuka kunci setelah selesai menulis
		mutex.Unlock()

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
```

Locking (Mengunci): Menggunakan lock dapat memastikan hanya satu thread atau goroutine yang bisa mengakses file. seperti contoh dalam bahasa python dengan penggunaan lock.acquire() atau mutex.Lock() di Go untuk 

Unlocking (Membuka Kunci): Menggunakan Unlocking setelah operasi selesai, agar thread lain bisa mengakses file untuk menhgindari race condition pada file corrupt seperti penggunaan lock.release() pada python atau mutex.Unlock() di Go.