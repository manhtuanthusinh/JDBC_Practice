### **Tổng quan về Truy vấn dữ liệu với JDBC (Câu lệnh SELECT)**

Bài học tập trung vào việc lấy dữ liệu từ cơ sở dữ liệu (CSDL) lên ứng dụng Java, ngược lại với các thao tác thêm/xóa/sửa trước đó.

#### **1. Quy trình 5 bước thực hiện**
Để truy vấn dữ liệu, chúng ta vẫn tuân thủ 5 bước cơ bản nhưng có sự thay đổi ở bước 3 và 4:
1.  **Tạo kết nối** đến CSDL.
2.  **Tạo đối tượng `Statement`**.
3.  **Thực thi câu lệnh SQL:** Sử dụng phương thức **`executeQuery(sql)`** (thay vì `executeUpdate`).
4.  **Xử lý kết quả:** Sử dụng đối tượng **`ResultSet`** để đọc dữ liệu.
5.  **Ngắt kết nối**.

#### **2. Đối tượng quan trọng: `ResultSet`**
*   **Khái niệm:** `ResultSet` giống như một bảng dữ liệu tạm thời chứa kết quả truy vấn.
*   **Cơ chế:** Sử dụng con trỏ để duyệt từng dòng dữ liệu qua phương thức **`rs.next()`**.
*   **Lấy dữ liệu:** Tùy vào kiểu dữ liệu của cột để chọn phương thức `get` tương ứng (nên dùng tên cột thay vì thứ tự cột để đảm bảo chính xác):
    *   `getString("tên_cột")`: Lấy dữ liệu kiểu chuỗi.
    *   `getInt("tên_cột")`: Lấy dữ liệu kiểu số nguyên.
    *   `getFloat("tên_cột")` / `getDouble("tên_cột")`: Lấy dữ liệu số thực.
    *   `getDate("tên_cột")`: Lấy dữ liệu kiểu ngày tháng (`java.sql.Date`).

---

### **Các đoạn code đáng chú ý**

#### **A. Thực thi truy vấn và duyệt kết quả (Dùng cho `selectAll`)**
```java
// Bước 1: tạo kết nối tới CSDl 
Connection con = JdbcUtil.getConnection();

// tạo đối tượng statement 
Statement st = con.createStatement();

// Bước 3: Thực thi câu lệnh
String sql = "SELECT * FROM ten_bang";
ResultSet rs = st.executeQuery(sql);

// Bước 4: Duyệt qua ResultSet và chuyển thành đối tượng Java
while (rs.next()) {
    String id = rs.getString("id");
    String ten = rs.getString("ten_cot_chuoi");
    double gia = rs.getDouble("ten_cot_so");
    
    // Tạo đối tượng và thêm vào danh sách
    Object obj = new Object(id, ten, gia);
    list.add(obj);
}
```

#### **B. Truy vấn theo điều kiện (Dùng cho `selectById`)**
```java
String sql = "SELECT * FROM ten_bang WHERE id = '" + id_truyen_vao + "'";
ResultSet rs = st.executeQuery(sql);

if (rs.next()) { // Chỉ lấy 1 kết quả duy nhất
    // Đọc dữ liệu và gán vào đối tượng
}
```

#### **C. Lấy dữ liệu kiểu Ngày tháng**
```java
// Sử dụng java.sql.Date để tương thích với SQL
Date ngaySinh = rs.getDate("ngay_sinh");
```
---

### **Lưu ý quan trọng**
*   **`executeQuery` vs `executeUpdate`:** Chỉ dùng `executeQuery` cho các lệnh không làm thay đổi dữ liệu (SELECT).
*   **Tính đóng gói:** Kết quả truy vấn thường được đưa vào một `ArrayList` để dễ dàng quản lý và hiển thị lên giao diện (Web, Desktop).
*   **Kiến trúc DAO:** Nên gom các logic truy vấn vào các lớp DAO (như `SachDAO`, `KhachHangDAO`) để mã nguồn sạch sẽ và dễ bảo trì.

**Ví dụ tương tự:** Hãy tưởng tượng `ResultSet` như một cuốn danh bạ, và `rs.next()` là hành động bạn lật từng trang. Ở mỗi trang (dòng), bạn sẽ ghi chép lại thông tin mình cần vào một tờ giấy (đối tượng Java) rồi cất vào một tập hồ sơ (ArrayList).