# Tạo cặp khóa RSA bằng OpenSSL
# Bước 1: Tạo private key (chuẩn PKCS#8 để Java đọc được)
openssl genpkey -algorithm RSA -out pkcs8.key -pkeyopt rsa_keygen_bits:2048

# Bước 2: Xuất public key từ private key
openssl rsa -pubout -in pkcs8.key -out publickey.crt