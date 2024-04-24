# Fungsionalitas Inti EpicArcade🎮
## Softaware Architecture - Microservices architecture
![image](https://github.com/ADPRO-B10/epicarcade_functional/assets/112263712/75515bd8-4188-4925-b9ed-bbd5a7d0630d)
- Sistem terbagi menjadi layanan yang lebih kecil dan independen.
- Setiap layanan menangani fungsi tertentu, sehingga lebih mudah untuk diukur dan dipelihara
  
## 1. Pencarian dan Filtrasi Game (🙋, 🧑‍, 🤖)
## 2. Pembelian dan Penjualan Produk Game (🙋, 🧑)
## 3. Riwayat Transaksi (🙋, 🧑‍)
## 4. Pengelolaan Keranjang Belanja (🙋) - Soros Febriano (2206083445)
  ### Design Pattern - Command Pattern
Command Pattern adalah pola desain perilaku yang mengubah request menjadi objek yang berdiri sendiri yang berisi semua informasi tentang permintaan tersebut.
Design pattern ini cocok untuk fitur yang memiliki berbagai tombol dalam berbagai halaman seperti keranjang belanja yang memungkinkan pengguna untuk 
`menambahkan`, `menghapus`, atau `men-checkout` item dari keranjang belanja.

| ![image](https://github.com/ADPRO-B10/epicarcade_functional/assets/112263712/98ad79da-2615-476e-8409-d7abdc7a7016)| 
|:--:| 
| *source: https://medium.com/@suryasai.venkatesh/part-2-a-comparison-of-crud-and-cqrs-patterns-using-shopping-cart-application-1f7484cb2d78* |

  ### Implementasi Command Pattern
  - `Command` : Interface yang mendefinisikan metode `execute` dan `undo`
  - `AddToCartCommand` : Implementasi dari `Command` yang menambahkan item ke keranjang belanja
  - `RemoveFromCartCommand` : Implementasi dari `Command` yang menghapus item dari keranjang belanja
  - `Invoker` : Menyimpan daftar perintah dan memanggil metode `execute` atau `undo`
  - `Receiver` : Menerima perintah dan melakukan aksi yang sesuai

## 5. Rating dan Ulasan Produk (🙋, 🧑‍) 

