# Multi-Timezone Problem

## Overview Masalah

Timezone atau zona waktu seringkali menjadi hal yang terlupakan
ketika mengembangkan aplikasi/software. Terutama pada aplikasi/
software yang memiliki pengguna dari berbagai daerah dengan zona
waktu berbeda. Hal ini mengakibatkan masalah selisih waktu atau
bahkan bug yang lebih parah serta dapat merugikan pengguna.

Dalam eksperimen ini, dilakukan cara penanganan waktu pada timezone
yang berbeda sehingga representasi waktu yang digunakan dapat konsisten.
Studi kasus dimulai dari hal yang sederhana yaitu dengan menentukan
tipe dan struktur data kemudian, diikuti dengan pembuatan function/method
untuk penyelarasan atau sinkronisasi waktu.

## Skenario

**Latar Belakang**: Sebuah maskapai penerbangan internasional membutuhkan sistem pemesanan tiket yang dapat diakses oleh pengguna dari berbagai zona waktu di seluruh dunia. Sistem ini harus mampu menangani waktu keberangkatan, waktu kedatangan, serta waktu pemesanan tiket dengan akurasi tinggi, terlepas dari lokasi pengguna. Selain itu, sistem harus mendukung berbagai zona waktu dan menghitung durasi penerbangan secara akurat.

**Hasil yang Diharapkan**: mendapatkan informasi tentang pemesanan tiket dalam format yang sesuai dengan zona waktu pengguna, memastikan akurasi dan menghindari kebingungan waktu.

## Solusi

Menggunakan Epoch timestamp sebagai format waktu yang digunakan. Yaitu sebuah sistem untuk melacak waktu sebagai jumlah detik yang telah berlalu sejak sebuah titik awal yang disebut Unix epoch. Unix epoch dimulai pada 1 Januari 1970, pukul 00:00:00 UTC.

Dengan menerapkan Epoch timestamp, representasi waktu dapat konsisten karena seluruh waktu akan dikonversi ke waktu UTC. Selain itu, melakukan konversi waktu dari epoch timestamp ke zona waktu lokal tergolong cukup mudah karena epoch timestamp didukung oleh banyak bahasa pemrograman.

## Kesimpulan

Penggunaan epoch time dalam penanganan timezone pada aplikasi yang diakses secara global memastikan bahwa waktu ditangani dengan benar, konsisten, dan akurat. Ini adalah pendekatan terbaik untuk menghindari kebingungan yang sering terjadi akibat perbedaan timezone dan untuk memastikan bahwa aplikasi memberikan pengalaman pengguna yang optimal di seluruh dunia.
