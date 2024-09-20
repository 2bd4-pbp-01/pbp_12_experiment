# TYPE SAFETY

Type safety adalah prinsip dalam pemrograman yang memastikan bahwa program hanya akan mengizinkan operasi yang valid berdasarkan tipe data yang digunakan. Dengan type safety, program dapat mencegah kesalahan yang diakibatkan oleh operasi pada tipe data yang tidak cocok.

***Manfaat Type Safety:***

- Deteksi Kesalahan Dini: Kesalahan tipe dapat terdeteksi saat kompilasi, sehingga mengurangi risiko bug yang sulit ditemukan saat runtime.
- Keandalan: Membantu menciptakan kode yang lebih stabil dan dapat diprediksi, mengurangi kemungkinan terjadinya kesalahan saat program dijalankan.
- Dokumentasi Kode: Tipe data yang jelas memberikan informasi lebih kepada pengembang lain tentang cara menggunakan fungsi dan variabel, membuat kode lebih mudah dipahami.
- Performa yang Lebih Baik: Dalam beberapa bahasa, type safety dapat meningkatkan performa karena compiler dapat mengoptimalkan kode lebih baik saat tahu tipe data yang digunakan.

***Tujuan Type Safety:***

- Menghindari kesalahan tipe yang bisa menyebabkan crash atau perilaku tidak terduga.
- Meningkatkan kepercayaan pengembang terhadap keandalan kode yang ditulis.
- Meningkatkan pengalaman pemrograman secara keseluruhan, baik dalam pengembangan maupun pemeliharaan.

***Urgensi Type Safety:***

- Dalam proyek besar dan kompleks, type safety sangat penting untuk mencegah kesalahan yang dapat mengakibatkan kerugian waktu dan biaya.
- Membantu tim pengembang bekerja lebih efisien, terutama ketika banyak orang terlibat dan perlu memahami kode satu sama lain.

## STATIC TYPING

Static typing adalah pendekatan di mana tipe data dari variabel ditentukan saat waktu kompilasi. Ini berarti bahwa tipe data harus dideklarasikan sebelum digunakan.

***Kelebihan:***

- **Deteksi Kesalahan Dini:** Kesalahan tipe data terdeteksi saat kompilasi, sehingga bug lebih mudah ditemukan sebelum program dijalankan.
- **Kinerja yang Lebih Optimal:** Compiler dapat melakukan optimisasi lebih baik karena tipe data sudah diketahui sebelum runtime.
- **Dokumentasi Otomatis:** Tipe data yang eksplisit membuat kode lebih mudah dipahami dan di-maintain oleh tim pengembang lain.
- **Keamanan Tipe yang Lebih Kuat:** Meminimalkan risiko bug tipe data dan error runtime karena semua tipe sudah dicek oleh compiler.

***Kekurangan:***

- **Verbosity (Kode Lebih Panjang):** Harus mendeklarasikan tipe secara eksplisit, membuat kode lebih panjang dan mungkin lebih sulit ditulis.
- **Kurang Fleksibel:** Variabel dengan tipe tetap tidak bisa berubah tipenya, membatasi fleksibilitas kode.


## DYNAMIC TYPING
Dynamic typing adalah pendekatan di mana tipe data dari variabel ditentukan saat runtime. Ini berarti Anda tidak perlu mendeklarasikan tipe data secara eksplisit.

***Kelebihan:***

- **Fleksibilitas Tinggi:** Tipe data variabel dapat berubah selama runtime, memberikan fleksibilitas yang lebih besar dalam pengembangan.
- **Kinerja yang Lebih Optimal:** Compiler dapat melakukan optimisasi lebih baik karena tipe data sudah diketahui sebelum runtime.
- **Pengembangan Lebih Cepat:** Tidak perlu mendeklarasikan tipe secara eksplisit, membuat kode lebih ringkas dan cepat ditulis.
- **Koding yang Simpel dan Mudah:** Kode lebih pendek dan mudah ditulis karena tidak perlu memikirkan tipe data saat awal menulis.

***Kekurangan:***

- **Kesalahan Terjadi di Runtime:** Kesalahan tipe baru ditemukan saat program dijalankan, sehingga ada risiko bug yang sulit dideteksi selama pengembangan.
- **Pemeliharaan yang Sulit:** Tipe data yang tidak jelas bisa membuat kode sulit dipahami, terutama dalam proyek besar atau saat bekerja dengan tim.
- **Kurang Optimal dalam Kinerja:** Interpreter harus menentukan tipe data di runtime, yang bisa memperlambat performa.






