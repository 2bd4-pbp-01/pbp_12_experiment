# Race Condition: Penjelasan, Implementasi, dan Penanganan

## Daftar Isi
- [Pendahuluan](#pendahuluan)
- [Apa itu Race Condition?](#apa-itu-race-condition)
  - [Contoh Situasi Race Condition](#contoh-situasi-race-condition)
  - [Mengapa Race Condition Berbahaya?](#mengapa-race-condition-berbahaya)
- [Contoh Implementasi Race Condition dalam Go](#contoh-implementasi-race-condition-dalam-go)

- [Navigasi ke Dokumen Detail Experiment](#navigasi-ke-dokumen-detail-experiment)

## Pendahuluan

Race condition adalah masalah umum dalam pemrograman concurrent yang terjadi ketika hasil akhir dari program bergantung pada urutan atau timing dari eksekusi thread. Kondisi ini dapat menyebabkan perilaku tak terduga, yang sering kali sulit untuk di-debug.

## Apa itu Race Condition?
![image](https://uploads.sitepoint.com/wp-content/uploads/2017/02/1486567898race-condition.jpg)
Race condition terjadi ketika dua atau lebih thread atau proses secara bersamaan mengakses dan memodifikasi shared resource tanpa adanya mekanisme sinkronisasi yang memadai. Hal ini menyebabkan urutan eksekusi yang tidak terprediksi, yang dapat menyebabkan bug atau hasil yang tidak diinginkan.

### Contoh Situasi Race Condition
1. **Double Spending:** Dalam aplikasi e-commerce, beberapa pengguna bisa membeli produk yang sama secara bersamaan, menyebabkan stok produk menjadi negatif.
2. **File Corruption:** Beberapa thread mencoba menulis ke file yang sama tanpa penguncian, menyebabkan file menjadi korup.
3. **Inconsistent Data:** Dua atau lebih thread membaca dan menulis ke variabel yang sama secara bersamaan, menghasilkan data yang tidak konsisten.

### Mengapa Race Condition Berbahaya?
- **Kesulitan Debugging:** Race condition sering kali sulit untuk direproduksi karena mereka bergantung pada urutan eksekusi yang tepat dari thread atau goroutine.
- **Hasil Tak Terduga:** Dapat menyebabkan data yang rusak, hasil yang tidak konsisten, atau bahkan crash aplikasi.
- **Masalah Keamanan:** Dalam beberapa kasus, race condition dapat dieksploitasi untuk tujuan jahat, seperti dalam kasus double spending pada transaksi keuangan.

# Contoh Implementasi Race Condition dalam Go

## Navigasi ke Dokumen Detail Experiment

<div style="display: flex; justify-content: space-between; gap: 20px; flex-wrap: nowrap;">

  <div style="background-color: #f2f2f2; border: 1px solid #ddd; border-radius: 8px; padding: 20px; width: 30%; text-align: center; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); transition: background-color 0.3s, transform 0.3s; flex-grow: 1;">
    <a href="./Double Spending/readme.md" style="text-decoration: none; color: #333; font-size: 18px; font-weight: bold;">Double Spending</a>
    <p>Experiment 1 Double Spending dalam race condition</p>
  </div>

  <div style="background-color: #f2f2f2; border: 1px solid #ddd; border-radius: 8px; padding: 20px; width: 30%; text-align: center; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); transition: background-color 0.3s, transform 0.3s; flex-grow: 1;">
    <a href="./File Corruption/readme.md" style="text-decoration: none; color: #333; font-size: 18px; font-weight: bold;">File Corruption</a>
    <p>Experiment 2 File Corruption dalam race condition</p>
  </div>

  <div style="background-color: #f2f2f2; border: 1px solid #ddd; border-radius: 8px; padding: 20px; width: 30%; text-align: center; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); transition: background-color 0.3s, transform 0.3s; flex-grow: 1;">
    <a href="./Inconsistent Data/readme.md" style="text-decoration: none; color: #333; font-size: 18px; font-weight: bold;">Inconsistent Data</a>
    <p>Experiment 3 Inconsistent Data dalam race condition</p>
  </div>

</div>









