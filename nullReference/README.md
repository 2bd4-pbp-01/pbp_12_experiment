# Null Reference

## Pengertian

Null reference adalah konsep dalam pemrograman dimana sebuah variabel referensi tidak menunjuk ke objek apapun, atau dengan kata lain, ia memiliki nilai null. Masalah dengan null reference menjadi terkenal dan disebut sebagai "The Billion Dollar Mistake" karena potensi kerusakan yang bisa ditimbulkannya pada software.

Istilah ini dipopulerkan oleh Sir Tony Hoare, seorang ilmuwan komputer yang menciptakan konsep null reference pada tahun 1965 saat mengembangkan bahasa pemrograman ALGOL W. Ia memperkenalkan null reference sebagai cara mudah untuk menunjukkan bahwa sebuah referensi tidak menunjuk ke objek apapun. Pada awalnya, itu tampak seperti solusi sederhana, tetapi seiring waktu, masalah yang ditimbulkannya menjadi sangat signifikan.

> [!TIP] Info
> Istilah "The Billion Dollar Mistake" merujuk pada perkiraan bahwa kesalahan ini telah menyebabkan kerugian yang sangat besar, baik dari segi biaya pengembangan, pemeliharaan, perbaikan bug, hingga kegagalan sistem. Jika ditotal dari seluruh perangkat lunak di dunia yang terkena dampak, nilainya bisa mencapai miliaran dolar.

## Skenario

Studi kasus yang dilakukan bertema sistem perbankan online dengan skenario
sebuah akun mencoba transfer dana ke rekening yang sudah nonaktif atau
tidak lagi tersedia. Sistem mungkin mengembalikan `null` saat mencari
data dari rekening.

Pada skenario, terdapat fungsi `findAccountByNumber` untuk mencari akun bank,
apabila terdapat akun bank yang tidak aktif atau tidak terdaftar maka akan
mengembalikan nilai `null`. Terdapat fungsi lain bernama `transferFunds` yang
memanggil fungsi `findAccountByNumber` , dimana perlu dilakukan penanganan
nilai `null` karena nilai yang dikembalikan `transferFunds` sangat bergantung
pada nilai dari fungsi `findAccountByNumber`.

Hasil yang diharapkan dari skenario diatas yaitu menangani fungsi yang
dapat memicu `null` sehingga ketika fungsi tersebut mengembalikan nilai
`null` maka transfer yang dilakukan akan gagal dan user atau pengguna
dapat menerima pesan yang informatif.

## Solusi

Melakukan pengecekan nilai pada setiap variabel atau fungsi yang memiliki
kemungkinan bernilai `null`. Membuat penanganan untuk nilai `null` misalnya
mengembalikan pesan khusus atau kode error.

## Kesimpulan

Null reference adalah salah satu masalah yang paling umum dan berpotensi merusak dalam pengembangan software/aplikasi. Masalah ini terjadi ketika kode mencoba mengakses atau memodifikasi data dari referensi yang ternyata tidak ada (null). Ketika hal ini terjadi, aplikasi bisa mengalami crash, menyebabkan gangguan dalam operasional, atau bahkan kehilangan data penting.

### Tips

Ada beberapa tips yang dapat diterapkan untuk menangani null reference, diantaranya:

1. Selalu lakukan pengecekan null

   Sebelum mengakses variabel, properti atau fungsi, pastikan untuk melakukan
   pengecekan apakah nilainya `null`. Contoh pengecekan seperti pada kode dibawah:

   ```ts
   if (myData !== null) {
     // akses variabel disini
   }
   ```

2. Early return untuk validasi

   Ketika melakukan validasi input atau pengecekan kondisi, gunakan early
   return untuk menangani kasus null sebelum melanjutkan eksekusi logika
   yang lebih kompleks. Contohnya:

   ```ts
   function myFn(account) {
     if (account == null) {
       return "account not found";
     }
     // bagian kode ketika terdapat nilai pada parameter `account`
   }
   ```

3. Unit testing

   Buat unit test yang secara eksplisit menguji skenario dimana referensi
   mungkin memiliki nilai `null`.
