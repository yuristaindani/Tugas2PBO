# Tugas2PBO
Program ini merupakan program membuat beckend API untuk membuat aplikasi ecommerce sederhana. Database dalam program ini akan disimpan pada SQLite dan dapat dilakukan pengujiannya pada postman.
API (Application Programming Interface) adalah kumpulan aturan dan protokol yang memungkinkan berbagai perangkat lunak berkomunikasi satu sama lain. Dalam konteks pengembangan perangkat lunak, API sering digunakan untuk mengizinkan aplikasi atau layanan eksternal mengakses dan berinteraksi dengan fungsionalitas atau data dari aplikasi atau layanan yang lain.

Secara umum, setiap endpoint API memiliki metode HTTP yang terkait seperti GET, POST, PUT, DELETE yang menentukan jenis tindakan yang akan dilakukan pada sumber daya yang terkait dengan endpoint tersebut. Begitu juga pada program ini, terdapat metode GET, POST, PUT, DELETE didalamnya. Berikut merupakan endpoint yang dapat digunakan dalam program ini.

USERS
-GET /users : untuk mendapatakan seluruh data users.
-GET /users/[id] : untuk mendapatkan data users berdasarkan id.
-POST /users : untuk membuat user baru.
-PUT /users/[id] : untuk mengupdate data user berdasarkan id yang dicari.
-PUT /users/addresses/[id] : untuk mengupdate data user dan alamat berdasarkan id yang dicari.
-DELETE /users/[id] : untuk menghapus data user berdasarkan id yang dicari.
-DELETE /users/addresses/[id] : untuk menghapus data user dan alamat berdasarkan id yang dicari.

PRODUCTS
-GET /products : untuk mendapatakan seluruh data products.
-GET /products/[id] : untuk mendapatkan data products berdasarkan id.
-GET /products/users/[id] : untuk mendapatkan data products yang dijual oleh user berdasarkan id.
-POST /products : untuk membuat products baru.
-PUT /products/[id] : untuk mengupdate data berdasarkan id yang dicari.
-DELETE /products/[id] : untuk menghapus data berdasarkan id yang dicari.

ORDERS
-GET /orders : untuk mendapatakan seluruh data order.
-GET /orders/[id] : untuk mendapatkan data order berdasarkan id.
-POST /orders : untuk membuat orders baru.
-PUT /orders/[id] : untuk mengupdate data berdasarkan id yang dicari.
-DELETE /orders/[id] : untuk menghapus data berdasarkan id yang dicari.

ORDER DETAIL
-GET /detail/[id] : untuk mendapatkan data order detail berdasarkan id.
-POST /detail : untuk membuat order detail baru.
-PUT /detail/[id] : untuk mengupdate data berdasarkan id yang dicari.
-DELETE /detail/[id] : untuk menghapus data berdasarkan id yang dicari.

REVIEW
-GET /reviews/[id] : untuk mendapatkan data review berdasarkan id.
-POST /reviews : untuk membuat review baru.
-PUT /reviews/[id] : untuk mengupdate data berdasarkan id yang dicari.
-DELETE /reviews/[id] : untuk menghapus data berdasarkan id yang dicari.



