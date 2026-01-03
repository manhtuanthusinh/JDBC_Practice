### **Tổng quan về PreparedStatement và Chống SQL Injection**

Trong JDBC, việc sử dụng `Statement` (cộng chuỗi SQL) tiềm ẩn rủi ro bảo mật nghiêm trọng --> dùng `PreparedStatement` để tối ưu hóa và bảo vệ ứng dụng.

#### **1. Vấn đề: SQL Injection là gì?**
*   **Khái niệm:** Là kỹ thuật tấn công khi hacker chèn các đoạn mã SQL độc hại vào các ô nhập liệu (như username, password).
*   **Hậu quả:** Làm thay đổi cấu trúc câu lệnh SQL gốc, giúp hacker có thể đăng nhập mà không cần mật khẩu hoặc đánh cắp toàn bộ dữ liệu (ví dụ dùng chuỗi `' OR 1=1 --`).

#### **2. Giải pháp: PreparedStatement**
*   **Cơ chế:** Câu lệnh SQL được biên dịch trước (pre-compiled) với các dấu chấm hỏi **`?`** làm tham số giữ chỗ.
*   **Ưu điểm:**
    *   **Bảo mật:** Ngăn chặn SQL Injection vì các tham số truyền vào chỉ được coi là dữ liệu, không phải mã thực thi.
    *   **Code sạch:** Không còn việc cộng chuỗi phức tạp với các dấu nháy đơn, nháy kép dễ gây lỗi.
    *   **Hiệu suất:** CSDL không cần biên dịch lại câu lệnh nhiều lần.

---

### **Các đoạn code đáng chú ý**

#### **A. Thực hiện INSERT/UPDATE/DELETE**
Thay vì cộng chuỗi, ta dùng dấu `?` và các hàm `setXXX`:
```java
// 1. Viết SQL với dấu ?
String sql = "INSERT INTO user (username, password, hoTen) VALUES (?, ?, ?)";

// 2. Tạo PreparedStatement
PreparedStatement pst = connection.prepareStatement(sql);

// 3. Gán giá trị (Index bắt đầu từ 1)
pst.setString(1, user.getUsername());
pst.setString(2, user.getPassword());
pst.setString(3, user.getHoTen());

// 4. Thực thi (Không truyền lại chuỗi SQL vào hàm execute)
int result = pst.executeUpdate();



```
#### **B. Thực hiện SELECT (Truy vấn)**
```java
String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
PreparedStatement pst = connection.prepareStatement(sql);

pst.setString(1, inputUser);
pst.setString(2, inputPass);

ResultSet rs = pst.executeQuery();
if (rs.next()) {
    // Xử lý kết quả trả về
}
```
---

### **Quy trình chuẩn khi dùng PreparedStatement**
1.  **Thiết lập câu lệnh SQL** có sử dụng tham số `?`.
2.  **Tạo đối tượng** bằng `connection.prepareStatement(sql)`.
3.  **Gán giá trị cho tham số** qua các phương thức `set` tương ứng (`setString`, `setInt`, `setDouble`...) dựa trên số thứ tự của dấu `?`.
4.  **Thực thi câu lệnh** bằng `executeUpdate()` (cho INSERT/UPDATE/DELETE) hoặc `executeQuery()` (cho SELECT).
5.  **Đóng kết nối**.

** Hãy luôn sử dụng `PreparedStatement` thay cho `Statement` trong mọi tình huống thực tế để đảm bảo an toàn hệ thống.
