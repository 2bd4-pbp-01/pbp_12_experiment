# Concurrent Programming

## Pendahuluan
Concurrent programming adalah teknik pemrograman yang memungkinkan beberapa bagian dari program untuk dieksekusi secara bersamaan. Ini membantu meningkatkan performa dan responsivitas aplikasi, terutama di sistem dengan banyak inti pemrosesan. Dengan mengoptimalkan penggunaan sumber daya, kita dapat mencapai hasil yang lebih efisien.

## Tujuan
Tujuan utama dari concurrent programming meliputi:
- Meningkatkan throughput aplikasi.
- Mengurangi waktu eksekusi tugas-tugas yang besar.
- Meningkatkan responsivitas aplikasi, terutama dalam aplikasi interaktif.
- Memanfaatkan sumber daya sistem (CPU, memori) secara optimal.

## Konsep Dasar

### 1. Proses dan Thread
- **Proses**: Sebuah proses adalah instance dari program yang dieksekusi. Setiap proses memiliki ruang alamat terpisah, dan proses tidak dapat berbagi data secara langsung.
- **Thread**: Thread adalah unit terkecil dari eksekusi yang dapat berjalan dalam konteks proses. Beberapa thread dalam satu proses dapat berbagi memori dan sumber daya, yang membuatnya lebih ringan dibandingkan proses.

### 2. Asynchronous vs. Synchronous
- **Asynchronous**: Eksekusi yang tidak menunggu satu tugas selesai sebelum melanjutkan ke tugas berikutnya. Contohnya adalah pemanggilan fungsi asynchronous yang mengembalikan `Promise`.
- **Synchronous**: Eksekusi yang menunggu hingga satu tugas selesai sebelum melanjutkan ke tugas berikutnya. Ini sering menyebabkan bottleneck, terutama pada I/O.

### 3. Race Condition
Race condition terjadi ketika dua atau lebih thread atau proses mengakses data yang sama secara bersamaan dan setidaknya satu dari mereka memodifikasi data tersebut. Ini dapat mengakibatkan hasil yang tidak terduga dan sulit untuk diprediksi.

### 4. Synchronization
Synchronization adalah teknik untuk mengatur akses ke sumber daya bersama untuk mencegah race condition. Beberapa mekanisme yang umum digunakan untuk synchronisasi meliputi:
- **Mutex (Mutual Exclusion)**: Mengizinkan hanya satu thread untuk mengakses sumber daya pada satu waktu.
- **Semaphore**: Mengizinkan sejumlah thread untuk mengakses sumber daya bersamaan, dengan batas maksimum.
- **Condition Variables**: Memungkinkan thread untuk menunggu hingga kondisi tertentu terjadi sebelum melanjutkan eksekusi.

### 5. Deadlock
Deadlock adalah kondisi di mana dua atau lebih thread saling menunggu satu sama lain untuk melepaskan sumber daya, sehingga tidak ada yang dapat melanjutkan. Penting untuk merancang aplikasi untuk menghindari situasi ini.

## Implementasi
Berbagai bahasa pemrograman menyediakan dukungan untuk concurrent programming dengan cara yang berbeda. Berikut adalah beberapa contoh:

### Contoh dalam Go
Go memiliki dukungan built-in untuk concurrent programming melalui goroutines dan channel.

```go
package main

import (
    "fmt"
    "sync"
)

func main() {
    var wg sync.WaitGroup
    for i := 1; i <= 5; i++ {
        wg.Add(1)
        go func(i int) {
            defer wg.Done()
            fmt.Println("Goroutine", i)
        }(i)
    }
    wg.Wait()
}
```

## kesimpulan
Concurrent programming adalah teknik penting dalam pengembangan perangkat lunak modern. Dengan memanfaatkan eksekusi paralel, pengembang dapat membangun aplikasi yang lebih efisien dan responsif. Memahami konsep dasar dan mekanisme yang terlibat dalam concurrent programming akan membantu dalam merancang sistem yang lebih baik dan menghindari masalah seperti race condition dan deadlock.