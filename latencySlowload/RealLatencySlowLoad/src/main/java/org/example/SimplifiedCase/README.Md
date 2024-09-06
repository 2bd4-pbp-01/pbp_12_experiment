# Latency dan Slow Load



## Pendahuluan
Masalah latency dan slow load pada situs e-commerce dapat berdampak besar bagi perusahaan, baik dari segi pendapatan maupun kepuasan pelanggan. Misalnya, penelitian menunjukkan bahwa 47% pelanggan mengharapkan situs web e-commerce untuk memuat dalam waktu kurang dari dua detik. Jika memuat lebih dari tiga detik, 57% dari mereka akan meninggalkan situs, dan 80% dari mereka mungkin tidak akan kembali lagi. Dampak ini bisa mengakibatkan penurunan tingkat konversi hingga 7%, yang berarti potensi kerugian pendapatan hingga ribuan dolar per tahun bagi e-commerce dengan volume transaksi besar​

Sebuah contoh kasus nyata terjadi pada Amazon, yang mengalami downtime selama 40 menit pada tahun 2013 dan menyebabkan kerugian sekitar $5 juta. Kasus ini memperlihatkan betapa krusialnya optimasi performa situs dalam lingkungan e-commerce yang sangat kompetitif​(Realm Digital).Selain itu, salah satu penyebab utama latency adalah overload server, yang sering terjadi selama periode promo atau musim liburan ketika lonjakan lalu lintas online terjadi​

## Apa itu Latency dan Slow Load?
<img src="https://img.ifunny.co/images/b335964b204f204ecb19d82eae52520980dabaa99c6c850f11c0d2b3043a4198_1.jpg" alt="image" width="300"/>
Latency adalah waktu tunda yang terjadi antara saat permintaan dikirim oleh pengguna hingga respons diterima dari server. Latency sering diukur dalam milidetik dan dipengaruhi oleh berbagai faktor seperti jarak fisik antara server dan pengguna, kecepatan jaringan, dan beban kerja server. Semakin tinggi latency, semakin lama waktu yang dibutuhkan untuk memuat data.

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm8hMc4e776C24CX9E5-vM5fZAOnz_X3B_NZDevnOrxxPFTAsvQMlBOl_dbLA3daBhCEI&usqp=CAU" alt="image" width="300"/>
Slow load adalah masalah ketika sebuah situs web atau aplikasi membutuhkan waktu yang lama untuk memuat keseluruhan halaman atau elemen-elemen tertentu. Ini biasanya disebabkan oleh server yang overload, file yang terlalu besar, query database yang tidak efisien, atau infrastruktur jaringan yang kurang optimal. Slow load secara langsung mempengaruhi pengalaman pengguna dan dapat menyebabkan mereka meninggalkan situs.

### Metode yang Dapat Menyelesaikan Latency dan Slow Load
Pengurangan Waktu Tunda (Delay) = Mengurangi waktu tunda langsung yang diterapkan selama pemrosesan permintaan. Ini adalah pendekatan langsung untuk mengurangi latency dalam simulasi.
Caching = Caching menyimpan hasil dari permintaan sebelumnya untuk mempercepat akses jika permintaan yang sama datang kembali. Ini mengurangi waktu yang diperlukan untuk memproses permintaan yang sama di masa depan.
Thread Pool Dinamis = Menggunakan thread pool dinamis untuk mengelola thread secara efisien, memungkinkan aplikasi menangani banyak permintaan secara bersamaan dan mengurangi waktu tunggu.
Pemrosesan Asinkron = Memproses permintaan secara asinkron menggunakan CompletableFuture untuk memungkinkan pemrosesan paralel dan menghindari pemblokiran thread utama.


### Mengapa Mengatasi Latency dan Slow Load Penting?
Pengalaman Pengguna: Latency tinggi atau load lambat bisa membuat pengguna frustasi dan meninggalkan transaksi, mengurangi kepuasan pelanggan.
Keandalan Sistem: Transaksi yang cepat dan responsif meningkatkan kepercayaan pengguna terhadap platform.
Skalabilitas: Mengurangi latency dan slow load membantu sistem menangani lebih banyak pengguna dan transaksi secara bersamaan.
Keamanan: Transaksi yang cepat dan efisien mengurangi risiko gagal bayar atau penipuan.
Efisiensi Operasional: Mengurangi waktu proses transaksi meningkatkan efisiensi server dan menurunkan biaya operasional.

# Flow Chart
Flowchart Program Solution
![image](../SimplifiedCase/image/FlowChartSolution.drawio.png)

# Contoh Penyelesaian Latency dan Slow Load dalam Java
